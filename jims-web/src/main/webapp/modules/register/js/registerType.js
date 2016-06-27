var rowNum=-1;

function onloadMethod(id,clinicName){
    $("#type").val(clinicName);
    $("#clinicTypeId").val(id);
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        //fit: true,//自动大小
        url:basePath+'/clinicType/itemList?typeId='+id,
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
        //pagination:true,//分页控件
        //pageSize:15,
        //pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'chargeItem',title:'收费项目',width:'24%',align:'center',formatter:itemFormatter,editor:{
                type:'combobox',
                options:{
                    data :item,
                    valueField:'value',
                    textField:'label'
                }}
            },
            {field:'priceItem',title:'项目代码',width:'25%',align:'center'

            },
            {field:'itemName',title:'项目名称',width:'25%',align:'center',formatter:function(value, rowData, rowIndex){
                    return priceItmeFormatter(rowData.priceItem,'','')
            },editor:{
                type:'combogrid',
                options:{
                    data:priceItme,
                    idField:'item_code',
                    textField:'item_name',
                    columns:[[
                        {field:'item_code',title:'项目代码',width:120},
                        {field:'item_name',title:'项目名称',width:120},
                        {field:'price',title:'项目价格',width:70}
                    ]],
                    onClickRow: function (index, data) {
                    var rows = $('#list_data').datagrid("getRows"); // 这段代码是// 对某个单元格赋值
                    var columns = $('#list_data').datagrid("options").columns;
                    rows[rowNum][columns[0][1].field]=data.item_code;
                        rows[rowNum][columns[0][3].field]=data.price;
                    $('#list_data').datagrid('endEdit', rowNum);
                    $('#list_data').datagrid('beginEdit', rowNum);
                }
                }
            }},{field:'price',title:'项目价格',width:'24%',align:'center',formatter:function(value, rowData, rowIndex){
                if (value==undefined) {
                    value=0;
                }
                return value+"/元";
             }
            },
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '新增号类',
            iconCls: 'icon-add',
            handler: function() {
                $("#clinicTypeId").val('');
                $("#type").val('');
                $("#type").focus()
                $('#list_data').datagrid('loadData', { total: 0, rows: [] });
            }
        },'-',{
            text: '增加',
            iconCls: 'icon-add',
            handler: function() {
                if(rowNum>=0){
                    rowNum++;
                }
                $("#list_data").datagrid('insertRow', {
                    index:0,
                    row:{

                    }

                });
            }
        },'-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                deleteItem();
            }
        },'-',{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                $("#list_data").datagrid('endEdit', rowNum);
                save();
            }
        }
        ],onClickRow: function (rowIndex, rowData) {
            var dataGrid=$('#list_data');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }
            if(rowNum!=rowIndex){
                if(rowNum>=0){
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum=rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);
            }
        }
    });
    ////设置分页控件
    //var p = $('#list_data').datagrid('getPager');

}
//号类列表
function clinicTypeList(){
    var typeHtml='';
    $.get(basePath + '/clinicType/findList',function(data){
        for(var i=0;i<data.length;i++){
            typeHtml+='<li><a href="#" onclick="onloadMethod(\''+data[i].id+'\',\''+data[i].clinicTypeName+'\')">'+data[i].clinicTypeName+'</a><a href="#" class="rp-close" onclick="deleteClinicType(\''+data[i].id+'\')">X</a></li>';
        }
        $("#clinicType").html(typeHtml);
    })
}
//加载号类列表
clinicTypeList();

//保存数据
function save(){
    var  rows=$('#list_data').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    var type=encodeURI($("#type").val());
    var clinicTypeId=$("#clinicTypeId").val();
    $.postJSON(basePath+'/clinicType/saveItem?type='+type+'&clinicTypeId='+clinicTypeId,tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $("#clinicTypeId").val('');
            $("#type").val('');
            $('#list_data').datagrid('loadData', { total: 0, rows: [] });
            clinicTypeList();
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}
//修改数据
function editItem(){
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要修改的数据!");
        return;
    }
}
//删除数据
function deleteItem(){
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            //删除
            $.ajax({
                'type': 'POST',
                'url': basePath+'/clinicType/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.code=='1'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#list_data').datagrid('load');
                        $('#list_data').datagrid('clearChecked');
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
//删除号类
function deleteClinicType(typeId){
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function () {
        $.ajax({
            'type': 'POST',
            'url': basePath+'/clinicType/deleteClinicType',
            'contentType': 'application/json',
            'data': id=typeId,
            'dataType': 'json',
            'success': function(data){
                if(data.code=='1'){
                    $.messager.alert("提示消息",data.code+"条记录删除成功！");
                    clinicTypeList();
                    onloadMethod('','');
                }else{
                    $.messager.alert('提示',"删除失败", "error");
                }
            },
            'error': function(data){
                $.messager.alert('提示',"删除失败", "error");
            }
        });

    })
}
