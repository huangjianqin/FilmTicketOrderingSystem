jQuery.GetUrl = function(win, layer) {
	var target = "";
	try {
		if(win) {
			target = win.top.Agency.Target.value;
		} else {
			target = top.Agency.Target.value;
		}
	} catch (e) {
		target = "web";
	}
	if(target && target == "local") {
		if(layer) {
			var strPath = "";
			for(var i=0; i<layer; i++) {
				strPath += "../";
			}
			return strPath;
		} else {
			return "../../";
		}
	} else {
		var hrefStr= top.location.href;
		var index = hrefStr.indexOf("\/", 8);
		index = hrefStr.indexOf("\/", index+1);
		return hrefStr.substring(0, index+1);//return "http://localhost:8080/spring/";
	}
}
function parseJSON(string) {
	try {
		return eval('(' + string + ')');
	} catch (e) {
		throw new SyntaxError("解析JSON字符串;出错");
	}
}

