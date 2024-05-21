package Les21.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("Les21")
@EnableWebMvc // Замена тега <mvc:annotation-driven/> из файла applicationContextMVC.xml,
// которая включает Spring MVC
    public class SpringConfig implements WebMvcConfigurer {
    //Интерфейс WebMvcConfigurer реализуется, когда нужно настроить Spring MVC. В данном
    // случае мы хотим вместо стандартного шаблонизатора использовать шаблонизатор Thymeleaf,
    // поэтому вы реализуем интерфейс в данном конфигурационном файле и реализуем его метод
    // в котором задается шаблонизатор (в данном случае Thymeleaf)
        private final ApplicationContext applicationContext;
    //Внедрение applicationContext с помощью аннотации @Autowired в бин templateResolver(),
    // чтобы настроить Thymeleaf
        @Autowired
        public SpringConfig(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }
        @Bean
        public SpringResourceTemplateResolver templateResolver() {
            SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
            templateResolver.setApplicationContext(applicationContext);
            templateResolver.setPrefix("/WEB-INF/views/"); //Папка где будут лежать представления
            templateResolver.setSuffix(".html"); // Задаем расширения этих представлений
            return templateResolver;
        }
        @Bean
        public SpringTemplateEngine templateEngine() {
            SpringTemplateEngine templateEngine = new SpringTemplateEngine();
            templateEngine.setTemplateResolver(templateResolver());
            templateEngine.setEnableSpringELCompiler(true);
            return templateEngine;
        }
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            ThymeleafViewResolver resolver = new ThymeleafViewResolver();
            resolver.setTemplateEngine(templateEngine());
            registry.viewResolver(resolver);
        }
    }

