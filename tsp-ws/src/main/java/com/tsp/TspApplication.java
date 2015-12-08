package com.tsp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import com.tsp.configuration.WebSecurityConfig;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"com.tsp"})
public class TspApplication extends AbstractDispatcherServletInitializer implements WebApplicationInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TspApplication.class, args);
    }
    
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        //registerProxyFilter(servletContext, "springSecurityFilterChain");
        //registerProxyFilter(servletContext, "oauth2ClientContextFilter");
    }
    
    private void registerProxyFilter(ServletContext servletContext, String name) {
      DelegatingFilterProxy filter = new DelegatingFilterProxy(name);
      filter.setContextAttribute("org.springframework.web.servlet.FrameworkServlet.CONTEXT.dispatcher");
      servletContext.addFilter(name, filter).addMappingForUrlPatterns(null, false, "/*");
  }


    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }


    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
    
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebSecurityConfig.class, ControllerConfig.class);
        return context;
    }
}
