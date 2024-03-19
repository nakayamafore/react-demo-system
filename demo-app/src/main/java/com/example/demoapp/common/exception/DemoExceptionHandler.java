package com.example.demoapp.common.exception;

import static java.util.stream.Collectors.toMap;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.demoapp.common.util.DemoError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * すべてのコントローラーで発生した例外に対して統一された形式のエラーレスポンスを返却する
 */
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class DemoExceptionHandler extends ResponseEntityExceptionHandler {

  private final DemoError demoError;

  /**
   * {@link NotFoundException}が発生した場合に呼び出されるハンドラメソッド
   *
   * @param exception 発生したNotFoundException
   * @return エラー情報を含むレスポンスエンティティ
   */
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ProblemDetail> handleNotFoundException(
      NotFoundException exception) {
    log.error(exception.getMessage());
    ErrorResponseException errorResponseException = demoError
        .of(exception.getStatus(), exception.getMessage());
    return new ResponseEntity<>(
        errorResponseException.getBody(), errorResponseException.getStatusCode());
  }

  /**
   * {@link ConflictException}が発生した場合に呼び出されるハンドラメソッド
   *
   * @param exception 発生したConflictException
   * @return エラー情報を含むレスポンスエンティティ
   */
  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ProblemDetail> handleConflictException(
      ConflictException exception) {
    log.error(exception.getMessage());
    ErrorResponseException errorResponseException = demoError
        .of(exception.getStatus(), exception.getMessage());
    return new ResponseEntity<>(
        errorResponseException.getBody(), errorResponseException.getStatusCode());
  }

  /**
   * {@link SQLIntegrityConstraintViolationException}が発生した場合に呼び出されるハンドラメソッド
   * キー重複などのデータベース整合性制約違反が原因でこの例外が発生した場合、
   * 409 Conflictステータスコードと共にエラーメッセージをクライアントに返却する。
   * 
   * @param ex 発生した例外
   * @return エラー情報を含むレスポンスエンティティ(409Conflictで返却)
   */
  @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
  public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(
      SQLIntegrityConstraintViolationException ex) {
    log.error("キー重複エラーが発生しました。", ex);

    final Map<String, Object> body = new HashMap<>();
    body.put("status", HttpStatus.CONFLICT.value());
    body.put("error", "Conflict");
    body.put("message", "キー重複エラー");
    return new ResponseEntity<>(body, HttpStatus.CONFLICT);
  }

  /**
   * リクエストデータのバリデーションエラーを処理する
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    final Map<String, String> fieldErrors = ex
        .getBindingResult().getFieldErrors().stream()
        .collect(toMap(FieldError::getField, FieldError::getDefaultMessage));
    final String errorMessage = "バリデーションエラーが発生しました。リクエスト: {}, エラー: {}";
    log.error(errorMessage, request.getContextPath(), fieldErrors);

    final Map<String, Object> body = new HashMap<>();
    body.put("status", status.value());
    body.put("instance", request.getContextPath());
    body.put("errors", fieldErrors);
    return new ResponseEntity<>(body, headers, status);
  }
}
