SELECT
    acc.crm_account_cd,                                                     --CRMアカウントコード
    acc.crm_account_name,                                                   --CRMアカウント名
    acc.crm_account_name_kana,                                              --CRMアカウント名（カナ）
    acc.crm_account_status,                                                 --状況
    acc.crm_account_type,                                                   --区分
    acc.zip_code,                                                           --郵便番号
    acc.address1,                                                           --住所1
    acc.address2,                                                           --住所2
    acc.address3,                                                           --住所3
    acc.version_no,                                                         --バージョン番号
    (CASE WHEN 
        ((cha.crm_account_cd >= 'A' OR cha.crm_account_cd < 'A') 
        AND 
        (uuu.user_name >= 'A' OR uuu.user_name < 'A'))
        THEN uuu.user_name 
        ELSE '' END) AS mainChargeUserName                                 --主担当者
FROM 
    crm_cc_account acc                                                                --CRMコンタクト
LEFT OUTER JOIN 
    crm_cc_account_charge_user cha                                         --CRMコンタクト担当者
ON 
    acc.company_cd = cha.company_cd
AND
    acc.crm_account_cd = cha.crm_account_cd
AND
    cha.crm_domain_cd = /*crmDomainCd*/''
AND
    cha.start_date <= /*systemDate*/
AND
    cha.end_date > /*systemDate*/
AND
    cha.main_charge_flag = '1'
AND
    cha.delete_flag = '0'
LEFT OUTER JOIN 
    crm_cc_user_cmn uuu                                                    --ユーザ_共通
ON 
    cha.user_cd = uuu.user_cd
AND
    uuu.locale_id = /*localeId*/
AND
    uuu.start_date <= /*systemDate*/
AND
    uuu.end_date > /*systemDate*/
AND
    uuu.delete_flag = '0'
WHERE 
    acc.company_cd = /*companyCd*/
AND
    acc.crm_account_cd = /*crmAccountCd*/
AND
    acc.locale_id = /*localeId*/
AND
    acc.start_date <= /*systemDate*/
AND
    acc.end_date > /*systemDate*/
AND
    (acc.new_crm_account_cd IS NULL OR acc.new_crm_account_cd = '')
AND
    acc.dummy_flag = '0'
AND
    acc.delete_flag = '0'