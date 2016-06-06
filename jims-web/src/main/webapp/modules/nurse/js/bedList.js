var column={};
var patId;
var editRow = undefined;
var rowNum=-1;
$(function(){
    var wardCode='160101';
    $('#bedRec').datagrid({
     /*   view: myview,
        emptyMsg: '没有查到相关信息',*/
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/bedRec/findBedInfo?wardCode='+wardCode,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        pagination: true, //分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '10%', align: 'center',editor:'text', required: true},
            {field: 'bedLabel', title: '床标号', width: '10%', align: 'center',editor:'text', required: true},
            {field: 'roomNo', title: '房间', width: '10%', align: 'center',editor:'text', required: true},
            {field: 'bedSexType', title: '男/女', width: '10%', align: 'center',editor:{
                type:'combobox',
                options:{
                    url: basePath+'/dict/label-value-list?type='+"SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                        //$(this).combobox('setValues', row.bedSexType);
                    }
            }
        }},
            {field: 'bedClass', title: '等级', width: '20%', align: 'center',editor:{
                type:'combobox',
                options: {
                    url: basePath + '/dict/label-value-list?type=' + "SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                       // $(this).combobox('setValues', row.bedClass);
                    }
                }
                }},
            {field: 'airconditionClass', title: '空调等级', width: '15%', align: 'center',editor:{
                type:'combobox',
                options: {
                    url: basePath + '/dict/label-value-list?type=' + "SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                      //  $(this).combobox('setValues', row.airconditionClass);
                    }
                }
            }},
            {field: 'bedApprovedType', title: '类型', width: '15%', align: 'center',editor:{
                type:'combobox',
                options: {
                    url: basePath + '/dict/label-value-list?type=' + "SEX_DICT",
                    valueField: 'value',
                    textField: 'label',
                    method: 'GET',
                    onLoadSuccess: function (row) {
                        var data = $(this).combobox('getData');
                     //   $(this).combobox('setValues', row.bedApprovedType);
                    }
                }
            }},
            {field: 'bedStatus', title: '空床', width: '10%', align: 'center', formatter:function(value, row, index){
                 if(row.bedStatus=='0'||row.bedStatus=='' || row.bedStatus ==null){
                     return "是";
                 }else{
                     return "否";
                 }

            }},
            {field:'wardCode',editor:{type:'textbox',options:{editable:true,disable:false}},hidden:'true'}

        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {

                if (rowNum >= 0) {
                    rowNum++;
                }
                $("#bedRec").datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {
                        wardCode:wardCode
                    }
                });

            }
        },{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#bedRec").datagrid('endEdit', rowNum);
                if (rowNum != -1) {
                    $("#bedRec").datagrid("endEdit", rowNum);
                }
                save();
            }
        },{
            text: '换床',
            iconCls:'icon-reload',
            handler:function(){
                changeBed();
            }
        },
            {
                text: '删除',
                iconCls: 'icon-remove',
                handler: function () {
                    doDelete();
                }
            }],onAfterEdit: function (rowIndex, rowData, changes) {

        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#bedRec").datagrid('endEdit', rowNum);
            }
            if (editRow == undefined) {
                $("#bedRec").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData) {
            var dataGrid = $('#bedRec');
            if (!dataGrid.datagrid('validateRow', rowNum)) {
                return false
            }
            if (rowNum != rowIndex) {
                if (rowNum >= 0) {
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum = rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);

            }
        },onLoadSuccess: function (data) {
            if (data.total == 0) {
                var body = $(this).data().datagrid.dc.body2;
                body.find('table tbody').append('<tr><td colspan="8" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
            }
        }
});


    //设置分页控件
    var p = $('#bedRec').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10
        pageList: [5,10,15],//可以设置每页记录条数的列表
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });


    //已经分配了床位的在院病人列表
    $('#inPat').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/bedRec/getInPat?wardCode='+wardCode,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        columns: [[      //每个列具体内容
            {field: 'bedno', title: '床号', width: '15%', align: 'center'},
            {field: 'name', title: '姓名', width: '15%', align: 'center'},
            {field: 'sex', title: '性别', width: '15%', align: 'center'},
            {field: 'patientid', title: '病人ID', width: '20%', align: 'center'},
            {field: 'visitid', title: '住院号', width: '20%', align: 'center'},
            {field: 'dedlabel', title: '床标号', width: '15%', align: 'center'}
        ]],onClickRow: function (index, row) {//单击行事件
             patId = row.patientid;


           /* column["field"]='patientId';
            column["width"]=60;
            column["title"]='病人Id';
            column["formatter"]='function(value,rec){return '+patId+';}';
            $('#emptyBed').datagrid('reload');*/
            $('#hasBed').datagrid({url: basePath + '/bedRec/findList?wardCode=' + wardCode + '&bedStatus=1' + '&patientId='+patId });



        },onLoadSuccess: function (data) {
            if (data.total == 0) {
                var body = $(this).data().datagrid.dc.body2;
                body.find('table tbody').append('<tr><td colspan="6" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
            }
        }
    });

    //未分配的床位信息
    $('#emptyBed').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'GET',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/bedRec/findList?wardCode=' + wardCode + '&bedStatus=' + '0&patientId='+"",
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '10%', align: 'center'},
            {field: 'bedLabel', title: '床标号', width: '10%', align: 'center'},
            {field: 'roomNo', title: '房间', width: '10%', align: 'center'},
            {field: 'bedSexType', title: '男/女', width: '10%', align: 'center'},
            {field: 'bedClass', title: '等级', width: '15%', align: 'center'},
            {field: 'airconditionClass', title: '空调等级', width: '15%', align: 'center'},
            {field: 'bedApprovedType', title: '类型', width: '15%', align: 'center'},
            {field: 'bedStatus', title: '空床', width: '10%', align: 'center', formatter:function(value, row, index){
                if(row.bedStatus=='0'||row.bedStatus=='' || row.bedStatus ==null){
                    return "是";
                }else{
                    return "否";
                }
            }}
        ]
        ],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],onLoadSuccess: function (data) {
            if (data.total == 0) {
                var body = $(this).data().datagrid.dc.body2;
                body.find('table tbody').append('<tr><td colspan="10" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
            }
        }
    });

//病人的包床信息 hasBed
    $('#hasBed').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        remoteSort: false,
        idField: 'id',
        method: 'GET',
        singleSelect: false,//是否单选
        columns: [[      //每个列具体内容
            {field: 'bedNo', title: '床号', width: '10%', align: 'center'},
            {field: 'bedLabel', title: '床标号', width: '10%', align: 'center'},
            {field: 'roomNo', title: '房间', width: '10%', align: 'center'},
            {field: 'bedSexType', title: '男/女', width: '10%', align: 'center'},
            {field: 'bedClass', title: '等级', width: '15%', align: 'center'},
            {field: 'airconditionClass', title: '空调等级', width: '15%', align: 'center'},
            {field: 'bedApprovedType', title: '类型', width: '15%', align: 'center'},
            {field: 'bedStatus', title: '空床', width: '10%', align: 'center', formatter:function(value, row, index){
                if(row.bedStatus=='0'||row.bedStatus=='' || row.bedStatus ==null){
                    return "是";
                }else{
                    return "否";
                }
            }}
        ]],onLoadSuccess: function (data) {
        if (data.total == 0) {
            var body = $(this).data().datagrid.dc.body2;
            body.find('table tbody').append('<tr><td colspan="8" width="' + body.width() + '" style="height: 5px; text-align: center;">暂无数据</td></tr>');
        }
    }
    });



    $('#oldBedNo').keydown(
        function(event){
            if(event.keyCode == "13")
            {
                alert(11111111);
                var oldBedNo =  $.trim($('#oldBedNo').val());
                alert(oldBedNo);
                if (oldBedNo!=''){
                    $.ajax({
                        method:"post",
                        contenType:"application/json",
                        dataType:"json",
                        url:basePath + '/bedRec/getOneBed?wardCode='+wardCode+"&bedNo="+oldBedNo,
                        success:function(data){
                            $('#oldBed').form('load',data);
                        }
                    })
                }
            }
        });



});


$('#oldBedNo').textbox({
    inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents, {
        keyup: function (event) {
            if (event.keyCode == 13) {
                alert('OK');
                var oldBedNo = $.trim($('#oldBedNo').val());
                //  alert(oldBedNo);
                if (oldBedNo != '') {
                    $.ajax({
                        method: "post",
                        contenType: "application/json",
                        dataType: "json",
                        url: basePath + '/bedRec/getOneBed?wardCode=' + wardCode + "&bedNo=" + oldBedNo,
                        success: function (data) {
                            $('#oldBed').form('load', data);
                        }
                    })
                }
            }
        }

    })
})


/*function keyDownFun(obj,event) {
    if (event.keyCode == "13") {
        var oldBedNo = $.trim($('#oldBedNo').val());
      //  alert(oldBedNo);
        if (oldBedNo != '') {
            $.ajax({
                method: "post",
                contenType: "application/json",
                dataType: "json",
                url: basePath + '/bedRec/getOneBed?wardCode=' + wardCode + "&bedNo=" + oldBedNo,
                success: function (data) {
                    $('#oldBed').form('load', data);
                }
            })
        }
    }
}*/

function save(){
   var bedRows =  $("#bedRec").datagrid("getChanges");
    var tableJson=JSON.stringify(bedRows);
    $.postJSON(basePath+'/bedRec/save',tableJson,function(data){
        if(data.data=='success'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#bedRec').datagrid('load');
            $('#bedRec').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })

}


function doDelete(){
    //把你选中的 数据查询出来。
    var selectRows = $('#bedRec').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            //真删除数据
            $.ajax({
                'type': 'POST',
                'url': basePath+'/bedRec/delete',
                'contentType': 'application/json',
                'data': ids=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.data=='success'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#bedRec').datagrid('load');
                        $('#bedRec').datagrid('clearChecked');
                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
        }
    })
}


//包床
function packBed(){
  var bedInfo =  $("#emptyBed").datagrid("getSelections");
    var tableJson=JSON.stringify(bedInfo);
  //  alert(tableJson);
    $.postJSON(basePath+'/bedRec/packBed',tableJson,function(data){
        if(data.data=='success'){
            var row = $('#inPat').datagrid('getSelected');
          //  alert(JSON.stringify(row));
            $.messager.alert("提示消息","包床成功");
            var patientId = row.patientid;
            $('#emptyBed').datagrid('load');
            $('#hasBed').datagrid('load');
        }else{
            $.messager.alert('提示',"包床失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"包床失败", "error");
    })


}

//换床
function   changeBed(){
    $('#dlg').dialog('open').dialog('center').dialog('setTitle', '换床处理');

}

function changeBedOk(){
    var oldBedNo = $('#oldBedNo').val();
    var patient = $('#patientid').val();
    var newBedNo = $('#newBedNo').val();
    var visitId = $('#visitId').val();
    $.ajax({
        'type': 'POST',
        'url': basePath+'/bedRec/changeBed',
        'contentType': 'application/json',
        'data': JSON.stringify({"visitId":visitId,"newBedNo":newBedNo,"oldBedNo":oldBedNo}),
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                $.messager.alert("提示消息","换床成功！");
                $('#bedRec').datagrid('load');

            }else{
                $.messager.alert('提示',"换床失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"换床失败", "error");
        }
    });
}