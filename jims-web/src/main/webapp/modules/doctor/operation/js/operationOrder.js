var rowNum = -1;
var visitIds = parent.patVisit.visitId;
var patientIds = parent.patVisit.patientId;
function onloadMethod() {
    $("#visitId").val(visitIds);
    $("#patientId").val(patientIds);
    /**
     * 医生自动补全
     */
    $("#surgeon").combogrid({
        data: doctorName,
        valueField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'surgeon');
            }
        },
        onClickRow: function (rowIndex, rowData) {
            $("#surgeon").combogrid('setText', rowData.name);
            $("#surgeonId").val(rowData.id);
        }
    })
    /**
     * firstAssistant自动补全
     */
    $("#firstAssistant").combogrid({
        data: doctorName,
        valueField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'firstAssistant');
            }
        },
        onClickRow: function (rowIndex, rowData) {
            $("#firstAssistant").combogrid('setText', rowData.name);
            $("#firstAssistantId").val(rowData.id);
        }
    })
    /**
     * secondAssistant自动补全
     */
    $("#secondAssistant").combogrid({
        data: doctorName,
        valueField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'secondAssistant');
            }
        },
        onClickRow: function (rowIndex, rowData) {
            $("#secondAssistant").combogrid('setText', rowData.name);
            $("#secondAssistantId").val(rowData.id);
        }
    })
    /**
     * thirdAssistant自动补全
     */
    $("#thirdAssistant").combogrid({
        data: doctorName,
        valueField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'thirdAssistant');
            }
        },
        onClickRow: function (rowIndex, rowData) {
            $("#thirdAssistant").combogrid('setText', rowData.name);
            $("#thirdAssistantId").val(rowData.id);
        }
    })
    /**
     * fourthAssistant自动补全
     */
    $("#fourthAssistant").combogrid({
        data: doctorName,
        valueField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'surgeon');
            }
        },
        onClickRow: function (rowIndex, rowData) {
            $("#fourthAssistant").combogrid('setText', rowData.name);
            $("#fourthAssistantId").val(rowData.id);
        }
    })
    /**
     * 供血医生自动补全
     */
    $("#bloodTranDoctor").combogrid({
        data: doctorName,
        valueField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'bloodTranDoctor');
            }
        },
        onClickRow: function (rowIndex, rowData) {
            $("#bloodTranDoctor").combogrid('setText', rowData.name);
            $("#bloodTranDoctorId").val(rowData.id);
        }
    })
    /**
     * 麻醉医生自动补全
     */
    $("#anesthesiaDoctor").combogrid({
        data: doctorName,
        valueField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'anesthesiaDoctor');
            }
        },
        onClickRow: function (rowIndex, rowData) {
            $("#anesthesiaDoctor").combogrid('setText', rowData.name);
            $("#anesthesiaDoctorId").val(rowData.id);
        }
    })
    /**
     * anesthesiaAssistant自动补全
     */
    $("#anesthesiaAssistant").combogrid({
        data: doctorName,
        valueField: 'id',
        textField: 'name',
        columns: [[
            {field: 'name', title: '医生姓名', width: 70},
            {field: 'dept_name', title: '科室', width: 120},
            {field: 'title', title: '职称', width: 70}
        ]], keyHandler: {
            up: function () {
            },
            down: function () {
            },
            enter: function () {
            },
            query: function (q) {
                comboGridCompleting(q, 'anesthesiaAssistant');
            }
        },
        onClickRow: function (rowIndex, rowData) {
            $("#anesthesiaAssistant").combogrid('setText', rowData.name);
            $("#anesthesiaAssistantId").val(rowData.id);
        }
    })
    $('#operationName').datagrid({
        rownumbers: true,
        singleSelect: true,
        fit: true,
        columns: [[      //每个列具体内容
            {
                field: 'operation', title: '拟实施手术名称', width: '48%', align: 'center', formatter: operationNameFormatter
                , editor: {
                type: 'combogrid',
                options: {
                    panelWidth: 500,
                    data: operation,
                    idField: 'operation_code',
                    textField: 'operation_name',
                    required: true,
                    //url: '/modules/operation/js/clinic_data.json',
                    columns: [[
                        {field: 'operation_code', title: '项目代码', width: '20%', align: 'center'},
                        {field: 'operation_name', title: '项目名称', width: '20%', align: 'center'},
                        {field: 'input_code', title: '拼音输入码', width: '20%', align: 'center', editor: 'text'},
                        //{field: 'input_code', title: '五笔输入码', width: '10%', align: 'center', editor: 'text'}
                    ]], keyHandler: {
                        up: function () {
                        },
                        down: function () {
                        },
                        enter: function () {
                        },
                        query: function (q) {
                            dataGridCompletings(q, 'operationName', 'operation');
                        }
                    },
                    fitColumns: true
                }
            }
            },
            {
                field: 'operationScale',
                title: '等级',
                width: '49%',
                align: 'center',
                editor: {
                    type: 'combobox',
                    options: {
                        panelWidth: 500,
                        data: operationScaleName,
                        valueField: 'value',
                        textField: 'label'
                    }
                },
                formatter: operationScaleFormatter
            }
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                if (!$("#operationName").datagrid("validateRow", rowNum)) {
                    $.messager.alert('提示', "请填写完本行数据后，再添加", "Warning")
                    return false;
                }
                if (rowNum >= 0) {
                    rowNum++;
                }
                //if(rowNum!=-1){
                //    $("#operationName").datagrid("endEdit",rowNum)
                //}
                $("#operationName").datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {}
                });
                if(rowNum>=0){
                    $("#operationName").datagrid('endEdit', rowNum);
                }
                $("#operationName").datagrid('endEdit', rowNum);
                rowNum = 0;
                $("#operationName").datagrid("beginEdit", rowNum);
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        }, {
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                $("#operationName").datagrid('endEdit', rowNum);
                if (rowNum != undefined) {
                    $("#operationName").datagrid("endEdit", rowNum);
                }
                save();
            }
        }],
        onClickRow: function (rowIndex, rowData) {
            var dataGrid = $('#operationName');
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
        }
    });
    /**
     * 通过visitId、patientId 获取病人住院手术主记录列表
     */
    $('#operationNameList').datagrid({
        rownumbers: true,
        singleSelect: true,
        fit: true,
        method: 'GET',
        url: basePath + '/operatioinOrder/getScheduleOutHos',
        queryParams: {'visitId': visitIds, "patientId": patientIds},
        //idField: 'id',
        columns: [[      //每个列具体内容
            {
                field: 'reqDateTime', title: '预约时间', width: '30%', align: 'center', formatter: formatDateBoxFull
            },
            {
                field: 'operatingRoom', title: '手术室', width: '20%', align: 'center', formatter: operationFormatter
            },
            {
                field: 'ackIndicator', title: '确认标志', width: '20%', align: 'center',
                formatter: function (value, rowData, rowIndex) {
                    if (value == "0") {
                        return "手术未做"
                    }
                    if (value == "1") {
                        return "手术已做"
                    }
                }
            },
            {
                field: 'id',
                title: '操作',
                width: '25%',
                align: 'center',
                formatter: function (value, row, index) {
                    var html = '';
                    html = html + '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>';
                    html = html + '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                    return html;
                }
            }
        ]]
    });


    //手术室下拉框
    $('#operatingRoom').combobox({
        data: operatingRoom,
        valueField: 'deptCode',
        textField: 'deptName',
        required: true,
        onSelect: function (n, o) {
            $("#operatingRoomCode").val(n.deptCode);
            comboboxLoad(n.deptCode, 'operatingRoomNo', 'operatingRoomNoCode');
        }
    });

    /**
     * 麻醉方式
     */
    $("#anesthesiaMethod").combobox({
        data: anaesthesiaName,
        valueField: 'id',
        textField: 'label',
        onSelect: function (n, o) {
            $("#anesthesiaMethodId").val(n.value);
        }
    });
    /**
     * 手术科室
     */
    $('#operatingDept').combobox({
        data: clinicDeptCode,
        valueField: 'id',
        textField: 'dept_name',
        onSelect: function (data) {
            $("#operatingDeptId").val(data.id);
        }
    })
    /**
     * 供血方式
     */
    $("#provideWay").combobox({
        data: provideWay,
        valueField: 'id',
        textField: 'label',
        onSelect: function (data) {
            $("#provideWayId").val(data.value);
        }
    })
    /**
     * 手术病情
     */
    $("#patientCondition").combobox({
        data: patientCondition,
        valueField: 'id',
        textField: 'label',
        onSelect: function (n, o) {
            $("#patientConditionId").val(n.value);
        }
    })

    /**
     * 隔离
     */
    $("#isolationIndicator").combobox({
        data: isolationIndicator,
        valueField: 'id',
        textField: 'label',
        onSelect: function (n, o) {
            $("#isolationIndicatorId").val(n.value);
        }
    })

    /**
     * 手术等级
     */
    $("#operationScale").combobox({
        data: operationScaleName,
        valueField: 'id',
        textField: 'label',
        onSelect: function (n, o) {
            $("#operationScaleId").val(n.value);
        }
    });
};

//增加手术名称
function save() {
    $.ajax({
        //添加
        url: basePath + "/diagnosis/findListOfIn",
        type: "GET",
        dataType: "json",
        data: {"patientId": patientIds, "visitId": visitIds},//住院visitId不为null
        success: function (data) {
            if (data != "" && data != null) {
                var rows = $('#operationName').datagrid('getRows');
                var formJson = fromJson('operation');
                formJson = formJson.substring(0, formJson.length - 1);
                var tableJson = JSON.stringify(rows);
                if(rows !=null && rows !=""){
                    var submitJson = formJson + ",\"scheduledOperationNameList\":" + tableJson + "}";
                    //alert(tableJson);
                    $.postJSON(basePath + '/operatioinOrder/saveIn', submitJson, function (data) {
                        if (data == "1") {
                            $.messager.alert("提示消息", data + "条记录，保存成功");
                            $('#operationName').datagrid('clearChecked');
                            $('#operationNameList').datagrid('load');
                            $("#operation").form('clear');
                            $('#operationName').datagrid('loadData', {total: 0, rows: []});

                        } else {
                            $.messager.alert('提示', "保存失败", "error");
                            $('#operationName').datagrid('load');
                            $('#operationName').datagrid('clearChecked');
                        }
                    }, function (data) {
                        $.messager.alert('提示', "保存失败", "error");
                    })
                }else {
                    $.messager.alert("提示消息", "请添加手术名称");
                }
            }else{
                $.messager.alert('提示消息', '病人没有诊断信息，不能开手术申请');
            }
        }
    })
}

//删除
function doDelete() {
    var selectRows = $('#operationName').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    var rows = $('#operationName').datagrid("getSelections");
    if (rows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    var copyRows = [];
    for (var j = 0; j < rows.length; j++) {
        copyRows.push(rows[j]);
    }
    for (var i = 0; i < copyRows.length; i++) {
        if (typeof(copyRows[i].id) != "undefined") {
        }
        var index = $('#operationName').datagrid('getRowIndex', copyRows[i]);
        $('#operationName').datagrid('deleteRow', index);
    }


    ////提醒用户是否是真的删除数据
    //$.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
    //    if (r) {
    //        var strIds = "";
    //        for (var i = 0; i < selectRows.length; i++) {
    //            strIds += selectRows[i].id + ",";
    //        }
    //        strIds = strIds.substr(0, strIds.length - 1);
    //        if (strIds == 'undefined' || strIds == '') {
    //            var index1 = $('#operationName').datagrid('getRowIndex', $("#operationName").datagrid('getSelected'))
    //            $('#operationName').datagrid('deleteRow', index1);
    //        } else {
    //            //真删除数据
    //            $.ajax({
    //                'type': 'POST',
    //                'url': basePath + '/operatioinOrder/deleteHos',
    //                'contentType': 'application/json',
    //                'data': id = strIds,
    //                'dataType': 'json',
    //                'success': function (data) {
    //                    if (data == 1) {
    //                        $.messager.alert("提示消息", data + "条记录删除成功！");
    //                        $('#operationName').datagrid('load');
    //                        $('#operationName').datagrid('clearChecked');
    //                    } else {
    //                        $.messager.alert('提示', "删除失败", "error");
    //                    }
    //                },
    //                'error': function (data) {
    //                    $.messager.alert('提示', "删除失败", "error");
    //                }
    //            });
    //        }
    //    }
    //})
}

function look(id){
    alert("1")
    $.ajax({
        type: 'get',
        url: basePath + '/operatioinOrder/get',
        data: {"id":id},
        async:true,
        success: function (data) {
            $("#operation").form("load",data);
            $("#patientCondition").combobox("setValue",patientConditionFormatter(data.patientCondition,'',''));
            $("#isolationIndicator").combobox("setValue",isolationIndicatorFormatter(data.isolationIndicator,'',''));
            $("#surgeon").combogrid("setValue",doctorNameFormatter(data.surgeon,'',''));
            $("#operatingRoom").combobox("setValue",operationFormatter(data.operatingRoom,'',''));
            //$("#operatingRoomNo").combobox("setValue",operationRoomNoFormatter(data.operatingRoomNo,'',''));
            $("#bloodTranDoctor").combogrid("setValue",doctorNameFormatter(data.bloodTranDoctor,'',''));
            $("#firstAssistant").combogrid("setValue",doctorNameFormatter(data.firstAssistant,'',''));
            $("#secondAssistant").combogrid("setValue",doctorNameFormatter(data.secondAssistant,'',''));
            $("#thirdAssistant").combogrid("setValue",doctorNameFormatter(data.thirdAssistant,'',''));
            $("#fourthAssistant").combogrid("setValue",doctorNameFormatter(data.fourthAssistant,'',''));
            $("#anesthesiaDoctor").combogrid("setValue",doctorNameFormatter(data.anesthesiaDoctor,'',''));
            $("#anesthesiaAssistant").combogrid("setValue",doctorNameFormatter(data.anesthesiaAssistant,'',''));
            $("#anesthesiaMethod").combobox("setValue",anaesthesiaNameFormatter(data.anesthesiaMethod,'',''));
            var scheduleId = data.id;
            $("#operationName").datagrid({
                url: basePath + "/operatioinOrder/getOperationNameList",
                queryParams:{"scheduleId":scheduleId},
                method: "get"
            })

        }
    })
}