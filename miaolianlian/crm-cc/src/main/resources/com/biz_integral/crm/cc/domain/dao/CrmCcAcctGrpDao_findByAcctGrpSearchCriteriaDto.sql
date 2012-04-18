SELECT
    grp.crm_account_group_cd
    , grp.crm_account_group_name
    , cmn_dept.department_name
    , cmn_user.user_name
FROM
    crm_cc_acct_grp grp
    LEFT OUTER JOIN crm_cc_acct_grp_charge_user charge_user
    ON grp.company_cd = charge_user.company_cd
    AND grp.crm_account_group_cd = charge_user.crm_account_group_cd
    AND charge_user.crm_domain_cd = /*crmDomainCd*/'crmDomain001'
    AND charge_user.main_charge_flag = /*mainChargeFlag*/'1'
    AND charge_user.start_date <= /*systemDate*/'2011/02/01 00:00:00.00'
    AND charge_user.end_date > /*systemDate*/'2011/02/01 00:00:00.00'
    AND charge_user.delete_flag = /*deleteFlag*/'0'
    LEFT OUTER JOIN crm_cc_user_cmn cmn_user
    ON charge_user.user_cd = cmn_user.user_cd
    AND cmn_user.locale_id = /*localeId*/'ja'
    AND cmn_user.start_date <= /*systemDate*/'2011/02/01 00:00:00.00'
    AND cmn_user.end_date > /*systemDate*/'2011/02/01 00:00:00.00'
    AND cmn_user.delete_flag = /*deleteFlag*/'0'
    LEFT OUTER JOIN crm_cc_acct_grp_charge_dept charge_dept
    ON grp.company_cd = charge_dept.company_cd
    AND grp.crm_account_group_cd = charge_dept.crm_account_group_cd
    AND charge_dept.crm_domain_cd = /*crmDomainCd*/'crmDomain001'
    AND charge_dept.main_charge_flag = /*mainChargeFlag*/'1'
    AND charge_dept.start_date <= /*systemDate*/'2011/02/01 00:00:00.00'
    AND charge_dept.end_date > /*systemDate*/'2011/02/01 00:00:00.00'
    AND charge_dept.delete_flag = /*deleteFlag*/'0'
    LEFT OUTER JOIN crm_cc_department_cmn cmn_dept
    ON charge_dept.department_cd = cmn_dept.department_cd
    AND cmn_dept.company_cd = /*companyCd*/'0001'
    AND cmn_dept.department_set_cd = /*crmDepartmentSetCd*/'0001'
    AND cmn_dept.locale_id = /*localeId*/'ja'
    AND cmn_dept.start_date <= /*systemDate*/'2011/02/01 00:00:00.00'
    AND cmn_dept.end_date > /*systemDate*/'2011/02/01 00:00:00.00'
    AND cmn_dept.delete_flag = /*deleteFlag*/'0'
WHERE
    grp.company_cd = /*companyCd*/'0001'
    AND grp.locale_id = /*localeId*/'ja'
    AND grp.start_date <= /*systemDate*/'2011/02/01 00:00:00.00'
    AND grp.end_date > /*systemDate*/'2011/02/01 00:00:00.00'
    AND grp.delete_flag = /*deleteFlag*/'0'
/*IF crmAccountGroupCd != null && crmAccountGroupCd.length() > 0*/
    AND grp.crm_account_group_cd like /*crmAccountGroupCd*/'crmacountgroupcd'
/*END*/
/*IF crmAccountGroupName != null && crmAccountGroupName.length() > 0*/
    AND grp.crm_account_group_name like /*crmAccountGroupName*/'crmacountgroupname'
/*END*/
/*IF crmAccountGroupSearchName != null && crmAccountGroupSearchName.length() > 0*/
    AND grp.crm_account_group_search_name like /*crmAccountGroupSearchName*/'crmacountgroupshortname'
/*END*/
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
    