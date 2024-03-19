package com.example.demoapp.common.exception;

import org.springframework.http.HttpStatus;
import lombok.Getter;

/**
 * 存在しないことを示す例外
 * 
 * <pre>
 * リソースが存在しないことを示すために利用されます。
 * 例えば、指定のキーに対応するレコードが存在しない時など
 * </pre>
 */
@Getter
public class NotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final HttpStatus status;
  private final String message;

  public NotFoundException(String message) {
    super(message);
    this.status = HttpStatus.NOT_FOUND;
    this.message = message;
  }
}
