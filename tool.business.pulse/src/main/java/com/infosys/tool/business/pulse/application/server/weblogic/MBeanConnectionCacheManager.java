package com.infosys.tool.business.pulse.application.server.weblogic;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MBeanConnectionCacheManager {
	private static MBeanConnectionCacheManager instance;
    private static Object monitor = new Object();
    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());

    private MBeanConnectionCacheManager() {
    }

    public void put(String cacheKey, Object value) {
        cache.put(cacheKey, value);
    }

    public Object get(String cacheKey) {
        return cache.get(cacheKey);
    }

    public void clear(String cacheKey) {
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public static MBeanConnectionCacheManager getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new MBeanConnectionCacheManager();
                }
            }
        }
        return instance;
    }

}
