package common.util;

import redis.clients.jedis.Jedis;

/**
 * 
 * <pre>项目名称：ssi-common    
 * 类名称：JedisUtil    
 * 类描述：    
 * 创建人：徐叶  
 * 创建时间：2017年7月31日 上午8:49:25    
 * 修改人：徐叶    
 * 修改时间：2017年7月31日 上午8:49:25    
 * 修改备注：       
 * @version </pre>
 */
public class JedisUtil {
	
	private static Jedis jedis = null;
	
	/**
	 * <pre>getJedis(连接数据库，双重判定锁)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月31日 上午8:54:46    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月31日 上午8:54:46    
	 * 修改备注： 来达到两个请求同时发送时至允许有一个连接数据库
	 * @return</pre>
	 */
	private static Jedis getJedis() {
		if (null == jedis) {
			synchronized (JedisUtil.class) {
				if (null == jedis) {
					jedis = new Jedis("192.168.94.128", 6379);
					jedis.auth("123");
				}
			}
		}
		return jedis;
	}
	
	/**
	 * <pre>setString(将数据存入到redis中)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月31日 上午8:49:36    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月31日 上午8:49:36    
	 * 修改备注： 
	 * @param k 对应的键
	 * @param v 对应的值
	 * @param seconds redis的失效时间，单位秒
	 * @return</pre>
	 */
	public static String setString(String k, String v, int seconds) {
		Jedis jedis = getJedis();
		String s = jedis.set(k, v);
		if (0 <= seconds) {
			jedis.expire(k, seconds);
		}
		return s;
	}
	
	/**
	 * <pre>getString(根据键来进行取值)   
	 * 创建人：徐叶     
	 * 创建时间：2017年7月31日 上午8:49:46    
	 * 修改人：徐叶       
	 * 修改时间：2017年7月31日 上午8:49:46    
	 * 修改备注： 
	 * @param k
	 * @return</pre>
	 */
	public static String getString(String k) {
		Jedis jedis = getJedis();
		String v = jedis.get(k);
		return v;
	}

}
