package com.example.demoapp.common.exception;

import static java.util.stream.Collectors.toMap;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * コントローラー以降で発生したエラーに対しての共通のエラー形式でレスポンスを返すクラス
 */
@ControllerAdvice
public class DemoExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * リクエストデータのバリデーションエラーを処理する
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    final Map<String, Object> body = new HashMap<>();
    body.put("status", status.value());
    body.put("instance", request.getContextPath());
    final Map<String, String> fieldErrors = ex
        .getBindingResult().getFieldErrors().stream()
        .collect(toMap(FieldError::getField, FieldError::getDefaultMessage));
    body.put("errors", fieldErrors);
    return new ResponseEntity<>(body, headers, status);
  }

  /**
   * キー重複エラー
   * 
   * @param ex 発生した例外
   * @return エラー情報を含むレスポンスエンティティ(409Conflictで返却)
   */
  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(
      SQLIntegrityConstraintViolationException ex) {
    final Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.CONFLICT.value());
    body.put("error", "Conflict");
    body.put("message", "キー重複エラー");
    return new ResponseEntity<>(body, HttpStatus.CONFLICT);
  }
}
