package com.yt.net;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import com.yt.util.Util;

public abstract class HttpHelper {
	public static final String SERVER_ADDR = "http://192.168.1.189:8080/";
	public static final String APP_NAME = "YTServer";
	
	public static String getURL(String path){ return SERVER_ADDR+APP_NAME+"/"+path; }
			
	public static String BuildRequestString(Map<String,String> map){
		if (map==null) return "";
		boolean start = true;
		Set<String> keys = map.keySet();
		StringBuilder sb=new StringBuilder();
		for (String key:keys){
			if (start) start = false; else sb.append('&');
			sb.append(key);
			sb.append('=');
			sb.append(map.get(key));
		}
		return sb.toString();
	}
	
	public static String readResultString(InputStream is){
		try {
			String curLn="";
			String ret = "";
			curLn = "";
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			while ((curLn = reader.readLine()) != null) {
				ret += curLn;
			}
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String doPost(String path, Map<String,String> params){
		try {
			URLConnection connection=new URL(getURL(path)).openConnection();
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			OutputStreamWriter outStream = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			outStream.write(BuildRequestString(params));
			outStream.flush();
			outStream.close();
			return readResultString(connection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String doPostDataSend(String path, byte[] data,int x){
		try {
			URLConnection connection=new URL(getURL(path)).openConnection();
			connection.setRequestProperty("Content-Type","image/png;charset=UTF-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			OutputStream outStream = connection.getOutputStream();
			outStream.write(Util.int2bytes(x));
			outStream.write(data);
			outStream.flush();  
			outStream.close();
			return readResultString(connection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] doPostDataRecv(String path,Map<String,String> params){
		try {
			URLConnection connection=new URL(getURL(path)).openConnection();
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			OutputStreamWriter outStream = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
			outStream.write(BuildRequestString(params));
			outStream.flush();
    		outStream.close();
    		
    		InputStream is=connection.getInputStream();
    		byte[] b4=new byte[4];
    		is.read(b4);
    		int totallen=Util.bytes2int(b4);
    		byte[] bs=new byte[totallen];
    		ByteArrayOutputStream stream=new ByteArrayOutputStream();    		
    		int len=0;
    		int l=0;
    		while (len<totallen){
    			l=is.read(bs);
    			if (l==-1) throw new IOException();
    			len+=l;
    			stream.write(bs, 0, l);
    		}    		
    		return stream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String doGet(String path,Map<String,String> params){
		URLConnection connection;
		try {
			ByteArrayOutputStream bs=new ByteArrayOutputStream();
			OutputStreamWriter writer=new OutputStreamWriter(bs,"UTF-8");
			writer.append(getURL(path)+"?"+BuildRequestString(params));
			writer.close();
			bs.close();
			String url=bs.toString();
			System.out.println(getURL(path)+"?"+BuildRequestString(params));
			connection =new URL(url).openConnection();
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.connect();
			return readResultString(connection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}
}
