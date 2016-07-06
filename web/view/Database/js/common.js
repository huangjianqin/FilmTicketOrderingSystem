var flag = true;
$(document).ready(function(){showWindow();})
function showWindow()
{
	var string = 
		'<div class = "info">'
		+'<span class="u_logo"></span>'
		+'<input id = "name" type = "text" placeholder="请输入用户名或邮箱" value="" size="40">'
		+'<br>'
		+'<input id = "pwd" type = "text" placeholder="请输入密码" value="" size="40">'
		+'</div>'
		+'<div class = "btn">'
		+'<span class="p_logo"></span>'
		+'<input id = "register" type = "button" value = "注册">'
		+'<input id = "login" type = "button" value = "登录">';
		+'</div>';
		var user = document.getElementById("happy");
		user.onclick = function(event)
		{
			var index0;
			if(flag)
			{
				closebtn = layer.open({
				type: 1,
				title: false,
				closeBtn: 0,
				shadeClose: true,
				skin: 'yourclass',
				content:string
				});	
				var login = document.getElementById("login");
				login.onclick = function()
				{
					if($("#name").val()=="lyh"&&$("#pwd").val()=="123")
					{
						flag = false;
						//window.sessionStorage.setItem('flag','false');
						layer.close(closebtn);
					}
					else
					{
						alert("用户名或密码错误");
					}
				}
				var rigister = document.getElementById("register");	
				register.onclick = function()
				{
					alert("注册成功");
				}
			}
			else
			{
				var divselect = document.getElementById("divselect");
				divselect.style.display = "block";
				event = event||window.event;
				if(event.stopPropagation)
				{
					event.stopPropagation();
				}
				else
				{
					event.cancellBubble = true;
				}
			}
		}
				
		document.onclick = function()
		{
			var divselect = document.getElementById("divselect");
			divselect.style.display = "none";
		}
}