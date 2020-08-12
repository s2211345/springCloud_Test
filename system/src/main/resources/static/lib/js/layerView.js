var LAYER_VIEW ={
	confirmPost: function(options){
		var btns = options.btns || ['确认', '取消'];
		var url = options.url || '';
		var data = options.data || {};
		var headers = options.headers || {};
		var msg = options.msg || '请确认';
		var successCode = options.successCode || '200';
		var callback = options.callback || function(){};
		//询问框
		var index = top.layer.confirm(msg,{
			btn: btns //按钮
		}, function(){loading("数据处理中，请稍候");
			HTTP.POST({
				url:url,
				data:JSON.stringify(data),
				okCall:function(result){
					close_loading();
					result.msg = result.msg || '操作成功';
					if(result.code==successCode){
						top.layer.msg(result.msg,{icon:1});
						if(callback){callback(result);}
					}else{
						top.layer.msg(result.msg,{icon:2});
					}
				},
				errorCall:function (result) {
					close_loading();
					top.layer.msg("加载失败",{icon:2});
				}
			})
		}, function(){
			top.layer.close(index);
		});
	},
	submitBSForm:function(options){
		var _name = options.name;
		var _url = options.url;
		var postUrl = options.postUrl;
		if(!_name && !_url) {
			return;
		}
		var anim = options.anim || 0;
		var _offset = options.offset;
		var _title = options.title;
		var _form_id = _name;
		var _area = options.area || ['900px','600px'];
		var _params = options.params || "";
		var headers = options.headers || {};
		var _before_submit = options.before_submit || function(FormID,layero){};
		var _success_callback = options.success_callback || function(data){};
		//页面层
		top.layer.open({
			type: 2,
			title : _title,
			offset:_offset,
			anim: anim,
			shade: [0.8, '#393D49'],
			skin: 'layui-layer-rim', //加上边框
			area: _area,//宽高
			content: _url,
			btn: ['提交', '关闭'],
			yes:function(index, layero){
				loading("数据处理中，请稍候");
				//获取iframe中的form对象
				// var iframe="layui-layer-iframe"+index;//获得layer层的名字
				var Ifame=layero.find("iframe")[0].contentWindow;//得到框架
				var FormID=eval(Ifame.document.getElementById(_form_id))//将字符转成框架中form的对象
				var paramArray = $(FormID).serializeArray()
				_before_submit(FormID,layero);
				var jsonObj={};
				$(paramArray).each(function(){
					jsonObj[this.name]=this.value;
				});
				//如果是Vue页面
				//拥有valudatorForm方法，调用校验
				let valudatorForm = Ifame.app.valudatorForm;
				if(valudatorForm){
					if(false == valudatorForm()){
						close_loading();
						let msg = Ifame.app.valudatorDataMsg;
						if(msg){
							msg()
						}
						return;
					}
				}
				//并且form变量有值，则使用Vue数据
				let formVar = Ifame.app.form;
				if(formVar){
					jsonObj = formVar;
				}
				console.log(jsonObj)
				HTTP.POST({
					url:postUrl,
					data:JSON.stringify(jsonObj),
					okCall:function(result){
						close_loading();
						result.msg = result.msg || '操作成功';
						if(result.code == "0" || result.code == "200"){
							top.layer.msg(result.msg ,{icon:1});
							_success_callback(result);
							top.layer.close(index);
						}else{
							close_loading();
							top.layer.msg(result.msg,{icon:2});
							return false;
						}
					},
					errorCall:function (result) {
						close_loading();
						top.layer.msg("加载失败",{icon:2});
					}
				})
			},
			no:function(index, layero){
				//按钮【按钮二】的回调
				top.layer.close(index);
			}
		});
	},
	view:function(options) {
		var _url = options.url;
		if (!_url) {
			return;
		}
		var anim = options.anim || 0;
		var _title = options.title;
		var _offset = options.offset;
		var headers = options.headers || {};
		var width = options.width || '700px';
		var height = options.height || '600px';
		var callback = options.callback || function () {}
		var cancelCallback = options.cancelCallback || function () {
		};
		//页面层
		top.layer.open({
			type: 2,
			title: _title,
			offset: _offset,
			shade: [0.8, '#393D49'],
			skin: 'layui-layer-rim', //加上边框
			area: [width, height], //宽高
			content: _url,
			anim: anim,
			headers : headers,
			isOutAnim: true,
			btn: ['关闭'],
			yes: function (index, layero) {
				if (callback) {
					callback();
				}
				top.layer.close(index);
			},
			cancel: function (index, layero) {
				if (callback) {
					callback();
				}
			}
		})
	},
	previewImg : function (config) {
		/***
		 * 图片弹出展示,默认原大小展示。图片大于浏览器时下窗口可视区域时，进行等比例缩小。
		 * config.src 图片路径。必须项
		 * default_config.height 图片显示高度，默认原大小展示。图片大于浏览器时下窗口可视区域时，进行等比例缩小。
		 * default_config.width 图片显示宽度，默认原大小展示。图片大于浏览器时下窗口可视区域时，进行等比例缩小。
		 * default_config.title 弹出框标题
		 */
		if(!config.src || config.src==""){
			layer.msg("没有发现图片！");
			return ;
		}
		var default_config = {title: "图片预览"};
		var img = new Image();
		img.onload = function() {//避免图片还未加载完成无法获取到图片的大小。
			//避免图片太大，导致弹出展示超出了网页显示访问，所以图片大于浏览器时下窗口可视区域时，进行等比例缩小。
			var max_height = $(window).height() - 100;
			var max_width = $(window).width();
			//rate1，rate2，rate3 三个比例中取最小的。
			var rate1 = max_height/img.height;
			var rate2 = max_width/img.width;
			var rate3 = 1;
			var rate = Math.min(rate1,rate2,rate3);
			//等比例缩放
			default_config.height = img.height * rate; //获取图片高度
			default_config.width = img.width  * rate; //获取图片宽度

			$.extend( default_config, config);
			var imgHtml = "<img src='" + default_config.src + "' width='"+default_config.width+"px' height='"+default_config.height+"px'/>";
			//弹出层
			layer.open({
				type: 1,
				shade: 0.8,
				offset: 'auto',
				area: [default_config.width + 'px',default_config.height + 50 +'px'], ////宽，高
				shadeClose:true,
				scrollbar: false,
				title: default_config.title, //不显示标题
				content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
				cancel: function () {
					//layer.msg('捕获就是从页面已经存在的元素上，包裹layer的结构', { time: 5000, icon: 6 });
				}
			});
		}
		img.src = config.src;
	},
	clickToEnlarge : function (div,ele,eve) {
		//图片点击放大
		$(div).on(eve, ele, function(){
			LAYER_VIEW.previewImg({
				src:$(this).attr('src')
			});
		})
		/*$(nodeUrl).each(function() {
			$(this).click(function() {

			})
		})*/
	},

}

function getDict(type) {
	var v = sessionStorage[type];
	if (v == null || v == "" || JSON.stringify(v) == "{}" || v == "{}") {
		$.ajax({
			type : 'get',
			url : '/dicts?type=' + type,
			async : false,
			headers : {
				"token" : localStorage.getItem("token")
			},
			success : function(data) {
				v = {};
				$.each(data, function(i, d) {
					v[d.k] = d.val;
				});

				sessionStorage[type] = JSON.stringify(v);
			}
		});
	}
	return JSON.parse(sessionStorage[type]);
}

function loading(msg){
	//加载层-风格4
	LAYER_VIEW.loading = top.layer.msg(msg, {
		icon: 16,
		shade: 0.01
	});
}
function close_loading(){
	//关闭加载层
	top.layer.close(LAYER_VIEW.loading)
}

function Format(now,mask)
{
	var d = now;
	var zeroize = function (value, length)
	{
		if (!length) length = 2;
		value = String(value);
		for (var i = 0, zeros = ''; i < (length - value.length); i++)
		{
			zeros += '0';
		}
		return zeros + value;
	};

	return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
	{
		switch ($0)
		{
			case 'd': return d.getDate();
			case 'dd': return zeroize(d.getDate());
			case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
			case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
			case 'M': return d.getMonth() + 1;
			case 'MM': return zeroize(d.getMonth() + 1);
			case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
			case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
			case 'yy': return String(d.getFullYear()).substr(2);
			case 'yyyy': return d.getFullYear();
			case 'h': return d.getHours() % 12 || 12;
			case 'hh': return zeroize(d.getHours() % 12 || 12);
			case 'H': return d.getHours();
			case 'HH': return zeroize(d.getHours());
			case 'm': return d.getMinutes();
			case 'mm': return zeroize(d.getMinutes());
			case 's': return d.getSeconds();
			case 'ss': return zeroize(d.getSeconds());
			case 'l': return zeroize(d.getMilliseconds(), 3);
			case 'L': var m = d.getMilliseconds();
				if (m > 99) m = Math.round(m / 10);
				return zeroize(m);
			case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
			case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
			case 'Z': return d.toUTCString().match(/[A-Z]+$/);
			// Return quoted strings with the surrounding quotes removed
			default: return $0.substr(1, $0.length - 2);
		}
	});
};

/**
 * 处理layer带参数连接有.html的问题
 * @param id
 */
/*function urlIdConver(id){
	if(isNaN(id)){
		if(id.indexOf('html') != -1 ){
			id = id.substring(0, id.indexOf('html')-1);
		}
	}
}*/
