package com.crazyloong.cat.config;

import com.crazyloong.cat.util.SnowflakeId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SnowFlakeConfig {

    @Bean(name="trade_cal")
    public SnowflakeId getTradeCalIdWorker() {
        return new SnowflakeId(Long.parseLong("${snowflake.workid}"), Long.parseLong("${snowflake.datacenterid-trade_cal}"));
    }
}
