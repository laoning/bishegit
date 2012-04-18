/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;

/**
 * CrmCcSkcsCmnTestクラスのテストケースです。
 */
@RunWith(BizIntegralTest.class)
public class CrmCcSkcsCmnDaoTest {
    /**
     * テスト対象
     */
    private CrmCcSkcsCmnDao crmCcSkcsCmnDao;

    /**
     * 住所選択ダイアログ一覧の処理のテスト<br>
     */
    @Test
    public void testFindByAddSelDialogCriteria() {
        AddressSelectDialogCriteriaDto dto =
            new AddressSelectDialogCriteriaDto();
        dto.searchBaseDate = DateUtil.parse("201008019", "yyyyMMdd");
        dto.tdfkAddress = "北海道";
        dto.skcsCd = "661";
        dto.tdfkCd = "01";
        dto.setLimit(10);
        dto.setOffset(0);

        PagingResult<CrmCcSkcsCmn> result =
            crmCcSkcsCmnDao.findByAddSelDialogCriteria(dto);
        assertEquals(5, result.size());
    }
}
