package com.czx.mall.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.core.convert.converter.Converter;

/**
 * 时间类型转换器
 * @author Administrator
 *
 */
public class MyDateConverter implements Converter<String, Date>{

	@Override
	/**
	 * arg0是页面传过来的时间字符串
	 */
	public Date convert(String arg0) {
		SimpleDateFormat sf = getSimpleDateFormat(arg0);
		try {
//			将时间字符串类型转换为Date类型
			Date d = sf.parse(arg0);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 *匹配时间格式
	 * @param source
	 * @return
	 */
	private  SimpleDateFormat getSimpleDateFormat(String source){
		SimpleDateFormat sdf = null;
		if(Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", source)){
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		}else if(Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", source)){
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		} else if(Pattern.matches("^\\d{4}\\d{2}\\d{2}$", source)){
		sdf = new SimpleDateFormat("yyyyMMdd");
		}
		return sdf;
		}

}
