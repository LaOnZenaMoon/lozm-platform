package lozm.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final InterceptorConfig interceptorConfig;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorConfig).addPathPatterns(new String[]{"/**"});
    }

}
