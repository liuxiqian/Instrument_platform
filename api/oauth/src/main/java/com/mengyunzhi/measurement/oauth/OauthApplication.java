package com.mengyunzhi.measurement.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableJpaRepositories(basePackages={"com.mengyunzhi.measurement.repository"})
@EntityScan(basePackages={"com.mengyunzhi.measurement.entity"})
@SpringBootApplication
@EnableAuthorizationServer
public class OauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}
}
