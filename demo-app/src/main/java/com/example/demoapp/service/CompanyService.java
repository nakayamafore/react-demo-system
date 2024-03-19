package com.example.demoapp.service;

import static com.example.demoapp.common.util.Messages.Items.COMPANY_CODE;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demoapp.common.exception.ConflictException;
import com.example.demoapp.common.exception.NotFoundException;
import com.example.demoapp.common.util.Messages;
import com.example.demoapp.model.CompanyData;
import com.example.demoapp.repository.CompanyRepository;
import com.example.demoapp.tables.pojos.CompanyEntity;
import lombok.RequiredArgsConstructor;

/**
 * 企業情報サービス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

  private final CompanyRepository companyRepository;
  private final ModelMapper mapper;

  /**
   * 指定の企業情報を登録する
   * 
   * @param companyData 企業情報
   */
  public void register(CompanyData companyData) {
    final boolean companyExists = companyRepository.exists(companyData.getCompanyCode());
    if (companyExists) {
      throw new ConflictException(Messages.IS_CONFLICT.message(COMPANY_CODE));
    }
    companyRepository.save(mapper.map(companyData, CompanyEntity.class));
  }

  /**
   * 企業情報を全取得する
   * 
   * @return 企業情報リスト
   */
  @Transactional(readOnly = true)
  public List<CompanyEntity> findByAll() {
    return companyRepository.findAll();
  }

  /**
   * 指定の企業コードで対応する企業情報を取得する
   * 
   * @param companyCode 企業コード
   * @return 企業情報
   */
  @Transactional(readOnly = true)
  public CompanyEntity findByCompanyCode(String companyCode) {
    final List<CompanyEntity> companyEntitys = companyRepository
        .findByCompanyCode(companyCode);
    if (companyEntitys.isEmpty()) {
      throw new NotFoundException(Messages.IS_NOT_EXIST.message(COMPANY_CODE));
    }
    return companyEntitys.get(0);
  }

  /**
   * 指定の企業情報で更新する
   * 
   * @param companyData 企業情報
   */
  public void update(CompanyData companyData) {
    final List<CompanyEntity> companyEntitys = companyRepository
        .findByCompanyCode(companyData.getCompanyCode());
    if (companyEntitys.isEmpty()) {
      throw new NotFoundException(Messages.IS_NOT_EXIST.message(COMPANY_CODE));
    }
    companyRepository.save(mapper.map(companyData, CompanyEntity.class));
  }

  /**
   * 指定の企業コードに対応する企業情報を削除する
   * 
   * @param companyCode 企業コード
   */
  public void delete(String companyCode) {
    companyRepository.delete(companyCode);
  }
}
