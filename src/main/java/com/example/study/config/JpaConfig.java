package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration  // 설정 어노테이션 설정파일에 대한
@EnableJpaAuditing // 활성화 강제로 Jpa에대해서 감시를 설정
public class JpaConfig {

}
