var herbalDrugData = [];
var herbalDrug={};
herbalDrug.isOrgId=false;
herbalDrug.itemClass="B";
herbalDrug.dictType="v_drug_info_mz";
herbalDrug.inputParamVos=inputParamVos;
/**
 * 中药药品
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(herbalDrug),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        herbalDrugData = data;
    }
});


//药品自动补全
function comboGridCompletingHerbalDrug(q,id){
    var drugNameData={};
    drugNameData.itemClass="B";
    drugNameData.isOrgId=false;
    drugNameData.dictType="v_drug_info_mz"
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName='rownum';
    InputParamVo1.colValue='20';
    InputParamVo1.operateMethod='<';
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }else{
        if(id!='' && id!=null){
            $("#"+id).combogrid('setValue','');
        }
    }
    drugNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(drugNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            herbalDrugData = data;

        }
    });
}
