SELECT crm_account_cd                                   --CRMアカウントコード
FROM crm_cc_account_charge_user                         --CRMアカウント担当者
WHERE company_cd = /*companyCd*/                        --会社コード
AND crm_account_cd = /*crmAccountCd*/                   --CRMアカウントコード
AND user_cd = /*recordUserCd*/                          --最終更新者
AND crm_domain_cd = (
    SELECT parameter_value
    FROM crm_cc_parameter
    WHERE company_cd = /*companyCd*/
    AND parameter_cd = 'CRMCC0002'
    AND start_date <= /*systemDate*/
    AND end_date > /*systemDate*/
    AND delete_flag = '0')
AND start_date <= /*salesActivityStartDate*/            --開始日
AND end_date > /*salesActivityStartDate*/               --終了日
AND delete_flag = '0'