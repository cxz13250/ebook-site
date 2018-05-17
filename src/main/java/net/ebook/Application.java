package net.ebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.boot.autoconfigure.cloud.CloudAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.boot.autoconfigure.mobile.DeviceDelegatingViewResolverAutoConfiguration;
import org.springframework.boot.autoconfigure.mobile.DeviceResolverAutoConfiguration;
import org.springframework.boot.autoconfigure.mobile.SitePreferenceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.boot.autoconfigure.reactor.ReactorAutoConfiguration;
import org.springframework.boot.autoconfigure.security.FallbackWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.social.FacebookAutoConfiguration;
import org.springframework.boot.autoconfigure.social.LinkedInAutoConfiguration;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.boot.autoconfigure.social.TwitterAutoConfiguration;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;

/**
 * @Author ROKG
 * @Description
 * @Date: Created in 下午7:30 2018/1/21
 * @Modified By:
 */
@SpringBootApplication(exclude = {
        ActiveMQAutoConfiguration.class,
        BatchAutoConfiguration.class,
        CloudAutoConfiguration.class,
        DeviceDelegatingViewResolverAutoConfiguration.class,
        DeviceResolverAutoConfiguration.class,
        ElasticsearchRepositoriesAutoConfiguration.class,
        FacebookAutoConfiguration.class,
        FallbackWebSecurityAutoConfiguration.class,
        FlywayAutoConfiguration.class,
        FreeMarkerAutoConfiguration.class,
        GroovyTemplateAutoConfiguration.class,
        GsonAutoConfiguration.class,
        HypermediaAutoConfiguration.class,
        IntegrationAutoConfiguration.class,
        JerseyAutoConfiguration.class,
        JmsAutoConfiguration.class,
        JmxAutoConfiguration.class,
        JndiConnectionFactoryAutoConfiguration.class,
        JndiDataSourceAutoConfiguration.class,
        LinkedInAutoConfiguration.class,
        LiquibaseAutoConfiguration.class,
        MailSenderAutoConfiguration.class,
        MongoAutoConfiguration.class,
        MongoRepositoriesAutoConfiguration.class,
        MustacheAutoConfiguration.class,
        PersistenceExceptionTranslationAutoConfiguration.class,
        RabbitAutoConfiguration.class,
        ReactorAutoConfiguration.class,
        RepositoryRestMvcAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        SitePreferenceAutoConfiguration.class,
        SocialWebAutoConfiguration.class,
        SolrAutoConfiguration.class,
        SolrRepositoriesAutoConfiguration.class,
        ThymeleafAutoConfiguration.class,
        TwitterAutoConfiguration.class,
        WebSocketAutoConfiguration.class,
        XADataSourceAutoConfiguration.class
})
@MapperScan(basePackages = "net.ebook.dao")
public class Application {

    public static void main(String[] args){
        SpringApplication application=new SpringApplication(Application.class);

        application.setWebEnvironment(true);
        application.run(args);
    }

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("100MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("500MB");
        return factory.createMultipartConfig();
    }
}
