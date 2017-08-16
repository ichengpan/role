package common.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * <pre>项目名称：ssi-common    
 * 类名称：JsonUtil    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月31日 上午8:47:06    
 * 修改人：徐叶    
 * 修改时间：2017年7月31日 上午8:47:06    
 * 修改备注：       
 * @version </pre>
 */
public class JsonUtil {
	
	private static Gson gson = new Gson();
	
	/**
	 * <pre>toJsonString(把对象转换成json)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月31日 上午8:47:18    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月31日 上午8:47:18    
	 * 修改备注： 
	 * @param obj
	 * @return</pre>
	 */
	public static String toJsonString(Object obj) {
		if (null == obj) {
			throw new NullPointerException();
		}
		String json = gson.toJson(obj);
		return json;
	}
	
	/**
	 * <pre>fromJson(把json转化成对象)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月31日 上午8:47:57    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月31日 上午8:47:57    
	 * 修改备注： 
	 * @param json
	 * @param t
	 * @return</pre>
	 */
	public static <T> T fromJson(String json, Class<T> t) {
		if (null == json) {
			throw new NullPointerException();
		}
		T obj = gson.fromJson(json, new TypeToken<T>(){}.getType());
		return obj;
	}

}
