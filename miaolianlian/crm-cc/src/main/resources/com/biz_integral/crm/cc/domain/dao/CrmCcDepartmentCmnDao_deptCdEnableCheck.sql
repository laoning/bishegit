SELECT department_cd                                        --組織コード
FROM crm_cc_department_cmn                                  --組織_共通
WHERE company_cd = /*companyCd*/                            --会社コード
AND department_set_cd = /*departmentSetCd*/                 --会社組織セットコード
AND department_cd = /*departmentCd*/                        --組織コード
AND locale_id = /*localeId*/                                --ロケールID
AND start_date <= /*salesActivityStartDate*/                --開始日
AND end_date > /*salesActivityStartDate*/                   --終了日
AND delete_flag = '0'
