var visitId = parent.patVisit.visitId;
var patientId = parent.patVisit.patientId;
var diagnosisTypeClinic = [{"value": "1", "text": "中医"}, {"value": "2", "text": "西医"}];

/**
 * /门诊诊断类型
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function diagnosisTypeClinicFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < diagnosisTypeClinic.length; i++) {
        if (diagnosisTypeClinic[i].value == value) {
            return diagnosisTypeClinic[i].text;
        }
    }
}
function onloadMethod() {
    $("#treeGrid").dialog("close");
    $("#saveBut").hide();
    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/labtest/listHos',
        queryParams: {'visitId': visitId, 'patientId': patientId},
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[      //每个列具体内容
            {field: 'requestedDateTime', title: '申请日期', width: '27%', align: 'center', formatter: formatDateBoxFull},
            {field: 'performedBy', title: '检验科室', width: '25%', align: 'center', formatter: clinicDeptCodeFormatter},
            {
                field: 'resultStatus', title: '状态', width: '15%', align: 'center', formatter: function (data) {
                if (data == '0') {
                    return '未检验';
                } else {
                    return '以检验';
                }
            }
            },
            {
                field: 'id',
                title: '操作',
                width: '24%',
                align: 'center',
                formatter: function (value, row, index) {
                    var html = '';
                    html = html + '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                    //html = html +'<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看结果</button>';
                    return html;
                }
            }
        ]],
        view: detailview,
        detailFormatter: function (rowIndex, rowData) {
            var detailHtml = "<table style='width:100%;color:blue' border='0'><tr><td><strong>检验项目：</strong></td><td><strong>金额：</strong></td></tr>";
            $.ajax({
                type: "POST",
                url: basePath + "/labtest/getItem",
                contentType: 'application/json',
                data: labMaster = rowData.id,
                async: false,
                dataType: 'json',
                success: function (data) {
                    $.each(data, function (i, list) {
                        detailHtml += "<tr><td width='50%'>" + list.itemName + "</td><td>" + list.price + "&nbsp;元</td></tr>";
                    });
                }
            })
            detailHtml += "</table>";
            return detailHtml;
        },
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                add();
            }
        }]
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    $('#items').datagrid({
        singleSelect: true,
        fit: true,
        nowrap: false,
        columns: [[
            {field: 'itemName', title: '项目名称', width: '40%', align: 'center'},
            {field: 'itemCode', title: '项目代码', width: '40%', align: 'center'},
            {field: 'price', title: '金额', width: '20%', align: 'center'}
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]]
    });

    $('#treeGrid').treegrid({
        rownumbers: true,
        animate: true,
        collapsible: true,
        fitColumns: true,
        idField: 'id',
        treeField: 'id',
        columns: [[      //每个列具体内容
            {field: 'id', title: '申请日期', width: '30%', align: 'center'},
            {field: 'itemCode', title: '检查科室', width: '25%', align: 'center', formatter: clinicDeptCodeFormatter},
            {field: 'itemName', title: '状态', width: '15%', align: 'center'}
        ]]
    });

}
function add() {
    clearForm();
    $('#labItemClass').removeAttr("disabled");
    $('#performedBy').removeAttr("disabled");
    $('#specimen').removeAttr("disabled");
    $("#saveBut").show();
    $("#visitId").val(visitId);
    $("#patientId").val(patientId);
    $("#name").val(parent.clinicMaster.name);
    $("#sex").val(parent.clinicMaster.sex);
    $("#chargeType").val(parent.clinicMaster.chargeType);
    //var newDate=new Date();
    //$('#requestedDateTime').datetimebox('setValue',newDate);
    $.ajax({
        //添加
        url: basePath + "/diagnosis/findListOfIn",
        type: "GET",
        dataType: "json",
        data: {"patientId": patientId, "visitId": visitId},//住院visitId不为null
        success: function (data) {
            if (data != "" && data != null) {
                var d = "";
                $.each(data, function (index, item) {
                    d = d + diagnosisTypeClinicFormatter(item.type) + "：" + item.icdName + "\r\n";
                });
                $("#relevantClinicDiag").val(d);
            }
        }
    })
    //类别下拉框
    $('#labItemClass').combobox({
        data: labItemClass,
        valueField: 'id',
        textField: 'class_name',
        required: true,
        onSelect: function (data) {
            $("#specimen").val("");
            SendProduct(data.class_name);
            $("#performedBy").val(data.dept_name);
            $("#performedByCode").val(data.dept_code);
        }
    })
}


function loadTreeGrid() {
    var menus = [];//菜单列表
    var menuTreeData = [];//菜单树的列表
    var menuPromise = $.get(basePath + '/labtest/treeresult', function (data) {
        $.each(data, function (index, item) {
            var d = {};
            d.id = item.id;
            d.itemCode = item.itemCode;
            d.itemName = item.itemName;
            d.diagnosisDate = formatDatebox(item.diagnosisDate);
            d.parentId = item.parentId;
            d.children = [];
            d.iconCls = "icon-file";
            menus.push(d);
        });
        for (var i = 0; i < menus.length; i++) {
            //判断儿子节点
            for (var j = 0; j < menus.length; j++) {
                if (menus[i].id == menus[j].parentId) {
                    menus[i].children.push(menus[j]);
                }
            }
        }
        for (var i = 0; i < menus.length; i++) {
            if (menus[i].parentId == "0") {
                menuTreeData.push(menus[i]);
            }
        }
        $("#treeGrid").treegrid('loadData', menuTreeData);
    });
}
//保存
function save() {
    //formSubmitInput();
    //加载值
    //$("#items").datagrid('endEdit', editRow);
    $.ajax({
        //添加
        url: basePath + "/diagnosis/findListOfIn",
        type: "GET",
        dataType: "json",
        data: {"patientId": patientId, "visitId": visitId},//住院visitId不为null
        success: function (data) {
            if (data != "" && data != null) {
                var rows = $('#items').datagrid('getRows');
                var formJson = fromJson('form');
                formJson = formJson.substring(0, formJson.length - 1);
                var tableJson = JSON.stringify(rows);
                if (rows != "" && rows != null) {
                    var submitJson = formJson + ",\"list\":" + tableJson + "}";
                    $.postJSON(basePath + '/labtest/saveHos', submitJson, function (data) {
                        if (data.data == 'success') {
                            $.messager.alert("提示消息", data.code + "条记录，保存成功");
                            $('#list_data').datagrid('load');
                            $('#list_data').datagrid('clearChecked');
                            clearForm();

                        } else {
                            $.messager.alert('提示', "保存失败", "error");
                            clearForm();
                        }
                    }, function (data) {
                        $.messager.alert('提示', "保存失败", "error");
                        clearForm();
                    })
                } else {
                    $.messager.alert("提示信息", "请选择需要的检验项目");
                }

            } else {
                $.messager.alert("提示信息", "病人没有诊断信息，不能开出检验申请");
            }
        }
    })
};

function look() {
    loadTreeGrid();
    $("#treeGrid").dialog("open");
}
function SendProduct(name) {
    var item={};
    item.dictType="lab_item_view";
    var inputParamVos=new Array();
    var InputParamVo={};
    InputParamVo.colName='expand2';
    InputParamVo.colValue=name;
    InputParamVo.operateMethod='=';
    inputParamVos.push(InputParamVo);
    item.inputParamVos=inputParamVos;
    var expand3 = $("#performedBy").val();
    var expand2 = $("#labItemClass").val();
    var expand1 = $("#specimen").val();
    $.ajax({
        'type': 'POST',
        'url': basePath + '/input-setting/listParam',
        data: JSON.stringify(item),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function (data) {
            if (data.length == 0) {
                $.messager.alert("提示消息", "没有检查项目");
                return false;
            }
            var divstr = "<table>";
            for (var i = 0; i < data.length; i++) {
                if (i == 0) {
                    divstr = divstr + "<tr><td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='" + data[i].item_code + "'><span>" + data[i].item_name + "</span><input type='hidden' name='expand2' value='" + data[i].expand1 + "'/><input type='hidden' name='price' value='" + data[i].price + "'></div></td>";
                }
                else if (i % 3 == 0) {
                    divstr = divstr + "<tr><td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='" + data[i].item_code + "'><span>" + data[i].item_name + "</span><input type='hidden' name='expand2' value='" + data[i].expand1 + "'/><input type='hidden' name='price' value='" + data[i].price + "'></div></td>";
                }
                else if (i % 3 == 2) {
                    divstr = divstr + "<td><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='" + data[i].item_code + "'><span>" + data[i].item_name + "</span><input type='hidden' name='expand2' value='" + data[i].expand1 + "'/><input type='hidden' name='price' value='" + data[i].price + "'></div></td></tr>";
                }
                else {
                    divstr = divstr + "<td ><div class='fitem'  style='WORD-WRAP: break-word;width: 300px'><input type='checkbox' name='' value='" + data[i].item_code + "'><span>" + data[i].item_name + "</span><input type='hidden' name='expand2' value='" + data[i].expand1 + "'/><input type='hidden' name='price' value='" + data[i].price + "'></div></td>";
                }
                //alert(data[i].expand1);
            }
            divstr = divstr + "</table>";
            divstr = divstr + "<div align='center'><button  class='easyui-linkbutton' onclick='doSelect();' style='width: 90px'>提交</button></div>";
            $("#SendProduct").html(divstr);
            $("#SendProduct").dialog("open");

        }
    });
}

$("#SendProduct").dialog({
    title: '发货操作',
    width: '400',
    height: '300',
    iconCls: 'icon-edit',
    modal: true,
    closed: true
});
function doSelect() {
    //把你选中的 数据查询出来。
    var selectRows = $('div.easyui-window :checkbox:checked');
    if (selectRows.size() < 1) {
        $.messager.alert("提示消息", "请选中数据!");
        return;
    } else {
        var rows = [];
        var all = 0;
        selectRows.each(
            function () {
                var row = {};
                row.itemName = $(this).next().html();
                row.itemCode = $(this).val();//增
                if ($(this).next().next().next(":hidden").val() == "null") {
                    $.messager.alert('提示消息', "此项目没有价格，不能选择");
                } else {
                    var a = $(this).next().next(":hidden").val();
                    row.price = $(this).next().next().next(":hidden").val();
                    $("#specimen").val(a);
                    //var temp = $(this).next().next(":hidden").val();
                    //all += parseFloat(temp);
                    // row.price = temp;//增
                    rows.push(row);
                }

            }
        )
        var row = {};
        //row.price = all;//增
        //  rows.push(row);
        var selectJson = {'total': selectRows.size(), 'rows': rows};
        $('#items').datagrid('loadData', selectJson);
    }
    $("#SendProduct").dialog("close");
}
function clearForm() {
    $("#form").form('clear');
    $("#saveBut").hide();
    $('#items').datagrid('loadData', {total: 0, rows: []});
}
function formSubmitInput() {
    var orgId = $("#orgId").val();
    var newHtml = '<input type="hidden" id="visitId" name="visitId" value="' + visitId + '" />'
        + '<input type="hidden" id="orgId"  name="orgId" value="' + orgId + '" />'
        + '<input type="hidden" id="clinicId"  name="clinicId" value="' + clinicId + '" />';
    $("#form").append(newHtml);
}
//行删除
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
function del(id) {
    $.ajax({
        'type': 'POST',
        'url': basePath + '/labtest/delHos',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", "已经删除");
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "保存失败", "error");
        }
    });
}

