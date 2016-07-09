/**
 * Created by 健勤 on 2016/7/9.
 */
$(function(){
    var filmId = $.getUrlParam("filmId");

    //为空或者不为数字时默认跳转主页
    if( !filmId || (filmId && !/^\d+$/.test(filmId)) ){
        window.location.href = "index.html";
    }

    $.ajax({
        url: "/user/query/getFilmInfo.do",
        data: {filmId: filmId},
        dataType: "json",
        type: "post",
        async: true,
        success: function(data){
            if(data.state == 1){
                $("#pricture").attr("src", data.pricturePath);
                $("#filmName").append(data.filmName);
                $("#kind").append("类型: " + data.kind);
                $("#releaseDate").append("上映时间: " + data.releaseDate);
                $("#description").append("简介: " + data.description);
                $("#duration").append("时长: " + data.duration + "分钟");
                
                var actorsStr = "演员:";
                $.each(data.actors, function (index) {
                    if(index != data.actors.length - 1){
                        actorsStr += data.actors[index].actorName + ",";
                    }
                    else{
                        actorsStr += data.actors[index].actorName;
                    }
                });

                $("#actors").append(actorsStr);
                $("#showSession").attr("onclick", "getFilmSession(" + filmId + ")");
            }
        },
        error: function(err){
            alert("系统管理员已记录错误操作,正为你马上解决问题,请你耐心等候!");
            return err;
        }
    });

});