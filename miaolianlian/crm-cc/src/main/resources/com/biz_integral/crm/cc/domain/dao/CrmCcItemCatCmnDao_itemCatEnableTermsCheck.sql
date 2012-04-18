SELECT item_category_cd                                     --品目カテゴリコード
FROM crm_cc_item_cat_cmn                                    --品目カテゴリ_共通
WHERE item_category_set_cd = /*itemCategorySetCd*/          --品目カテゴリセットコード
AND item_category_cd = /*itemCategoryCd*/                   --品目カテゴリコード
AND locale_id = /*localeId*/                                --ロケールID
AND start_date <= /*salesActivityStartDate*/                --開始日
AND end_date > /*salesActivityStartDate*/                   --終了日
AND delete_flag = '0'