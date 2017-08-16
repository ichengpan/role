/** 
 * <pre>项目名称:ssi-service 
 * 文件名称:MenuService.java 
 * 包名:com.jk.service.menu 
 * 创建日期:2017年7月27日下午10:58:53 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.service.menu;

import java.util.List;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;

/** 
 * <pre>项目名称：ssi-service    
 * 类名称：MenuService    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午10:58:53    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午10:58:53    
 * 修改备注：       
 * @version </pre>    
 */
public interface MenuService {

	/** <pre>selectMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午11:14:45    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午11:14:45    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectMenuListJson(MenuRequest menuRequest);

	/** <pre>selectFirstMenuList(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午11:26:04    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午11:26:04    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectFirstMenuList(MenuRequest menuRequest);

	/** <pre>insertMenu(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午11:31:08    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午11:31:08    
	 * 修改备注： 
	 * @param menuRequest</pre>    
	 */
	void insertMenu(MenuRequest menuRequest);

	/** <pre>deleteMenu(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 上午7:52:49    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 上午7:52:49    
	 * 修改备注： 
	 * @param menuRequest</pre>    
	 */
	void deleteMenu(MenuRequest menuRequest);

}
