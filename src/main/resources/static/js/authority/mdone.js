layui.use(['form', 'laydate'], function() {
	var form = layui.form, laydate = layui.laydate;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;

	var menuNo = $('#menuNo').val();
	var parentMenuNo = $('#parentMenuNo').val();
	if(menuNo != null && menuNo != undefined && menuNo != ""){
		$.post('/authority/queryMenuInfo', {menuNo: menuNo}, function(d){
			if(d.status === 1){
				var menu = d.data
				//初始化表单数据
				form.val('emp-form', {
				    "name": menu.name
				});
			}else {
				layer.msg(date.msg, {icon: 5, anim: 6}); 
			}
		});
	}

	//监听提交
	form.on('submit(done)', function(data) {
		var url = "/authority/createMenu";
		if(menuNo != null && menuNo != undefined && menuNo != ""){
			url = "/authority/editMenu";
		}
		$.post(url, data.field, function(d){
			if(d.status === 1){
				//关闭窗口
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				
				// 表格重载
				parent.layui.table.reload('menuTable',{page:{curr:1}});
			}else {
				layer.msg(d.msg, {icon: 6, anim: 6}); 
			}
		});
		return false;
	});
});