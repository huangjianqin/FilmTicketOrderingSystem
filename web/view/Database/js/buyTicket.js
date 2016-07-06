var money = 0;
var ticketNum = 0;
window.onload = function()
{
	function showSeats()
	{
		var $icon = '<img src="picture/seat.ico" width="30" height="30"';
		var $temp = "";
		var $count = 0;
		for (var i = 0;i < 10;i++)
		{
			for(var j = 0;j < 10;j++)
			{
				var $icon = '<img src="picture/seat.ico" width="30" height="30" id = pic'+ $count +' onclick = change(this) />';
				$temp += $icon;
				$count = $count + 1;
			}
			$temp += "<br />";
		}
		return $temp;
	}
	$(".seat").append(showSeats());
	
	$("#buy").click(function(){
		alert("购票成功！");
	});
}
function change(object){
	if($(object).attr("src")=="picture/seat.ico")
	{
		$(object).attr("src","picture/right.jpg");
		money = money + 50;
		ticketNum++;
	}
	else
	{
		$(object).attr("src","picture/seat.ico");
		money = money - 50;
		ticketNum--;
	}
	$("#money").html("总计：￥"+money);
	$("#ticketNum").html("票数："+ticketNum);
	$("#leftSeats").html("（剩余"+(100-ticketNum)+"个座位）");
}	