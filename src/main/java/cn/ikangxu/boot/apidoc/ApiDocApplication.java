/*
 *  Engine3D Technologies Co., Ltd. Copyright 2019,  All rights reserved
 */

package cn.ikangxu.boot.apidoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @className ApiDocApplication
 * @description 
 * @author kangxu [xukang@engine3d.com]
 * @date 2019/11/8 11:19
 * @version v1.0
 */
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class ApiDocApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDocApplication.class, args);
    }

}
