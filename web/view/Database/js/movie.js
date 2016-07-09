$(function(){
    var filmId = $.getUrlParam("filmId");
    var dateStr = $.getUrlParam("dateStr");

    //为空或者不为数字时默认跳转主页
    if( !filmId || (filmId && !/^\d+$/.test(filmId)) ){
        window.location.href = "index.html";
    }

    $.ajax({
        url: "/user/query/getOneFilmSessionInfo.do",
        data: {filmId: filmId, dateStr: dateStr},
        dataType: "json",
        type: "post",
        success: function (data) {
            if(data.state == 1){
                var simpleFilmBean = data.simpleFilmBean;
                $("#filmPicture").attr("src", "http://localhost:8080" + simpleFilmBean.pricturePath);
                $("#filmPicture").attr("onclick", "getFilmInfo(" + filmId + ")");
                $("#filmInfo").append(
                    "<li>总评分:" + simpleFilmBean.rating + "</li>" +
                    "<li>上映：" + simpleFilmBean.releaseDate + "</li>" +
                    "<li>类型：" + simpleFilmBean.kind + "</li>" +
                    "<li>时长：" + simpleFilmBean.duration + "分钟" + "</li>"
                );
                $("#filmTitle").append(simpleFilmBean.filmName);

                //获取今明后天的显示与后台格式化
                var nowDate = new Date();
                var year = nowDate.getFullYear();
                var month = nowDate.getMonth() + 1;
                var day = nowDate.getDate();

                var now = "今天 " + month + "月" + day + "日";
                var nowFormat = getFormatDate(year, month, day);

                var tomorrowDate = getTomorrow(nowDate);
                year = tomorrowDate.getFullYear();
                month = tomorrowDate.getMonth() + 1;
                day = tomorrowDate.getDate();

                var tomorrow = "明天 " + month + "月" + day + "日";
                var tomorrowFormat = getFormatDate(year, month, day);

                var dayAfterTomorrowDate = getDayAfterTomorrow(nowDate);
                year = dayAfterTomorrowDate.getFullYear();
                month = dayAfterTomorrowDate.getMonth() + 1;
                day = dayAfterTomorrowDate.getDate();

                var dayAfterTomorrow = "后天 " + month + "月" + day + "日";
                var dayAfterTomorrowFormat = getFormatDate(year, month, day);

                $("#nav").append("<li class=\"active\" onclick=\"" + "getOneDayFilmSessionInfo(" +
                    filmId + ",'" + nowFormat +"')\">" + now + "</li>");
                $("#nav").append("<li onclick=\"" + "getOneDayFilmSessionInfo(" +
                    filmId + ",'" + tomorrowFormat +"')\">" + tomorrow + "</li>");
                $("#nav").append("<li onclick=\"" + "getOneDayFilmSessionInfo(" +
                    filmId + ",'" + dayAfterTomorrowFormat +"')\">" + dayAfterTomorrow + "</li>");

                showSession(data.film2Session);

            }

        },
        error: function(err){
            alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
            return err;
        }
    });

});
//跳转至显示电影主要信息
function getFilmInfo(filmId){
    window.location.href = "./filmInfo.html?filmId=" + filmId;
}
//获取某一天的当前电影的影院与场次信息
function getOneDayFilmSessionInfo(filmId, dateStr){
    window.location.href = "./movie.html?filmId=" + filmId + "&dateStr=" + dateStr;
}
//<li>
//<h5 id="cinemaName">哈艺时尚影城-白云YH城店</h5>
//<h5 id="cinemaAddress">广州市白云区鹤龙一路208号商业楼三层</h5>
//
//    <ul class="clearFix">
//        <!--<li>-->
//        <!--<a href="./buyTicket.html?sessionId=">-->
//        <!--<div class="timeBox f1">-->
//        <!--<p>17:35</p>-->
//         <!--<p>英语 3D</p>-->
//    <!--</div>-->
//    <!--<div class="priceBox f1">-->
//         <!--<span>￥33</span>-->
//    <!--</div>-->
//    <!--</a>-->
//    <!--</li>-->
//     </ul>
//</li>
//<hr/>
//显示影院及场次信息
function showSession(film2Session){
    //清空内容
    $(".nowTab").html("");
    $.each(film2Session, function(key, value){
        var heads = key.split(",");
        var showCinemaName = "<h5 id=\"cinemaName\" onclick='showCinemaSessionInfo(" + heads[2]
            + ")'>" + heads[0] + "</h5>";
        var showCinemaAddress = "<h5 id=\"cinemaAddress\">" + heads[1] +"</h5>";
        var showMinPrice = "<h5 id=\"cinemaSessionMinPrice\">最低售价:" + heads[3] +"</h5>";

        var sessions = eval(value);

        var top = "<ul class=\"clearFix\">";
        var bottom = "</ul>"

        $.each(sessions, function(index){
            var content =
                "<li>" +
                "<a href=\"./buyTicket.html?sessionId=" + sessions[index].filmSessionId + "\">" +
                "<div class=\"timeBox f1\">" +
                "<p>" + sessions[index].startDate + "</p>" +
                "<p>" + sessions[index].type +"</p>" +
                "</div>" +
                "<div class=\"priceBox f1\">" +
                "<span>￥" + sessions[index].price + "</span>" +
                "</div>" +
                "</a>" +
                "</li>";

            top += content;
        });

        var sessionContent = top + bottom;
        var htmlContent = "<li>" + showCinemaName + showCinemaAddress + showMinPrice + sessionContent +
                "</li><hr/>";

        $(".nowTab").append(htmlContent);
    })
}

function showCinemaSessionInfo(cinemaId){
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() + 1;
    var day = nowDate.getDate();

    var nowFormat = getFormatDate(year, month, day);

    window.location.href = "cinema.html?cinemaId=" + cinemaId + "&dateStr=" + nowFormat;
}

////特效
//function test(){
//    var nav = document.getElementById("nav");
//    var ali = nav.getElementsByTagName("li");
//    var tab = document.getElementById("tab-list");
//    var adiv = tab.getElementsByTagName("div");
//    for(var i = 0;i<ali.length;i++)
//    {
//        ali[i].index = i;
//        ali[i].onmouseover = function()
//        {
//            for(var j =0;j < ali.length;j++)
//            {
//                ali[j].className = "";
//            }
//            this.className = "active";
//
//            for(var j =0;j<adiv.length;j++)
//            {
//                adiv[j].className = "hide";
//            }
//            adiv[this.index].className = "show";
//        }
//    }
//}
//
//var input = document.getElementsByTagName("input");
//for(var i = 0;i < input.length;i++)
//{
//	document.getElementsByTagName("input")[i].onclick = function()
//	{
//		window.open("buyTicket.html","_blank","width=600,height=400");
//	}
//}