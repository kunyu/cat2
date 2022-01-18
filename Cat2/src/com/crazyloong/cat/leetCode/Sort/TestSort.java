package com.crazyloong.cat.leetCode.Sort;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.net.URL;

public class TestSort {

    public static void main(String[] args) {
        int[] a = {1,4,2,7,2,8,10,22,23,12,41,31};
        Sorter Sort = new MergeSort();
        Sort.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i]+" ");
        }
        /*URL url = TestSort.class.getResource("/ehcache.xml");
        System.out.println(TestSort.class.getResource("/ehcache.xml"));
        System.out.println(TestSort.class.getResource("/ehcache.xml"));
        // 2. 准备配置对象
        XmlConfiguration config = new XmlConfiguration(url);

        // 3. 创建一个新的缓存管理器类
        CacheManager cacheManager = CacheManagerBuilder.newCacheManager(config);
        // 4. 初始化缓存管理器
        cacheManager.init();//一定要初始化，否则会报错。
        // 5. 获取缓存对象
        Cache<String, String> cache = cacheManager.getCache("cache1", String.class, String.class);
        // 6. 存储键值
        cache.put("xi,an", "西安");
        // 7. 根据键获取值
        System.out.println(cache.get("xi,an"));
        // 8. 删除键
        cache.remove("xi,an");
        System.out.println(cache.get("xi,an"));
        // 9. 清空缓存
        cache.clear();*/
    }

}
