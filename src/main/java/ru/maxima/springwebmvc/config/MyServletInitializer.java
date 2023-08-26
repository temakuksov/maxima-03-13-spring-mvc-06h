package ru.maxima.springwebmvc.config;

import jakarta.annotation.Nonnull;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author AramaJava 24.07.2023
 */

public class MyServletInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    @Nonnull
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}
