package net.ebook.configure;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午9:39 2018/2/11
 * @Modified By:
 */
@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean getFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter());
        registration.addUrlPatterns("/api");
        registration.setName("sessionFilter");
        return registration;
    }

    /**
     * 创建一个bean
     * @return
     */
    @Bean(name = "sessionFilter")
    public Filter sessionFilter() {
        return new MyFilter();
    }

}
