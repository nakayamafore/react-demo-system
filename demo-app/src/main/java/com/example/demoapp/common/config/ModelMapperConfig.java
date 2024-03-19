package com.example.demoapp.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ModelMapperライブラリの設定クラス
 */
@Configuration
public class ModelMapperConfig {
  /**
   * ModelMapperオブジェクトを生成
   * 
   * @return 初期化済のModelMapperオブジェクト
   */
  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
