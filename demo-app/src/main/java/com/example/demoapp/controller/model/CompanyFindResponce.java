package com.example.demoapp.controller.model;

import lombok.Data;

/**
 * 企業情報取得レスポンス
 */
@Data
public class CompanyFindResponce {
  // 企業コード
  private String companyCode;
  // 企業名
  private String bizName;
  // 企業所在地
  private String bizPlace;
}
