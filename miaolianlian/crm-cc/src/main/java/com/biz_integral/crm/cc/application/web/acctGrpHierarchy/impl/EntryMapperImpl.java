/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrpHierarchy.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.biz_integral.crm.cc.application.web.acctGrpHierarchy.EntryInitializeRequestModel;
import com.biz_integral.crm.cc.application.web.acctGrpHierarchy.EntryInitializeResponseModel;
import com.biz_integral.crm.cc.application.web.acctGrpHierarchy.EntryMapper;
import com.biz_integral.crm.cc.application.web.acctGrpHierarchy.EntryRequestModel;
import com.biz_integral.crm.cc.domain.dto.AcctGrpHierarchyDto;
import com.biz_integral.crm.cc.domain.dto.NodeDto;
import com.biz_integral.crm.cc.domain.entity.CrmCcAcctGrpIncAth;
import com.biz_integral.crm.cc.domain.logic.AcctGrpHierarchyLogic;
import com.biz_integral.crm.cc.domain.logic.ParameterLogic;
import com.biz_integral.crm.cc.domain.types.FileKind;
import com.biz_integral.crm.cc.domain.util.FileUtil;
import com.biz_integral.crm.sp.application.web.salesPlanList.ExportFileRequestModel;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.service.domain.master.BizConfigurationProvider;

public class EntryMapperImpl implements EntryMapper {

    /**
     * {@link ParameterLogic}です
     */
    @Resource
    private ParameterLogic parameterLogic;

    /**
     * {@link ContextContainer コンテキストコンテナー}
     */
    @Resource
    protected ContextContainer contextContainer;

    /**
     * {@link BizConfigurationProvider}のDaoクラスです。
     */
    @Resource
    protected BizConfigurationProvider bizConfigurationProvider;

    @Override
    public AcctGrpHierarchyDto createSearchModel(
            EntryInitializeRequestModel requestModel) {
        AcctGrpHierarchyDto dto = new AcctGrpHierarchyDto();

        dto = setDtoCmnItem(dto);

        // エクスポートファイル名
        dto.fileName = getExportFileName();

        return dto;
    }

    @Override
    public EntryInitializeResponseModel createResponseModel(
            List<AcctGrpHierarchyDto> list) {

        EntryInitializeResponseModel responseModel =
            new EntryInitializeResponseModel();

        List<NodeDto> treeNodeList = new ArrayList<NodeDto>();

        String tmp = "";
        // ツリーモデルにセット
        for (AcctGrpHierarchyDto dto : list) {
            if (dto.rootFlag) {
                // ルートグループ
                NodeDto nodeDto = new NodeDto();
                nodeDto.setLabel(dto.crmAccountGroupName);
                nodeDto.setName(dto.path);
                nodeDto.setParent(null);
                nodeDto.setParentCd(dto.crmAccountGroupCd);
                nodeDto.setType("category");
                treeNodeList.add(nodeDto);
            } else if (dto.depth == 1) {
                // 子グループ
                NodeDto nodeDto = new NodeDto();
                nodeDto.setLabel(dto.crmAccountGroupName);
                nodeDto.setName(dto.path);
                tmp = StringUtils.stripEnd(dto.path, dto.crmAccountGroupCd);
                tmp =
                    StringUtils.stripEnd(
                        tmp,
                        AcctGrpHierarchyLogic.PATH_SPLIT_CHAR);
                nodeDto.setParent(tmp);
                nodeDto.setType("category");
                nodeDto.setParentCd(dto.crmAccountGroupCd);
                treeNodeList.add(nodeDto);
            }
        }

        responseModel.treeNodeList = treeNodeList;

        return responseModel;
    }

    @Override
    public EntryInitializeResponseModel createRespMdlFromLower(
            List<AcctGrpHierarchyDto> list) {

        EntryInitializeResponseModel responseModel =
            new EntryInitializeResponseModel();

        List<NodeDto> treeNodeList = new ArrayList<NodeDto>();

        // 末端グループを取得

        // ツリーモデルにセット
        for (AcctGrpHierarchyDto dto : list) {
            if (dto.rootFlag) {
                // ルートグループ
                NodeDto nodeDto = new NodeDto();
                nodeDto.setLabel(dto.crmAccountGroupName);
                nodeDto.setName(dto.crmAccountGroupCd);
                nodeDto.setParent("");
                nodeDto.setType("category");
                nodeDto.setParentCd(dto.crmAccountGroupCd);
                treeNodeList.add(nodeDto);
            } else if (dto.depth == 1) {
                // 子グループ
                NodeDto nodeDto = new NodeDto();
                nodeDto.setLabel(dto.crmAccountGroupName);
                nodeDto.setName(dto.crmAccountGroupCd);
                nodeDto.setParent(dto.parentCrmAccountGroupCd);
                nodeDto.setType("category");
                nodeDto.setParentCd(dto.parentCrmAccountGroupCd);
                treeNodeList.add(nodeDto);
            }
        }

        responseModel.treeNodeList = treeNodeList;

        return responseModel;
    }

    @Override
    public AcctGrpHierarchyDto createModel(EntryRequestModel requestModel) {
        AcctGrpHierarchyDto dto = new AcctGrpHierarchyDto();

        dto = setDtoCmnItem(dto);

        // 移動するグループ
        dto.path = requestModel.path;

        // 移動先グループ
        dto.targetPath = requestModel.targetPath;

        // 移動先がルート
        dto.isRoot = requestModel.isRoot;

        // 移動先がTOP
        dto.isTop = requestModel.isTop;

        return dto;
    }

    @Override
    public CrmCcAcctGrpIncAth entityMapper(CrmCcAcctGrpIncAth entity) {

        if (entity == null) {
            return entity;
        }
        entity.setCompanyCd(contextContainer
            .getCurrentFeatureContext()
            .getCompanyCode());
        entity.setCreateDate(DateUtil.nowDate());
        entity.setStartDate(bizConfigurationProvider.getStartDate());
        entity.setEndDate(bizConfigurationProvider.getEndDate());
        entity.setDeleteFlag(false);
        entity.setCreateUserCd(contextContainer.getUserContext().getUserID());
        entity.setRootFlag(false);
        entity.setSortKey(1L);

        return entity;
    }

    @Override
    public AcctGrpHierarchyDto createExportModel(ExportFileRequestModel model) {

        AcctGrpHierarchyDto dto = new AcctGrpHierarchyDto();

        dto = setDtoCmnItem(dto);

        dto.fileKind = FileKind.getEnum(model.fileType);

        dto.fileName = getExportFileName();

        if (FileKind.EXCEL == dto.fileKind) {
            dto.fileName = dto.fileName + FileUtil.EXCEL_FILE;
        } else {
            dto.fileName = dto.fileName + FileUtil.CSV_FILE;
        }

        return dto;
    }

    private AcctGrpHierarchyDto setDtoCmnItem(AcctGrpHierarchyDto dto) {
        // 会社コード
        dto.companyCd =
            contextContainer.getCurrentFeatureContext().getCompanyCode();
        // ロケールID
        dto.localeId = contextContainer.getUserContext().getLocaleID();
        // システム日付
        dto.sysDate =
            DateUtil.parse(
                DateUtil.nowDateString(DateUtil.DATE_FORMAT),
                DateUtil.DATE_FORMAT);
        return dto;
    }

    private String getExportFileName() {
        Object fnm = parameterLogic.getEntity("CRMCC0019");
        if (fnm != null) {
            return fnm.toString();
        }
        return null;
    }
}
