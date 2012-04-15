/*
 * Copyright (c) 2009-2010 NTT DATA BIZINTEGRAL CORPORATION. All rights reserved.
 */
package com.biz_integral.crm.cc.domain.logic;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import jp.co.intra_mart.foundation.solr.crawler.common.DynamicDocumentType;
import jp.co.intra_mart.foundation.solr.crawler.exception.SolrCrawlerException;
import jp.co.nttdata.intra_mart.solr.domain.SolrSearchCondition;
import jp.co.nttdata.intra_mart.solr.exception.SearchException;

import com.biz_integral.crm.cc.domain.dto.solr.CrawlerWorkDto;
import com.biz_integral.crm.cc.domain.dto.solr.CrawlerWorkPagerDto;
import com.biz_integral.crm.cc.domain.logic.batch.config.CrmSolrCrawlerConfig;
import com.biz_integral.crm.cc.domain.logic.batch.config.impl.CrmSolrCrawlerConfigImpl;
import com.biz_integral.crm.cc.domain.logic.batch.types.BatchCrawlingType;
import com.biz_integral.crm.cc.domain.logic.constants.CrmSolrConstans;
import com.biz_integral.crm.extension.solr.BizIntegralSolrManager;
import com.biz_integral.crm.extension.solr.common.BizIntegralCrawlerUtil;
import com.biz_integral.foundation.core.context.ContextContainer;
import com.biz_integral.foundation.core.context.FeatureStateOperation;
import com.biz_integral.foundation.core.exception.IORuntimeException;
import com.biz_integral.foundation.core.logging.BizLogger;
import com.biz_integral.foundation.core.logging.BizLoggerFactory;
import com.biz_integral.foundation.core.logging.LogLevel;
import com.biz_integral.foundation.core.util.DateUtil;
import com.biz_integral.foundation.core.util.StringUtil;
import com.biz_integral.service.domain.master.locale.BizLocaleManager;

/**
 * クローラロジックの基底クラス
 */
public abstract class CrmCrawlerBaseLogic {

    /** ログ */
    protected BizLogger log = BizLoggerFactory.getLogger(this.getClass());

    /**
     * １処理件数<br>
     * 処理対象のうち１度の処理における文書登録件数。<br>
     * 処理対象6000件、１処理件数1000件ならば、<br>
     * 6回に分けて文書の検索が行われる。
     */
    protected long oneTransactionNumber;

    /** クローラ実行設定 */
    protected CrmSolrCrawlerConfig config;
    /** ソーラーマネージャー */
    protected BizIntegralSolrManager solrMng;

    /** 文書種別 */
    protected String documentType;
    /** バッチクローラー区分 */
    protected BatchCrawlingType crawlingType;

    /** 文書種別（基本文書種別）リスト */
    protected List<String> appDocTypeList;

    /** クローラ実行日時 */
    protected String nowDate;
    /** 初回設定日時 */
    protected String initialDate;
    /** 開始日時 */
    protected String startDate;

    /** 初回実行フラグ */
    protected boolean initialFlg;
    /** 月数未指定フラグ */
    protected boolean noLimitFlg;

    /** 動的文書種別のファセット表示名称設定マップ */
    protected Map<String, String> dynamicTypeInfo;
    /** 動的文書種別のファセット表示名変更フラグ */
    protected boolean dynamicTypeEditFlg;

    /** コンテキスト */
    @Resource
    protected ContextContainer contextContainer;

    /** フィーチャー操作クラス */
    @Resource
    protected FeatureStateOperation featureStateOperation;

    // /** 名称設定 */
    // @Resource
    // protected EnumNamesRegistry enumNamesRegistry;

    /** Bizロケール管理クラス */
    @Resource
    protected BizLocaleManager bizLocaleManager;

    /** 最終クローラー起動日時管理クラス */
    @Resource
    private LastCrawlingDateLogic lastCrawlingDateLogic;

    /**
     * 文書IDを取得します。
     * 
     * @return 文書ID
     */
    protected abstract String getDocumentId();

    /**
     * 会社別クローラ実行前の処理です。<br>
     * 
     * @throws SolrCrawlerException
     *             Solrのアクセス例外
     */
    protected void beforeCrawler() throws SolrCrawlerException {
    }

    /**
     * クローラ処理を実行します。<br>
     * 指定された会社で処理を行います。
     * 
     * @param companyCd
     *            会社コード
     * @throws SolrCrawlerException
     *             Solrのアクセス例外
     */
    protected abstract void crawler(String companyCd)
        throws SolrCrawlerException;

    /**
     * 会社別クローラ実行後の処理です。<br>
     * 
     * @throws SolrCrawlerException
     *             Solrのアクセス例外
     */
    protected void afterCrawler() throws SolrCrawlerException {
    }

    /**
     * クローラの実行を行います。<br>
     * フィールドの初期化等を行い、<br>
     * 会社ごとのcrawlerを呼び出します。<br>
     * crawlerは継承先で実装を記述します。
     * 
     * @param crawlingType
     *            クローリングタイプ
     * @throws SolrCrawlerException
     */
    public void execute(BatchCrawlingType crawlingType)
        throws SolrCrawlerException {
        // 初期値を設定
        nowDate = DateUtil.nowDateString();
        this.crawlingType = crawlingType;
        documentType = getDocumentType();
        appDocTypeList = getBaseDocumentTypeList();

        // 設定ファイルの読み込み
        config = new CrmSolrCrawlerConfigImpl(CrmSolrConstans.CONFIG_FILE_NAME);
        List<String> companyCdList = config.getCompanyList(documentType);
        // 動的文書種別ファイルの読み込み
        loadDynTypeMap();

        // １処理件数の設定
        oneTransactionNumber = config.getOneTransactionNumber(documentType);
        try {
            // 会社別のクローラ処理前
            beforeCrawler();

            // 以下は会社コード毎の繰り返し
            for (String companyCd : companyCdList) {
                // 会社別にコンテキストを切り替える
                featureStateOperation.switchTo(companyCd);
                solrMng =
                    BizIntegralSolrManager.getInstance(
                        documentType,
                        companyCd,
                        contextContainer.getUserContext().getLoginGroupID());

                // 期間とフラグの設定
                init(companyCd);
                // 再作成時の文書削除
                deleteIndexBefore(companyCd);
                // 登録更新処理の実行
                crawler(companyCd);

                // 会社ごとのクローラ起動日時を更新
                // config.setLastCrawlingDate(documentType, companyCd, nowDate);
                // updateLastCrawlingDate(getDocumentId(), companyCd, nowDate);
                lastCrawlingDateLogic.updateLastCrawlingDate(
                    getDocumentId(),
                    nowDate);
            }

            // 会社別のクローラ処理後
            afterCrawler();

            // 最適化
            solrMng.optimize();
        } catch (SearchException e) {
            throw new SolrCrawlerException(e.getMessage(), e);
        } finally {
            saveDynamicType();
        }
    }

    /**
     * 文書種別を返却します。
     * 
     * @return 文書種別
     */
    protected String getDocumentType() {
        return BizIntegralCrawlerUtil.makePrefix(contextContainer
            .getApplicationContext()
            .getApplicationID(), contextContainer
            .getApplicationContext()
            .getModuleID(), getDocumentId());

    }

    /**
     * 基本文書種別を取得します。<br>
     * 一件目はアプリケーションID、2件目は文書種別
     * 
     * @return 基本文書種別
     */
    protected List<String> getBaseDocumentTypeList() {
        List<String> typeList = new ArrayList<String>();
        typeList.add(contextContainer
            .getApplicationContext()
            .getApplicationID());
        typeList.add(documentType);
        return typeList;
    }

    /**
     * クローラ実行情報を初期化します。<br>
     * 
     * @param companyCd
     *            会社コード
     */
    protected void init(String companyCd) {

        // 初回設定日時
        initialDate = config.getInitialDate();
        // 最終クローラ起動日時
        // String lastDate = config.getLastCrawlingDate(documentType,
        // companyCd);
        // String lastDate = getLastCrawlingDate(getDocumentId(), companyCd);
        String lastDate =
            lastCrawlingDateLogic.getLastCrawlingDate(getDocumentId());

        // 差分か再作成を判定する
        if (crawlingType == BatchCrawlingType.DELTA) {
            // 差分作成の場合
            if (StringUtil.isEmpty(lastDate)) {
                // 最終クローラ起動日時がないので初回起動
                initialFlg = true;
                // 初回設定日を開始日とする
                startDate = initialDate;
            } else {
                // 最終クローラ起動日時があるので２回目以降
                initialFlg = false;
                // 最終クローラ起動日時を開始日とする
                startDate = lastDate;
            }

        } else {
            // 再作成の場合

            // 指定月数を取得する
            int limit = config.getReindexMonth(documentType, companyCd);
            if (limit == 0) {
                startDate = initialDate;// config.getInitialDate();
                initialFlg = true;
                noLimitFlg = true;
            } else {
                noLimitFlg = false;

                if (StringUtil.isEmpty(lastDate)) {
                    // 最終クローラ起動日時がないので初回起動
                    initialFlg = true;
                } else {
                    // 最終クローラ起動日時があるので２回目以降
                    initialFlg = false;
                }
                startDate =
                    getReIndexingStartDate(
                        initialFlg,
                        nowDate,
                        initialDate,
                        limit);
            }
        }
    }

    // /**
    // * 最終クローラー起動日時を取得します。<br>
    // * 設定ファイルが存在しない場合、nullを返却します。
    // *
    // * @param documentId
    // * 文書ID
    // * @param companyCd
    // * 会社コード
    // * @return 最終クローラー起動日時
    // */
    // private String getLastCrawlingDate(String documentId, String companyCd) {
    // // パス作成
    // String filepath = getLastCrawlingDateFilePath(companyCd);
    // String filename = getLastCrawlingDateFileName(documentId);
    // String lastCrawlingDate = null;
    // // try {
    // try {
    // NetworkFile vf = new NetworkFile(filepath);
    // // ディレクトリが存在しなかった場合
    // if (!vf.isDirectory()) {
    // return null;
    // }
    // vf =
    // new NetworkFile(new StringBuilder()
    // .append(filepath)
    // .append("/")
    // .append(filename)
    // .toString());
    // // ファイルが存在しなかった場合
    // if (!vf.isFile()) {
    // return null;
    // }
    //
    // lastCrawlingDate = vf.read();
    //
    // } catch (IOException e) {
    // throw new BatchRuntimeException(e, MessageBuilder.create(
    // "E.CRM.CC.00088").addParameter(filename).toMessage());
    // }
    //
    // return lastCrawlingDate;
    // }
    //
    // /**
    // * 最終クローラ起動日時を更新します。<br>
    // *
    // * @param documentId
    // * 文書ID
    // * @param companyCd
    // * 会社コード
    // * @param nowDate
    // * 起動日時
    // */
    // private void updateLastCrawlingDate(String documentId, String companyCd,
    // String nowDate) {
    // // パス作成
    // String filepath = getLastCrawlingDateFilePath(companyCd);
    // String filename = getLastCrawlingDateFileName(documentId);
    //
    // try {
    // NetworkFile vf = new NetworkFile(filepath);
    // if (!vf.isDirectory()) {
    // vf.makeDirectories();
    // }
    //
    // vf =
    // new NetworkFile(new StringBuilder()
    // .append(filepath)
    // .append("/")
    // .append(filename)
    // .toString());
    // vf.write(this.nowDate);
    //
    // } catch (IOException e) {
    // throw new BatchRuntimeException(e, MessageBuilder.create(
    // "E.CRM.CC.00088").addParameter(filename).toMessage());
    // }
    // }
    //
    // /**
    // * 最終クローラー起動日時の登録ファイルパスを取得します。<br>
    // *
    // * @param companyCd
    // * 会社コード
    // * @return 最終クローラー起動日時の登録ファイルパス
    // */
    // private String getLastCrawlingDateFilePath(String companyCd) {
    // return MessageFormat.format(
    // CrmSolrConstans.LAST_CRAWLING_DATE_PATH,
    // contextContainer.getApplicationContext().getApplicationID(),
    // companyCd);
    // }
    //
    // /**
    // * 最終クローラー起動日時の登録ファイル名を取得します。<br>
    // *
    // * @param documentId
    // * 文書ID
    // * @return 最終クローラー起動日時の登録ファイル名
    // */
    // private String getLastCrawlingDateFileName(String documentId) {
    // return new StringBuilder().append(documentId).append(
    // CrmSolrConstans.LAST_CRAWLING_DATE_FILENAME).toString();
    // }

    /**
     * 再作成クローラーの開始日時を取得します。
     * 
     * @param initialFlg
     *            初回起動フラグ
     * @param today
     *            クローラ起動日時
     * @param initialDate
     *            初回設定日
     * @param limit
     *            再作成範囲月数
     * @return クローリング開始日時の初期値(起動日時 - limit月数 00:00:00)
     */
    private String getReIndexingStartDate(boolean initialFlg, String today,
            String initialDate, int limit) {
        if (initialFlg) {
            return initialDate;
        }
        Date date = DateUtil.parse(today);
        date = DateUtil.getCalculator(date).addMonth(-limit).asDate();
        if (date.compareTo(DateUtil.parse(initialDate)) <= 0) {
            return initialDate;
        }
        return DateUtil.format(date, DateUtil.DEFAULT_DATE_FORMAT);
    }

    /**
     * 動的文書種別ファイルを読み込みます。
     */
    protected void loadDynTypeMap() {
        try {
            dynamicTypeInfo =
                DynamicDocumentType.getDynamicTypeInfo(
                    documentType,
                    contextContainer.getUserContext().getLoginGroupID());

            if (dynamicTypeInfo == null) {
                dynamicTypeInfo = new LinkedHashMap<String, String>();
            }
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * 動的文書種別にキーと名称を追加します。
     * 
     * @param type
     *            キー
     * @param typeName
     *            名称
     */
    protected void putDynamicType(String type, String typeName) {
        if (dynamicTypeInfo.containsKey(type)
            && (dynamicTypeInfo.get(type)).equals(typeName)) {
            return;
        }
        dynamicTypeInfo.put(type, typeName);
        dynamicTypeEditFlg = true;
        return;
    }

    /**
     * 動的文書種別にキーと名称を追加します。
     * 
     * @param typeMap
     *            key=キー、value=名称
     */
    protected void putDynamicType(Map<String, String> typeMap) {
        if (typeMap != null) {
            for (Map.Entry<String, String> type : typeMap.entrySet()) {
                putDynamicType(type.getKey(), type.getValue());
            }
        }
    }

    /**
     * 動的文書種別からキーを削除します。
     * 
     * @param type
     *            キー
     */
    protected void removeDynamicType(String type) {
        if (dynamicTypeInfo.remove(type) != null) {
            dynamicTypeEditFlg = true;
        }
    }

    /**
     * 編集した動的文書種別を保存します。
     */
    protected void saveDynamicType() {
        if (!dynamicTypeEditFlg)
            return;
        try {
            DynamicDocumentType.saveDynamicTypefile(
                documentType,
                dynamicTypeInfo,
                contextContainer.getUserContext().getLoginGroupID());
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * オフセットから件数のエンティティを検索するDTOを取得します。<br>
     * Solrへ処理する文書の検索条件です。
     * 
     * @param companyCd
     *            会社コード
     * @param offset
     *            オフセット
     * @return クローラワークページャーDTO
     */
    protected CrawlerWorkPagerDto getPagerDto(String companyCd, long offset) {
        CrawlerWorkPagerDto dto = new CrawlerWorkPagerDto();
        dto.setLimit(new Long(oneTransactionNumber).intValue());
        dto.setOffset(new Long(offset).intValue());
        dto.companyCdList.add(companyCd);
        dto.startDate = DateUtil.parse(startDate);
        dto.endDate = DateUtil.parse(nowDate);
        dto.simpleStartDate = getSimpleStartDate(startDate);
        dto.simpleEndDate = getSimpleEndDate(nowDate);
        return dto;
    }

    /**
     * 件数を検索するDTOを取得します。<br>
     * Solrへ処理する文書件数の検索条件です。
     * 
     * @param companyCd
     *            会社コード
     * @return クローラワークDTO
     */
    protected CrawlerWorkDto getDto(String companyCd) {
        CrawlerWorkDto dto = new CrawlerWorkDto();
        dto.companyCdList.add(companyCd);
        dto.startDate = DateUtil.parse(startDate);
        dto.endDate = DateUtil.parse(nowDate);
        dto.simpleStartDate = getSimpleStartDate(startDate);
        dto.simpleEndDate = getSimpleEndDate(nowDate);
        return dto;
    }

    /**
     * 開始日付を編集する。<br>
     * 時分秒を持っていない値を比較するために、<br>
     * startDate の時分秒が未設定の日付に変更する。
     * 
     * @param startDate
     *            開始日付（前回クローラー起動日時）
     * @return 開始日付から時分秒を除いた日付
     */
    protected Date getSimpleStartDate(String startDate) {
        return DateUtil
            .getCalculator(DateUtil.parse(startDate))
            .resetTime()
            .asDate();
    }

    /**
     * 終了日付を編集する。<br>
     * 時分秒を持っていない値を比較するために、<br>
     * endDate + 1日で時分秒が未設定の日付に変更する。
     * 
     * @param endDate
     *            終了日付（クローラー実行日付）
     * @return 終了日付 + 1日で時分秒が未設定の日付
     */
    protected Date getSimpleEndDate(String endDate) {
        return DateUtil
            .getCalculator(DateUtil.parse(endDate))
            .addDay(1)
            .resetTime()
            .asDate();
    }

    /**
     * クローラ登録処理の前に、再作成対象を削除します。<br>
     * 対象の会社コードの文書を削除する条件は以下となります。<br>
     * クローラの動作が再作成の場合、<br>
     * 期間制限なし時は全indexを削除します。<br>
     * 期間制限あり時は再作成の開始日以降に登録されたindexを削除します。
     * 
     * @param companyCd
     *            会社コード
     * @throws SolrCrawlerException
     *             削除失敗エラー
     */
    protected void deleteIndexBefore(String companyCd)
        throws SolrCrawlerException {
        if (crawlingType.equals(BatchCrawlingType.REINDEXING)) {
            if (noLimitFlg) {
                deleteIndexAll(companyCd);
            } else {
                deleteIndexByUpdate(companyCd, startDate);
            }
        }
    }

    /**
     * 検索条件に合致する文書を削除します。
     * 
     * @param cond
     *            Solr検索条件
     * @throws SolrCrawlerException
     *             削除失敗エラー
     */
    protected void deleteIndexByCondition(SolrSearchCondition cond)
        throws SolrCrawlerException {
        try {

            log.log(LogLevel.DEBUG, MessageFormat.format(
                "delete [condition = {0} ",
                cond.getExpression()).toString());
            solrMng.deleteDocuments(cond);
        } catch (SearchException e) {
            throw new SolrCrawlerException(e.getMessage(), e);
        }
    }

    /**
     * 更新日よりも後に登録された文書を削除します。
     * 
     * @param company
     *            会社コード
     * @param updateDate
     *            更新日 Bizのdefault形式 {@link DateUtil#DEFAULT_DATE_FORMAT}
     * @throws SolrCrawlerException
     *             削除失敗エラー
     */
    protected void deleteIndexByUpdate(String company, String updateDate)
        throws SolrCrawlerException {
        SolrSearchCondition cond = new SolrSearchCondition();
        cond
            .term("type", documentType)
            .and()
            .term("company", company)
            .and()
            .dateGreaterThanOrEqualTo(
                CrmSolrConstans.CRM_FIELD_UPDATE_DATE,
                DateUtil.parse(updateDate));
        deleteIndexByCondition(cond);
    }

    /**
     * 同会社コードの文書を削除します。
     * 
     * @param company
     *            会社コード
     * @throws SolrCrawlerException
     *             Solrアクセス例外
     */
    protected void deleteIndexAll(String company) throws SolrCrawlerException {
        SolrSearchCondition cond = new SolrSearchCondition();
        cond.term("type", documentType).and().term("company", company);
        deleteIndexByCondition(cond);
    }

    /**
     * 同IDの文書を削除します。
     * 
     * @param id
     *            ID
     * @throws SolrCrawlerException
     *             Solrアクセス例外
     */
    protected void deleteIndexById(String id) throws SolrCrawlerException {
        SolrSearchCondition cond = new SolrSearchCondition();
        cond.term("id", id);
        deleteIndexByCondition(cond);
    }

}
