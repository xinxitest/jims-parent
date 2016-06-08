/**
 * Created by heren on 2016/6/7.
 */

window.addTab = function (title, href) {
    //如果路径为空，则直接返回
    if (!href) {
        return;
    }
    var tabs = $("#mainContent").tabs('tabs');
    if (tabs.length > 10) {
        $.messager.alert("系统提示", "打开的Tab页面太多，请观不需要的，重新在打开", 'info');
        return;
    }
    if ($("#mainContent").tabs('exists', title)) {
        $("#mainContent").tabs('select', title);
    } else {
        var content = undefined;
        content = '<iframe scrolling="auto" frameborder="0"  src="' + href + '" style="width:100%;height:100%;"></iframe>'
        $("#mainContent").tabs('add', {
            title: title,
            content: content,
            closable: true
        });
    }

}

$(document).ready(function () {
    var menuDatas = [];

    for (var i = 0; i < menus.length; i++) {
        menus[i].children = [];
    }
    for (var i = 0; i < menus.length; i++) {
        for (var j = 0; j < menus.length; j++) {
            if (menus[j].pid == menus[i].id) {
                menus[i].children.push(menus[j])
            }
        }
        if (menus[i].pid == "" || menus[i].pid == undefined || menus[i].pid == null) {
            menuDatas.push(menus[i]);
        }
    }
    var data = {
        title: '嵌入子模板',
        list: menuDatas
    };

    var html = template('master_menu', data);
    document.getElementById('content').innerHTML = html;
    $("#menuTree").tree();

    $(".vertical-nav").verticalnav({speed: 400, align: "left"});
});