SELECT COUNT(*)
FROM crm_cc_contact
    LEFT OUTER JOIN crm_cc_contact_charge_user
    ON crm_cc_contact.crm_contact_cd = crm_cc_contact_charge_user.crm_contact_cd
WHERE crm_cc_contact.company_cd = /*companyCd*/
AND crm_cc_contact.crm_contact_cd = /*crmContactCd*/
AND crm_cc_contact.start_date <= /*salesActivityStartDate*/
AND crm_cc_contact.end_date > /*salesActivityStartDate*/
AND crm_cc_contact_charge_user.delete_flag = '0'
