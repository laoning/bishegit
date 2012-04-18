SELECT
    c5.crm_account_cd               AS  crmAccountCd,
    c5.crm_contact_cd               AS  crmContactCd
FROM 
    crm_cc_account               c1,
    crm_cc_contact               c3,
    crm_cc_account_contact       c5
WHERE
    c1.company_cd = c5.company_cd
AND
    c1.crm_account_cd = c5.crm_account_cd
AND
    c1.company_cd= /*companyCd*/'0001'
AND
    c1.locale_id = /*localeId*/'ja'
AND
    c1.start_date <= /*baseDate*/'2010/10/30'
AND
    c1.end_date > /*baseDate*/'2010/10/30'
AND
    (c1.new_crm_account_cd IS NULL OR c1.new_crm_account_cd = '')
AND
    c1.dummy_flag = '0'
AND
    c1.delete_flag = '0'
AND
    c3.company_cd = c5.company_cd
AND
    c3.crm_contact_cd = c5.crm_contact_cd
AND
    c3.company_cd= /*companyCd*/'0001'
AND
    c3.locale_id = /*localeId*/'ja'
AND
    c3.start_date <= /*baseDate*/'2010/10/30'
AND
    c3.end_date > /*baseDate*/'2010/10/30'
AND
    (c3.new_crm_contact_cd IS NULL OR c3.new_crm_contact_cd = '')
AND
    c3.dummy_flag = '0'
AND
    c3.delete_flag = '0'
AND
    c5.company_cd= /*companyCd*/'0001'
AND
    c5.start_date <= /*baseDate*/'2010/10/30'
AND
    c5.end_date > /*baseDate*/'2010/10/30'
AND
    c5.delete_flag = '0'
AND EXISTS
(SELECT c2.crm_account_cd 
FROM 
    crm_cc_account_charge_user   c2
WHERE 
    c2.company_cd = c1.company_cd
AND
    c2.crm_account_cd = c1.crm_account_cd
AND
    c2.company_cd = /*companyCd*/'0001'
AND
    c2.user_cd = /*userCd*/'ba00101'
AND
    c2.crm_domain_cd = /*crmDomainCd*/'CrmDomain001'
AND
    c2.start_date <= /*baseDate*/'2010/10/30'
AND
    c2.end_date > /*baseDate*/'2010/10/30'
AND
    c2.delete_flag = '0')
AND EXISTS
(SELECT c4.crm_contact_cd 
FROM 
    crm_cc_contact_charge_user   c4
WHERE 
    c4.company_cd = c3.company_cd
AND
    c4.crm_contact_cd = c3.crm_contact_cd
AND
    c4.company_cd = /*companyCd*/'0001'
AND
    c4.user_cd = /*userCd*/'ba00101'
AND
    c4.start_date <= /*baseDate*/'2010/10/30'
AND
    c4.end_date > /*baseDate*/'2010/10/30'
AND
    c4.delete_flag = '0')
UNION ALL
SELECT
    c5.crm_account_cd               AS  crmAccountCd,
    c5.crm_contact_cd               AS  crmContactCd
FROM 
    crm_cc_account               c1,
    crm_cc_contact               c3,
    crm_cc_account_contact       c5
WHERE
    c1.company_cd = c5.company_cd
AND
    c1.crm_account_cd = c5.crm_account_cd
AND
    c1.company_cd= /*companyCd*/'0001'
AND
    c1.locale_id = /*localeId*/'ja'
AND
    c1.start_date <= /*baseDate*/'2010/10/30'
AND
    c1.end_date > /*baseDate*/'2010/10/30'
AND
    (c1.new_crm_account_cd IS NULL OR c1.new_crm_account_cd = '')
AND
    c1.dummy_flag = '0'
AND
    c1.delete_flag = '0'
AND
    c3.company_cd = c5.company_cd
AND
    c3.crm_contact_cd = c5.crm_contact_cd
AND
    c3.company_cd= /*companyCd*/'0001'
AND
    c3.locale_id = /*localeId*/'ja'
AND
    c3.start_date <= /*baseDate*/'2010/10/30'
AND
    c3.end_date > /*baseDate*/'2010/10/30'
AND
    (c3.new_crm_contact_cd IS NULL OR c3.new_crm_contact_cd = '')
AND
    c3.dummy_flag = '0'
AND
    c3.delete_flag = '0'
AND
    c5.company_cd= /*companyCd*/'0001'
AND
    c5.start_date <= /*baseDate*/'2010/10/30'
AND
    c5.end_date > /*baseDate*/'2010/10/30'
AND
    c5.delete_flag = '0'
AND EXISTS
(SELECT c2.crm_account_cd 
FROM 
    crm_cc_account_charge_user   c2
WHERE 
    c2.company_cd = c1.company_cd
AND
    c2.crm_account_cd = c1.crm_account_cd
AND
    c2.company_cd = /*companyCd*/'0001'
AND
    c2.user_cd = /*userCd*/'ba00101'
AND
    c2.crm_domain_cd = /*crmDomainCd*/'CrmDomain001'
AND
    c2.start_date <= /*baseDate*/'2010/10/30'
AND
    c2.end_date > /*baseDate*/'2010/10/30'
AND
    c2.delete_flag = '0')
AND NOT EXISTS
(SELECT c4.crm_contact_cd 
FROM 
    crm_cc_contact_charge_user   c4
WHERE 
    c4.company_cd = c3.company_cd
AND
    c4.crm_contact_cd = c3.crm_contact_cd
AND
    c4.company_cd = /*companyCd*/'0001'
AND
    c4.start_date <= /*baseDate*/'2010/10/30'
AND
    c4.end_date > /*baseDate*/'2010/10/30'
AND
    c4.delete_flag = '0')
ORDER BY 
crmAccountCd,
crmContactCd