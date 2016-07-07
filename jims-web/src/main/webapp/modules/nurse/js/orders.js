var editRow = undefined;
var rowNum=-1;
$(function() {
    $('#orderList').datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        method: 'get',
        url: basePath + '/ordersNurse/getNurseOrders?' + $('#searchform').serialize(),
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        fitColumns:true,
        fit: true,//自动大小
        columns: [[      //每个列具体内容
            {field: 'repeatIndicator', title: '长', formatter:itemFormatter,width: '2%', align: 'center'},
            {field: 'orderClass', title: '类别', width: '3%', formatter:orderClassFormatter,align: 'center'},
            {field: 'startDateTime', title: '开始时间', width: '6%', align: 'center',formatter:formatDateBoxFull},
            {field: 'orderText', title: '医嘱内容', width: '5%', align: 'center'},
            {
                field: 'dosage',
                title: '剂量',
                width: '3%',
                align: 'center'
            },
            {field: 'administration', title: '途径', width: '5%', formatter:billingAttrFormatter,align: 'center'},
            {field: 'frequency', title: '次数', width: '3%',formatter:performFreqFormatter, align: 'center'},
            {field: 'performSchedule', title: '执行时间', width: '5%', align: 'center'},
            {field: 'stopDateTime', title: '结束时间', width: '7%', align: 'center',formatter:formatDateBoxFull},
            {field: 'verifyDataTime', title: '校对时间', width: '8%', align: 'center',formatter:formatDateBoxFull},
            {field: 'time', title: '摆药截至时间', width: '8%', align: 'center'},
            {field: 'billingAttr', title: '自', width: '2%', align: 'center',formatter:billingAttrFormatter},
            {field:'performResult',title:'阴阳',width:'5%',align:'center',editor:'text'},
            {field: 'freqDetail', title: '医生说明', width: '5%', align: 'center'},
            {
                field: 'dosageUnits',
                title: '单位',
                width: '3%',
                align: 'center'

            },
            {field: 'freqCounter', title: '临嘱执行次数', width: '5%', align: 'center'},
            {field:'doctor',title:'开医嘱医生',width:'5%',align:'center'},
            {field:'nurse',title:'校对护士',width:'5%',align:'center'},
            {field:'stopDoctor',title:'停医生',width:'5%',align:'center'},
            {field:'stopNurse',title:'停止护士',width:'5%',align:'center'},
            {field:'execDateTime',title:'执行时间',width:'6%',align:'center',formatter:formatDateBoxFull},
            {field:'execOperator',title:'执行护士',width:'5%',align:'center'},
            {field:'patientId',hidden:true},
            {field:'visitId',hidden:true},
            {field:'orderNo',hidden:true}
        ]],
       onClickRow: function (rowIndex, rowData) {

            var row = $('#orderList').datagrid('getSelected');
            var dataGrid = $('#orderList');
            var row = $('#orderList').datagrid('getSelected');
            if (!dataGrid.datagrid('validateRow', rowNum)) {
                return false//新开
            } else {
                if (rowNum != rowIndex) {
                    if (rowNum >= 0) {
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum = rowIndex;
                    dataGrid.datagrid('beginEdit', rowIndex);
                }
            }


        }, onDblClickRow: function (rowIndex, rowData) {
            $("#ordersDialog").dialog('open');
        }, rowStyler:function(index,row){
            if (row.orderNo!=row.orderSubNo){
                return 'background-color:#D4D4D4;';
                if(row.orderStatus=='6'){//传输
                    return 'color:#8A2BE2;';
                }else if(row.orderStatus=='1'){//转抄
                    return 'color:black;';
                }else if(row.orderStatus=='2') {//执行
                    return 'color:blue;';
                }else if(row.orderStatus=='3') {//停止
                    return 'color:yellow;';
                }else if(row.orderStatus=='4') {//作废
                    return 'color:red;';
                }

            }else{
                if(row.orderStatus=='6'){//传输
                    return 'background-color:#90EE90;color:#8A2BE2;';
                }else if(row.orderStatus=='1'){//转抄
                    return 'background-color:#A7CACB;color:black;';
                }else if(row.orderStatus=='2') {//执行
                    return 'background-color:#A7CACB;color:blue;';
                }else if(row.orderStatus=='3') {//停止
                    return 'background-color:#A7CACB;color:yellow;';
                }else if(row.orderStatus=='4') {//作废
                    return 'background-color:#A7CACB;color:red;';
                }
            }





        }
    });
    $("#submit_search").linkbutton({iconCls: 'icon-search', plain: true}).click(function () {
        $('#orderList').datagrid("load");   //点击搜索
    });
});

//设置分页控件
var p = $('#orderList').datagrid('getPager');
$(p).pagination({
    pageSize: 10,//每页显示的记录条数，默认为10
    pageList: [5,10,15],//可以设置每页记录条数的列表
    beforePageText: '第',//页数文本框前显示的汉字
    afterPageText: '页    共 {pages} 页',
    displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
});
//校对
function proofOrders(){
    var ordersRow = $('#orderList').datagrid("getSelections");
    var tableJson=JSON.stringify(ordersRow);
    $.postJSON(basePath+'/ordersNurse/proofOrders',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","校对成功");
            $('#orderList').datagrid("reload");
        }else{
            $.messager.alert('提示',"校对失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"校对错误", "error");
    })
}


//执行
function executeOrders(){


    var ordersRow = $('#orderList').datagrid("getSelections");
    var tableJson=JSON.stringify(ordersRow);
    $.postJSON(basePath+'/ordersNurse/executeOrders',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","执行成功");
            $('#orderList').datagrid("reload");
        }else{
            $.messager.alert('提示',"执行失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"执行错误", "error");
    })
}


function loadBaseInfo(id){
    $.ajax({
        method: "POST",
        dataType: 'json',
        contentType: 'application/json',
        data: id = id,
        url: basePath + '/bedRec/getInPats',
        success: function (data) {
            $.each(data,function(id,item) { //循环对象取值
                $('#baseInfo').form('load',item);
            })
        }
    });
}

//转抄
function operationCopied(){
    var ordersRow = $('#orderCopied').datagrid("getSelections");
    var tableJson=JSON.stringify(ordersRow);
    $.postJSON(basePath+'/ordersNurse/operationCopied',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息","处理成功");
            $('#orderCopied').datagrid("load");
        }else{
            $.messager.alert('提示',"处理失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"处理失败", "error");
    })

}