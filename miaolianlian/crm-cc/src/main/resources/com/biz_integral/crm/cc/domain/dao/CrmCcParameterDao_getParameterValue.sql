SELECT 
    parameter_value
FROM 
    crm_cc_parameter
WHERE 
    parameter_cd = /*parameterCd*/'xxx'
AND company_cd = /*companyCd*/'xxx'
AND delete_flag = '0'