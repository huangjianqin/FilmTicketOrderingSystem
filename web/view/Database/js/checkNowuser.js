/**
 * Created by 健勤 on 2016/7/7.
 */
$(function(){
    //判断当前是否有用户登录
    $.ajax({
        url: "/user/info/getNowUser.do",
        async: true,
        dataType: "json",
        type: "post",
        success: function(data){
            if(data.state == 1){
                $("#userName").html(data.userName);

                $("#user_selector").append(
                    "<li><a href='#' id='personalOrder'>个人订单</a></li>" +
                    "<li><a href='#' id='modifyInfo'>修改个人信息</a></li>" +
                    "<li><a href='#' id='recharge'>充值</a></li>" +
                    "<li><a href='#' id='logout'>注销</a></li>");

                $("#logout").bind(logout);
            }
        }
    })
})

function logout(){
    var userName= $("#userName").val();

    if(!userName==""){
        $.ajax({
            url: "/user/info/logout.do",
            dataType: "json",
            async: true,
            type: "post",
            success: function(data){
                alert("成功注销!");
            },
            error: function(err){
                alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
                return err;
            }
        })
    }

}