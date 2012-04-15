/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.util.List;

import com.biz_integral.crm.cc.application.web.accountManage.EntryAccountChargeDeptResponseModel;
import com.biz_integral.crm.cc.application.web.accountManage.EntryAccountChargeUserResponseModel;
import com.biz_integral.crm.cc.application.web.acctGrp.AcctGrpAthModel;
import com.biz_integral.crm.cc.application.web.acctGrp.EntryAccountModel;
import com.biz_integral.crm.cc.domain.dto.AcctGrpDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchCriteriaDto;
import com.biz_integral.crm.cc.domain.dto.AcctGrpSearchResultDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrp;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeDept;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpChargeUser;
import com.biz_integral.extension.mvc.maskat.beans.PagingResponse;
import com.biz_integral.service.persistence.pager.PagingResult;

/**
 * アカウントグループのロジックです。
 */
public interface CrmAcctGrpLogic {

    /**
     * アカウントグループ一覧を取得します。
     * 
     * @param dto
     *            アカウントグループ一覧検索条件モデル
     * 
     * @return アカウントグループ一覧エンティティ
     * 
     */
    public abstract PagingResult<AcctGrpSearchResultDto> getAcctGrpList(
            AcctGrpSearchCriteriaDto dto);

    /**
     * CRMアカウントグループを登録します。
     * 
     * @param CrmCcAcctGrp
     *            CRMアカウントグループEntity
     */
    public abstract void create(CrmCcAcctGrp crmCcAcctGrp);

    /**
     * CRMアカウントグループを更新します。
     * 
     * @param CrmCcAcctGrp
     *            CRMアカウントグループEntity
     */
    public abstract void update(CrmCcAcctGrp crmCcAcctGrp);

    /**
     * CRMアカウントグループを削除します。
     * 
     * @param String
     *            CRMアカウントグループコード
     * @return なし
     */
    public abstract void delete(CrmCcAcctGrp crmCcAcctGrp);

    /**
     * CRMアカウントグループ担当組織を登録します。
     * 
     * @param CrmCcAcctGrp
     *            CRMアカウントグループDTO
     */
    public abstract void createDept(List<CrmCcAcctGrpChargeDept> CrmCcAcctGrp,
            String crmAccountGroupCd);

    /**
     * CRMアカウントグループ担当ユーザを登録します。
     * 
     * @param CrmCcAcctGrp
     *            CRMアカウントグループDTO
     */
    public abstract void createUser(List<CrmCcAcctGrpChargeUser> CrmCcAcctGrp,
            String crmAccountGroupCd);

    /**
     * CRMアカウントグループ分類を登録します。
     * 
     * @param CrmCcAcctGrp
     *            CRMアカウントグループDTO
     */
    public abstract void createClassAth(String crmCcAcctGrpClassAth,
            String crmAccountGroupCd);

    /**
     * CRMアカウントグループ所属を検索します。
     * 
     * @param String
     *            CRMアカウントグループコード
     * @return List<AcctGrpAthModel>
     */
    public abstract PagingResponse<AcctGrpAthModel> searchAcctGrpAthInfo(
            AcctGrpDto dto);

    /**
     * アカウントグループ情報を取得
     * 
     * @param String
     *            CRMアカウントグループコード
     * @return
     */
    public abstract CrmCcAcctGrp getAcctGrpInfo(String crmAccountGroupCd);

    /**
     * アカウントグループ所属情報を取得
     * 
     * @param String
     *            CRMアカウントグループコード
     * @return なし
     */
    public abstract String getAcctGrpClassInfo(String crmAccountGroupCd);

    /**
     * アカウントグループ担当組織情報を取得
     * 
     * @param crmAccountCheckChargeDto
     *            CRMアカウントグループ担当チェックモデル
     * @return なし
     */
    public abstract List<EntryAccountChargeDeptResponseModel> getAcctGrpChargeDeptInfo(
            String crmAccountGroupCd);

    /**
     * アカウントグループ担当ユーザ情報を取得
     * 
     * @param String
     *            CRMアカウントグループコード
     * @return なし
     */
    public abstract List<EntryAccountChargeUserResponseModel> getAcctGrpChargeUserInfo(
            String crmAccountGroupCd);

    /**
     * アカウント所属追加
     * 
     * @param String
     *            CRMアカウントグループコード
     * @return なし
     */
    public abstract void addAccount(List<EntryAccountModel> accountList,
            String crmAccountGroupCd);

    /**
     * アカウントグループの削除を行いします。
     * 
     * @param EntryRequestModel
     *            レスポンスモデル
     * @return なし
     */
    public abstract void deleteAccount(String crmAccountGroupCd,
            String accountCd, String termCd);

    public abstract String getVersionNo(String crmAccountGroupCd);

}
