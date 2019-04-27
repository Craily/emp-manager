layui.use('form', function() {
	var form = layui.form;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;

	//监听提交
	form.on('submit(login)', function(data) {
		$.post("/login/login", data.field, function(d){
			if(d.status === 1){
				location = "/index/index";
			}else {
				layer.msg(d.msg, {icon: 6, anim: 6}); 
			}
		});
		return false;
	});
});