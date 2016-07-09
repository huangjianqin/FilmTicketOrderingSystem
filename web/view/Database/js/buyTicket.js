$(function(){
	var sessionId = $.getUrlParam("sessionId");

	//为空或者不为数字时默认跳转主页
	if( !sessionId || (sessionId && !/^\d+$/.test(sessionId)) ){
		window.location.href = "index.html";
	}

	$.ajax({
		url: "/user/query/getOneFilmSessionDetail.do",
		async: true,
		data: {sessionId: sessionId},
		dataType: "json",
		type: "post",
		success: function(data){
			if(data.state == 1){
				$("#cinemaNameAndHall").append(data.cinemaName + " " + data.cinemaHallName);
				$("#leftSeats").append(data.restSeatNum + "个座位");
				$("#pricture").attr("src","http://localhost:8080" + data.pricturePath);
				$("#filmName").append(data.filmName);
				$("#duration").append(data.duration);
				$("#kind").append(data.kind);

				$("#cinemaName").append(data.cinemaName);
				$("#type").append(data.type);
				$("#startDate").append(data.startDate.replace("-", "年").replace("-", "日"));
				$("#price").append(data.price);

				//显示座位
				$(".seat").append(
					showSeats(data.selectedSeats, data.hall2Seat, data.restSeatNum,
							data.price));

				$("#buy").attr("onclick", "handInOrder(" + sessionId + ")");

			}
		},
		error: function(err){
			alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
			return err;
		}
	});

});

function handInOrder(sessionId){
	var userName = $("#userName").text();
	//未选择位置
	if(ticketNum == 0){
		alert("请选择观影座位!");
	}
	//未登录
	else if(userName === "请登录"){
		alert("请登录账号!");
		window.location.href = "./register.html";
	}
	//已登录
	else{
		$.ajax({
			url: "/user/ordering/handInOrder.do",
			data: {filmSessionId: sessionId, selectedSeat: selects},
			dataType: "json",
			type: "post",
			async: true,
			success: function(data){
				if(data.state == 1){
					alert("您的订单号:" + data.orderNum);
					//window.location.href = "?orderNum=" + data.orderNum;
				}
			},
			error: function (err) {
				alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
				return err;
			}
		})
	}
}


var money = 0;
var ticketNum = 0;
var selects = "";
function showSeats(selectedSeats, hall2Seat, restSeatNum, price)
{
	var changeFun = "change(this," + restSeatNum + "," + price + ")";
	var $seat = '<img src="picture/seat.ico" width="30" height="30"' +
				' onclick = "' + changeFun + '" ';
	var $seatEnd = '/>';
	var $selectedSeat = '<img src="picture/right.jpg" width="30" height="30" />';

	var $content = "";

	$.each(hall2Seat, function(key, value){
		$.each(eval(value), function(index){
			if(isSelectedSeat(value[index], selectedSeats)==1){
				$content += $selectedSeat;
			}
			else{
				$content += $seat + 'value="' + value[index] + '" ' + $seatEnd;
			}
		});

		$content += '<br/>';

	});

	return $content;
}

function isSelectedSeat(seat, selectedSeats){
	var flag = -1;
	$.each(selectedSeats, function(index){
		if(seat===selectedSeats[index]){
			flag = 1;
		}
	});
	return flag;
}

function change(object, restSeatNum, price){
	if($(object).attr("src")=="picture/seat.ico" && ticketNum < 4)
	{
		$(object).attr("src","picture/right.jpg");
		money = money + price;
		ticketNum++;
		addSelect($(object).attr("value"));
	}
	else if($(object).attr("src")=="picture/right.jpg")
	{
		$(object).attr("src","picture/seat.ico");
		money = money - price;
		ticketNum--;
		removeSelect($(object).attr("value"));
	}

	$("#selectedSeats").html("座位：" + selects);
	$("#money").html("总计：￥"+money);
	$("#ticketNum").html("票数："+ticketNum);
	$("#leftSeats").html("（剩余"+(restSeatNum-ticketNum)+"个座位）");
}

function addSelect(select){
	if(ticketNum != 1){
		selects += "," + select;
	}
	else{
		selects += select;
	}
}

function removeSelect(select){
	var temp = selects.split(",");
	selects = "";
	for(i = 0; i < temp.length; i++){
		if(select != temp[i]){
			selects += temp[i] + ",";
		}
	}

	temp = selects.split(",");
	selects = "";
	for(i = 0; i < temp.length - 2; i++){
		selects += temp[i] + ",";
	}

	selects += temp[temp.length - 2];

	if(ticketNum == 0){
		selects = "";
	}

}