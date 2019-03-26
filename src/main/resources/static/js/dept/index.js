layui.use('table', function() {
	var table = layui.table;
	
	table.render({
		elem: '#deptTable',
		url: '/dept/queryDept',
		method: 'post',
		toolbar: 'default',
		defaultToolbar: [],
		height: 'full-210',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'deptNo', title: '编号', minWidth: 80, fixed: 'left'},
			{field: 'name', title: '名称', minWidth: 120},
			{fixed: 'right', width: 165, align:'center', toolbar: '#deptTableBar'}
		]],
		page: {
			limit: 10,
			limits: [10, 20, 30]
		},
		loading: true,
		response: {
			statusCode: 1
		},
		parseData: function(d) {
			return {
				"code": d.status,
				"msg": d.msg,
				"count": d.data.count,
				"data": d.data.list
			};
		}
	});
	
	//监听头工具栏事件
	table.on('toolbar(deptTable)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;
	    switch(obj.event){
	    	case 'add':
	    		layer.msg('添加');
    		break;
	    	case 'update':
		        if(data.length === 0){
		          layer.msg('请选择一行');
		        } else if(data.length > 1){
		          layer.msg('只能同时编辑一个');
		        } else {
		          layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
		        }
	        break;
	    	case 'delete':
		        if(data.length === 0){
		          layer.msg('请选择一行');
		        } else {
		          layer.msg('删除');
		        }
	        break;
	    };
	});
});