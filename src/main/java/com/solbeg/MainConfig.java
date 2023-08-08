package com.solbeg;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.nio.charset.StandardCharsets;

@EnableWebMvc
@ComponentScan({"com.solbeg"})
public class MainConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    public MainConfig() {
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MainConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        final CharacterEncodingFilter cef = new CharacterEncodingFilter();
        cef.setEncoding(StandardCharsets.UTF_8.name());
        cef.setForceEncoding(true);
        return new Filter[]{new HiddenHttpMethodFilter(), cef};
    }
}
