UPDATE crm_cc_contact                                       --CRMコンタクト
SET
crm_contact_name = /*crmContactName*/,                      --CRMコンタクト名
crm_contact_name_kana = /*crmContactNameKana*/,             --CRMコンタクト名（カナ）
sex = /*sex*/,                                              --性別
belong = /*belong*/,                                        --所属
post = /*post*/,                                            --役職
key_person = /*keyPerson*/,                                 --キーパーソン
hometown = /*hometown*/,                                    --出身地
version_no = /*versionNo*/ + 1,                             --バージョン番号
record_user_cd = /*recordUserCd*/,                          --最終更新者
record_date = /*recordDate*/                                --最終更新日
WHERE
company_cd = /*companyCd*/                                  --会社コード
AND crm_contact_cd = /*crmContactCd*/                       --CRMコンタクトコード
AND version_no = /*versionNo*/
