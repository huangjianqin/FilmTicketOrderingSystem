/**
 * Created by 健勤 on 2016/7/9.
 */
$(function(){
    $("#login").bind("click", function(){
        $.ajax({
            url: "/user/info/login.do",
            data: {id: $("#name").val(), password: $("#pwd").val()},
            dataType: "json",
            type: "post",
            async: true,
            success: function(data){
                if(data.state == 1){
                    alert("登录成功!");
                    history.back();
                }
                else if(data.state == -1){
                    alert("登录失败!用户id或密码错误");
                }
            },
            error: function(err){
                alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
                return err;
            }
        });
    });
});