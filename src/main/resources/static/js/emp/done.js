layui.use(['form', 'laydate'], function() {
	var form = layui.form, laydate = layui.laydate;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;

	laydate.render({
		elem: '#birthday'
	});

	//监听提交
	form.on('submit(done)', function(data) {
		$.post('/emp/createEmp', data.field, function(){
			//关闭窗口
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
			
			// 表格重载
			parent.layui.table.reload('empTable',{page:{curr:1}});
		});
		return false;
	});
});