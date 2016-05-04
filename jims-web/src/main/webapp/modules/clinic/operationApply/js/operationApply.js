
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
        url:basePath+'/operationApply/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'mazuifangfa',title:'麻醉方法',width:'18%',align:'center'},
            {field:'shoushuDoctor',title:'手术医师',width:'18%',align:'center'},
            {field:'shoushuDate',title:'手术时间',width:'30%',align:'center',formatter:formatDateBoxFull},
            {field:'id',title:'操作',width:'40%',align:'center',formatter:function(value, row, index){
                var state="1";
                var html='<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="getOperation(\''+row.id+'\',\''+state+'\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>'+
                    '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="getOperation(\''+row.id+'\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>'+
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
 * 保存申请记录
 * @param id
 */
function savePperationApply() {
    $("#patientId").attr("value","123");
    $("#zhuyuanId").attr("value","123");
    $.postForm(basePath + "/operationApply/save", "operationApplyForm", function (data) {
        if (data.code == "1") {
            $.messager.alert("提示信息", "保存成功");
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
            $("#operationApplyForm").form("clear");
            //$("#operationApplyForm").form('clear');
        } else {
            $.messager.alert("提示信息", "保存失败", "error");
        }

    }), function (data) {
        $.messager.alert("提示信息", "保存失败", "error");
    }
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

/**
 * 显示修改
 * @param data
 */
function getOperation(id,state){
    if(state=="1"){
        $("#pperationApply").hide();
    }
    else
    {
        $("#pperationApply").show();
    }
    $.ajax({
        'type': 'post',
        'url': basePath+'/operationApply/getOperation',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            $('#operationApplyForm').form('load',data);
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
        'url': basePath+'/operationApply/del',
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


