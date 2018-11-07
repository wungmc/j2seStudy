package com.wung.util;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * used to access web url
 *
 */

public class WebAccess
{
    static Hashtable<Object, String> m_statusTable = new Hashtable<Object, String>();
    static final String FIELDSEPERATOR_TEXT = "|SEP|";
    static final String FIELDSEPERATOR_REGEX = "\\|SEP\\|";
    static final String TAG_SUCCESS = "success";
    static final String TAG_FAILED = "failed";
    static final String TAG_AVGSPEED = "avgspeed";
    static final String TAG_BURSTSPEED = "burstspeed";
    static final String TAG_RATE = "avgrate";
    static final String TAG_BURSTRATE = "burstrate";
    static final String TAG_DROPCOUNTER = "dropcounter";
    static final int DAEMONINTERVAL_MILLI = 60*1000;
    
    static long s_startMillis = System.currentTimeMillis();
    static long s_lastSysMillis = s_startMillis;
    static long s_nextDaemonRunningTime = s_startMillis+60*1000;
    
    static int s_successRateDropCounter = 0;

    
    public String getUrlContent( String url, int connectTimeout, int readTimeout, String encoding )
    {
        String result= null;
        try
        {
            Object[] arr = getPageContent( url, connectTimeout, readTimeout );
            if ( arr != null )
            {
                ByteArrayOutputStream bos = (ByteArrayOutputStream)arr[1];
                if  (bos == null )
                {
                    result = null;
                }
                else
                {   
                    if ( encoding != null )
                    {
                        result =  new String( bos.toByteArray(), encoding );
                    }
                    else
                    {
                        result = new String( bos.toByteArray() );
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
    public Object[] getUrlResponse( String url, String encoding )
    {
        return getUrlResponse( url, encoding, 300, 300 );
    }
    
    public Object[] getUrlResponse( String url, String encoding, int connectTimeout, int readTimeout )
    {
        Object[] ret = null;
        String result= null;
        Object[] arr = null;
        try
        {
            arr = getPageContent( url, connectTimeout, readTimeout );

            if ( arr != null )
            {
                ByteArrayOutputStream bos = (ByteArrayOutputStream)arr[1];
                if  (bos == null )
                {
                    result = null;
                }
                else
                {   
                    if ( encoding != null )
                    {
                        result =  new String( bos.toByteArray(), encoding );
                    }
                    else
                    {
                        result = new String( bos.toByteArray() );
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        if ( arr != null )
        {
            ret = new Object[2];
            ret[0] = arr[0];
            ret[1] = result;
        }      
        return ret;
    }
    
    public String getUrlContent( String url, String encoding )
    {
        return getUrlContent( url, 300, 300, encoding );
    }
            
    private Object[] getPageContent( String url, int connectTimeout, int readTimeout )
    {
        String staticTag = url.split( "\\?" )[0];
        String successCountStr = (String)m_statusTable.get( staticTag+FIELDSEPERATOR_TEXT+TAG_SUCCESS );
        
        int successCountInt = 0;
        if ( successCountStr != null )
        {
            successCountInt = Integer.parseInt( successCountStr );
        }
        String failedCountStr = (String)m_statusTable.get( staticTag+FIELDSEPERATOR_TEXT+TAG_FAILED );
        int failedCountInt = 0;
        if ( failedCountStr != null )
        {
            failedCountInt = Integer.parseInt( failedCountStr );
        }
        
        ByteArrayOutputStream bos = null;
        int code = -1;
        InputStream in = null;
        HttpURLConnection uc = null;
        ByteArrayOutputStream _bos = new ByteArrayOutputStream();
        try {
            
            URL u = new URL(url);
            uc = (HttpURLConnection) u.openConnection();
            uc.setConnectTimeout( connectTimeout );
            uc.setReadTimeout(readTimeout);

            in = uc.getInputStream();
            code = uc.getResponseCode();
            byte[] data = new byte[1024];           
            int read;
            while ( -1 != (read = in.read(data, 0, data.length)))
            {
                _bos.write( data, 0, read );
            }
            bos = _bos;
            successCountInt++;
            updateStatics( successCountInt, failedCountInt );

            m_statusTable.put( staticTag+FIELDSEPERATOR_TEXT+TAG_SUCCESS, ""+successCountInt );
            
        }
        catch (Exception e)
        {
            try
            {
                //cat.error( "[getPageContent][exception]["+url+"]["+(System.currentTimeMillis()-now)+"]", e );
                in = uc.getErrorStream();
                code = uc.getResponseCode();
                byte[] data = new byte[1024];           
                int read;
                while ( -1 != (read = in.read(data, 0, data.length)))
                {
                    _bos.write( data, 0, read );
                }
                bos = _bos;
            }
            catch( Exception ex )
            {
                ex.printStackTrace();
            }
            failedCountInt++;
            updateStatics( successCountInt, failedCountInt );
            m_statusTable.put( staticTag+FIELDSEPERATOR_TEXT+TAG_FAILED, ""+failedCountInt );
            
        }
        finally
        {
            if ( in != null )
            {
                try{in.close();}catch(Exception e){e.printStackTrace();}
            }
        }
        Object[] resultArray = { new Integer( code ), bos };
        return resultArray;
    }
    
    public static Hashtable<?, String> getStatisticInfo()
    {
        return m_statusTable;
    }
    
    private synchronized void updateStatics( int newSuccessInt, int newFailedInt )
    {
        Hashtable<Object, String> tempTable = new Hashtable<Object, String>();
        
        if ( System.currentTimeMillis() < s_nextDaemonRunningTime )
        {
            return;
        }
        s_nextDaemonRunningTime+=DAEMONINTERVAL_MILLI;
        
        Set<?> set = m_statusTable.entrySet();
    
        Iterator<?> it = set.iterator();
        while( it.hasNext() )
        {
            Entry<?, ?> entry = (Entry<?, ?>)it.next();
            String key = (String)entry.getKey();
            String value = (String)entry.getValue();
            String[] fields = key.split( FIELDSEPERATOR_REGEX );
            if ( fields.length == 2)
            {
                String item = fields[0];
                String status = fields[1];
                if ( status.equals( TAG_FAILED ))
                {
                    String successText = "";
                    String failedText = value;
                    String avgsuccessSpeedText = "";
                    String avgfailedSpeedText = "";
                    String avgsuccessRateText = "";
                    String avgfailedRateText = "";
                    
                    String burstsuccessSpeedText = "";
                    String burstfailedSpeedText = "";

                    successText = (String)m_statusTable.get( item+FIELDSEPERATOR_TEXT+TAG_SUCCESS );
                    avgsuccessSpeedText = (String)m_statusTable.get( item+FIELDSEPERATOR_TEXT+TAG_SUCCESS+TAG_AVGSPEED );
                    avgfailedSpeedText = (String)m_statusTable.get( item+FIELDSEPERATOR_TEXT+TAG_FAILED+TAG_AVGSPEED );
                    avgsuccessRateText = (String)m_statusTable.get( item+FIELDSEPERATOR_TEXT+TAG_SUCCESS+TAG_RATE );
                    avgfailedRateText = (String)m_statusTable.get( item+FIELDSEPERATOR_TEXT+TAG_FAILED+TAG_RATE );
                    
                    
                    burstsuccessSpeedText = (String)m_statusTable.get( item+FIELDSEPERATOR_TEXT+TAG_SUCCESS+TAG_BURSTSPEED );
                    burstfailedSpeedText = (String)m_statusTable.get( item+FIELDSEPERATOR_TEXT+TAG_FAILED+TAG_BURSTSPEED );
               
                    int oldSuccessInt = parseInt( successText );
                    int oldFailedInt = parseInt( failedText );
                    parseDouble( avgsuccessSpeedText );
                    parseDouble( avgfailedSpeedText );
                    double oldSuccessAvgRate = parseDouble( avgsuccessRateText );
                    parseDouble( avgfailedRateText );
                    
                    parseDouble( burstsuccessSpeedText );
                    parseDouble( burstfailedSpeedText );

                    
                    double newSuccessAvgRate = 0;
                    double newFailedAvgRate = 0;
                    double newFailedAvgSpeed = 0;
                    double newSuccessAvgSpeed = 0;

                    double newFailedBurstSpeed = 0;
                    double newSuccessBurstSpeed = 0;
                    
                    long nowTimeMillis = System.currentTimeMillis();
                    int total = newSuccessInt+newFailedInt;
                    if ( total != 0 )
                    {
                        newFailedAvgRate = newFailedInt / (double)total;
                        newSuccessAvgRate = newSuccessInt / (double)total;
                        newFailedAvgSpeed =  newFailedInt / (double)(( nowTimeMillis - s_startMillis )/1000.0);
                        newSuccessAvgSpeed =  newSuccessInt / (double)(( nowTimeMillis - s_startMillis )/1000.0);
                        
                        newSuccessBurstSpeed = ( newSuccessInt - oldSuccessInt ) / (double)(( nowTimeMillis - s_lastSysMillis )/1000.0);
                        newFailedBurstSpeed = ( newFailedInt - oldFailedInt ) / (double)(( nowTimeMillis - s_lastSysMillis )/1000.0);
                    }
                    if ( newSuccessAvgRate < oldSuccessAvgRate )
                    {
                        String successRateDropCounterStr = (String)m_statusTable.get( item+FIELDSEPERATOR_TEXT+TAG_DROPCOUNTER );
                        int successRateDropCounterInt = parseInt( successRateDropCounterStr );

                        successRateDropCounterInt++;
                        if ( successRateDropCounterInt == 5 )
                        {
                            notifySuccessRateDrop( item, newSuccessAvgRate );
                            successRateDropCounterInt = 0;
                            tempTable.put( item+FIELDSEPERATOR_TEXT+TAG_DROPCOUNTER, ""+successRateDropCounterInt );
                        }
                    }
                    tempTable.put( item+FIELDSEPERATOR_TEXT+TAG_SUCCESS+TAG_AVGSPEED, Double.toString( newSuccessAvgSpeed ) );
                    tempTable.put( item+FIELDSEPERATOR_TEXT+TAG_FAILED+TAG_AVGSPEED, Double.toString( newFailedAvgSpeed ) );
                    tempTable.put( item+FIELDSEPERATOR_TEXT+TAG_SUCCESS+TAG_RATE, Double.toString( newSuccessAvgRate ) );
                    tempTable.put( item+FIELDSEPERATOR_TEXT+TAG_FAILED+TAG_RATE, Double.toString( newFailedAvgRate ) );
                    
                    tempTable.put( item+FIELDSEPERATOR_TEXT+TAG_SUCCESS+TAG_BURSTSPEED, Double.toString( newSuccessBurstSpeed ) );
                    tempTable.put( item+FIELDSEPERATOR_TEXT+TAG_FAILED+TAG_BURSTSPEED, Double.toString( newFailedBurstSpeed ) );
                    
                    s_lastSysMillis = nowTimeMillis;
                }
            }
        }
        m_statusTable.putAll( tempTable );
    }
    
    private void notifySuccessRateDrop( String item, double rate )
    {
        //SubFactory.getInstance().sendTextSms("13810337807", "13810337807", "001122", item+"_success_rate_drop_"+rate );
    }
    
    private int parseInt( String number )
    {
        if ( number == null )
        {
            return 0;
        }
        else
        {
            return Integer.parseInt( number );
        }
    }
    
    private double parseDouble( String number )
    {
        if ( number == null )
        {
            return 0;
        }
        else
        {
            return Double.parseDouble( number );
        }
    }
}
