UPDATE crm_cc_account                                       --CRMアカウント
SET
crm_account_name = /*crmAccountName*/,                      --CRMアカウント名
crm_account_name_kana = /*crmAccountNameKana*/,             --CRMアカウント名（カナ）
crm_account_status = /*crmAccountStatus*/,                  --状況
crm_account_type = /*crmAccountType*/,                      --区分
zip_code = /*zipCode*/,                                     --郵便番号
address1 = /*address1*/,                                    --住所１
address2 = /*address2*/,                                    --住所２
address3 = /*address3*/,                                    --住所３
version_no = /*versionNo*/ + 1,                             --バージョン番号
record_user_cd = /*recordUserCd*/,                          --最終更新者
record_date = /*recordDate*/                                --最終更新日
WHERE
company_cd = /*companyCd*/                                  --会社コード
AND crm_account_cd = /*crmAccountCd*/                       --CRMアカウントコード
AND version_no = /*versionNo*/