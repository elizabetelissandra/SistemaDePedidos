function ArniaUtil(){
}

ArniaUtil.prototype.getJSON = function(url,data,callback){
	 $.ajax({
			type: "POST",
			url: url,
			data: data,
			contentType:"application/x-www-form-urlencoded; charset=UTF-8",
			success: function(data){
					data = eval("(" +data + ")");
					callback(data);
				}
		});
}

var $a = new ArniaUtil();