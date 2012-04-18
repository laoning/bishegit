SELECT
    con.crm_contact_cd,                                                  --CRMコンタクトコード
    con.crm_contact_name,                                                --CRMコンタクト名
    con.crm_contact_name_kana,                                           --CRMコンタクト名（カナ）
    con.sex,                                                             --性別
    con.belong,                                                          --所属
    con.post,                                                            --役職
    con.key_person,                                                      --キーパーソン
    con.hometown,                                                        --出身地
    con.version_no,                                                      --バージョン番号
    (CASE WHEN 
        ((cha.crm_contact_cd >= 'A' OR cha.crm_contact_cd < 'A') 
        AND 
        (uuu.user_name >= 'A' OR uuu.user_name < 'A'))
        THEN uuu.user_name 
        ELSE '' END) AS mainChargeUserName                                 --主担当者
FROM 
    crm_cc_contact con                                                                --CRMコンタクト
LEFT OUTER JOIN 
    crm_cc_contact_charge_user cha                                         --CRMコンタクト担当者
ON 
    con.company_cd = cha.company_cd
AND
    con.crm_contact_cd = cha.crm_contact_cd
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
    con.company_cd = /*companyCd*/
AND
    con.crm_contact_cd = /*crmContactCd*/
AND
    con.locale_id = /*localeId*/
AND
    con.start_date <= /*systemDate*/
AND
    con.end_date > /*systemDate*/
AND
    (con.new_crm_contact_cd IS NULL OR con.new_crm_contact_cd = '')
AND
    con.dummy_flag = '0'
AND
    con.delete_flag = '0'