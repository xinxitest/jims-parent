var skinFlag = [];
var skinResult =  [];

/**
 * 皮试
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=SKIN_FLAG_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        skinFlag=data;
    }
});

/**
 * 皮试翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function skinFlagFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < skinFlag.length; i++) {
        if (skinFlag[i].value == value) {
            return skinFlag[i].label;
        }
    }
}

/**
 * 皮试结果
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=SKIN_FLAG_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        skinResult=data;
    }
});

/**
 * 皮试结果翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function skinResultFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < skinResult.length; i++) {
        if (skinResult[i].value == value) {
            return skinResult[i].label;
        }
    }
}