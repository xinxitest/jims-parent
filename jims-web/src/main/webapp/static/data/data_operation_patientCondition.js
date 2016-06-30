var patientCondition=[];//手术病情
var patientConditionData={};
patientConditionData.orgId="";
patientConditionData.dictType="SYS_DICT";

var InputParamVo = {};
var inputParamVos=[];
var q='PATIENT_STATUS_DICT';
InputParamVo.colName = 'TYPE';
InputParamVo.colValue = q;
InputParamVo1.colValue = '20';
InputParamVo.operateMethod = '=';
inputParamVos.push(InputParamVo);
patientConditionData.inputParamVos = inputParamVos;
$.ajax({
    'type':'POST',
    'url': basePath + '/input-setting/listParam',
    data:JSON.stringify(patientConditionData),
    'contentType':'application/json',
    'dataType':'json',
    'async':'false',
    'success':function(data){
        patientCondition=data;
    }
})
/**
 * 手术病情翻译
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function patientConditionFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < patientCondition.length; i++) {
        if (patientCondition[i].id == value) {
            return operationScaleName[i].label;
        }
    }
}