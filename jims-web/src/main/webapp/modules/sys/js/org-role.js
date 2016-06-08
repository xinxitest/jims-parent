/**
 * Created by fyg on 2016/6/2.
 */
$(function () {
    //var orgId = parent.config.orgId;
    var orgId = '1';
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }
    //alert("orgId:" + orgId);
    $("#dg").datagrid({
        title: '角色信息维护',
        fit: true,
        footer: '#tb',
        singleSelect: true,
        rownumbers: true,
        method: 'get',
        url: basePath + '/org-role/findAllListByOrgId?orgId=' + orgId,
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "组织ID",
            field: "orgId",
            align: 'center',
            width: "20%",
            hidden: true
        }, {
            title: "角色名称",
            field: "roleName",
            align: 'center',
            width: '20%',
            editor: 'text'
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });

    $("#addBtn").on("click", function () {
        stopEdit();
        $("#dg").datagrid('appendRow', {});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
    });

    $("#delBtn").on("click", function () {
        var row = $("#dg").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#dg").datagrid('getRowIndex', row);
            $("#dg").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

    $("#saveBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateDate = $("#dg").datagrid("getChanges", "updated");
        var deleteDate = $("#dg").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteDate;
        beanChangeVo.updated = updateDate;

        if (beanChangeVo.inserted.length > 0) {
            for (var i = 0; i < beanChangeVo.inserted.length; i++) {
                beanChangeVo.inserted[i].orgId = orgId;     //设置组织ID
                var roleName = beanChangeVo.inserted[i].roleName;
                if (roleName.length == 0) {
                    $.messager.alert('提示', '角色名称不能为空!!', 'error');
                    return;
                }
            }
        }
        if (beanChangeVo.updated.length > 0) {
            for (var i = 0; i < beanChangeVo.updated.length; i++) {
                var roleName = beanChangeVo.updated[i].roleName;
                if (roleName.length == 0) {
                    $.messager.alert('提示', '角色名称不能改为空!!', 'error');
                    return;
                }
            }
        }
        if (beanChangeVo) {
            $.postJSON(basePath + '/org-role/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    });
    $("#searchBtn").on("click", function () {
        var name = $("#name").textbox("getValue");
        $.get(basePath + '/org-role/find-by-name?roleName=' + name,function(data){
            $("#dg").datagrid('loadData', data);
        });
    });

    var loadDict = function () {
        $.get(basePath + '/org-role/findAllListByOrgId?orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }
    loadDict();

});