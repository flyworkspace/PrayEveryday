package org.cathassist.daily.util;

import java.io.FileOutputStream;
import java.io.IOException;

public class CreateHtmlFile {
	private static final String mHtmlHead = "<!DOCTYPE html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>无标题文档</title></head><body><p style=\"word-break:break-all\">";
	private static final String mHtmlEnd = "</p></body></html>";

	public static void convert(String path, String content) {
		try {
			String result = mHtmlHead + content + mHtmlEnd;
			saveStringToFile(path, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean saveStringToFile(String path, String content) {
		// FileWriter fw = new FileWriter(path);
		// MTDebug.startCount();
		// ByteBuffer dst = ByteBuffer.allocate(content.length() * 4);

		try {
			FileOutputStream fos = new FileOutputStream(path);
			// 把长宽写入头部
			fos.write(content.getBytes());
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
