<%--
  Created by IntelliJ IDEA.
  User: 健勤
  Date: 2016/7/6
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <script src="/view/jquery/jquery.js"></script>
</head>
<body>
<input type="button" value="提交" id="submit"/>
<script type="text/javascript">
  $(function(){
    $("#submit").bind("click", function (){
      $.ajax({
        url : "/user/query/getOneFilmSessionDetail.do",
        data : {sessionId:1},
        dataType : "json",
        type : "post",
        success : function(data){

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
