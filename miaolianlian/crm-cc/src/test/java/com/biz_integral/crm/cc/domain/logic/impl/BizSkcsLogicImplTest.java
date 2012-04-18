/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seasar.framework.unit.annotation.PostBindFields;

import com.biz_integral.crm.cc.domain.dao.CrmCcSkcsCmnDao;
import com.biz_integral.crm.cc.domain.dto.AddressSelectDialogCriteriaDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcSkcsCmn;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.persistence.pager.PagingResult;
import com.biz_integral.unit.BizIntegralTest;
import com.biz_integral.unit.support.MockitoComponent;

/**
 * 住所選択ダイアログ一覧ロジックのテストクラスです。<br/>
 */
@RunWith(BizIntegralTest.class)
public class BizSkcsLogicImplTest {

    /**
     * BizSkcsLogicImpl
     */
    private BizSkcsLogicImpl bizSkcsLogicImpl;

    /**
     * Mockitoを利用し、生成したモックオブジェクトです。
     */
    @MockitoComponent(componentName = "crmCcSkcsCmnDao")
    private CrmCcSkcsCmnDao crmCcSkcsCmnDao;

    /**
     * AddressSelectDialogCriteriaDto
     */
    AddressSelectDialogCriteriaDto dto = new AddressSelectDialogCriteriaDto();

    /**
     * Mockitoで作成したDAOへメソッド呼び出しに対する結果を登録します。
     */
    @PostBindFields
    public void postBindFields() {

        PagingResult<CrmCcSkcsCmn> result = new PagingResult<CrmCcSkcsCmn>();

        for (int i = 0; i < 10; i++) {
            CrmCcSkcsCmn bizSkcs = new CrmCcSkcsCmn();

            bizSkcs.setEndDate(DateUtil.parse("20990101" + i, "yyyyMMdd"));
            bizSkcs.setStartDate(DateUtil.parse("20100101" + i, "yyyyMMdd"));
            bizSkcs.setTdfkCd("0" + i);
            bizSkcs.setTdfkAddress("北海道" + i);
            bizSkcs.setSkcsCd("00" + i);
            bizSkcs.setSkcsAddress("川上郡標茶町" + i);
            result.add(bizSkcs);
        }

        when(crmCcSkcsCmnDao.findByAddSelDialogCriteria(dto))
            .thenReturn(result);
    }

    /**
     * 住所選択ダイアログ一件を取得するのテスト。
     */
    @Test
    public void testGetEntityList() {

        PagingResult<CrmCcSkcsCmn> result = bizSkcsLogicImpl.getEntityList(dto);

        // メソッド呼び出しの確認
        verify(crmCcSkcsCmnDao).findByAddSelDialogCriteria(dto);

        for (int i = 0; i < 10; i++) {
            CrmCcSkcsCmn bizSkcs = result.getResultList().get(i);

            assertEquals("00" + i, bizSkcs.getSkcsCd());

            assertEquals("川上郡標茶町" + i, bizSkcs.getSkcsAddress());

            assertEquals(DateUtil.parse("20100101" + i, "yyyyMMdd"), bizSkcs
                .getStartDate());

            assertEquals(DateUtil.parse("20990101" + i, "yyyyMMdd"), bizSkcs
                .getEndDate());

            assertEquals("0" + i, bizSkcs.getTdfkCd());

            assertEquals("北海道" + i, bizSkcs.getTdfkAddress());
        }
    }
}
