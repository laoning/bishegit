package com.biz_integral.crm.extension.solr.display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import jp.co.intra_mart.common.aid.jdk.javax.xml.DOMBuilder;
import jp.co.intra_mart.common.aid.jdk.javax.xml.XmlNode;
import jp.co.intra_mart.foundation.solr.display.DocumentTypeInfo;
import jp.co.intra_mart.foundation.solr.display.DynamicDocTypeHandler;
import jp.co.intra_mart.system.service.provider.platform.AdministrationFile;

import com.biz_integral.crm.extension.solr.model.ContextLoadingModel;
import com.biz_integral.foundation.core.message.NamedItem;
import com.biz_integral.foundation.core.message.impl.DefaultNamedItemResource;

/**
 * 全文検索画面マネージャクラス.<br />
 * 全文検索画面に関する設定情報を管理するクラスです。
 */
public class SolrDisplayManager {

    /** 設定ファイルパス **/
    private static final String CONF_DIRECTORY = "conf";
    /** 設定ファイル名 **/
    private static final String DISPLAY_CONFIG_FILE_NAME =
        "biz-solr-display-config";

    /** 設定ファイル ID display **/
    private static final String ID_DISPLAY = "display";
    /** 設定ファイル ID dispay-count **/
    private static final String ID_DISPLAY_COUNT = "dispay-count";
    /** 設定ファイル ID use-highlight **/
    private static final String ID_USE_HIGHTLIGHT = "use-highlight";
    /** 設定ファイル ID facet-depth **/
    private static final String ID_FACET_DEPTH = "facet-depth";
    /** 設定ファイル ID document **/
    private static final String ID_DOCUMENT = "document";
    /** 設定ファイル ID document-type **/
    private static final String ID_DOCUMENT_TYPE = "document-type";
    /** 設定ファイル ID display-combo **/
    private static final String ID_DISPLAY_COMBO = "display-combo";
    /** 設定ファイル ID parent-document-type **/
    private static final String ID_PARENT_DOCUMENT_TYPE =
        "parent-document-type";
    /** 設定ファイル ID display-string-key **/
    private static final String ID_DISPLAY_STR_KEY = "display-string-key";
    /** 設定ファイル ID templete-url **/
    private static final String ID_TEMPLETE_URL = "templete-url";
    /** 設定ファイル ID default-date-format **/
    private static final String ID_DEFAULT_DATE_FORMAT = "default-date-format";
    /** 設定ファイル ID require-fields **/
    private static final String ID_REQUIRE_FIELDS = "require-fields";
    /** 設定ファイル ID field **/
    private static final String ID_FIELD = "field";
    /** 設定ファイル ID name **/
    private static final String ID_NAME = "name";
    /** 設定ファイル ID date-format **/
    private static final String ID_DATE_FORMAT = "date-format";

    /** 動的文書種別オンメモリフラグ */
    private static final String ID_DYNAMIC_ON_MEMORY = "dynamictype-on-memory";

    /** ハイライト開始タグ **/
    public static final String SOLR_HIGHLIGHT_PRE =
        "<span class='solr_highlight'>";
    /** ハイライト終了タグ **/
    public static final String SOLR_HIGHLIGHT_POST =
        "<!-- #solr_highlight --></span>";

    /** 動的文書種別プレフィックス **/
    public static final String DYNAMIC_DOCTYPE_PREFIX = "d$$";
    /** 動的文書種別セパレータ **/
    public static final String DYNAMIC_DOCTYPE_SEPARATOR = "$$";
    /** 動的文書種別セパレータ正規表現 **/
    public static final String DYNAMIC_DOCTYPE_SEPARATOR_REGEX = "\\$\\$";

    /** 動的文書種別表示用セパレータ **/
    public static final String FACET_DISPLAY_SEPARATOR = "&nbsp;>&nbsp;";

    /** デフォルト表示件数 **/
    private static final String DEFAULT_DISPLAY_COUNT = "10,20,30,50";

    /** Solr検索画面マネージャ **/
    private static SolrDisplayManager instance;

    /** 文書種別情報マップ **/
    private LinkedHashMap<String, DocumentTypeInfo> typemap;

    /** 動的文書情報ハンドラ **/
    private DynamicDocTypeHandler handler;

    /** 表示件数リスト **/
    private String[] displayCounts;

    /** デフォルト選択表示件数 **/
    private String defaultDisplayCount;

    /** ハイライト表示フラグ **/
    private boolean useHighlight;

    /** ファセット表示階層数 **/
    private int facetDepth;

    /** OnMemoryフラグ **/
    private boolean onMemory;

    /**
     * マネージャのインスタンスを取得します。 <br>
     * <br>
     * 
     * @return マネージャのインスタンス
     */
    public synchronized static SolrDisplayManager getInstance() {
        if (SolrDisplayManager.instance == null) {
            SolrDisplayManager.instance = new SolrDisplayManager();
        }
        return SolrDisplayManager.instance;
    }

    /**
     * コンストラクタ。 <br>
     * <br>
     * 隠蔽化します。
     */
    @SuppressWarnings("unchecked")
    private SolrDisplayManager() {
        super();
        typemap = new LinkedHashMap<String, DocumentTypeInfo>();
        boolean basicFileExist = false;

        AdministrationFile adminFile = new AdministrationFile(CONF_DIRECTORY);
        try {
            Collection<String> files = adminFile.files();
            Iterator<String> ite = files.iterator();

            while (ite.hasNext()) {
                String file = ite.next();
                if (!file.startsWith(DISPLAY_CONFIG_FILE_NAME)
                    || !file.endsWith(".xml")) {
                    continue;
                }

                adminFile = new AdministrationFile(CONF_DIRECTORY + "/" + file);

                XmlNode root =
                    new XmlNode(DOMBuilder.newInstance(
                        adminFile.getInputStream()).getRootNode());

                // if (root != null) {
                // 画面情報の取得
                if (file.equals(DISPLAY_CONFIG_FILE_NAME + ".xml")) {
                    setDispalyInfo(root.lookup(ID_DISPLAY));
                    basicFileExist = true;
                }

                XmlNode documentNode = root.lookup(ID_DOCUMENT);
                if (documentNode != null) {
                    // 文書種別の取得
                    setDocumentsInfo(documentNode);
                }
                // }
            }

            if (!basicFileExist) {
                throw new RuntimeException(
                    "[IM_ContentsSearch] file.not.found:"
                        + DISPLAY_CONFIG_FILE_NAME
                        + ".xml");
            }

            if (isOnMemory()) {
                handler = new DynamicDocTypeHandler();
                // 動的文書種別をメモリに事前読み込み
                handler.preLoadAllFile();
            }
        } catch (Exception e) {
            typemap = null;
            throw new RuntimeException(e);
        }
    }

    /**
     * 文書種別情報マップを取得します。
     * 
     * @return 文書種別情報マップ
     */
    public LinkedHashMap<String, DocumentTypeInfo> getTypeMap() {
        return typemap;
    }

    /**
     * ハイライトを表示するか否かを返却します。
     * 
     * @return ハイライトを表示するか否か
     */
    public boolean useHighlight() {
        return useHighlight;
    }

    /**
     * ファセット表示階層数を返却します。
     * 
     * @return ファセット表示階層数
     */
    public int getFacetDepth() {
        return facetDepth;
    }

    /**
     * 動的文書種別情報をメモリに保持するか否かを返却します。
     * 
     * @return 動的文書種別情報をメモリに保持するか否か
     */
    public boolean isOnMemory() {
        return onMemory;
    }

    /**
     * 設定ファイルに文書種別情報が存在するか否かを返却します。<br>
     * 文書種別が動的文書種別の場合は、基本文書種別の情報の<br>
     * 存在チェックを行います。
     * 
     * @param type
     *            文書種別 　
     * 
     * @return 設定ファイルに文書種別情報が存在するか否か
     */
    public boolean isTypeExist(String type) {
        String targetType = type;
        if (isDynamicType(type)) {
            targetType = getBasicType(type);
        }

        if (targetType == null) {
            return false;
        }
        return typemap.containsKey(targetType);
    }

    /**
     * 文書種別に該当する親の階層の文書種別を取得します。<br>
     * 
     * 親の階層の文書種別の設定がない場合は、length0の配列を<br>
     * 返却します。<br>
     * <br>
     * この文書種別に該当する文書種別情報が設定ファイルにない場合は、<br>
     * nullを返却します。
     * 
     * @param type
     *            文書種別 　
     * 
     * @return 親の階層の文書種別の配列
     */
    public String[] getParentIds(String type) {
        boolean isDynamic = false;
        if (typemap.get(type) == null && !(isDynamic = isDynamicType(type))) {
            return null;
        }
        // 基本文書種別
        if (!isDynamic) {
            return typemap.get(type).getParentIds();
        }
        // 動的文書種別の場合、親の動的文書種別情報を追加
        ArrayList<String> parentIds = new ArrayList<String>();

        String basicType = getBasicType(type);
        type = type.substring(DYNAMIC_DOCTYPE_PREFIX.length());
        String[] typeList = type.split(DYNAMIC_DOCTYPE_SEPARATOR_REGEX);
        int index = typeList.length - 2;
        while (type.lastIndexOf(DYNAMIC_DOCTYPE_SEPARATOR) != -1) {
            type =
                type.substring(0, type.lastIndexOf(DYNAMIC_DOCTYPE_SEPARATOR));

            if (typeList[index--].equals(basicType)) {
                parentIds.add(0, basicType);
                break;
            }
            parentIds.add(0, DYNAMIC_DOCTYPE_PREFIX + type);
        }
        parentIds.addAll(0, Arrays.asList(getParentIds(basicType)));
        return parentIds.toArray(new String[parentIds.size()]);
    }

    /**
     * 文書種別画面表示文字列のメッセージキーを取得します。<br>
     * <br>
     * この文書種別に該当する文書種別情報が設定ファイルにない場合、<br>
     * または文書種別画面表示文字列のメッセージキーの設定がない場合は<br>
     * nullを返却します。
     * 
     * @param type
     *            文書種別 　
     * @return 文書種別画面表示文字列のメッセージキー
     */
    public String getDisplayStringKey(String type) {
        if (typemap.get(type) == null) {
            return null;
        }

        return typemap.get(type).getDisplayStringKey();
    }

    /**
     * 文書種別に該当するテンプレートURLを取得します。<br>
     * 文書種別が階層構造を持つ場合は、下位の種別から上位の種別まで<br>
     * 最初にテンプレートURLの設定があったテンプレートURLを返却します。 <br>
     * 文書種別が動的文書種別の場合は、その基本文書種別に該当する<br>
     * テンプレートURLを取得します。<br>
     * <br>
     * この文書種別に該当する文書種別情報が設定ファイルにない場合、<br>
     * または親の文書種別に遡ってもテンプレートURLの設定が見つからなかった場合は、<br>
     * nullを返却します。
     * 
     * @param type
     *            文書種別 　
     * 
     * @return 文書種別に該当するテンプレートURL
     */
    public String getTempleteUrl(String type) {
        String basictype = getBasicType(type);
        if (typemap.get(basictype) == null) {
            return null;
        }

        if (typemap.get(basictype).getTempleteUrl() != null) {
            return typemap.get(basictype).getTempleteUrl();
        }

        String[] parents = typemap.get(basictype).getParentIds();
        if (parents != null) {

            for (int i = parents.length - 1; i > 0; i--) {

                if (typemap.get(parents[i]).getTempleteUrl() != null) {
                    return typemap.get(parents[i]).getTempleteUrl();
                }
            }
        }
        return null;
    }

    /**
     * 文書種別に該当するリクエスト要求フィールドを取得します。<br>
     * 文書種別が階層構造を持つ場合は、下位の種別から上位の種別まで<br>
     * 最初にリクエスト要求フィールドの設定があった<br>
     * リクエスト要求フィールドを取得します。<br>
     * 文書種別が動的文書種別の場合は、その基本文書種別に該当する<br>
     * リクエスト要求フィールドを取得します。 <br>
     * リクエスト要求フィールドの設定がない場合は、length0の配列を<br>
     * 返却します。<br>
     * <br>
     * この文書種別に該当する文書種別情報が設定ファイルにない場合は、<br>
     * nullを返却します。
     * 
     * @param type
     *            文書種別 　
     * 
     * @return 文書種別に該当するリクエスト要求フィールド
     */
    public String[] getRequireFields(String type) {
        String basictype = getBasicType(type);
        if (typemap.get(basictype) == null) {
            return null;
        }

        // テンプレートURLに紐付くリクエスト要求フィールド
        if (typemap.get(basictype).getRequireFields() != null) {
            return typemap.get(basictype).getRequireFields();
        }

        String[] parents = typemap.get(basictype).getParentIds();
        if (parents != null) {
            for (int i = parents.length - 1; i > 0; i--) {

                if (typemap.get(parents[i]).getRequireFields() != null) {
                    return typemap.get(parents[i]).getRequireFields();
                }
            }
        }
        // リクエスト要求フィールド無
        return new String[0];
    }

    /**
     * 文書種別に該当するデフォルトDATE型フォーマットを取得します。<br>
     * 文書種別が階層構造を持つ場合は、自身の種別から上位の種別まで<br>
     * 最初にデフォルトDATE型フォーマットの設定があった<br>
     * デフォルトDATE型フォーマットを取得します。<br>
     * <br>
     * この文書種別に該当する文書種別情報が設定ファイルにない場合、<br>
     * または親の文書種別に遡ってもデフォルトDATE型フォーマットの設定が<br>
     * 見つからなかった場合は、nullを返却します。
     * 
     * @param type
     *            文書種別 　
     * 
     * @return 文書種別に該当するデフォルトDATE型フォーマット
     */
    public String getDefaultDateFormat(String type) {
        String basictype = getBasicType(type);
        if (typemap.get(basictype) == null) {
            return null;
        }

        if (typemap.get(basictype).getDefaultDateFormat() != null) {
            return typemap.get(basictype).getDefaultDateFormat();
        }

        String[] parents = typemap.get(basictype).getParentIds();
        if (parents != null) {

            for (int i = parents.length - 1; i > 0; i--) {

                if (typemap.get(parents[i]).getDefaultDateFormat() != null) {
                    return typemap.get(parents[i]).getDefaultDateFormat();
                }
            }
        }
        return null;
    }

    /**
     * 文書種別とフィールドに該当するDate型の場合のフォーマットを取得します。<br>
     * 文書種別が階層構造を持つ場合は、自身の種別から上位の種別まで<br>
     * 最初にフィールドに該当するDate型フォーマットの設定があった<br>
     * Date型フォーマットを取得します。<br>
     * <br>
     * この文書種別に該当する文書種別情報が設定ファイルにない場合、<br>
     * または親の文書種別に遡ってもフィールドに該当するDate型フォーマットの設定が<br>
     * 見つからなかった場合は、nullを返却します。
     * 
     * @param type
     *            文書種別
     * @param field
     *            フィールド
     * 
     * @return 文書種別とフィールドに該当するDate型の場合のフォーマット
     */
    public String getDateFormat(String type, String field) {
        String basictype = getBasicType(type);
        if (typemap.get(basictype) == null) {
            return null;
        }

        // リクエスト要求フィールドがある場合
        if (typemap.get(basictype).getRequireFields() != null) {
            return typemap.get(basictype).getDateFields().get(field);
        }

        String[] parents = typemap.get(basictype).getParentIds();
        if (parents != null) {
            for (int i = parents.length - 1; i > 0; i--) {

                if (typemap.get(parents[i]).getRequireFields() != null) {
                    return typemap.get(parents[i]).getDateFields().get(field);
                }
            }
        }
        return null;
    }

    /**
     * 表示件数リストを取得します。
     * 
     * @return 表示件数リスト
     */
    public String[] getDisplayCounts() {
        return displayCounts;
    }

    /**
     * デフォルト表示件数を取得します。
     * 
     * @return デフォルト表示件数
     */
    public String getDefaultDisplayCount() {
        return defaultDisplayCount;
    }

    /**
     * 設定ファイルより、文書種別情報に関する設定情報を取得してセットします。
     * 
     * @param node
     *            XMLノード
     */
    private void setDocumentsInfo(XmlNode node) {
        LinkedHashMap<String, DocumentTypeInfo> map =
            new LinkedHashMap<String, DocumentTypeInfo>();

        XmlNode[] nodes = node.select(ID_DOCUMENT_TYPE);

        for (XmlNode document : nodes) {

            DocumentTypeInfo info = new DocumentTypeInfo();
            HashMap<String, String> datefield = new HashMap<String, String>();
            info.setDateFields(datefield);
            String[] fieldList = null;

            info.setId(document.getString("id"));
            if (document.getString(ID_PARENT_DOCUMENT_TYPE) != null) {
                info.setParentIds(new String[] { document
                    .getString(ID_PARENT_DOCUMENT_TYPE) });
            }
            info.setDisplayStringKey(document.getString(ID_DISPLAY_STR_KEY));
            info.setDisplayCombo(Boolean.valueOf(document.getString(
                ID_DISPLAY_COMBO,
                "true")));
            info.setTempleteUrl(document.getString(ID_TEMPLETE_URL));
            info.setDefaultDateFormat(document
                .getString(ID_DEFAULT_DATE_FORMAT));

            XmlNode reqFields = document.lookup(ID_REQUIRE_FIELDS);
            if (reqFields != null) {
                XmlNode[] fields = reqFields.select(ID_FIELD);
                fieldList = new String[fields.length];

                for (int i = 0; i < fields.length; i++) {
                    XmlNode field = fields[i];
                    fieldList[i] = field.getString(ID_NAME);

                    if (field.lookup(ID_DATE_FORMAT) != null) {
                        datefield.put(field.getString(ID_NAME), field
                            .getString(ID_DATE_FORMAT));
                    }
                }
            }
            info.setRequireFields(fieldList);
            map.put(info.getId(), info);
        }
        // 階層構造の整理
        adjustDepth(map);
    }

    /**
     * 設定情報の階層関係の整理をします。<br>
     * 階層構造は設定ファイル単位で解決できる必要があります。
     * 
     * @param map
     *            設定ファイルを順序で保持したマップ
     */
    private void adjustDepth(LinkedHashMap<String, DocumentTypeInfo> map) {

        Iterator<Map.Entry<String, DocumentTypeInfo>> ite =
            map.entrySet().iterator();

        while (ite.hasNext()) {
            ArrayList<String> parentIds = new ArrayList<String>();
            Map.Entry<String, DocumentTypeInfo> entry = ite.next();
            if (entry.getValue().getParentIds().length == 0) {
                continue;
            }
            parentIds.add(entry.getValue().getParentIds()[0]);
            DocumentTypeInfo parentInfo =
                map.get(entry.getValue().getParentIds()[0]);
            while (parentInfo.getParentIds().length > 0) {
                parentIds.add(0, parentInfo.getParentIds()[parentInfo
                    .getParentIds().length - 1]);
                parentInfo =
                    map
                        .get(parentInfo.getParentIds()[parentInfo
                            .getParentIds().length - 1]);
            }
            entry.getValue().setParentIds(
                parentIds.toArray(new String[parentIds.size()]));
        }
        typemap.putAll(map);
    }

    /**
     * 設定ファイルより、画面情報に関する設定情報を取得してセットします。
     * 
     * @param node
     *            XMLノード
     */
    private void setDispalyInfo(XmlNode node) {
        String dispayCount =
            node.getString(ID_DISPLAY_COUNT, DEFAULT_DISPLAY_COUNT);
        displayCounts = dispayCount.split(",");
        defaultDisplayCount =
            node.lookup(ID_DISPLAY_COUNT).getString("default", "");
        useHighlight =
            Boolean.valueOf(node.getString(ID_USE_HIGHTLIGHT, "true"));
        onMemory =
            Boolean.valueOf(node.getString(ID_DYNAMIC_ON_MEMORY, "true"));
        facetDepth = node.getInteger(ID_FACET_DEPTH, -1);
    }

    /**
     * 動的文書種別の基本文書種別を取得します。
     * 
     * @param docType
     *            文書種別
     * 
     * @return 基本文書種別
     */
    public String getBasicType(String docType) {

        if (!isDynamicType(docType)) {
            return docType;
        }

        String[] typeList =
            docType.substring(DYNAMIC_DOCTYPE_PREFIX.length()).split(
                DYNAMIC_DOCTYPE_SEPARATOR_REGEX);
        String baseType = null;
        // 最下位の文書種別からチェック
        for (int i = typeList.length; i > 0; i--) {
            // 文書種別が基本文書種別に存在する場合
            if (typemap.containsKey(typeList[i - 1])) {
                baseType = typeList[i - 1];
                break;
            }
        }
        return baseType;
    }

    /**
     * 階層付文書種別名を生成します。
     * 
     * @param docTypeHandler
     *            動的文書種別ハンドラ
     * @param type
     *            文書種別
     * @param context
     *            コンテキスト
     * @return 階層付文書種別名
     * @throws IOException
     *             動的文書種別ファイルの読込みエラー
     */
    public String createTypeNameWithDepth(DynamicDocTypeHandler docTypeHandler,
            String type, ContextLoadingModel context)
    // String type, UserInfo userInfo, String companyCd)
        throws IOException {
        StringBuffer buf = new StringBuffer();
        String[] parentstypes = null;
        parentstypes = getParentIds(type);

        for (int i = 0; i < parentstypes.length; i++) {
            buf.append(getTypeName(docTypeHandler, parentstypes[i], context));
            // userInfo,
            // companyCd));
            buf.append(FACET_DISPLAY_SEPARATOR);
        }
        // buf.append(getTypeName(docTypeHandler, type, userInfo, companyCd));
        buf.append(getTypeName(docTypeHandler, type, context));
        return buf.toString();
    }

    /**
     * 文書種別が動的文書種別か判定します。
     * 
     * @param docType
     *            文書種別
     * 
     * @return 判定結果 動的文書種別の場合はtrue
     */
    public boolean isDynamicType(String docType) {
        if (docType != null && docType.startsWith(DYNAMIC_DOCTYPE_PREFIX)) {
            return true;
        }
        return false;
    }

    /**
     * 文書種別名を取得します。
     * 
     * @param docTypeHandler
     *            動的文書種別ハンドラ
     * @param docType
     *            文書種別
     * @param context
     *            コンテキスト
     * @return 文書種別名
     * @throws IOException
     */
    public String getTypeName(DynamicDocTypeHandler docTypeHandler,
            String docType, ContextLoadingModel context)
    // String docType, UserInfo userInfo, String companyCd)
        throws IOException {

        boolean isDynType = false;
        String typeName = null;
        int index = -1;
        if ((getDisplayStringKey(docType) == null || getDisplayStringKey(
            docType).length() == 0)
            && !(isDynType = isDynamicType(docType))) {
            return docType;
        }
        // 動的文書種別
        if (isDynType) {
            String baseType = getBasicType(docType);
            String[] parentTypes =
                getParentIds(baseType) != null
                    ? getParentIds(baseType)
                    : new String[0];
            while (typeName == null && index < parentTypes.length) {
                if (index != -1) {
                    baseType = parentTypes[index];
                }
                if (docTypeHandler != null) {
                    // 動的文書種別の都度読み
                    typeName =
                        docTypeHandler.getDynamicDocTypeName(
                            baseType,
                            docType,
                            context.getLoginGroupID());
                    // userInfo.getLoginGroupID());
                } else {
                    // 動的文書種別のメモリ読み
                    typeName =
                        this.handler.getDynamicDocTypeName(
                            baseType,
                            docType,
                            context.getLoginGroupID());
                    // userInfo.getLoginGroupID());
                }
                index++;
            }
            return typeName;
        }
        // 基本文書種別
        return getBizMessage(getDisplayStringKey(docType), context
            .getCompanyCode(), context.getLocaleID());
        // return getBizMessage(getDisplayStringKey(docType), companyCd,
        // userInfo
        // .getLocale());
    }

    /**
     * 動的文書種別情報をシステムに保存します。
     * 
     * @param baseDocType
     *            動的文書種別情報が紐付く基本文書種別
     * @param dynamicTypeInfo
     *            動的文書種別情報
     * @param loginGroupId
     *            ログイングループID
     * @throws IOException
     *             動的文書ファイルのエラー
     * 
     */
    public void saveDynamicTypefile(String baseDocType,
            Map<String, String> dynamicTypeInfo, String loginGroupId)
        throws IOException {
        DynamicDocTypeHandler tempHandler = null;
        if (this.handler != null) {
            tempHandler = this.handler;
        } else {
            tempHandler = new DynamicDocTypeHandler();
        }

        tempHandler.saveFile(dynamicTypeInfo, baseDocType, loginGroupId);

        tempHandler = null;
    }

    /**
     * 基本文書種別に紐付く動的文書種別情報を取得します。
     * 
     * @param baseDocType
     *            　基本文書種別
     * @param loginGroupId
     *            ログイングループID
     * 
     * @return 動的文書種別情報
     * @throws IOException
     *             動的文書ファイルのエラー
     */
    public Map<String, String> getDynamicDocTypeMap(String baseDocType,
            String loginGroupId) throws IOException {
        DynamicDocTypeHandler tempHandler = null;
        if (this.handler != null) {
            tempHandler = this.handler;
        } else {
            tempHandler = new DynamicDocTypeHandler();
        }
        Map<String, String> map =
            tempHandler.getDynamicDocTypeMap(baseDocType, loginGroupId);
        tempHandler = null;
        return map;
    }

    /**
     * Bizの名称文字列を取得します。<br>
     * Bizの{@link DefaultNamedItemResource}を利用して名称を取得します。<br>
     * その際に会社コードを必要とします。<br>
     * 
     * @param key
     *            Biz用のリソースキー
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケール
     * @return 名称文字列
     */
    public String getBizMessage(String key, String companyCd, Locale localeId) {
        DefaultNamedItemResource defaultNamedItemResource =
            new DefaultNamedItemResource();

        return defaultNamedItemResource.getString(
            new NamedItem(key, companyCd),
            localeId);

    }

    /**
     * Bizの名称文字列を取得します。<br>
     * Bizの{@link DefaultNamedItemResource}を利用して名称を取得します。<br>
     * その際に会社コードを必要とします。<br>
     * 
     * @param key
     *            Biz用のリソースキー
     * @param companyCd
     *            会社コード
     * @param localeId
     *            ロケール
     * @return 名称文字列
     */
    public String getBizMessage(String key, String companyCd, String localeId) {
        DefaultNamedItemResource defaultNamedItemResource =
            new DefaultNamedItemResource();

        return defaultNamedItemResource.getString(
            new NamedItem(key, companyCd),
            new Locale(localeId));

    }
}
