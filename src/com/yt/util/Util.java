package com.yt.util;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.yt.activity.BaseActivity;
import com.yt.protocol.Yueting.Account;
import com.yt.protocol.Yueting.User;
import com.yt.thread.PostThread;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;

/**用于切换Activity, *检测更新, *更新 */
public class Util {

	public static final boolean TEST_MODE = false;

	public static void startNewActivity(Activity current, Class<?> cls,	boolean finishSelf) {
		Intent intent = new Intent(current, cls);
		current.startActivity(intent);
		if (finishSelf) current.finish();
	}
	
	@SuppressWarnings("unchecked")
	public static<T> T[] list2Arr(List<Object> list,Class<T> type){
		T[] ret=(T[])Array.newInstance(type,list.size());
		list.toArray(ret);	
		return ret;		
	}
			
	public static<T> List<T> arr2List(T[] array){
		List<T> ret=new ArrayList<T>();
		for (T e:array){
			ret.add(e);
		}
		return ret;
	}
	
	public static boolean isEmpty(Object s){
		if (s==null) return true;
		if (s instanceof String&&((String)s).length()==0) return true;
		return false;
	}
	
	// 整数到字节数组转换
	public static byte[] int2bytes(int n) {
		byte[] ab = new byte[4];
		ab[0] = (byte) (0xff & n);
		ab[1] = (byte) ((0xff00 & n) >> 8);
		ab[2] = (byte) ((0xff0000 & n) >> 16);
		ab[3] = (byte) ((0xff000000 & n) >> 24);
		return ab;
	}

	// 字节数组到整数的转换
	public static int bytes2int(byte b[]) {
		int s = 0;
		s = ((((b[3] & 0xff) << 8 | (b[2] & 0xff)) << 8) | (b[1] & 0xff)) << 8 | (b[0] & 0xff);
		return s;
	}
	
	/**
	 * 从指定容器中随机选出若干个
	 * i>0选出i个
	 * i=0选出小于src.size()个，数量随机。
	 * i<0选出小于-i个，数量随机。
	 * */
	public static<T> List<T> some(int i,Collection<T> src){
		if (i==0) return some((int)(1+Math.random()*(src.size()-1)),src); else
			if (i<0) return some((int)(1+Math.random()*(-i-1)),src);
		ArrayList<T> list=new ArrayList<T>();
		Iterator<T> iter=src.iterator();
		double possibility=1f/src.size();
		while (list.size()<i){
			T t;
			if (!iter.hasNext()) iter=src.iterator();
			t=iter.next();
			if (Math.random()<possibility)
				list.add(t);
		}
		return list;
	}
	
	public static String toJson(Object obj){
		Gson gson=new Gson();
		return gson.toJson(obj);
	}
	
	public static<T> T fromJson(String json,Class<T> cls){
		Gson gson=new Gson();
		T ret=gson.fromJson(json,cls);
		return ret;
	}
	
	/** 
     * 获得超类的参数类型，取第一个参数类型 
     * @param <T> 类型参数 
     * @param clazz 超类类型 
     */  
    @SuppressWarnings("rawtypes")  
    public static <T> Class<T> getClassGenricType(final Class clazz) {  
        return getClassGenricType(clazz, 0);  
    }  
      
    /** 
     * 根据索引获得超类的参数类型 
     * @param clazz 超类类型 
     * @param index 索引 
     */  
    @SuppressWarnings("rawtypes")  
    public static Class getClassGenricType(final Class clazz, final int index) {
        Type genType = clazz.getGenericSuperclass();  
        if (!(genType instanceof ParameterizedType)) {  
            return Object.class;  
        }  
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();  
        if (index >= params.length || index < 0) {  
            return Object.class;  
        }  
        if (!(params[index] instanceof Class)) {  
            return Object.class;  
        }  
        return (Class) params[index];
    	
    	//Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
    }  
    
    /*static public<T> Class<T> getClassGenricType(Class<?> cls,int index){
    	Class<T> entityClass = (Class <T>) ((ParameterizedType) cls.getGenericSuperclass()).getActualTypeArguments()[index];
    	return entityClass;
    }*/
    
    public static void Toast(String text,int length){
    	if (BaseActivity.getFrontActivity()==null) return;
    	Message msg=Message.obtain();
    	msg.arg1=length;
    	msg.obj=text;
    	BaseActivity.getFrontActivity().toastHandler.sendMessage(msg);
    }
}
