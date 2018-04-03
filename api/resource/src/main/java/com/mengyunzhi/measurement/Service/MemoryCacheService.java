package com.mengyunzhi.measurement.Service;

import org.apache.log4j.Logger;

import java.util.*;


/**
 * 内存缓存，直接使用程序的内存做缓存，适用于存一些字符串的小信息
 */
public interface MemoryCacheService {
    Logger logger = Logger.getLogger("MemoryCacheService");
    String EXPIRE_DATE_KEY = "expireDate";  // 数据失效时间关键字（指在某个时间失效）
    String VALUE_KEY = "value";  // 存数据的KEY
    Integer DEFAULT_EXPIRE_TIME = 1800;   // 未设置时默认的过期时间
    Integer DEFAULT_CLEAR_EXPIRED_DATA_TIME = 100;  // 自动清除过期缓存的概率为1/100
    Stack<Integer> EXPIRE_TIME = new Stack<>();                       // 可设置的过期时间(秒) -- 由于无法更改final中的普通变量，所以采用 践 的形式
    HashMap<String, HashMap<String, Object>> hashMap = new HashMap<>();  // 缓存值

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    static Object get(String key) {
        if (MemoryCacheService.shouldClearExpiredData()) {
            logger.info("定期清除过期缓存");
            MemoryCacheService.clearExpiredData();
        }

        HashMap<String, Object> cachedObject = MemoryCacheService.getCachedObjectByKey(key);
        if (cachedObject == null) {
            return null;    // 未获取到缓存数据，返回null
        }

        if (MemoryCacheService.isExpired(cachedObject)) {
            logger.info("缓存过期，清除缓存.返回null");
            MemoryCacheService.remove(key);
            return null;
        }

        return cachedObject.get(MemoryCacheService.VALUE_KEY);
    }

    /**
     * 清除过期数据
     * panjie
     */
    static void clearExpiredData() {
        Iterator<Map.Entry<String, HashMap<String, Object>>> it = MemoryCacheService.hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, Object>> entry = it.next();
            if (MemoryCacheService.isExpired(entry.getValue())) {
                it.remove();
            }
        }
    }

    /**
     * 是否要清除过期数据
     * @return
     * panjie
     */
    static boolean shouldClearExpiredData() {
        if ((new Date().getTime() % DEFAULT_CLEAR_EXPIRED_DATA_TIME) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 数据是否已过期
     * @param cachedObject
     * @return
     * panjie
     */
    static boolean isExpired(HashMap<String, Object> cachedObject) {
        if ((Long) cachedObject.get(MemoryCacheService.EXPIRE_DATE_KEY) <= new Date().getTime()) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * 获取缓存的 Object
     * Object中两个数据：1. 缓存的值 2.过期时间
     *
     * @param key
     * @return
     */
    static HashMap<String, Object> getCachedObjectByKey(String key) {
        HashMap<String, Object> hashMap = MemoryCacheService.hashMap.get(key);
        return hashMap;
    }

    /**
     * 设置缓存
     * 以系统设置的过期时间做为过期时间
     *
     * @param key
     * @param object
     */
    static void put(String key, Object object) {
        Integer expireTime = MemoryCacheService.getExpireTime();
        MemoryCacheService.put(key, object, expireTime);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param object
     * @param expireTime 过期时间，单位秒
     */
    static void put(String key, Object object, Integer expireTime) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(MemoryCacheService.VALUE_KEY, object);
        hashMap.put(MemoryCacheService.EXPIRE_DATE_KEY, new Date().getTime() + expireTime * 1000);
        MemoryCacheService.hashMap.put(key, hashMap);
    }

    /**
     * 设置过期时间
     * 设置后将忽略系统设置中的过期时间
     * 而且在整体程序的生命周期中都起作用
     *
     * @param expireTime
     */
    static void setExpireTime(Integer expireTime) {
        if (MemoryCacheService.EXPIRE_TIME.size() > 0) {
            MemoryCacheService.EXPIRE_TIME.clear();
        }
        MemoryCacheService.EXPIRE_TIME.push(expireTime);
    }

    /**
     * 清空缓存
     */
    static void clear() {
        MemoryCacheService.hashMap.clear();
    }

    /**
     * 获取过期时间
     *
     * @return
     */
    static Integer getExpireTime() {
        Integer expireTime;
        try {
            expireTime = MemoryCacheService.EXPIRE_TIME.peek();
        } catch (EmptyStackException e) {
            expireTime = MemoryCacheService.DEFAULT_EXPIRE_TIME;
        }

        return expireTime;
    }

    /**
     * 移除KEY
     *
     * @param key
     */
    static void remove(String key) {
        logger.info("删除hashMap中的KEY对应的值。不能使用remove, 否则将出现ConcurrentModificationException");
        Iterator<Map.Entry<String, HashMap<String, Object>>> it = MemoryCacheService.hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, HashMap<String, Object>> entry = it.next();
            if (entry.getKey().equalsIgnoreCase(key)) {
                it.remove();
            }
        }

    }
}
