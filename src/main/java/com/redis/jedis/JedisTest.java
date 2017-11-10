package com.redis.jedis;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class JedisTest 
{
    public static void main( String[] args )
    {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        System.out.println(jedis);
        jedis.set("s2","22222");
        jedis.set("s3","33333");
        List<String> result = jedis.mget("s1","s2","s3");
        for(String str:result) {
        	System.out.println(str);
        }
        jedis.close();
    }
}
