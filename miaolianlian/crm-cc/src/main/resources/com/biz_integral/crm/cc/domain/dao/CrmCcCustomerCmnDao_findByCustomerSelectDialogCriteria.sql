SELECT 
    company_cd,                              --会社コード
    locale_id,                               --ロケールID
    start_date,                              --開始日
    end_date,                                --終了日
    customer_cd,                             --取引先コード
    customer_name,                           --取引先名
    customer_short_name,                     --取引先略称
    customer_search_name,                    --取引先検索名
    delete_flag                              --削除フラグ

FROM crm_cc_customer_cmn
WHERE                       
    company_cd = /*companyCd*/                                    --会社コード
    
    AND locale_id = /*localeId*/                                  --ロケールID
    
    AND start_date <= /*searchBaseDate*/                          --開始日
    
    AND end_date > /*searchBaseDate*/                             --終了日
    
    /*IF customerCd != null && customerCd.length() > 0 */
    AND customer_cd LIKE /*customerCd*/ ESCAPE '|'               --取引先コード
    /*END*/
    
    /*IF customerName != null && customerName.length() > 0 */
    AND customer_name LIKE /*customerName*/ ESCAPE '|'           --取引先名
    /*END*/
    
    /*IF customerShortName != null && customerShortName.length() > 0 */
    AND customer_short_name LIKE /*customerShortName*/ ESCAPE '|'    --取引先略称
    /*END*/
    
    /*IF customerSearchName != null && customerSearchName.length() > 0 */
    AND customer_search_name LIKE /*customerSearchName*/ ESCAPE '|'  --取引先検索名
    /*END*/
        
    AND delete_flag = '0'
   
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
