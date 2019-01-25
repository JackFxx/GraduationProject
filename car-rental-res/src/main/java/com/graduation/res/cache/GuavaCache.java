package com.graduation.res.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 基于guava的本地cache
 * @Author fuxiaoxiang2
 * @Create 2019/1/24 19:38
 */
@Component
public class GuavaCache<K, V> {
    private static final Logger LOG = LoggerFactory.getLogger(GuavaCache.class);

    private LoadingCache<K, V> cache;

    public GuavaCache() {
        try {
            cache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(10, TimeUnit.MINUTES)
                    .build(new CacheLoader<K, V>() {
                        @Override
                        public V load(K k) throws Exception {
                            return get(k);
                        }
                    });
        } catch (Exception e) {
            LOG.error("GuavaCache init error", e);
            return;
        }
        LOG.info("GuavaCache init finish");
    }

    public void put(K k, V v) {
        cache.put(k, v);
    }
    public V get(K k){
        try {
            return cache.get(k);
        } catch (ExecutionException e) {
            LOG.error("GuavaCache get value error", e);
            return null;
        }
    }
}
