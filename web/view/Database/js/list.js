/**
 * Created by 健勤 on 2016/7/9.
 */
$(function(){
    var keyword = $.getUrlParam("keyword");

    //为空或者不为数字时默认跳转主页
    if( !keyword ){
        window.location.href = "index.html";
    }

    $.ajax({
        url: "/user/query/search.do",
        data: {keyword: keyword},
        dataType: "json",
        type: "post",
        async: true,
        success: function(data){
            if(data.state == 1){
                var searchResultBeans = data.searchResultBeans;

                var filmListContent = "";
                var cinemaListContent = "";

                $.each(searchResultBeans, function(index){
                    var id = searchResultBeans[index].id;
                    var name = searchResultBeans[index].name;
                    var type = searchResultBeans[index].type;

                    var nowDate = new Date();
                    var year = nowDate.getFullYear();
                    var month = nowDate.getMonth() + 1;
                    var day = nowDate.getDate();

                    var nowFormat = getFormatDate(year, month, day);

                    if(type === "film"){
                        filmListContent +=
                            "<li><a href='./movie.html?filmId=" + id + "&dateStr=" + nowFormat + "' >" + name + "</a></li>"
                    }else if(type === "cinema"){
                        cinemaListContent +=
                            "<li><a href='./cinema.html?cinemaId=" + id + "&dateStr=" + nowFormat + "' >" + name + "</a></li>"
                    }

                });

                if(filmListContent === ""){
                    $(".top").attr("style","display: none");
                }
                else{
                    $("#topTitle").append("电影");
                }

                if(cinemaListContent === ""){
                    $(".bottom").attr("style","display: none");
                }
                else{
                    $("#bottomTitle").append("电影院");
                }

                $("#filmList").append(filmListContent);
                $("#cinemaList").append(cinemaListContent);

            }
        },
        error: function(err){
            alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
            return err;
        }
    });

});