package com.crazyloong.cat;


import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableCaching
@MapperScan("com.crazyloong.cat.*.mybatis.dao")
@RetrofitScan("com.crazyloong.cat")

public class SampleController {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);

    }
}
