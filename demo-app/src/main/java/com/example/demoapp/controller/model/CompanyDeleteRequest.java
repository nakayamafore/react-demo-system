package com.example.demoapp.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 企業情報削除リクエスト
 */
@Data
public class CompanyDeleteRequest {
  // 企業コード
  @Size(min = 10, max = 10)
  @Pattern(regexp = "^[a-zA-Z0-9]*$")
  @NotBlank
  private String companyCode;
}
