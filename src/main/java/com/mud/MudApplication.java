package com.mud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by leeesven on 17/8/19.
 */

@SpringBootApplication
@MapperScan("com.mud.dao")
public class MudApplication {
    static {

    }
    public static void main(String[] args){
        SpringApplication.run(MudApplication.class, args);
    }
}
