SELECT 
    t1.crm_account_group_cd,
    t1.crm_account_group_name,
    delete_flag
FROM crm_cc_acct_grp t1
WHERE 
        t1.company_cd = /*companyCd*/
    AND t1.locale_id = /*localeId*/
    AND t1.start_date <= /*systemDate*/ 
    AND t1.end_date > /*systemDate*/
    AND t1.delete_flag = '0'
/*IF crmAccountGroupCd != null && crmAccountGroupCd.length() > 0*/
    AND t1.crm_account_group_cd like /*crmAccountGroupCd*/
/*END*/
/*IF crmAccountGroupName != null && crmAccountGroupName.length() > 0*/
    AND t1.crm_account_group_name like /*crmAccountGroupName*/
/*END*/
/*IF crmAccountGroupSearchName != null && crmAccountGroupSearchName.length() > 0*/
    AND t1.crm_account_group_search_name like /*crmAccountGroupSearchName*/
/*END*/
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
