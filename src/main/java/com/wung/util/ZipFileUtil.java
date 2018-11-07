package com.wung.util;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;

/**
 * Zip文件工具类
 * 
 */
public class ZipFileUtil {

	/**
	 * 把文件压缩成zip格式
	 * 
	 * @param files
	 *            需要压缩的文件
	 * @param zipFilePath
	 *            压缩后的zip文件路径 ,如"D:/test/aa.zip";
	 */
	public static void compressFiles2Zip(File[] files, String[] entryNames, String zipFilePath) {
		if (files == null || files.length == 0) 
			return;
				
		if (!isEndsWithZip(zipFilePath)) 
			return;
		
		//如果压缩文件名为空，则默认取文件名
		if (entryNames == null) {
			entryNames = new String[files.length];
			for (int i = 0; i < files.length; i++) {
				entryNames[i] = files[i].getName();
			}
		}
		
		ZipArchiveOutputStream zaos = null;
		try {
			File zipFile = new File(zipFilePath);
			zaos = new ZipArchiveOutputStream(zipFile);
			// Use Zip64 extensions for all entries where they are
			// required
			zaos.setUseZip64(Zip64Mode.AsNeeded);

			// 将每个文件用ZipArchiveEntry封装
			// 再用ZipArchiveOutputStream写到压缩文件中
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file != null) {
					ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, entryNames[i]);
					zaos.putArchiveEntry(zipArchiveEntry);
					InputStream is = null;
					try {
						is = new FileInputStream(file);
						byte[] buffer = new byte[1024 * 5];
						int len = -1;
						while ((len = is.read(buffer)) != -1) {
							// 把缓冲区的字节写入到ZipArchiveEntry
							zaos.write(buffer, 0, len);
						}
						// Writes all necessary data for this entry.
						zaos.closeArchiveEntry();
					} catch (Exception e) {
						throw new RuntimeException(e);
					} finally {
						if (is != null)
							is.close();
					}

				}
			}
			zaos.finish();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (zaos != null) {
					zaos.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 把zip文件解压到指定的文件夹
	 * 
	 * @param zipFilePath
	 *            zip文件路径, 如 "D:/test/aa.zip"
	 * @param saveFileDir
	 *            解压后的文件存放路径, 如"D:/test/"
	 */
	public static void decompressZip(String zipFilePath, String saveFileDir) {
		if (isEndsWithZip(zipFilePath)) {
			File file = new File(zipFilePath);
			if (file.exists()) {
				InputStream is = null;
				// can read Zip archives
				ZipArchiveInputStream zais = null;
				try {
					is = new FileInputStream(file);
					zais = new ZipArchiveInputStream(is);
					ArchiveEntry archiveEntry = null;
					// 把zip包中的每个文件读取出来
					// 然后把文件写到指定的文件夹
					while ((archiveEntry = zais.getNextEntry()) != null) {
						// 获取文件名
						String entryFileName = archiveEntry.getName();
						// 构造解压出来的文件存放路径
						String entryFilePath = saveFileDir + entryFileName;
						byte[] content = new byte[(int) archiveEntry.getSize()];
						zais.read(content);
						OutputStream os = null;
						try {
							// 把解压出来的文件写到指定路径
							File entryFile = new File(entryFilePath);
							os = new FileOutputStream(entryFile);
							os.write(content);
						} catch (IOException e) {
							throw new IOException(e);
						} finally {
							if (os != null) {
								os.flush();
								os.close();
							}
						}

					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					try {
						if (zais != null) {
							zais.close();
						}
						if (is != null) {
							is.close();
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
	}

	/**
	 * 判断文件名是否以.zip为后缀
	 * 
	 * @param fileName
	 *            需要判断的文件名
	 * @return 是zip文件返回true,否则返回false
	 */
	public static boolean isEndsWithZip(String fileName) {
		boolean flag = false;
		if (fileName != null && !"".equals(fileName.trim())) {
			if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
				flag = true;
			}
		}
		return flag;
	}

}