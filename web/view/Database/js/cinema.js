window.onload = function(){
    var nav = document.getElementById("nav");
    var ali = nav.getElementsByTagName("li");
    var tab = document.getElementById("tab-list");
    var adiv = tab.getElementsByTagName("div");
    for(var i = 0;i<ali.length;i++)
    {
        ali[i].index = i;
        ali[i].onmouseover = function()
        {
            for(var j =0;j < ali.length;j++)
            {
                ali[j].className = "";
            }
            this.className = "active";
                
            for(var j =0;j<adiv.length;j++)
            {
                adiv[j].className = "hide";
            }
            adiv[this.index].className = "show";
        }
    }
}
var input = document.getElementsByTagName("input");
for(var i = 0;i < input.length;i++)
{
	document.getElementsByTagName("input")[i].onclick = function()
	{
		window.open("buyTicket.html","_blank","width=600,height=400");
	}
}