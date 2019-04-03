layui.use('form', function() {
	var form = layui.form;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	var deptNo = $('#deptNo').val();
	if(deptNo != null && deptNo != undefined && deptNo != ""){
		$.post('/dept/queryDept', {deptNo: deptNo}, function(date){
			if(date.status === 1){
				var dept = date.data.list[0];
				//初始化表单数据
				form.val('dept-form', {
				    "name": dept.name,
				    "address": dept.address
				});
			}else {
				layer.msg(date.msg, {icon: 6, anim: 6}); 
			}
		});
	}
	
	//监听提交
	form.on('submit(done)', function(data) {
		var url = "/dept/createDept";
		if(deptNo != null && deptNo != undefined && deptNo != ""){
			url = "/dept/editDept";
		}
		$.post(url, data.field, function(date){
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