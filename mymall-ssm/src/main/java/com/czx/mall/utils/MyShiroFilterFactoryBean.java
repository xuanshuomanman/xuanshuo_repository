package com.czx.mall.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;

public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean{
	//注入
		//@Autowired
		//private MenuService ms;
		//{0}为占位符  ROLE_STRING是用于字符串拼接
		private static final String ROLE_STRING = "roles[{0}]";
		//默认权限
		public static  String filterChainDefinitions="";
	    @Override
	    public void setFilterChainDefinitions(String definitions) {
	    	System.out.println("$$$$$$$$$$$$$$");
	    	//每次都付给filterChainDefinitions
	    	filterChainDefinitions = definitions;
	        Ini ini = new Ini();
	        ini.load(definitions);
	        //did they explicitly state a 'urls' section?  Not necessary, but just in case:
	        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
	        if (CollectionUtils.isEmpty(section)) {
	            //no urls section.  Since this _is_ a urls chain definition property, just assume the
	            //default section contains only the definitions:
	            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
	        }
	        Map<String,String[]> permsMap = new HashMap<String, String[]>();
	       // List<Menu> menus = ms.findMenuAll();
//	        for (Menu menu : menus) {
//				List<Role> rs = menu.getRole();
//				String[] roleNames = new String[rs.size()];
//				for (int i=0;i<rs.size();i++) {
//	 				roleNames[i]=rs.get(i).getRoleName();
//	 			}
//				permsMap.put(menu.getMenuLink(),roleNames);
//			}
	        for (String url : permsMap.keySet()) {
				 System.out.println("路径："+url);
				 //通过路径取得对应的角色
				 String[] roles = permsMap.get(url);
				 StringBuilder sb = new StringBuilder();
				 for (String role : roles) {
					 sb.append(role).append(",");
				}
				 
				 //admin,test,guest,
				 //截取最后一个,
				 String str = sb.substring(0,sb.length()-1);
				 System.out.println("str:"+str+"%%%%%%%%%%%");
				 System.out.println(permsMap+"************");
				 //把对应的路径以及权限放到section中， MessageFormat.format(ROLE_STRING, str) 替换占位符{0}  
				 System.out.println("MessageFormat.format(ROLE_STRING, str):"+MessageFormat.format(ROLE_STRING, str));
				 section.put(url, MessageFormat.format(ROLE_STRING, str));
			}
	        section.put("/**", "authc");
	        setFilterChainDefinitionMap(section);
	    }
}
