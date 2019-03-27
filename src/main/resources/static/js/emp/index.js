layui.use('table', function() {
	var table = layui.table;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	table.render({
		elem: '#empTable',
		url: '/emp/queryEmp',
		method: 'post',
		toolbar: 'default',
		defaultToolbar: [],
		height: 'full-140',
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'emp_no', title: '编号', minWidth: 80, fixed: 'left'},
			{field: 'emp_name', title: '姓名', minWidth: 120},
			{field: 'sex', title: '性别', minWidth: 80, templet: function(d){
				return d.sex == "1" ? "男" : d.sex == "0" ? "女" : "-";
			}},
			{field: 'mobile_phone', title: '电话', minWidth: 150},
			{field: 'birthday', title: '生日', minWidth: 150},
			{field: 'dept_name', title: '部门', minWidth: 150},
			{field: 'job_name', title: '职位', minWidth: 100},
			{fixed: 'right', width: 165, align:'center', toolbar: '#empTableBar'}
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
	table.on('toolbar(empTable)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;
	    switch(obj.event){
	    	case 'add':
	    		layer.open({
    				type: 2,
    				title: '员工操作',
    				maxmin: true,
					shadeClose: true, //点击遮罩关闭层
					area : ['800px' , '520px'],
					content: '/emp/done'
	    		});
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