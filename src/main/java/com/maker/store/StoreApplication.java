package com.maker.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.maker.store.mapper")
@EnableSwagger2
@EnableScheduling
@EnableAsync
@EnableWebSecurity
public class StoreApplication extends SpringBootServletInitializer {

    private Logger logger= LoggerFactory.getLogger(StoreApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(StoreApplication.class);
    }
}
