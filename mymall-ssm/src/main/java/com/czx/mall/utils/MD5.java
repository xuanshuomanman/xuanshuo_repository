package com.czx.mall.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * MD5加密工具类
 * */
public class MD5 {
	
	//加密 使用MD5
    private static String hashAlgorithmName = "MD5";
    //加密次数
    private static int hashIterations = 1024;
	public static Object getMD5(String pwd,String salt){
		Object obj = new SimpleHash(hashAlgorithmName, pwd, salt, hashIterations);        
		return obj;
	}
}
