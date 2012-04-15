SELECT 
    tdfk_cd,                                 --都道府県コード
    skcs_cd,                                 --市区町村コード
    start_date,                              --開始日
    end_date,                                --終了日
    tdfk_address,                            --都道府県名
    skcs_address                             --市区町村名

FROM crm_cc_skcs_cmn
WHERE
    start_date <= /*searchBaseDate*/                      --開始日
    
    AND end_date > /*searchBaseDate*/                     --終了日
    
    /*IF tdfkAddress != null && tdfkAddress.length() > 0 */
    AND tdfk_address LIKE /*tdfkAddress*/ ESCAPE '|'     --都道府県名
    /*END*/
    
    /*IF skcsAddress != null && skcsAddress.length() > 0 */
    AND skcs_address LIKE /*skcsAddress*/ ESCAPE '|'    --市区町村名
    /*END*/
    
    /*IF tdfkCd != null && tdfkCd.length() > 0 */
    AND tdfk_cd  LIKE /*tdfkCd*/ ESCAPE '|'             --都道府県コード
    /*END*/
    
    /*IF skcsCd != null && skcsCd.length() > 0 */
    AND skcs_cd LIKE /*skcsCd*/ ESCAPE '|'              --市区町村コード
    /*END*/
        
    AND delete_flag = '0'
   
/*IF !countQuery*/
  /*IF order*/
ORDER BY 
    /*$orderByClause*/
  /*END*/
/*END*/
