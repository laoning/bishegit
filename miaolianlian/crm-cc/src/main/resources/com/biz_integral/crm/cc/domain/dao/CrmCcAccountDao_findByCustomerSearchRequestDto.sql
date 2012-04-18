SELECT
    c2.crm_account_cd               AS  crmAccountCd,          -- CRMアカウントコード
    ''                              AS  crmContactCd           -- CRMコンタクトコード
FROM 
    crm_cc_account c1,                                         -- CRMアカウントデーブル
    crm_cc_account_charge_user   c2                            -- CRMアカウント担当者デーブル
WHERE
    c2.company_cd = c1.company_cd
AND
    c2.crm_account_cd = c1.crm_account_cd
AND
    c1.company_cd=/*companyCd*/
AND
    c1.locale_id = /*localeId*/
AND
    c1.start_date <= /*baseDate*/
AND
    c1.end_date > /*baseDate*/
AND
    (c1.new_crm_account_cd IS NULL OR c1.new_crm_account_cd = '')
AND
    c2.company_cd = /*companyCd*/
AND
    c2.user_cd = /*userCd*/
AND
    c2.crm_domain_cd = /*crmDomainCd*/
AND
    c2.start_date <= /*baseDate*/
AND
    c2.end_date > /*baseDate*/
AND
    c1.dummy_flag = '0'
AND
    c1.delete_flag = '0'
AND
    c2.delete_flag = '0'
