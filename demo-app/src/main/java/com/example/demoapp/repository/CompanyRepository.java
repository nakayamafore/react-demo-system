package com.example.demoapp.repository;

import static com.example.demoapp.Tables.COMPANY;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.example.demoapp.tables.pojos.CompanyEntity;
import com.example.demoapp.tables.records.CompanyRecord;
import lombok.RequiredArgsConstructor;

/**
 * 企業情報リポジトリ
 */
@Repository
@RequiredArgsConstructor
public class CompanyRepository {

  private final DSLContext dslContext;

  /**
   * 指定の企業コードに一致する企業情報を取得する
   * 
   * @param companyCode 企業コード
   * @return 企業情報レコード
   */
  public List<CompanyEntity> findByCompanyCode(String companyCode) {
    return dslContext
        .selectFrom(COMPANY)
        .where(COMPANY.COMPANY_CODE.eq(companyCode))
        .fetchInto(CompanyEntity.class);
  }

  /**
   * 全ての企業情報を取得する
   * 
   * @return 企業情報一覧
   */
  public List<CompanyEntity> findAll() {
    return dslContext.selectFrom(COMPANY).fetchInto(CompanyEntity.class);
  }

  /**
   * 指定の企業情報を保存する
   * 存在する場合は上書きする
   * 
   * @param companyEntity 企業情報
   */
  public void save(CompanyEntity companyEntity) {
    final List<CompanyEntity> result = findByCompanyCode(companyEntity.getCompanyCode());
    if (result.isEmpty()) {
      final CompanyRecord record = dslContext.newRecord(COMPANY);
      record.setCompanyCode(companyEntity.getCompanyCode());
      record.setBizName(companyEntity.getBizName());
      record.setBizPlace(companyEntity.getBizPlace());
      record.store();
    } else {
      dslContext
          .update(COMPANY)
          .set(COMPANY.BIZ_NAME, companyEntity.getBizName())
          .set(COMPANY.BIZ_PLACE, companyEntity.getBizPlace())
          .where(COMPANY.COMPANY_CODE.eq(companyEntity.getCompanyCode()))
          .execute();
    }
  }

  /**
   * 指定の企業コードに対応する企業情報を削除する
   * 
   * @param companyCode 企業コード
   */
  public void delete(String companyCode) {
    dslContext
        .deleteFrom(COMPANY)
        .where(COMPANY.COMPANY_CODE.eq(companyCode))
        .execute();
  }

  /**
   * 指定の企業コードが存在するか確認する
   * 
   * @param companyCode 企業コード
   * @return true: 存在する, false: 存在しない
   */
  public boolean exists(String companyCode) {
    return dslContext
        .selectOne()
        .whereExists(
            dslContext
                .selectFrom(COMPANY)
                .where(COMPANY.COMPANY_CODE.eq(companyCode)))
        .fetchOptional()
        .isPresent();
  }
}
