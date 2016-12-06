package com.wung.i18n;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * Created by wung on 2016/7/7.
 */
public class LocaleTest {
    public static void main(String[] args) {
//        Locale locale1 = new Locale("en", "US");
//        Locale.setDefault(locale1);
        Locale locale = Locale.TRADITIONAL_CHINESE;

        Locale lo = new Locale("en", "US", "guigu");
//        Locale locale = Locale.getDefault();
        System.out.println(locale.getLanguage()); // zh
        System.out.println(locale.getDisplayLanguage()); // 中文
        System.out.println(locale.getCountry()); // TW
        System.out.println(locale.getDisplayCountry()); // 台湾地区

//        Locale[] list = Locale.getAvailableLocales();
//        for (Locale locale1 : list) {
//            System.out.println(locale1.getDisplayCountry() + "=" + locale1.getCountry() + ", "
//                + locale1.getDisplayLanguage() + "=" + locale1.getLanguage() + ", " + locale1.getVariant());
//        }

        Locale[] thaiLocale = {new Locale("th"), new Locale("th","TH"),
                new Locale("th","TH", "TH")};

        for(Locale loca: thaiLocale) {
            NumberFormat nf = NumberFormat.getNumberInstance(loca);
            StringBuffer msgBuff = new StringBuffer();
            msgBuff.append(loca.toString() + ": ");
            msgBuff.append(nf.format(573.34));
            System.out.println(msgBuff.toString());
        }
        // output:
        //th: 573.34
        //th_TH: 573.34
        //th_TH_TH: ๕๗๓.๓๔

//        Locale myLocale = Locale.getDefault();
        Locale myLocale = new Locale("en", "US");
        ResourceBundle rb = ResourceBundle.getBundle("com.wung.i18n.Resource", myLocale);
//        ResourceBundle rb = ListResourceBundle.getBundle("com.wung.i18n.Resource", myLocale);
        System.out.println(rb.getString("hello"));
        System.out.println(MessageFormat.format(rb.getString("msg"), "Jack", new Date()));


    }
}
