var before = document.getElementById("before");
var count = 0;
var pictures = new Array(4);
pictures[0] = '<img src="picture/Americian.jpg" width="500" height="550" />';
pictures[1] = '<img src="picture/fenQiZhe.jpg" width="500" height="550" />';
pictures[2] = '<img src="picture/nightPeacock.jpg" width="500" height="550" />';
pictures[3] = '<img src="picture/angryBird.jpg" width="500" height="550" />';
window.onload = function(){
	before.onclick = function()
	{
		if (count == 0)
		{
			count = pictures.length - 1;
		}
		else{
			count = count - 1;
		}
		document.getElementById("picture").innerHTML = pictures[count];
	}
	next.onclick = function()
	{
		if (count == pictures.length - 1)
		{
			count = 0;
		}
		else{
			count = count + 1;
		}
		document.getElementById("picture").innerHTML = pictures[count];
	}
	var searchIcon = document.getElementById("search");
	searchIcon.onclick = function()
	{
		alert("搜索中。。。");
	}
}