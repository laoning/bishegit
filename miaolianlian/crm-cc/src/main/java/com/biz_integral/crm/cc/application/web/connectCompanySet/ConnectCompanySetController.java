/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.connectCompanySet;

import javax.annotation.Resource;

import com.biz_integral.crm.cc.domain.context.CrmContext;
import com.biz_integral.crm.cc.domain.dao.CrmCcParameterDao;
import com.biz_integral.crm.cc.domain.dto.ConnectCompanySetDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcParameter;
import com.biz_integral.crm.cc.domain.logic.ConnectCompanySetLogic;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.service.domain.master.department.BizBasicDepartmentManager;

/**
 * 接続会社設定画面のコントローラです。
 */
public class ConnectCompanySetController {

    /**
     *CRM組織コード用のID
     */
    @Resource
    private static final String DEPATMENT_SET_CD_ID = "CRMCC0004";

    /**
     *CRMアプリケーションコンテキスト
     */
    @Resource
    protected CrmContext crmContext;

    /** bizクラス */
    @Resource
    protected BizBasicDepartmentManager bizBasicDepartmentManager;

    /**
     * {@link ConnectCompanySetLogic 接続会社設定Logic}
     */
    @Resource
    protected ConnectCompanySetLogic connectCompanySetLogic;

    /**
     * {@link CrmCcParameter}のDaoクラスです。
     */
    @Resource
    protected CrmCcParameterDao crmCcParameterDao;

    /**
     * 初期化を行います。
     * 
     * @return 画面初期化に利用する項目
     */
    @Execute
    public ConnectCompanySetInitializeResponseModel initialize() {

        ConnectCompanySetInitializeResponseModel model =
            new ConnectCompanySetInitializeResponseModel();

        // 所属会社コードを取得
        String companyCd = crmContext.getCompanyCd();

        // 処理会社一覧を取得
        model.handleCompanies = connectCompanySetLogic.getHandleCompanyList();

        // 処理会社コードをresponseモデルにセット(選択状態の会社コード)
        model.handleCompany = companyCd;

        String userCd = crmContext.getUserCd(); // ユーザコードを取得
        String localeId = crmContext.getLocaleId(); // ロケールIDを取得

        // 会社組織セットコードを取得
        String departmentSetCd =
            crmCcParameterDao.getParameterValue(DEPATMENT_SET_CD_ID, companyCd);

        // 条件モデル作成
        ConnectCompanySetDto connectCompanySetDto = new ConnectCompanySetDto();
        connectCompanySetDto.userCd = userCd;
        connectCompanySetDto.setLocaleId(localeId);
        connectCompanySetDto.setCompanyCd(companyCd);
        connectCompanySetDto.setDepartmentSetCd(departmentSetCd);

        // 所属組織一覧を取得
        model.affiliationDepartments =
            connectCompanySetLogic
                .getAffiliationDepartmentList(connectCompanySetDto);

        // 主所属組織コードを取得
        String mainDepartmentCd = crmContext.getMainDepartmentCd();
        if (mainDepartmentCd == null) {
            mainDepartmentCd =
                connectCompanySetLogic
                    .getMainDepartmentCd(connectCompanySetDto);
        }
        model.mainDepartment = mainDepartmentCd;

        return model;

    }

    /**
	 * 処理会社の変更による処理を行います。
	 * 
	 * @param requesModel
	 *            接続会社設定画面の条件モデル
	 * @return 接続会社設定画面の条件モデル
	 */
    @Execute
    @Validation
    public ConnectCompanySetInitializeResponseModel change(
            ConnectCompanySetChangeRequestModel requesModel) {

        ConnectCompanySetInitializeResponseModel model =
            new ConnectCompanySetInitializeResponseModel();

        // 所属会社コードを取得
        String companyCd = requesModel.handleCompany;

        // 会社組織セットコードを取得
        String departmentSetCd =
            crmCcParameterDao.getParameterValue(DEPATMENT_SET_CD_ID, companyCd);

        String userCd = crmContext.getUserCd(); // ユーザコードを取得
        String localeId = crmContext.getLocaleId(); // ロケールIDを取得

        ConnectCompanySetDto connectCompanySetDto = new ConnectCompanySetDto();
        connectCompanySetDto.userCd = userCd;
        connectCompanySetDto.setLocaleId(localeId);
        connectCompanySetDto.setCompanyCd(companyCd);
        connectCompanySetDto.setDepartmentSetCd(departmentSetCd);

        // 所属組織一覧を取得
        model.affiliationDepartments =
            connectCompanySetLogic
                .getAffiliationDepartmentList(connectCompanySetDto);

        // 主所属組織コードを取得
        model.mainDepartment =
            connectCompanySetLogic.getMainDepartmentCd(connectCompanySetDto);

        return model;
    }

    /**
	 * 登録を行います。
	 * 
	 * @param model
	 *            接続会社設定画面登録用条件モデル
	 */
    @Execute
    @Validation
    public void registration(ConnectCompanySetRegistrationRequestModel model) {
        ConnectCompanySetDto connectCompanySetDto = new ConnectCompanySetDto();
        connectCompanySetDto.setCompanyCd(model.handleCompany);
        connectCompanySetDto.setDepartmentCd(model.mainDepartment);

        // 登録
        connectCompanySetLogic.sessionInfoUpdate(connectCompanySetDto);

    }
}
