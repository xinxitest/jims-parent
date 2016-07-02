var herbalDrugData = [];

var herbalDrug={};
herbalDrug.orgId="1";
herbalDrug.itemClass="B";
herbalDrug.dictType="v_drug_info_mz";



var comboGridComplete = [];
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
function comboGridCompleting(q,id){
    var drugNameData={};
    drugNameData.orgId="1";
    drugNameData.itemClass="B";
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
        $("#"+id).combogrid('setValue','');
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
            comboGridComplete = data;

        }
    });
}
