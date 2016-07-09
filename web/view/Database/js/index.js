/**
 * Created by 健勤 on 2016/7/6.
 */
//获取首页电影海报
$(function(){
    $.ajax({
        url: "/user/query/getRecentFilms.do",
        async: true,
        dataType: "json",
        type: "post",
        success: function(data){
            if(data.state == 1){
                var simpleFilmBeans = data.simpleFilmBeans;

                var array = eval(simpleFilmBeans);
                var carouselIndicatorsContent = "";
                var carouselInnerContent = "";

                $.each(array, function(index){

                    var filmId = array[index].filmId;
                    var pricturePath = array[index].pricturePath;

                    //第一个要设置active
                    if(index == 0){
                        carouselIndicatorsContent += "<li data-target=\"#carousel-example-generic\" data-slide-to=\""
                            + index + "\" class=\"active\"></li>";
                        carouselInnerContent += (
                            "<div class=\"item active\">" +
                            "<input type=\"hidden\" name=\"filmId\" value=\"" + filmId + "\"/>" +
                            "<img src=\"http://localhost:8080" + pricturePath + "\" onclick=\"getFilmSession(" + filmId + ")\" />" +
                            "</div>");
                    }
                    else{
                        carouselIndicatorsContent += "<li data-target=\"#carousel-example-generic\" data-slide-to=\""
                            + index + "\"></li>";

                        carouselInnerContent += (
                            "<div class=\"item\">" +
                            "<input type=\"hidden\" name=\"filmId\" value=\"" + filmId + "\"/>" +
                            "<img src=\"http://localhost:8080" + pricturePath + "\" onclick=\"getFilmSession(" + filmId + ")\" />" +
                            "</div>");
                    }
                });

                $(".carousel-indicators").append(carouselIndicatorsContent);
                $(".carousel-inner").append(carouselInnerContent);
            }
        }
    });

});

function getFilmSession(filmId){
    //获取今天的格式化时间
    var nowDate = new Date();
    var year = nowDate.getFullYear();
    var month = nowDate.getMonth() + 1;
    var day = nowDate.getDate();

    var nowFormat = getFormatDate(year, month, day);

    window.location.href = "movie.html?filmId=" + filmId + "&dateStr=" + nowFormat;
}