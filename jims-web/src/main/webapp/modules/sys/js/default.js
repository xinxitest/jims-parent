$(function () {
    var loginInfo = undefined ;
    var promise = $.getJSON("/service/login/get-login-info",function(data){
        loginInfo = data ;
        console.log(data);
        //阻止非法用户进入和长时间未操作需要再次登录
        if(loginInfo == undefined || loginInfo==null || !loginInfo.persionId){
            window.location.href = "/modules/sys/login.html";
        }
    }) ;
    promise.done(function() {
        var dataArr=[] ;
        $.get('/service/persion-service-list/findListByFlag?persionId=' + loginInfo.persionId, function (data) {

            if (data != null) {
                for (var i = 0; i < data.length; i++) {
                    dataArr.push({id:data[i].id,serviceName: data[i].serviceName,serviceImage:data[i].serviceImage,tranServiceDescription:data[i].tranServiceDescription});
                }

                //向页面添加服务
                var liArr = $('#addServiceModel ul li')
                for (var i = 0; i < dataArr.length; i++) {
                    var li = '<li id="service_' + dataArr[i].id + '" data-target="#serviceDialog" data-toggle="modal">';
                    li += '<a ><span class="service-name">' + dataArr[i].serviceName + '</span></a>'
                    if(dataArr[i].serviceImage==null)
                    {
                        li += '<img src="/static/bookstrap/images/service/normal.jpg"/>'
                    }  else{
                        li += '<img src="'+dataArr[i].serviceImage+'"/>'
                    }
                    li += '<div id="des_'+dataArr[i].id+'" style=" display:none; ">'+(dataArr[i].tranServiceDescription==null?"":dataArr[i].tranServiceDescription)+'</div>'
                    li += '</li>'
                    $('#addServiceModel ul').append(li);
                }

                //点击服务跳转到首页
                $('#addServiceModel ul li').each(function () {
                    $(this).click(function () {
                        var id=this.id.substring(8)
                        $.get('/service/sys-sompany/get-sysCompany-by-id?id=' + id, function (data) {
                            if(data == undefined){
                                if($("#des_"+id).html()==null||$("#des_"+id).html()==""){
                                    $("#serviceDialogDes").html('尚未添加描述');
                                }else{
                                    $("#serviceDialogDes").html($("#des_"+id).html());
                                }
                            }else{
                                if(data.applyStatus=="2")
                                {
                                    window.location.href="/modules/index.html";
                                }else{
                                    window.location.href="/modules/sys/company.html?flag=1";
                                }
                            }
                        });


                    });
                });

                //" +"&persion_id="+persion_id



                if (dataArr.length != 7) {
                    for (var j = 0; j < (7 - dataArr.length); j++) {
                        var li = '<li>';
                        li += '</li>';
                        $('#addServiceModel ul').append(li);
                    }
                }
                if (dataArr.length > 7) {
                    var li = '<li> <span style="font-size: 40px;" id="more">更多</span>';
                    li += '</li>';
                    $('#addServiceModel ul').append(li);
                }


            }

        });
    }) ;
    //
    //$("#serviceDialog1").dialog({
    //    title: '基础服务描述',
    //    width: 580,
    //    height: 250,
    //    closed:true
    //});
    //注册机构
    $("#setCompany").on('click', function () {
        window.location.href = "/modules/sys/company.html";
    });
    //个人首页
    $("#default").on('click', function () {
        window.location.href = "/modules/sys/default.html";
    });
    //我的服务
    $("#myServices").on('click', function () {
        window.location.href = "/modules/sys/service-list.html";
    });
    //定制更多个人服务
    $("#moreServices").click(function () {
        window.location.href = "/modules/sys/persion-services.html";
    });
    //退出
    $("#exit").on("click", function () {
        $.getJSON("/service/login/exit",function(data){
            window.location.href = "/modules/sys/login.html";
        }) ;
    });
});
