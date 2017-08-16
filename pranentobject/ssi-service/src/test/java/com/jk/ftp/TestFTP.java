/** 
 * <pre>项目名称:ssi-service 
 * 文件名称:TestFTP.java 
 * 包名:com.jk.ftp 
 * 创建日期:2017年8月1日下午7:22:16 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.ftp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

/** 
 * <pre>项目名称：ssi-service    
 * 类名称：TestFTP    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年8月1日 下午7:22:16    
 * 修改人：徐叶    
 * 修改时间：2017年8月1日 下午7:22:16    
 * 修改备注：       
 * @version </pre>    
 */
public class TestFTP {
	@Test
	public void connFTP() {
		//1、实例化客户端
		FTPClient ftp = new FTPClient();
		try {
			//2、连接ftp服务器
			ftp.connect("192.168.1.111", 21);
			//3、登陆ftp服务器
			boolean login = ftp.login("root", "root");
			if (login) {
				//4、切换到根路径(/)下
				ftp.changeWorkingDirectory("/");
				//4、1切换到/chengp/aaa下
				boolean boo1 = ftp.changeWorkingDirectory("chengp/aaa");
				if (!boo1) {
					//创建路径
					ftp.makeDirectory("chengp/aaa");
					ftp.changeWorkingDirectory("chengp/aaa");
				}
				
				//如果上传媒体文件，需要设置二进制
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				
				InputStream f = new FileInputStream("e:/w60-1920x1024.jpg");
				//5、向ftp上传文件
				boolean storeFile = ftp.storeFile(new String("111.jpg".getBytes("GBK"), "ISO-8859-1"), f);
				System.out.println(storeFile);
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
