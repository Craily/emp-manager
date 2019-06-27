layui.use('form', function() {
	var form = layui.form;
	
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
		var menuNo = $('#menuNo').val();
		if(menuNo != null && menuNo != undefined && menuNo != ""){
			url = "/authority/editMenu";
		}
		$.post(url, data.field, function(d){
			if(d.status === 1){
				//关闭窗口
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				
				// 表格重载
				var selectedJobNo = $('#selectedJobNo').val();
				if(parentMenuNo == null || parentMenuNo == "" || parentMenuNo == undefined || parentMenuNo == "null"){
					var data = getMenuData($, selectedJobNo);
					//执行重载
					parent.layui.table.reload('menuTable', {
			    		data: data
				    });
					parent.layui.table.reload('childrenMenuTable', {
			    		data: []
				    });
					parent.layui.table.reload('haveOperationTable', {
			    		data: []
				    });
				}else {
					var data = getMenuData($, selectedJobNo, parentMenuNo);
					parent.layui.table.reload('childrenMenuTable', {
			    		data: data
				    });
					parent.layui.table.reload('haveOperationTable', {
			    		data: []
				    });
				}
			}else {
				layer.msg(d.msg, {icon: 6, anim: 6}); 
			}
		});
		return false;
	});
});

function getMenuData($, jobNo, menuNo){
	var data = [];
	$.ajax({
		url: '/authority/queryMenu',
		type: 'POST',
		async: false,
		data: {
			selectedJobNo: jobNo,
			selectedMenuNo: menuNo
		},
		traditional: true,
		dataType: 'json',
		success: function(d){
			if(d.status === 1) {
				data = d.data.list;
			}
		}
	});
	return data;
}