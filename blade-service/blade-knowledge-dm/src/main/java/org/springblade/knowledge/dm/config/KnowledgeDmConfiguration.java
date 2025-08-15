package org.springblade.knowledge.dm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * 配置feign、mybatis包名
 *
 * @author Chill
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients({"org.springblade"})
@MapperScan({"org.springblade.**.mapper.**"})
public class KnowledgeDmConfiguration {

}
