package com.ssafy.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ssafy.guestbook.interceptor.ConfirmInterceptor;

@Configuration  //이 java파일이 환경설정 파일이다 라고 명시
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private ConfirmInterceptor confirmIntegerCeptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(confirmIntegerCeptor).addPathPatterns("/article/**"); //article로 들어오면 무조건 인터셉터 거쳐 가자
	}

	
	
	
}
