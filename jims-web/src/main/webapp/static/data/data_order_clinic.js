//非药品
var clinicData={};
clinicData.dictType="V_CLINIC_NAME_PRICE";
clinicData.isOrgId=false;
clinicData.inputParamVos =inputParamVos;
var clinicOrderData = [];
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        clinicOrderData = data;
    }
});