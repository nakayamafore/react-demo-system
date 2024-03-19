package com.example.demoapp.common.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

/**
 * 特定のビジネスロジックが競合状態にあることを示す例外
 * 
 * <pre>
 * リソースが既に存在するなど、操作が競合状態の状況で利用されます。
 * 例えば、ユニークキー制約違反でinsertしようとした場合など
 * </pre>
 */
@Getter
public class ConflictException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final HttpStatus status;
  private final String message;

  public ConflictException(String message) {
    super(message);
    this.status = HttpStatus.CONFLICT;
    this.message = message;
  }
}
