package com.example.demoapp.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 企業情報取得リクエスト
 */
@Data
public class CompanyFindRequest {
  // 企業コード
  @Size(min = 10, max = 10)
  @Pattern(regexp = "^[a-zA-Z0-9]*$")
  @NotBlank
  private String companyCode;

}
