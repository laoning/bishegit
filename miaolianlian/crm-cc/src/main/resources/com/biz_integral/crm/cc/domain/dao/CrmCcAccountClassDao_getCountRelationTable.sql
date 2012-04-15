SELECT
    company_cd 
FROM
    crm_cc_account_class_ath 
WHERE
    company_cd = /*companyCd*/'0001' 
    AND crm_account_class_cd = /*crmAccountClassCd*/'class001' 
    AND delete_flag = /*deleteFlag*/'0' 
UNION ALL 
SELECT
    company_cd 
FROM
    crm_cc_acct_grp_class_ath 
WHERE
    company_cd = /*companyCd*/'0001' 
    AND crm_account_class_cd = /*crmAccountClassCd*/'class001' 
    AND delete_flag = /*deleteFlag*/'0' 
UNION ALL 
SELECT
    company_cd 
FROM
    crm_cc_account_attr 
WHERE
    company_cd = /*companyCd*/'0001' 
    AND crm_account_class_cd = /*crmAccountClassCd*/'class001' 
    AND delete_flag = /*deleteFlag*/'0' 
UNION ALL 
SELECT
    company_cd 
FROM
    crm_cc_account_grp_attr 
WHERE
    company_cd = /*companyCd*/'0001' 
    AND crm_account_class_cd = /*crmAccountClassCd*/'class001' 
    AND delete_flag = /*deleteFlag*/'0'
