layui.use('form', function() {
	var form = layui.form;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;

	var menuNo = $('#menuNo').val();
	var operationsNo = $('#operationsNo').val();
	if(operationsNo != null && operationsNo != undefined && operationsNo != ""){
		$.post('/authority/queryOperationInfo', {operationsNo: operationsNo}, function(d){
			if(d.status === 1){
				var operation = d.data
				//初始化表单数据
				form.val('operation-form', {
				    "operationsName": operation.operationsName
				});
			}else {
				layer.msg(date.msg, {icon: 5, anim: 6}); 
			}
		});
	}

	//监听提交
	form.on('submit(done)', function(data) {
		var url = "/authority/createOperation";
		if(operationsNo != null && operationsNo != undefined && operationsNo != ""){
			url = "/authority/editOperation";
		}
		$.post(url, data.field, function(d){
			if(d.status === 1){
				//关闭窗口
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				
				// 表格重载
				var selectedJobNo = $('#selectedJobNo').val();
				var data = getOperationsData($, selectedJobNo, menuNo);
				//执行重载
				parent.layui.table.reload('haveOperationTable', {
		    		data: data
			    });
			}else {
				layer.msg(d.msg, {icon: 6, anim: 6}); 
			}
		});
		return false;
	});
});

function getOperationsData($, jobNo, menuNo){
	var data = null;
	$.ajax({
		url: '/authority/queryOperations',
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