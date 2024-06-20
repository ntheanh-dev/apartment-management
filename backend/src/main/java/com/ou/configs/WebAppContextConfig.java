/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.configs;

import com.ou.formatters.CabinetFormatter;
import com.ou.formatters.CriterionFormatter;
import com.ou.formatters.FloorFormmatter;
import com.ou.formatters.LocalDateFormatter;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import java.nio.charset.Charset;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Admin
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableCaching
@ComponentScan(basePackages = {
        "com.ou.controllers",
        "com.ou.mapper",
        "com.ou.services",
        "com.ou.repositories",
        "com.ou.exception",
        "com.ou.components"
})
public class WebAppContextConfig implements WebMvcConfigurer {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new FloorFormmatter());
        registry.addFormatter(new CabinetFormatter());
        registry.addFormatter(new LocalDateFormatter());
        registry.addFormatter(new CriterionFormatter());
    }


    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.ENGLISH);
        slr.setDefaultTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        return slr;
    }

    //    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver resolver  = new InternalResourceViewResolver();
//        // Configure the view class to be used as JSTL for rendering views
//        resolver .setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/pages/"); // Tiền tố
//        resolver.setSuffix(".jsp"); // Hậu tố
//        return resolver;
//    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
