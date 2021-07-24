package info.config;

import info.ExecuteTimeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.ContentVersionStrategy;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ExecuteTimeInterceptor executeTimeInterceptor(){
        return new ExecuteTimeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(executeTimeInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
                // 웹 리소스 경로 추가 및 설정
        registry.addResourceHandler("/assets/**") // URI 값
                .addResourceLocations("classpath:/assets/", "/assets/") // 실제 폴더의 경로
                .setCachePeriod(3600) // 웹 리소스 캐시 주기 : 1년
                .resourceChain(true) // VersionResourceResolver 같은 추가 설정 가능하도록
                // VersionResourceResolver: 웹 리소스 파일들을 로드시에 css가 적용되도록
                .addResolver(new VersionResourceResolver().addVersionStrategy(new ContentVersionStrategy(), "/**"));

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
