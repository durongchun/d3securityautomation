package com.d3security.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d3security.constant.TestConstant;

import bsh.This;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * Redis tools
 *
 * @author Lucy Du
 * @version 1.0.0
 * @date 2022/10/22
 */
@Slf4j
public class RedisUtil {
    /**
     * Redis connection pool
     */
    private static JedisPool jedisPool;

    /**
     * Jedis connection 
     */
    private Jedis jedis;

    /**
     * Jedis Key-value pair default expiration time (s)
     */
    private int jedisExpireTime;

    /**
     * Initialization redis connection pool
     *
     * @return JedisPool
     */
    public static JedisPool initJedisPool() {
        /* Redis connection parameters */
        String redisIp = PropertiesReader.getKey("redis.ip");
        int redisPort = Integer.parseInt(PropertiesReader.getKey("redis.port"));
        String redisPwd = PropertiesReader.getKey("redis.pwd");
        /* Redis connection pool config parameters */
        int jedisPoolMaxTotal = Integer.parseInt(PropertiesReader.getKey("jedis.pool.maxTotal"));
        int jedisPoolMaxIdle = Integer.parseInt(PropertiesReader.getKey("jedis.pool.maxIdle"));
        int jedisPoolMaxWaitMillis = Integer.parseInt(PropertiesReader.getKey("jedis.pool.maxWaitMillis"));
        boolean jedisPoolTestOnBorrow = Boolean.parseBoolean(PropertiesReader.getKey("jedis.pool.testOnBorrow"));
        boolean jedisPoolTestOnReturn = Boolean.parseBoolean(PropertiesReader.getKey("jedis.pool.testOnReturn"));
        /* Redis connection pool execution configuration */
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(jedisPoolMaxTotal);
        jedisPoolConfig.setMaxIdle(jedisPoolMaxIdle);
        jedisPoolConfig.setMaxWaitMillis(jedisPoolMaxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(jedisPoolTestOnBorrow);
        jedisPoolConfig.setTestOnReturn(jedisPoolTestOnReturn);
        /* Connect redis server and produce redis connection pool */
        try {
            log.info("Start to initionize redis connection pool");
            if (redisPwd.isEmpty()) {
                // Whatever redis starts or not，JedisPool can run, wouldn't go to in catch, but it would get error when getResource
                jedisPool = new JedisPool(jedisPoolConfig, redisIp, redisPort, TestConstant.THREE_THOUSANG);
            } else {
                jedisPool = new JedisPool(jedisPoolConfig, redisIp, redisPort, TestConstant.TEN_THOUSANG, redisPwd);
            }
        } catch (Exception e) {
            log.error("Fail to produce redis pool!");
        }
        return jedisPool;
    }

    /**
     * Initialize redis connection
     *
     * @return jedis connection
     */
    public Jedis initJedis() {       
        if (jedis != null) {
            returnJedis();
        }
        try {
            jedis = jedisPool.getResource();
            // Time expired by default
            String jedisExpireTimeStr = PropertiesReader.getKey("jedis.expireTime");
            if (jedisExpireTimeStr != null && !jedisExpireTimeStr.isEmpty()) {
                jedisExpireTime = Integer.parseInt(jedisExpireTimeStr);
            }
            log.info("Succeed to produce redis pool and new a connection!");
        } catch (Exception e) {
            log.error("Failed to produce a new  connection from redis pool!");
        }
        return jedis;
    }

    /**
     * Get redis pool object
     *
     * @return redis pool
     */
    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * Get jedis connection from pool
     *
     * @return jedis 
     */
    public Jedis getJedis() {
        return jedis;
    }

    /**
     * Set key-value
     *
     * @param key   
     * @param value 
     * 
     */
    public void setKey(String key, String value) {
        jedis.set(key, value);
        // If there is default expired time set in cofig
        if (jedisExpireTime != 0) {
            jedis.expire(key, jedisExpireTime);
        }
    }

    /**
     * Set key-value
     *
     * @param key     
     * @param value   
     * @param seconds expiration time
     */
    public void setKey(String key, String value, int seconds) {
        jedis.set(key, value);
        // Set expiration time for key-value
        jedis.expire(key, seconds);
    }

    /**
     * Get value according to key
     *
     * @param key 
     * @return value
     */
    public String getKey(String key) {
        return jedis.get(key);
    }

    /**
     * Return jedis connection
     */
    public void returnJedis() {
        if (jedis != null) {
            jedis.close();
            jedis = null;
            jedisExpireTime = 0;
            log.info("Jedis returned!");
        }
    }
}
