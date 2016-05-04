
/**
 * 设置动态行
 * @param id
 */

var editRow = undefined;
var serialNo='';
var units = [{"value": "1", "text": "毫升"}, {"value": "2", "text": "单位"}, {"value": "3", "text": "人/份"}];
var userBlood = [{"value": "1", "text": "全血"}, {"value": "2", "text": "全血1"}, {"value": "3", "text": "全血2"}, {"value": "4", "text": "全血3"}];
$(function(){
    $('#list_doctor').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        method:'post',
        url:basePath+'/operationApply/list',
        columns:[[
            {field:'id',title:'id',hidden:true,align:'center'},
            {field: 'fastSlow', title: '用血方式', width: '20%', align: 'center', editor: 'text'},
            //每个列具体内容
            {field: 'transDate', title: '预订输血时间', width: '20%', align: 'center', editor: 'text'},
            {field: 'transCapacity', title: '血量', width: '20%', align: 'center', editor: 'text'},
            {
                field: 'unit', title: '单位', width: '20%', align: 'center', editor: {
                type:'combobox',
                options:{
                    data: units,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
            {
                field: 'bloodType', title: '血液要求', width: '20%', align: 'center', editor: {
                type:'combobox',
                options:{
                    data: userBlood,
                    valueField:'value',
                    textField:'text',
                    required:true
                }
            }},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function() {
                $("#list_doctor").datagrid('insertRow', {
                    index:0,
                    row:{}
                });
            }
        }, {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                inDoDelete();
            }
        }, {
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                saveUseBloodApply();
            }
        }
        ],

        onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
        },onDblClickRow:function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#list_doctor").datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#list_doctor").datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },onClickRow:function(rowIndex,rowData){
            //tooltips选中行，药品价目列表信息
            if (editRow != undefined) {
                $("#list_doctor").datagrid('endEdit', editRow);
            }
        }
    });
});
function onloadMethod(){
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        fit: true,//自动大小
        url:basePath+'/bloodApply/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'deptCode',title:'科室',width:'18%',align:'center'},
            {field:'applyNum',title:'申请单号',width:'18%',align:'center'},
            {field:'bloodInuse',title:'血源',width:'18%',align:'center'},
            {field:'bloodDiagnose',title:'诊断',width:'18%',align:'center'},
            {field:'preBloodType',title:'血型',width:'18%',align:'center'},
            {field:'bloodInuse',title:'方式',width:'18%',align:'center'},
            {field:'bloodSum',title:'用血量',width:'18%',align:'center'},
            {field:'gatherDate',title:'申请时间',width:'30%',align:'center',formatter:formatDateBoxFull},
            {field:'id',title:'操作',width:'40%',align:'center',formatter:function(value, row, index){
                var state="1";
                var html='<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="getBloodApply(\''+row.id+'\',\''+state+'\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>'+
                    '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="getBloodApply(\''+row.id+'\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>'+
                    '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\''+value+'\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                return html;
            }}
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {
                var selectRows = $('#list_data').datagrid("getSelections");
                if (selectRows.length < 1) {
                    $.messager.alert("提示消息", "请选中需要修改的数据");
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                doDelete();
            }
        }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
}


/**
 * 保存
 * @param id
 */
function saveUseBloodApply() {
    $("#list_doctor").datagrid('endEdit', editRow);
    var  rows=$('#list_doctor').datagrid('getRows');
    var formJson=fromJson('useBloodForm');
    formJson = formJson.substring(0, formJson.length - 1);
    var tableJson=JSON.stringify(rows);
    var submitJson=formJson+",\"bloodCapacityList\":"+tableJson+"}";
    $("#inpNo").attr("value","123");
    $("#applyNum").attr("value","123");
    $("#matchSubNum").attr("value","1");
    $.postJSON(basePath + "/bloodApply/save", submitJson, function (data) {
        if (data.code == "1") {
            $.messager.alert("提示信息", "保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
            $("#useBloodForm").form("clear");
        } else {
            $.messager.alert("提示信息", "保存失败", "error");
        }

    }), function (data) {
        $.messager.alert("提示信息", "保存失败", "error");
    }
}
//批量删除
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            //真删除了  1,3,4
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            del(strIds);
        }
    })
}
//列删除
function deleteRow(id) {
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            del(id);
        }
    })
}

/**
 * 删除方法
 * @param id
 */
function del(id){
    $.ajax({
        'type': 'POST',
        'url': basePath+'/bloodApply/del',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                if(data.code>0){
                    $.messager.alert("提示消息",data.code+"条记录，已经删除");
                    $('#list_data').datagrid('load');
                    $('#list_data').datagrid('clearChecked');
                }else{
                    $.messager.alert('提示',"删除失败", "error");
                }
            }else{
                $.messager.alert('提示',"删除失败", "error");
            }
        },
        'error': function(data){
            $.messager.alert('提示',"保存失败", "error");
        }
    });
}
/**
 * 显示修改
 * @param data
 */
function getBloodApply(id,state){
    if(state=="1"){
        $("#saveUseBlood").hide();
    }
    else
    {
        $("#saveUseBlood").show();
    }
    $.ajax({
        'type': 'post',
        'url': basePath+'/bloodApply/getBloodApply',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#useBloodForm').form('load',data);
        }
    })
}

