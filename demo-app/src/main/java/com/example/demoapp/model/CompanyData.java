package com.example.demoapp.model;

import lombok.Data;

/**
 * 企業情報モデル
 */
@Data
public class CompanyData {
  // 企業コード
  private String companyCode;
  // 企業名
  private String bizName;
  // 企業所在地
  private String bizPlace;
}
