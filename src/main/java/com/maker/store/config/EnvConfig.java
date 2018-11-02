package com.maker.store.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ConfigurationProperties("server")
public class EnvConfig {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    private Integer port;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Autowired
    Environment environment;

    public Integer getServerPort(){
        Integer serverPort=environment.getProperty("server.port",Integer.class);
//        logger.info(serverPort.toString());
        return serverPort;
    }
}
