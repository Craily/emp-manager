layui.use(['form', 'laydate'], function() {
	var form = layui.form, laydate = layui.laydate;

	laydate.render({
		elem: '#birthday'
	});

	//监听提交
	form.on('submit(done)', function(data) {
		$.post('', JSON.stringify(data.field), function(){
			
		});
		return false;
	});
});