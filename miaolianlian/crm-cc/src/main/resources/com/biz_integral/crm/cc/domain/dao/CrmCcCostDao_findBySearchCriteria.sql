SELECT
    cost_cd AS cost_cd,                                           --経費コード
    cost_name AS cost_name,                                       --経費名
    start_date AS start_date,                                     --開始日
    end_date AS end_date                                          --終了日
FROM crm_cc_cost                                                 --CRM経費テーブル
WHERE
/*IF costCd != null && costCd.length() > 0 */
    cost_cd LIKE /*costCd*/ ESCAPE '|' AND                                  --経費コード
/*END*/
/*IF costName != null && costName.length() > 0 */
    cost_name LIKE /*costName*/ ESCAPE '|' AND                              --経費名
/*END*/
/*IF baseDate != null */
    start_date <= /*baseDate*/ AND                               --開始日
    /*baseDate*/ < end_date AND                                  --終了日
/*END*/
    company_cd = /*companyCd*/ AND                               --会社コード
    locale_id = /*localeId*/ AND                               --ロケールID
    delete_flag = '0'
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
