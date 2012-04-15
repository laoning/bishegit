SELECT 
    t1.crm_account_cd  , 
    t2.crm_account_name, 
    t1.term_cd          
FROM 
    crm_cc_acct_grp_ath t1
    LEFT JOIN
    crm_cc_account t2
    ON t2.company_cd = /*companyCd*/
    AND t2.locale_id = /*localeId*/
    AND t1.crm_account_cd = t2.crm_account_cd
    AND t2.start_date <= /*baseDate*/ 
    AND t2.end_date   > /*baseDate*/ 
    AND t2.delete_flag = '0'
WHERE
    t1.crm_account_group_cd = /*crmAccountGroupCd*/
    AND t1.company_cd = /*companyCd*/
    AND t1.start_date <= /*baseDate*/ 
    AND t1.end_date   > /*baseDate*/ 
    AND t1.delete_flag = '0'
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/