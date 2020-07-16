var HTTP ={
	GET: function(options){
		var url = options.url || ''
		var contentType = options.contentType || 'application/json; charset=utf-8';
		var okCall = options.okCall || {};
		var errorCall = options.errorCall || null;
		var headers = options.headers || {
			'Authorization': accessToken.token
		};

		let accessToken = getAccessToken();
		if(accessToken){
			return;
		}
		if(judgeDate(accessToken.expirationTime) <= 0){
			localStorage.removeItem("token");
			let refreshTokenUrl = '/admin/sysUser/refreshToken?token=' + accessToken.token;
			$.ajax({
				type : 'get',
				url : refreshTokenUrl,
				contentType: contentType,
				success : function(result) {
					console.log(result)
					HTTP.SET_ACCESSTOKEN(result);
				}
			});
		}
		$.ajax({
			type : 'get',
			url : url,
			contentType: contentType,
			headers: headers,
			success : function(result) {
				okCall(result);
			},
			error:function(result){
				if(errorCall){
					errorCall(result)
				}else{
					top.layer.msg("执行失败",{icon:2});
				}
			}
		});
	},
	POST:function(options){
		var url = options.url || ''
		var contentType = options.contentType || 'application/json; charset=utf-8';
		var okCall = options.okCall || {};
		var data = options.data || {};
		var headers = options.headers || {
			'Authorization': accessToken.token
		};

		let accessToken = getAccessToken();
		if(accessToken){
			return;
		}
		if(judgeDate(accessToken.expirationTime) <= 0){
			localStorage.removeItem("token");
			let refreshTokenUrl = '/admin/sysUser/refreshToken?token=' + accessToken.token;
			$.ajax({
				type : 'get',
				url : refreshTokenUrl,
				contentType: contentType,
				success : function(result) {
					console.log(result)
					HTTP.SET_ACCESSTOKEN(result);
				},
				error:function(result){
					if(errorCall){
						errorCall(result)
					}else{
						top.layer.msg("执行失败",{icon:2});
					}
				}
			});
		}
		$.ajax({
			type : 'post',
			url : url,
			contentType: contentType,
			data:JSON.stringify(data),
			headers: headers,
			success : function(result) {
				okCall(result);
			}
		});
	},
	GET_TOKEN:function() {
		let accessJson = getAccessToken();
		if(accessJson){
			return accessJson.token;
		}else{
			return null;
		}
	},
	SET_ACCESSTOKEN : function (accessJson) {
		let tokenJSON = $.parseJSON(accessJson);
		localStorage.setItem("accessToken", result);
		localStorage.setItem("token", tokenJSON.token);
	}
}

function getAccessToken(){
	let accessJson = localStorage.getItem("token")
	if(accessJson){
		return $.parseJSON(accessJson);
	}else{
		return;
	}
}

function judgeDate(tomodifyDate){
	return new Date(tomodifyDate).getTime() - new Date().getTime();
}