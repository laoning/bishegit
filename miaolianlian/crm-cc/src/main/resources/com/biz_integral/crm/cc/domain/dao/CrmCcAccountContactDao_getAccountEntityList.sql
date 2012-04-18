SELECT 
        t2.crm_account_name AS crm_account_name,
        t2.crm_account_status AS status,
        t2.important_level AS important_level,
        t2.crm_account_type AS type,
        t2.address1 AS address1,
        t2.crm_account_cd AS crm_account_cd,
        t1.start_date AS start_date,
        t1.end_date AS end_date,
        t3.user_name AS user_name,
        t3.user_cd AS user_cd,
        t1.term_cd AS term_cd,
        t1.version_no AS version_no,
        t2.delete_flag AS delete_flag
FROM crm_cc_account_contact t1,
      crm_cc_account t2 LEFT OUTER JOIN 
      (SELECT 
        t5.company_cd,
        t5.crm_account_cd,
        t5.start_date,
        t5.end_date,
        t5.main_charge_flag,
        t5.crm_domain_cd,
        t5.delete_flag,
        t4.user_name,
        t5.user_cd
      FROM crm_cc_account_charge_user t5 LEFT OUTER JOIN crm_cc_user_cmn t4
                ON t5.user_cd = t4.user_cd
                  /*IF localeId != null && localeId.length() > 0*/
			      AND t4.locale_id = /*localeId*/
			      /*END*/
			      AND t4.start_date <= /*systemDate*/
			      AND t4.end_date > /*systemDate*/
			      AND t4.delete_flag = '0'
      ) t3
      ON t2.company_cd = t3.company_cd
      AND t2.crm_account_cd = t3.crm_account_cd
      /*IF crmDomainCd != null && crmDomainCd.length() > 0*/
      AND t3.crm_domain_cd = /*crmDomainCd*/
      /*END*/
      AND t3.main_charge_flag = '1'
	  AND t3.start_date <= /*systemDate*/
	  AND t3.end_date > /*systemDate*/
	  AND t3.delete_flag = '0'

WHERE t1.company_cd = t2.company_cd
AND t1.crm_account_cd = t2.crm_account_cd
/*IF companyCd != null && companyCd.length() > 0*/
AND t1.company_cd = /*companyCd*/
/*END*/
/*IF crmContactCd != null && crmContactCd.length() > 0*/
AND t1.crm_contact_cd = /*crmContactCd*/
/*END*/
AND t1.delete_flag = '0'
/*IF companyCd != null && companyCd.length() > 0*/
AND t2.company_cd = /*companyCd*/
/*END*/
/*IF localeId != null && localeId.length() > 0*/
AND t2.locale_id = /*localeId*/
/*END*/
AND t2.start_date <= /*systemDate*/
AND t2.end_date > /*systemDate*/
AND t2.delete_flag = '0'

/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
