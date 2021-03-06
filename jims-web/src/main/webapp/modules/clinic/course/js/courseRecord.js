var postUrl="";
var getUrl="";
var patientId=parent.clinicMaster.patientId;
function onloadMethod(){
    $("#patientId").val(patientId);

    $("#childrenType").combobox({
        data:courseRecord,
        valueField:'value',
        textField:'label',
        onSelelct:function(data){
            $("#childrenTypeId").val(data.value);
        }
    })
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
        url:basePath+'/courseRecord/list',
        remoteSort:false,
        idField:'fldId',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'luruShijian',title:'病程日期',width:'30%',align:'center',formatter:formatDateBoxFull},
            {field:'type',title:'类型',width:'28%',align:'center',formatter:courseRecordFormatter},
            {field:'id',title:'操作',width:'40%',align:'center',formatter:function(value, row, index){
                var html='<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="getRowData(\''+row.id+'\',\''+row.type+'\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>'+
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
                getRowData(selectRows[0].id);
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

    $("#childrenType").combobox({
        onChange: function (n,o) {
            $("#childrenDiv").load(getHtmlPath(n));
        }
    });
}
function getHtmlPath(n){
    var html="";
    if(n=='0'){
        postUrl="";
        return false;
    }else if(n=='1'){//每日病程
        html="/modules/clinic/course/courseRecordEachdis.html";
        postUrl=basePath + "/courseRecordeachdis/save";
        getUrl=basePath + "/courseRecordeachdis/get";
    }else if(n=='2'){//上级医师查房
        html="/modules/clinic/course/courseRecordSuperiorDocrecor.html";
        postUrl=basePath+"/courseRecordSuperiorDocrecor/save";
        getUrl=basePath + "/courseRecordSuperiorDocrecor/get";
    }else if(n=='3'){//阶段小结
        html="/modules/clinic/course/courseRecordStage.html";
        postUrl=basePath + "/courseRecordState/save";
        getUrl=basePath + "/courseRecordState/get";
    }
    return html;
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
        'url': basePath+'/courseRecord/del',
        'contentType': 'application/json',
        'data': id=id,
        'dataType': 'json',
        'success': function(data){
            if(data.data=='success'){
                if(data.code>0){
                    $.messager.alert("提示消息",data.code+"条记录，已经删除");
                    $('#list_data').datagrid('load');
                    $('#list_data').datagrid('clearChecked');
                    $("#courseRecordForm").form('clear');
                    $("#childrenDiv").html('');
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
 * 保存病程记录
 */
function saveCourseRecord(){
    formSubmitInput("courseRecordForm");
    $.postForm(postUrl,'courseRecordForm',function(data){
        if(data.data=='success'){
            if(data.code>0){
                $.messager.alert("提示消息",data.code+"条记录，保存成功");
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
                $("#courseRecordForm").form('clear');
                $("#childrenDiv").html('');
            }else{
                $.messager.alert('提示',"保存失败", "error");
            }
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}
/**
 * 显示修改
 * @param data
 */
function getRowData(id,type){
    $('#childrenType').combobox("setValue",type);
    $("#childrenDiv").load(getHtmlPath(type),'',function(){
        $.ajax({
            'type': 'post',
            'url': getUrl,
            'contentType': 'application/json',
            'data': id=id,
            'async':false,
            'cache':false,
            'dataType': 'json',
            'success': function(data){
                $('#courseRecordForm').form('load',data);
                getDiv('courseRecordForm');
            }
        })
        return false;
    });

}


