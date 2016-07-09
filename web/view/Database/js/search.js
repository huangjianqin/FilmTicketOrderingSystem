/**
 * Created by 健勤 on 2016/7/8.
 */
$(function () {
    $("#search").bind("click", search);
});

function search(){
    window.location.href = "./list.html?keyword=" + $("#keyword").val();
}