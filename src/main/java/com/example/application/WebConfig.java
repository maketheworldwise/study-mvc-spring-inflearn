package com.example.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new PersonFormatter());
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Order 옵션을 주지 않으면 Add한 순서대로 적용
        registry.addInterceptor(new GreetingInterceptor()).order(0);
        registry.addInterceptor(new AnotherInterceptor())
                .addPathPatterns("/hi/")
                .order(-1);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mobile/**")
                .addResourceLocations("classpath:/mobile/")
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
//                // 캐시를 사용할지 말지를 결정. 운영중이라면 true, 개발중이라면 false가 적절
//                .resourceChain(true)
//                // 요청에 해당하는 리소스를 찾는 방법
//                .addResolver()
//                // 응답으로 내보낼 리소스를 변경하는 방법
//                .addTransformer()
    }
}
