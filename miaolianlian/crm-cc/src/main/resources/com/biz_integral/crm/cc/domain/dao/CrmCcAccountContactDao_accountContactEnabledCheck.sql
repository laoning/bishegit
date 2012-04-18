SELECT crm_account_cd                                       --CRMアカウントコード
FROM crm_cc_account_contact                                 --CRMアカウント
WHERE company_cd = /*companyCd*/                            --会社コード
AND crm_account_cd = /*crmAccountCd*/                       --CRMアカウントコード
AND crm_contact_cd = /*crmContactCd*/                       --CRMコンタクトコード
AND start_date <= /*salesActivityStartDate*/                --開始日
AND end_date > /*salesActivityStartDate*/                   --終了日
AND delete_flag = '0'
