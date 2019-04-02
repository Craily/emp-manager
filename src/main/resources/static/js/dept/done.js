layui.use('form', function() {
	var form = layui.form;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;

	//监听提交
	form.on('submit(done)', function(data) {
		$.post('/dept/createDept', data.field, function(date){
			if(date.status === 1){
				//关闭窗口
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				
				// 表格重载
				parent.layui.table.reload('deptTable',{page:{curr:1}});
			}else {
				layer.msg(date.msg, {icon: 6, anim: 6}); 
			}
		});
		return false;
	});
});