SELECT
    item_category_set_cd        AS item_category_set_cd,           --品目カテゴリセットコード
    item_category_cd            AS item_category_cd,               --品目カテゴリコード
    locale_id                   AS locale_id,                      --ロケールID
    term_cd                     AS term_cd,                        --期間コード
    start_date                  AS start_date,                     --開始日
    end_date                    AS end_date,                       --終了日
    item_category_name          AS item_category_name,             --品目カテゴリ名
    item_category_short_name    AS item_category_short_name,       --品目カテゴリ略称
    item_category_search_name   AS item_category_search_name,      --品目カテゴリ検索名
    notes                       AS notes,                          --備考
    delete_flag                 AS delete_flag                     --削除フラグ
FROM
    crm_cc_item_cat_cmn                                         --TABLE_品目カテゴリ_共通
WHERE
        start_date <= /*baseDate*/

    AND end_date > /*baseDate*/
    
    /*IF itemCategoryCd!=null && itemCategoryCd.length()>0 */ 
    AND item_category_cd LIKE /*itemCategoryCd*/ ESCAPE '|'                         --品目カテゴリコード.前方一致
    /*END*/
    
    /*IF itemCategoryName!=null && itemCategoryName.length()>0 */ 
    AND item_category_name LIKE /*itemCategoryName*/ ESCAPE '|'                     --品目カテゴリ名.含む検索
    /*END*/
    
    /*IF itemCategoryShortName!=null && itemCategoryShortName.length()>0 */ 
    AND item_category_short_name LIKE /*itemCategoryShortName*/ ESCAPE '|'          --品目カテゴリ略称.含む検索
    /*END*/
    
    /*IF itemCategorySearchName!=null && itemCategorySearchName.length()>0 */ 
    AND item_category_search_name LIKE /*itemCategorySearchName*/ ESCAPE '|'        --品目カテゴリ検索名.含む検索
    /*END*/
    
    AND item_category_set_cd = /*itemCategorySetCd*/                                --品目カテゴリセットコード
    
    AND delete_flag = /*deleteFlag*/                                                --削除フラグ
    
    AND locale_id = /*localeId*/

/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/