package com.escom.CreadorPracticas.Config.interceptor;

import com.escom.CreadorPracticas.loggin.LogginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final LogginInterceptor logginInterceptor;

    // Constructor para inyectar el interceptor
    public InterceptorConfig(LogginInterceptor loggingInterceptor) {
        this.logginInterceptor = loggingInterceptor;
    }

    // Registrar el interceptor para rutas específicas
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Registrar el interceptor para la ruta /externos/logistica/getItemsV1
        registry.addInterceptor(logginInterceptor)
                .addPathPatterns("*/**"); // Ruta específica

    }
}
