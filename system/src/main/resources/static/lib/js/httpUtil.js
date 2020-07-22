var HTTP ={
	GET: function(options){
		var url = options.url || ''
		var contentType = options.contentType || 'application/json; charset=utf-8';
		var okCall = options.okCall || {};
		var errorCall = options.errorCall || null;
		var data = options.data || '';
		var async = options.async || false;

		let authAccess = getAccessToken('authAccess');
		if(!authAccess){
			authAccess.token = ''
		}
		var headers = options.headers || {
			'Authorization': authAccess.token
		};
		//过去1个小时刷新一次token
		if(judgeDate(authAccess.expirationTime) <= 3600000){ //3600000毫秒 1小时
			let refreshTokenUrl = '/admin/sysUser/refreshToken?token=' + authAccess.token;
			$.ajax({
				async:false,  //刷新token使用同步
				type : 'get',
				url : refreshTokenUrl,
				headers:headers,
				contentType: contentType,
				success : function(result) {
					console.log(result)
					if(result.code == '202'){
						layer.msg("认证已失效，请重新登录")
						location.href = '/login.html';
					}
					if(result.code == '200'){
						if(result.data){
							localStorage.removeItem("token");
							HTTP.SET_ACCESSTOKEN(result.data);
						}
					}
				}
			});
		}
		$.ajax({
			async:async,
			type : 'get',
			url : url,
			data:JSON.stringify(data),
			contentType: contentType,
			headers: headers,
			success : function(result) {
				okCall(result);
			},
			error:function(xhr, textStatus, errorThrown){
				if(errorCall){
					errorCall(xhr, textStatus, errorThrown)
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
		let authAccess = getAccessToken('authAccess');
		if(!authAccess){
			authAccess.token = ''
		}
		var headers = options.headers || {
			'Authorization': authAccess.token
		};
		//过去1个小时刷新一次token
		if(judgeDate(authAccess.expirationTime) <= 3600000){ //3600000毫秒 1小时
			let refreshTokenUrl = '/admin/sysUser/refreshToken?token=' + authAccess.token;
			$.ajax({
				async:false,  //刷新token使用同步
				type : 'get',
				url : refreshTokenUrl,
				contentType: contentType,
				headers:headers,
				success : function(result) {
					console.log(result)
					if(result.code == '202'){
						layer.msg("认证已失效，请重新登录")
						location.href = '/login.html';
					}
					if(result.code == '200'){
						if(result.data){
							localStorage.removeItem("token");
							HTTP.SET_ACCESSTOKEN(result.data);
						}
					}
				},
				error:function(xhr, textStatus, errorThrown){
					if(errorCall){
						errorCall(xhr, textStatus, errorThrown)
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
		let token = getAccessToken('token');
		if(token){
			return token;
		}else{
			return null;
		}
	},
	SET_ACCESSTOKEN : function (accessJson) {
		let tokenJSON = accessJson;
		console.log(tokenJSON)
		localStorage.setItem('accessToken', JSON.stringify(tokenJSON));
		let temp = localStorage.getItem('accessToken');
		localStorage.setItem('token', tokenJSON.token);
	},
	DEL_ACCESSTOKEN : function () {
		localStorage.removeItem('accessToken');
		localStorage.removeItem('token');
	}
}

function getAccessToken(key){
	let accessJson;
	if(key == 'token'){
		accessJson = localStorage.getItem("token")
	}else if(key == 'authAccess'){
		let temp = localStorage.getItem("accessToken")
		accessJson = JSON.parse(temp);
	}
	if(accessJson){
		return accessJson;
	}else{
		return;
	}
}

function judgeDate(tomodifyDate){
	return new Date(tomodifyDate).getTime() - new Date().getTime();
}