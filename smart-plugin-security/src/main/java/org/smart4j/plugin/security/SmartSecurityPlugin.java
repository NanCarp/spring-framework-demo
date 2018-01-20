package org.smart4j.plugin.security;

import java.util.Set;

/**
 * Created by nanca on 1/20/2018.
 */
public class SmartSecurityPlugin implements ServletContainerInitializer {

    public void onStartup(Set<Class<?>> handlesTypes, ServletContext
                          servletContext) throws ServletException {
        // 设置初始化参数
        servletContext.setInitParameter("shiroConfigLocations", "classpath:smart-security.ini");
        // 注册 Listener
        servletContext.addListener(EnvironmentLoaderListener.class);
        // 注册 Filter
        FilterRegistration.Dynamic smartSecurityFilter = servletContext.addFilter("SmartSecurityFilter",
                 SmartSecurityFilter.class);
        smartSecurityFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
