<%--
  Created by IntelliJ IDEA.
  User: 健勤
  Date: 2016/6/30
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎!</title>
    <script src="/view/jquery/jquery.js"></script>
</head>
<body>
    <p id="p">原来的,后来的是</p>
    <input type="text" id="id"/>
    <input type="text" id="password"/>
    <input type="button" value="提交" id="submit"/>
    <script type="text/javascript">
        $(function(){
            $("#submit").bind("click", function (){
                $("#p").append($("input#id").val() + "<>" + $("input#password").val());
                $.ajax({
                    url : "/user/info/login.do",
                    async : true,
                    data : {id: $("input#id").val(), password: $("input#password").val()},
                    dataType : "json",
                    type : "post",
                    success : function(data){
                        if(data.state == 1){
                            alert(data.userName);
                        }
                        else{
                            alert("登录失败!");
                        }
                    },
                    error: function (err) {
                        alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
                        return err;
                    }
                });
            });
        });

    </script>

</body>
</html>
