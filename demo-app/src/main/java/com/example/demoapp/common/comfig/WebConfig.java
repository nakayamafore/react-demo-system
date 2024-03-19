package com.example.demoapp.common.comfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Webアプリケーション設定
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * CORS設定
   * 
   * @param registry CORS設定を管理するCorsRegistryオブジェクト
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/api/**")
        .allowedOrigins("http://localhost:5173")
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowedHeaders("*")
        .allowCredentials(true);
  }
}
