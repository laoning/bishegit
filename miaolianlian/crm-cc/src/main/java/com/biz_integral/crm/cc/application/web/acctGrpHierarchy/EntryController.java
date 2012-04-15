/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.application.web.acctGrpHierarchy;

import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.biz_integral.crm.cc.application.controller.common.FileExportResponseModel;
import com.biz_integral.crm.cc.domain.dto.AcctGrpHierarchyDto;
import com.biz_integral.crm.cc.domain.logic.AcctGrpHierarchyLogic;
import com.biz_integral.crm.cc.domain.types.ConfirmWay;
import com.biz_integral.crm.sa.application.web.salesActivityFile.IoUploadRequestModel;
import com.biz_integral.crm.sp.application.web.salesPlanList.ExportFileRequestModel;
import com.biz_integral.extension.mvc.maskat.annotaion.Execute;
import com.biz_integral.extension.mvc.maskat.annotaion.Validation;
import com.biz_integral.extension.mvc.maskat.beans.MessageDetailResponse;
import com.biz_integral.extension.mvc.maskat.beans.MessageResponse.MessageType;
import com.biz_integral.foundation.core.message.MessageBuilder;
import com.biz_integral.foundation.core.util.UniqueIdGenerator;
import com.biz_integral.foundation.core.validation.ValidationException;
import com.biz_integral.foundation.core.validation.ValidationResult;
import com.biz_integral.foundation.core.validation.ValidationResults;
import com.biz_integral.service.maskat.service.model.FileDownloadResponseModel;
import com.biz_integral.service.storage.SharedFile;
import com.biz_integral.service.storage.SharedStorageManager;

/**
 * アカウントグループ階層登録／更新画面のコントローラークラスです。<br/>
 */
public class EntryController {

    @Resource
    protected SharedStorageManager sharedStorageManager;

    /**
     * {@link EntryMapper 階層マッパー}
     */
    @Resource
    protected EntryMapper entryMapper;

    /**
     * {@link AcctGrpHierarchyLogic 階層マッパー}
     */
    @Resource
    protected AcctGrpHierarchyLogic acctGrpHierarchyLogic;

    /**
     * 画面の初期化時に利用する情報を取得し、返します。<br/>
     * <p>
     * <ul>
     * <li>アカウントグループ階層を取得します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            画面の初期化時に利用するリクエストモデル
     * @return アカウントグループ階層情報をセットしたレスポンスモデル
     */
    @Execute
    @Validation
    public EntryInitializeResponseModel initialize(
            EntryInitializeRequestModel requestModel) {

        // 検索条件モデルを作成
        AcctGrpHierarchyDto dto = entryMapper.createSearchModel(requestModel);

        // 検索処理(全て)
        EntryInitializeResponseModel responseModel =
            acctGrpHierarchyLogic.searchAll(dto);

        responseModel.fileName = dto.fileName;

        return responseModel;
    }

    /**
     * アカウントグループ階層を下位から表示する情報を返します。<br/>
     * <p>
     * <ul>
     * <li>アカウントグループ階層を取得します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            画面のリクエストモデル
     * @return アカウントグループ階層情報をセットしたレスポンスモデル
     */
    @Execute
    @Validation
    public EntryInitializeResponseModel displayReverse(
            EntryInitializeRequestModel requestModel) {

        // 検索条件モデルを作成
        AcctGrpHierarchyDto model = entryMapper.createSearchModel(requestModel);

        // 下位から表示する情報
        EntryInitializeResponseModel responseModel =
            acctGrpHierarchyLogic.displayReverse(model);

        return responseModel;
    }

    /**
     * アカウントグループ階層を削除します。<br/>
     * <p>
     * <ul>
     * <li>アカウントグループ階層を削除します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            画面のリクエストモデル
     * @return なし
     */
    @Execute
    @Validation
    public void delete(EntryRequestModel requestModel) {
        // パラメータ登録登録モデルを作成する。
        AcctGrpHierarchyDto dto = entryMapper.createModel(requestModel);

        // 入力チェックを行う。
        acctGrpHierarchyLogic.validateDelete(dto);

        // 階層削除処理
        acctGrpHierarchyLogic.delete(dto);
    }

    /**
     * アカウントグループ階層を最上位に追加します。<br/>
     * <p>
     * <ul>
     * <li>アカウントグループ階層を追加します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            画面のリクエストモデル
     * @return なし
     */
    @Execute
    @Validation
    public void appendTop(EntryRequestModel requestModel) {

        AcctGrpHierarchyDto dto = entryMapper.createModel(requestModel);

        List<EntryAddRequestModel> addGrpList = requestModel.addGrpList;

        if (addGrpList != null && addGrpList.size() > 1) {
            ValidationResults validationResults = new ValidationResults();
            validationResults.add(new ValidationResult(MessageBuilder.create(
                "E.CRM.CC.00122").toMessage()));
            throw new ValidationException(validationResults);
        }

        dto.grpCd = addGrpList.get(0).crmAccountGroupCd;

        // チェック
        acctGrpHierarchyLogic.validateAppendTop(dto);

        // 追加
        acctGrpHierarchyLogic.appendTop(dto);
    }

    /**
     * アカウントグループ階層を追加します。<br/>
     * <p>
     * <ul>
     * <li>アカウントグループ階層を追加します。</li>
     * </ul>
     * </p>
     * 
     * @param requestModel
     *            画面のリクエストモデル
     * @return なし
     */
    @Execute
    @Validation
    public void append(EntryRequestModel requestModel) {

        String path = requestModel.path;

        AcctGrpHierarchyDto dto = entryMapper.createModel(requestModel);

        List<EntryAddRequestModel> addGrpList = requestModel.addGrpList;

        if (addGrpList == null || addGrpList.size() <= 0) {
            ValidationResults validationResults = new ValidationResults();
            validationResults.add(new ValidationResult(MessageBuilder.create(
                "E.CRM.CC.00109").toMessage()));
            throw new ValidationException(validationResults);
        }

        if (StringUtils.isEmpty(path)) {
            for (EntryAddRequestModel mdl : addGrpList) {
                if (!"false".equalsIgnoreCase(mdl.deleteFlag)) {
                    continue;
                }
                // パラメータ登録登録モデルを作成する。
                dto.grpCd = mdl.crmAccountGroupCd;

                // ルートの追加処理
                acctGrpHierarchyLogic.appendAcctGrpRoot(dto);
            }
        } else {
            for (EntryAddRequestModel mdl : addGrpList) {
                if (!"false".equalsIgnoreCase(mdl.deleteFlag)) {
                    continue;
                }
                // パラメータ登録登録モデルを作成する。
                dto.path = path;
                dto.grpCd = mdl.crmAccountGroupCd;

                // 入力チェックを行う。
                acctGrpHierarchyLogic.validateAppend(dto);

                // 階層追加処理
                acctGrpHierarchyLogic.appendAcctGrp(dto);
            }
        }

    }

    /**
     * 非表示エクスポートボタン押下を行います。
     * 
     * @param request
     *            エクスポートボタン押下submitモデル
     * @return エクスポートボタン押下responseモデルです。
     */
    @Execute
    public FileExportResponseModel exportFile(ExportFileRequestModel request) {

        FileExportResponseModel responseModel = new FileExportResponseModel();

        // モデルを用意する
        AcctGrpHierarchyDto dto = entryMapper.createExportModel(request);
        responseModel.fileName = request.fileName;

        if (ConfirmWay.LATER_CONFIRM.value().equals(request.confirmWay)) {
            // 入力値．確認方法　＝　区分値.確認方法.あとで確認する の場合
            acctGrpHierarchyLogic.asyncRegister(dto);

            responseModel.add(new MessageDetailResponse(
                MessageType.INFO,
                "I.CRM.CC.00004"));
        } else {
            // 入力値．確認方法　＝　区分値.確認方法.すぐに確認する の場合
            FileDownloadResponseModel downloadResponseModel =
                acctGrpHierarchyLogic.exportFile(dto, UniqueIdGenerator
                    .getInstance()
                    .create());
            if (downloadResponseModel.getStoragePath() == null) {
                // ファイル作成処理結果モデル．ファイルIDがnullの場合
                responseModel.add(new MessageDetailResponse(
                    MessageType.INFO,
                    "I.CRM.CC.00005"));
            } else {
                // ファイル作成処理結果モデル．ファイルIDがnull以外の場合
                responseModel.fileId = downloadResponseModel.getStoragePath();
                responseModel.fileName =
                    downloadResponseModel.getFileNameForDownload();
            }
        }
        return responseModel;
    }

    /**
     * 非表示インポートボタン押下を行います。
     * 
     * @param request
     *            非表示取込ボタン押下submitモデル
     */
    @Execute
    public void importFile(IoUploadRequestModel request) {

        // ファイル管理情報を取得する
        List<SharedFile> sharedFileList =
            sharedStorageManager.findFileInfo(request.uploadContextId);

        // ファイルを取得する
        for (SharedFile sharedFile : sharedFileList) {
            InputStream inputStream =
                sharedStorageManager.loadSharedFileAsStream(sharedFile
                    .getStoragePath());

            // マスタに追加する
            acctGrpHierarchyLogic.importFile(inputStream);
        }

    }

}
