package net.ebook.configure;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午7:41 2018/1/27
 * @Modified By:
 */
@Configuration
@AutoConfigureAfter(MapperAutoConfiguration.class)
public class MyBatisScannerConfig {

    @Bean
    public PageHelper pageHepler() {
        System.out.println("MyBatisMapperScannerConfig.pageHelper Opening... ");
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

}
