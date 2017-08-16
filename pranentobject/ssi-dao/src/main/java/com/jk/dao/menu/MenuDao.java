/** 
 * <pre>项目名称:ssi-dao 
 * 文件名称:MenuDao.java 
 * 包名:com.jk.dao.menu 
 * 创建日期:2017年7月27日下午11:00:47 
 * Copyright (c) 2017, ixuye@hotmail.com All Rights Reserved.</pre> 
 */  
package com.jk.dao.menu;

import java.util.List;

import com.jk.pojo.menu.MenuRequest;
import com.jk.pojo.menu.MenuResponse;

/** 
 * <pre>项目名称：ssi-dao    
 * 类名称：MenuDao    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月27日 下午11:00:47    
 * 修改人：徐叶    
 * 修改时间：2017年7月27日 下午11:00:47    
 * 修改备注：       
 * @version </pre>    
 */
public interface MenuDao {

	/** <pre>selectMenuListJson(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午11:16:34    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午11:16:34    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectMenuListJson(MenuRequest menuRequest);

	/** <pre>selectFirstMenuList(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午11:27:25    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午11:27:25    
	 * 修改备注： 
	 * @param menuRequest
	 * @return</pre>    
	 */
	List<MenuResponse> selectFirstMenuList(MenuRequest menuRequest);

	/** <pre>insertMenu(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月27日 下午11:37:25    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月27日 下午11:37:25    
	 * 修改备注： 
	 * @param menuRequest</pre>    
	 */
	void insertMenu(MenuRequest menuRequest);

	/** <pre>deleteMenu(这里用一句话描述这个方法的作用)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月28日 上午7:53:32    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月28日 上午7:53:32    
	 * 修改备注： 
	 * @param menuRequest</pre>    
	 */
	void deleteMenu(MenuRequest menuRequest);

}
