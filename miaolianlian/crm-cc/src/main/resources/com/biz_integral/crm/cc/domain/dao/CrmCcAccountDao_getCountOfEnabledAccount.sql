SELECT COUNT(*)
FROM crm_cc_account
    LEFT OUTER JOIN crm_cc_account_charge_user
    ON crm_cc_account.crm_account_cd = crm_cc_account_charge_user.crm_account_cd
WHERE crm_cc_account.company_cd = /*companyCd*/
AND crm_cc_account.crm_account_cd = /*crmAccountCd*/
AND crm_cc_account.start_date <= /*salesActivityStartDate*/
AND crm_cc_account.end_date > /*salesActivityStartDate*/
AND crm_cc_account_charge_user.delete_flag = '0'
