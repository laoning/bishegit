SELECT crm_contact_cd                                       --CRMコンタクトコード
FROM crm_cc_contact                                         --CRMコンタクト
WHERE company_cd = /*companyCd*/                            --会社コード
AND crm_contact_cd = /*crmContactCd*/                       --CRMコンタクトコード
AND start_date <= /*salesActivityStartDate*/                --開始日
AND end_date > /*salesActivityStartDate*/                   --終了日
AND delete_flag = '0'
