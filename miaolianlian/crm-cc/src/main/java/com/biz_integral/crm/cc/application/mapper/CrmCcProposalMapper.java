/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.mapper;

import com.biz_integral.crm.cc.application.controller.common.CrmCcProposalRequestModel;
import com.biz_integral.crm.cc.application.controller.common.CrmCcProposalResponseModel;
import com.biz_integral.crm.cc.domain.dto.CrmPmProposalDto;
import com.biz_integral.crm.pm.domain.entity.CrmPmProposal;

/**
 * 案件コードの一件取得で利用するマッパーです。
 */
public interface CrmCcProposalMapper {

    /**
     * 
     * 案件コード入力された検索条件をビジネスロジックの処理引数DTOに変換します。
     * 
     * @param model
     *            案件コード検索条件モデル
     * @return 案件コードDTO
     */
    public CrmPmProposalDto convert(CrmCcProposalRequestModel model);

    /**
     * エンティティクラスを案件コード検索結果モデルに変換します。
     * 
     * @param crmPmProposal
     *            エンティティクラス
     * @return {@link CrmCcProposalResponseModel}
     */
    public CrmCcProposalResponseModel convert(CrmPmProposal crmPmProposal);
}
