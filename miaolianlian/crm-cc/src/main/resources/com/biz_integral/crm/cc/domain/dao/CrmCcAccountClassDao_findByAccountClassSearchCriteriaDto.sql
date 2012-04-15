SELECT
    t1.locale_id,
    t1.crm_account_class_cd,
    t1.crm_account_class_name
FROM crm_cc_account_class t1
WHERE 
        t1.company_cd = /*companyCd*/
    AND t1.locale_id = /*localeId*/
    AND t1.delete_flag = /*deleteFlag*/
/*IF crmAccountClassCd != null && crmAccountClassCd.length() > 0*/
    AND t1.crm_account_class_cd like /*crmAccountClassCd*/
/*END*/
/*IF crmAccountClassName != null && crmAccountClassName.length() > 0*/
    AND t1.crm_account_class_name like /*crmAccountClassName*/
/*END*/
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
