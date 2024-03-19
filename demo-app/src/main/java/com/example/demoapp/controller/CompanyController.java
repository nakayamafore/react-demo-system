package com.example.demoapp.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demoapp.form.CompanyDeleteRequest;
import com.example.demoapp.form.CompanyFindAllResponce;
import com.example.demoapp.form.CompanyFindResponce;
import com.example.demoapp.form.CompanyRegisterRequest;
import com.example.demoapp.form.CompanyUpdateRequest;
import com.example.demoapp.model.CompanyData;
import com.example.demoapp.service.CompanyService;
import com.example.demoapp.tables.pojos.CompanyEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

/**
 * 企業情報コントローラ
 */
@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

  private final CompanyService companyService;
  private final ModelMapper mapper;

  /**
   * 企業情報登録
   * 
   * @param request 企業登録リクエスト
   */
  @PostMapping
  public void register(@Validated @RequestBody CompanyRegisterRequest request) {
    final var companyData = mapper.map(request, CompanyData.class);
    companyService.register(companyData);
  }

  /**
   * 企業情報全取得
   * 
   * @return 企業情報全取得レスポンス
   */
  @GetMapping
  public CompanyFindAllResponce findByAll() {
    final List<CompanyEntity> companyEntitys = companyService.findByAll();
    return CompanyFindAllResponce.builder().companyEntitys(companyEntitys).build();
  }

  /**
   * 企業情報取得
   * 
   * @param companyCode 企業コード
   * @return 企業情報取得レスポンス
   */
  @GetMapping("/{companyCode}")
  public CompanyFindResponce findByCompanyCode(
      @PathVariable("companyCode") @NotBlank String companyCode) {
    final CompanyEntity companyEntity = companyService.findByCompanyCode(companyCode);
    return mapper.map(companyEntity, CompanyFindResponce.class);
  }

  /**
   * 企業情報更新
   * 
   * @param request 企業情報更新リクエスト
   */
  @PutMapping
  public void update(@Validated @RequestBody CompanyUpdateRequest request) {
    final var companyData = mapper.map(request, CompanyData.class);
    companyService.update(companyData);
  }

  /**
   * 企業情報削除
   * 
   * @param request 企業情報削除リクエスト
   */
  @DeleteMapping
  public void delete(@Validated @RequestBody CompanyDeleteRequest request) {
    companyService.delete(request.getCompanyCode());
  }
}
