package com.example.demoapp.controller.model;

import java.util.List;
import com.example.demoapp.tables.pojos.CompanyEntity;
import lombok.Builder;
import lombok.Data;

/**
 * 企業情報一覧取得レスポンス
 */
@Data
@Builder
public class CompanyFindAllResponce {
  // 企業情報一覧
  private List<CompanyEntity> companyEntitys;
}
