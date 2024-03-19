/*
 * This file is generated by jOOQ.
 */
package com.example.demoapp.tables.records;


import com.example.demoapp.tables.Company;
import com.example.demoapp.tables.pojos.CompanyEntity;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class CompanyRecord extends UpdatableRecordImpl<CompanyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>company.company_id</code>.
     */
    public void setCompanyId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>company.company_id</code>.
     */
    public Long getCompanyId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>company.company_code</code>.
     */
    public void setCompanyCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>company.company_code</code>.
     */
    public String getCompanyCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>company.biz_name</code>.
     */
    public void setBizName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>company.biz_name</code>.
     */
    public String getBizName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>company.biz_place</code>.
     */
    public void setBizPlace(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>company.biz_place</code>.
     */
    public String getBizPlace() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CompanyRecord
     */
    public CompanyRecord() {
        super(Company.COMPANY);
    }

    /**
     * Create a detached, initialised CompanyRecord
     */
    public CompanyRecord(Long companyId, String companyCode, String bizName, String bizPlace) {
        super(Company.COMPANY);

        setCompanyId(companyId);
        setCompanyCode(companyCode);
        setBizName(bizName);
        setBizPlace(bizPlace);
        resetChangedOnNotNull();
    }

    /**
     * Create a detached, initialised CompanyRecord
     */
    public CompanyRecord(CompanyEntity value) {
        super(Company.COMPANY);

        if (value != null) {
            setCompanyId(value.getCompanyId());
            setCompanyCode(value.getCompanyCode());
            setBizName(value.getBizName());
            setBizPlace(value.getBizPlace());
            resetChangedOnNotNull();
        }
    }
}
