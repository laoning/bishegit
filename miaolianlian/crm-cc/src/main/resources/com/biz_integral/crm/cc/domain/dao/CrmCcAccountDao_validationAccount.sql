SELECT  crm_account_cd AS count      --データ妥当性チェック アカウント有効チェック
FROM crm_cc_account                    --CRMアカウント
WHERE company_cd = /*companyCd*/       --会社コード
  AND locale_id  = /*localeId*/        --ロケールID
  AND crm_account_cd = /*crmAccountCd*/   --CRMアカウントコード
  AND start_date     <= /*baseDate*/      --開始日
  AND end_date       >  /*baseDate*/      --終了日
  AND delete_flag = '0'