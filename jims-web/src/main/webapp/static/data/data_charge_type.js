var chargeType= [];
var chargeTypeData={};
chargeTypeData.dictType="charge_type_dict";
/**
 * 费别项目
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(chargeTypeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        chargeType=data;
    }
});
/**
 * 费别翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function chargeTypeFormatter(value, rowData, rowIndex) {
    if (value == 'undefined') {
        return;
    }

    for (var i = 0; i < chargeType.length; i++) {
        if (chargeType[i].id == value) {
            return chargeType[i].charge_type_name;
        }
    }
}