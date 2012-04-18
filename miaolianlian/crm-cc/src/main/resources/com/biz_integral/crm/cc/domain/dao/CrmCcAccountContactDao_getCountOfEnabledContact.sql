SELECT COUNT(*)
FROM crm_cc_account_contact
WHERE company_cd = /*companyCd*/
AND crm_account_cd = /*crmAccountCd*/
AND crm_contact_cd = /*crmContactCd*/
AND start_date <= /*salesActivityStartDate*/
AND end_date > /*salesActivityStartDate*/
AND delete_flag = '0'
