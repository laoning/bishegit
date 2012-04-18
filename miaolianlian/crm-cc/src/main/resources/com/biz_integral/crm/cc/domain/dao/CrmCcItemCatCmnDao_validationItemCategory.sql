SELECT  item_category_set_cd AS count      --品目カテゴリコード有効チェック
FROM crm_cc_item_cat_cmn                    --品目カテゴリ_共通
WHERE item_category_set_cd = /*itemCategorySetCd*/   --品目カテゴリセットコード
  AND item_category_cd = /*itemCategoryCd*/          --品目カテゴリコード
  AND locale_id  = /*localeId*/                       --ロケールID
  AND start_date <= /*baseDate*/                     --開始日
  AND end_date    > /*baseDate*/                        --終了日
  AND delete_flag = '0'