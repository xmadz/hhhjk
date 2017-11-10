package com.redis.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisPoolTest {
	private static Jedis jedis;
	private static JedisPool pool;
	static{
		pool = new JedisPool("127.0.0.1", 6379);
		jedis = pool.getResource();
	}

	public static void main(String[] args) {
		
		// String类型使用
		jedis.set("str", "Jedis Hello");
		// 向字符串末尾添加字符串
		jedis.append("str", " World!");
		String str = jedis.get("str");
		System.out.println(str);
		// 获取String类型中value值得字符串长度
		System.out.println(jedis.strlen("str"));
		// String类型中value值自增2
		jedis.incrBy("s1", 2);
		System.out.println(jedis.get("s1"));

		
		// Map类型使用
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "何昕");
		map.put("age", "23");
		// 一次可以设置多个字段值
		jedis.hmset("str2", map);
		// hsetnx 如果str2中没有job字段则设置job值为java工程师，否则不做任何操作
		jedis.hsetnx("str2", "job", "java工程师");
		// 一次可以获取多个字段值
		System.out.println(jedis.hmget("str2", "name", "age"));
		// 获取所有字段值
		System.out.println(jedis.hgetAll("str2"));
		// 可以删除一个或多个字段，返回值是被删除的字段个数
		// jedis.hdel("str2", "job","age");
		// System.out.println(jedis.hgetAll("str2"));
		// 将年龄增加2岁
		jedis.hincrBy("str2", "age", 2);
		System.out.println(jedis.hgetAll("str2"));
		// 判断字段是否存在
		Boolean flag = jedis.hexists("str2", "job");
		System.out.println(flag);
		//只获取字段名或字段值
		Set<String> keyName = jedis.hkeys("str2");
		for (String string : keyName) {
			System.out.println("字段名:"+string);
		}
		List<String> list = jedis.hvals("str2");
		for (String string : list) {
			System.out.println("字段值:"+string);
		}
		//获取字段数量
		Long num = jedis.hlen("str2");
		System.out.println("字段数量:"+num);
		Set<String> set = jedis.keys("*");
		System.out.println(set);
		
		Long time = jedis.ttl("str2");
		System.out.println(time);
		
		
		jedis.close();
		pool.close();

	}

}
