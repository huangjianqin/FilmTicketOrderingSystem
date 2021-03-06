$(function(){
	$.getUrlParam = function (name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	};

	$("#userName").bind("click", function(){
		if($("#userName").val() === "请登录"){
			window.location.href = "./register.html";
		}
		else{
			logout();
		}
	});

	$("#logo").bind("click", function(){
		window.location.href = "./index.html";
	})

});


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
				$("#userName").html("请登录");
			},
			error: function(err){
				alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
				return err;
			}
		})
	}

}

function getFormatDate(fullYear, month, day){
	var formatDate = fullYear + "-";
	if(month < 10){
		formatDate += "0";
	}
	formatDate += month + "-";
	if(day < 10){
		formatDate += "0";
	}
	formatDate += day;
	return formatDate;
}

function getTomorrow(date){
	date = +date + 1000*24*60*60;
	return new Date(date);
}

function getDayAfterTomorrow(date){
	return getTomorrow(getTomorrow(date));
}

//var flag = true;
//$(document).ready(function(){showWindow();})
//function showWindow()
//{
//	var string =
//		'<div class = "info">'
//		+'<span class="u_logo"></span>'
//		+'<input id = "name" type = "text" placeholder="请输入用户名或邮箱" value="" size="40">'
//		+'<br>'
//		+'<input id = "pwd" type = "text" placeholder="请输入密码" value="" size="40">'
//		+'</div>'
//		+'<div class = "btn">'
//		+'<span class="p_logo"></span>'
//		+'<input id = "register" type = "button" value = "注册">'
//		+'<input id = "login" type = "button" value = "登录">';
//		+'</div>';
//		var user = document.getElementById("userName");
//		user.onclick = function(event)
//		{
//			var index0;
//			if(flag)
//			{
//				closebtn = layer.open({
//				type: 1,
//				title: false,
//				closeBtn: 0,
//				shadeClose: true,
//				skin: 'yourclass',
//				content:string
//				});
//				var login = document.getElementById("login");
//				login.onclick = function()
//				{
//					if($("#name").val()=="lyh"&&$("#pwd").val()=="123")
//					{
//						flag = false;
//						//window.sessionStorage.setItem('flag','false');
//						layer.close(closebtn);
//					}
//					else
//					{
//						alert("用户名或密码错误");
//					}
//				}
//				var rigister = document.getElementById("register");
//				register.onclick = function()
//				{
//					alert("注册成功");
//				}
//			}
//			else
//			{
//				var divselect = document.getElementById("divselect");
//				divselect.style.display = "block";
//				event = event||window.event;
//				if(event.stopPropagation)
//				{
//					event.stopPropagation();
//				}
//				else
//				{
//					event.cancellBubble = true;
//				}
//			}
//		}
//
//		user.hover = function()
//		{
//			var divselect = document.getElementById("divselect");
//			divselect.style.display = "none";
//		}
//}
