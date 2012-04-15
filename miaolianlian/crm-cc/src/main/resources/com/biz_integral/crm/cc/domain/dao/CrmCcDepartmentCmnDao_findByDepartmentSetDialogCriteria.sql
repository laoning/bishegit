SELECT 
    company_cd,                              --会社コード
    locale_id,                               --ロケールID
    start_date,                              --開始日
    end_date,                                --終了日
    department_set_cd,                       --会社組織セットコード
    department_cd,                           --組織コード
    department_name,                         --組織名
    department_short_name,                   --組織略称
    department_search_name,                  --組織検索名
    delete_flag                              --削除フラグ

FROM crm_cc_department_cmn
WHERE                       
    company_cd = /*companyCd*/                              --会社コード
    
    AND locale_id = /*localeId*/                            --ロケールID
    
    AND start_date <= /*systemDate*/                        --開始日
    
    AND end_date > /*systemDate*/                           --終了日
    
    AND department_set_cd = /*departmentSetCd*/             --会社組織セットコード
    
    /*IF departmentCd != null && departmentCd.length() > 0 */
    AND department_cd LIKE /*departmentCd*/ ESCAPE '|'    --組織コード
    /*END*/
    
    /*IF departmentName != null && departmentName.length() > 0 */
    AND department_name LIKE /*departmentName*/ ESCAPE '|'    --組織名
    /*END*/
    
    /*IF departmentShortName != null && departmentShortName.length() > 0 */
    AND department_short_name LIKE /*departmentShortName*/ ESCAPE '|'    --組織略称
    /*END*/
    
    /*IF departmentSearchName != null && departmentSearchName.length() > 0 */
    AND department_search_name LIKE /*departmentSearchName*/ ESCAPE '|'  --組織検索名
    /*END*/
        
    AND delete_flag = '0'
   
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
