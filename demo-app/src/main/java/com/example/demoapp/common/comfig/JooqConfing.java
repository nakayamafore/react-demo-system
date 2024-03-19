package com.example.demoapp.common.comfig;

import javax.sql.DataSource;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import lombok.RequiredArgsConstructor;

/**
 * JOOQ設定
 */
@Configuration
@RequiredArgsConstructor
public class JooqConfing {

  private final DataSource dataSource;

  /**
   * JOOQの初期設定
   * 
   * @return JOOQの設定済オブジェクト
   */
  @Bean
  DefaultConfiguration defaultConfiguration() {
    // JOOQの実行ログを有効にするための設定
    final var settings = new Settings().withExecuteLogging(true);
    final var configuration = new DefaultConfiguration();
    configuration
        // トランザクション対応のデータソースプロキシを設定
        .set(new TransactionAwareDataSourceProxy(dataSource))
        // MYSQL設定
        .set(SQLDialect.MYSQL)
        .set(settings);
    return configuration;
  }
}
