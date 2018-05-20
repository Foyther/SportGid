package android.kfu.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;


@EnableWebMvc
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    public WebInitializer() {
    }

    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext cfg = new AnnotationConfigWebApplicationContext();
        cfg.register(WebConfig.class);
        return cfg;
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext cfg = new AnnotationConfigWebApplicationContext();
        cfg.register(RootConfig.class);
        return cfg;
    }
}
