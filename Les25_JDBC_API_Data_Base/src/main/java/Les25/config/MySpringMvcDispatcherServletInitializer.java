package Les25.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
        //Метод onStartup запускается при запуске спринг приложения, а в нем выполняется приватный
        // метод  registerHiddenFieldFilter, где уже и добавляется фильтр HiddenHttpMethodFilter().
        // При том мы сами не реализуем фильтр, он уже есть в Spring
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
        //Данный фильтр смотрит на значение скрытого поля _method, смотрит какой там HTTP метод
        // находится и перенаправлять метод и скрытого поля на нужный метод контроллера
    }
}