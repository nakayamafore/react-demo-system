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
import com.example.demoapp.controller.model.CompanyDeleteRequest;
import com.example.demoapp.controller.model.CompanyFindAllResponce;
import com.example.demoapp.controller.model.CompanyFindResponce;
import com.example.demoapp.controller.model.CompanyRegisterRequest;
import com.example.demoapp.controller.model.CompanyUpdateRequest;
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
    final var data = mapper.map(request, CompanyData.class);
    companyService.register(data);
  }

  /**
   * 企業情報全取得
   * 
   * @param request 企業情報取得リクエスト
   */
  @GetMapping
  public CompanyFindAllResponce findByAll() {
    final List<CompanyEntity> companyEntitys = companyService.findByAll();
    return CompanyFindAllResponce.builder().companyEntitys(companyEntitys).build();
  }

  /**
   * 企業情報取得
   * 
   * @param request 企業情報取得リクエスト
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
    final var data = mapper.map(request, CompanyData.class);
    companyService.update(data);
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
