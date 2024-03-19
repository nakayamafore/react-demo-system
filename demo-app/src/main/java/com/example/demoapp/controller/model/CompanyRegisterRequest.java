package com.example.demoapp.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 企業登録リクエスト
 */
@Data
public class CompanyRegisterRequest {
  // 企業コード
  @Size(min = 10, max = 10)
  @Pattern(regexp = "^[a-zA-Z0-9]*$")
  @NotBlank
  private String companyCode;

  // 企業名
  @Size(max = 50)
  @NotBlank
  private String bizName;

  // 企業所在地
  @Size(max = 50)
  @NotBlank
  private String bizPlace;
}
