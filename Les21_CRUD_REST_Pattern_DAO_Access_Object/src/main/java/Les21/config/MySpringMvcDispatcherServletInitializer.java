package Les21.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //Данный класс - замена web.xml и он обязательно должен наследоваться от класса
    // AbstractAnnotationConfigDispatcherServletInitializer и реализовывать его методы
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class}; //Локация спринг конфигурации
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //Посылаем все запросы от пользователя на DispatcherServlet
    }
}
