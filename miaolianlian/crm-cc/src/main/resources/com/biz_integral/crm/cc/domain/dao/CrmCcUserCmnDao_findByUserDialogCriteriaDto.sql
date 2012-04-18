SELECT
    T1.user_cd,                                 --ユーザコード
    T1.user_name,                               --ユーザ名
    T2.department_cd,                           --組織コード
    T2.department_name,                         --組織名
    T1.start_date,                              --有効期間開始日
    T1.end_date                                 --有効期間終了日
FROM
    crm_cc_user_cmn T1,
    crm_cc_department_cmn T2,
    crm_cc_department_ath_cmn T3
WHERE
    T1.user_cd = T3.user_cd
AND
    T1.locale_id = T2.locale_id
AND
    T3.company_cd = T2.company_cd
AND
    T3.department_set_cd = T2.department_set_cd
AND
    T3.department_cd = T2.department_cd
AND
    T1.locale_id = /*localeId*/                                 --ロケールID
    /*IF searchBaseDate!= null*/
AND
    T1.start_date <= /*searchBaseDate*/                         --ユーザ.開始日
AND
    /*searchBaseDate*/ < T1.end_date                            --ユーザ.終了日
AND
    T3.start_date <= /*searchBaseDate*/                         --組織所属.開始日
AND
    /*searchBaseDate*/ < T3.end_date                            --組織所属.終了日
AND
    T2.start_date <= /*searchBaseDate*/                         --組織.開始日
AND
    /*searchBaseDate*/ < T2.end_date                            --組織.終了日
    /*END*/
    /*IF userCd != null && userCd.length() > 0 */
AND
    T1.user_cd LIKE /*userCd*/ ESCAPE '|'                      --ユーザコード
    /*END*/
    /*IF userName != null && userName.length() > 0 */
AND
    T1.user_name LIKE /*userName*/ ESCAPE '|'                  --ユーザ名  
    /*END*/
    /*IF userShortName != null && userShortName.length() > 0 */
AND
    T1.user_search_name LIKE /*userShortName*/ ESCAPE '|'      --ユーザ検索名
    /*END*/ 
AND
    T3.company_cd = /*companyCd*/                               --会社コード
    /*IF departmentCd != null && departmentCd.length() > 0 */
AND
    T3.department_cd = /*departmentCd*/                         --組織コード
    /*END*/ 
AND
    T1.delete_flag = /*deleteFlag*/                             --削除フラグ
AND
    T2.delete_flag = /*deleteFlag*/                             --削除フラグ
AND
    T3.delete_flag = /*deleteFlag*/                             --削除フラグ
AND
    T3.department_set_cd = /*companyDepartmentSetCd*/
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
