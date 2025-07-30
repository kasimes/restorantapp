package com.ornek.restorant.restorantapp.config;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    private static  final int DEFAULT_MAX_SIZE = 1000;
    private  static final int DEFAULT_EXPIRE_MINUTES = 15;



    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(Arrays.asList(
                buildCache("restaurantsCache"),
                buildCache("usersCache"),
                buildCache("branchesCache"),
                buildCache("menusCache"),
                buildCache("menuItemsCache")
        ));
        return manager;



    }
    private CaffeineCache buildCache(String name) {
        return new CaffeineCache(name,
                Caffeine.newBuilder()
                        .expireAfterWrite(DEFAULT_EXPIRE_MINUTES, TimeUnit.MINUTES)
                        .maximumSize(DEFAULT_MAX_SIZE)
                        .recordStats() // hit/miss oranlarını tutar
                        .build()
        );
    }
}
