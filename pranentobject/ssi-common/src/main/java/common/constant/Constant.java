/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:Constant.java 
 * 包名:common.constant 
 * 创建日期:2017年7月19日下午3:14:59 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.constant;

/** 
 * <pre>项目名称：ssi-03    
 * 类名称：Constant    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年7月19日 下午3:14:59    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年7月19日 下午3:14:59    
 * 修改备注：       
 * @version </pre>    
 */
public class Constant {
	
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_ACCOUNT_ERROR = 2;
	public static final int LOGIN_PWD_ERROR = 3;
	public static final int LOGIN_CODE_ERROR = 4;
	public static final int LOGIN_CODE_NULL = 5;
	public static final int ACCOUNT_LOCKED = 6;
	
	
	/**
	 * 用户名已存在
	 * USERNAME_EXISTS = 1
	 */
	public static Integer USERNAME_EXISTS = 1;
	
	public static Integer USERNAME_AVAILABLE = 2;
	
	/**
	 * 验证码正确  CODE_SUCESS = 1
	 */
	public static Integer CODE_SUCESS = 1;
	
	public static Integer CODE_ERROR = 2;
}
