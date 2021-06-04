package com.ernie.mock_instagram_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;

public class MainWebAppInitializer implements WebApplicationInitializer {

    private final String TMP_FOLDER = "/tmp";
    private final int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        WebApplicationContext context = new GenericWebApplicationContext();
        Servlet servlet = new DispatcherServlet(context);

        ServletRegistration.Dynamic appServlet = sc.addServlet("mvc", servlet);
        appServlet.setLoadOnStartup(1);

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                TMP_FOLDER,
                MAX_UPLOAD_SIZE,
                MAX_UPLOAD_SIZE * 2L,
                MAX_UPLOAD_SIZE / 2);

        appServlet.setMultipartConfig(multipartConfigElement);
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
