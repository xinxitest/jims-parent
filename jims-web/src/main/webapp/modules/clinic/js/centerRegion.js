var clinicMaster={};
var doctorDept=[];
$(function(){
    $.ajax({
        type: "GET",
        url:basePath +"/dept-dict/getDoctorDept",
        data: "doctorGroup=门诊医生组",
        dataType: "json",
        async: false,
        success: function(data){
            doctorDept=data;
            if(data.length>1){
                var deptHtml='<ul>';
                for(var i=0; i<data.length; i++){
                    deptHtml+='<li style="text-align: center" onclick="getDoctorDept(\''+data[i].id+'\')">'+data[i].deptName+'</li>';
                }
                deptHtml+='</ul>';
                $("#doctorDeptDiv").html(deptHtml);
                $('#doctorDept').dialog({
                    title: '出诊科室选择',
                    iconCls: "icon-edit",
                    closable: false,
                    width: "40%",
                    height: "30%",
                    modal: true
                });
            }else{
                parent.config.deptCode=data[0].deptCode;
                parent.config.deptName=data[0].deptName;
                parent.config.deptId=data[0].id;
            }

        }
    });




    ///**
    // * 科室下拉框
    $('#deptNameId').combobox({
        data: doctorDept,
        valueField: 'id',
        textField: 'deptName',
        onChange: function () {
            var status=$('#wrap input[name="rad05"]:checked ').val();
            var dept=$('#deptNameId').combobox('getValue');
            patientList(status,dept);
        }
    })
    if(doctorDept.length==1){
        $('#deptNameId').combobox('select', parent.config.deptId);
    }
    //添加Tabs
    $(".tabs-header").bind('contextmenu',function(e){
        e.preventDefault();
        $('#rcmenu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    });
    // 刷新当前标签页
    $("#refresh").bind("click",function(){
        var currTab = $('#tabs-header').tabs('getSelected'); //获得当前tab
        var frameObj=$("iframe",currTab);
        $(frameObj).attr("src", $(frameObj).attr("src"))
        //var url = $(currTab.panel('options').content).attr('src');
        //currTab.panel('refresh', url);
    });

    //关闭当前标签页
    $("#closecur").bind("click",function(){
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        $('#tabs-header').tabs('close',index);
    });
    //关闭所有标签页
    $("#closeall").bind("click",function(){
        //var tablist = $('#tabs-header').tabs('tabs');
        //for(var i=tablist.length-1;i>=0;i--){
        //    $('#tabs-header').tabs('close',i);
        //}
        closeTabs();
    });
    //关闭非当前标签页（先关闭右侧，再关闭左侧）
    $("#closeother").bind("click",function(){
        var tablist = $('#tabs-header').tabs('tabs');
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#tabs-header').tabs('close',i);
        }
        var num = index-1;
        for(var i=num;i>=0;i--){
            $('#tabs-header').tabs('close',0);
        }
    });
    //关闭当前标签页右侧标签页
    $("#closeright").bind("click",function(){
        var tablist = $('#tabs-header').tabs('tabs');
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#tabs-header').tabs('close',i);
        }
    });
    //关闭当前标签页左侧标签页
    $("#closeleft").bind("click",function(){
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        var num = index-1;
        for(var i=0;i<=num;i++){
            $('#tabs-header').tabs('close',0);
        }
    });
});

/**
 * 获取医生科室
 */
function getDoctorDept(id){
    parent.config.deptId=id;
    $('#deptNameId').combobox('select', id);
    $("#doctorDept").dialog("close");
}

function closeTabs(){
    var tablist = $('#tabs-header').tabs('tabs');
    for(var i=tablist.length-1;i>=0;i--){
        $('#tabs-header').tabs('close',i);
    }
}

/**
 * tabs 增加
 * @param id
 * @param name
 * @param url
 * @param lia
 */
function addTabs(id,name,url,lia){
    $(lia).parent().parent().find("li a").removeClass();
    $(lia).addClass("active");
    var content = '<iframe  src="'+url+'" frameborder="0" border="0" marginheight="0" marginwidth="0" width="100%" height="91%"></iframe>';
    if(!$("#tabs-header").tabs('exists',name)){
        $('#tabs-header').tabs('add',{
            id:id,
            title: name,
            selected: true,
            content:content,
            //href:url,
            closable:true
        });
    }else $('#tabs-header').tabs('select',name);


}

/**
 * 显示DIV
 * @param id
 */
function showDiv(targetid,objN){
    var d=$("#"+targetid);
    var sb=$("#"+objN);
    if (d.css('display')=="none"){
        d.show();
        sb.html("<img src='/static/images/index/up-icon.png' class='show-hid-img'/>&nbsp;收缩")

    } else {
        d.hide();
        sb.html("<img src='/static/images/index/down-icon.png' class='show-hid-img'/>&nbsp;展开")
    }
}
/**
 * 显示医生
 * @param id
 */
function showDoctor(targetid){
    var d=$("#"+targetid);
    if (d.css('display')=="none"){
        d.show();

    } else {
        d.hide();
    }
}
//加载病人列表  默认 我的病人（待诊）
function patientList(status,dept){
    var liHtml='';
    var url='';
    if(status=='0'){
        url=basePath + '/clinicMaster/clinicMasterList?deptId='+dept;
    }else{
        url=basePath + '/clinicMaster/clinicMasterDiagnosed?deptId='+dept;
    }
    $.get(url, function (data) {
        for (var i = 0; i < data.length; i++) {
            liHtml+='<li><a onclick="userMenu(\''+data[i].id+'\',this)">' +
            '<span class="cus-lbor"></span>' +
            '<span class="cus-name">'+data[i].name+'</span>' ;
            var sex=setDataFormatter(data[i].sex, '', '');
            liHtml=liHtml+'&nbsp;'+sex+'&nbsp; '+data[i].age+'</a></li>';
        }
        $('ul.cus-list').html(liHtml);
    })
}
/**
 * 获取病人的就诊信息
 * @param clinicMasterId
 */
function userMenu(clinicMasterId,aBtn){
    $(aBtn).parent().parent().find("li a").removeClass();
    $(aBtn).addClass("active");
    closeTabs();
    $.ajax({
        'type': 'get',
        'url': basePath + '/clinicMaster/get',
        'contentType': 'application/json',
        'data': {id:clinicMasterId},
        'dataType': 'json',
        'success': function(data){
            clinicMaster=data;
            $("#nameId").html(data.name);
            $("#ageId").html(data.age);
            $("#sexId").html(setDataFormatter(data.sex, '', ''));
            $("#clinicNo").html(data.clinicNo);
            $("#visitDate").html(formatDatebox(data.visitDate));
            $("#clinicMasterId").val(data.id);
        },
        'error': function(){

        }
    })
    var html='';
    html+='<li><a class="active" onclick="addTabs(\'3\',\'病人信息\',\'/modules/clinic/patientInfo.html\',this)"><span>病人信息</span></a></li>';
    html+='<li><a  onclick="addTabs(\'2\',\'病历文书\',\'/modules/emr/enterHospital/enterHosptial.html\',this)"><span>病历文书</span></a></li>';
    html+='<li><a onclick="addTabs(\'4\',\'检查申请\',\'/modules/doctor/clinicInspect/clinicInspect.html\',this)"><span>检查申请</span></a></li>';
    html+='<li><a  onclick="addTabs(\'6\',\'检验申请\',\'/modules/doctor/lab/labTest.html\',this)"><span>检验申请</span></a></li>';
    html+='<li><a onclick="addTabs(\'7\',\'处方\',\'/modules/doctor/prescription/prescriptionList.html\',this)"><span>处方</span></a></li>';
    html+='<li><a onclick="addTabs(\'8\',\'治疗信息\',\'/modules/doctor/clinicItem/clinicItem.html\',this)"><span>治疗信息</span></a></li>';
    html+='<li><a onclick="addTabs(\'10\',\'用血申请\',\'/modules/doctor/useBlood/docUseBloodList.html\',this)" ><span>用血申请</span></a></li>';
    html+='<li><a   onclick="addTabs(\'13\',\'手术申请\',\'/modules/doctor/operation/docOperationApplyList.html\',this)"><span>手术申请</span></a></li>';
    html+='<li><a   onclick="addTabs(\'14\',\'住院通知单\',\'/modules/doctor/hospitalNotice/patHospitalNoticeList.html\',this)"><span>住院通知单</span></a></li>';
    $("#userMenuId").html(html);
    $("#userMenuId li:first a").click();
}




