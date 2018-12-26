package com.tiuweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * <p>
 * Title: springboot 内置容器启动
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author Torlay
 * @version 1.00.00 创建时间：2017-2-21 上午9:35:46
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 * 
 */

@SpringBootApplication
@EnableScheduling
@ServletComponentScan
@EnableSwagger2
@MapperScan(basePackages = { "com.tiuweb.**.**.mapper" })
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

}