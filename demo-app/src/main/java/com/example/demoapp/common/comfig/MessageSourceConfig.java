package com.example.demoapp.common.comfig;

import java.nio.charset.StandardCharsets;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * メッセージ設定
 */
@Configuration
public class MessageSourceConfig {

  /**
   * メッセージソースの設定
   * 
   * @return 設定済みのMessageSourceオブジェクト
   */
  @Bean
  MessageSource messageSource() {
    final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    // メッセージプロパティファイルのベース名を指定
    messageSource.setBasenames("messages");
    // 文字エンコーディングをUTF-8に設定
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    return messageSource;
  }

  @Bean
  MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
    return new MessageSourceAccessor(messageSource);
  }
}
