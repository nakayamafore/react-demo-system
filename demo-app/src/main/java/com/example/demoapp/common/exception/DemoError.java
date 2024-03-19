package com.example.demoapp.common.exception;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponseException;

/**
 * アプリケーション共通エラー管理
 */
@Component
public class DemoError {

  // アプリケーションのベースURL
  @Value("${app.baseUrl}")
  private String baseUrl;

  /**
   * 指定されたHTTPステータスとメッセージに基づいてエラーレスポンスを生成する
   * 
   * @param status HTTPステータス
   * @param message エラーメッセージ
   * @return ErrorResponseException エラーレスポンス情報を持つ例外オブジェクト
   */
  public ErrorResponseException of(HttpStatus status, String message) {
    final var problemDetail = ProblemDetail.forStatus(status);
    problemDetail.setType(URI.create("%s/code-%s".formatted(baseUrl, status.value())));
    problemDetail.setDetail(message);
    return new ErrorResponseException(status, problemDetail, null);
  }
}
