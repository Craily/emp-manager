layui.use('table', function() {
	var table = layui.table;
	
	table.render({
		elem: '#deptTable',
		url: '/dept/queryDept',
		method: 'post',
		toolbar: 'default',
		defaultToolbar: [],
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'deptNo', title: '编号', minWidth: 80, hide: true},
			{field: 'name', title: '名称', minWidth: 120},
			{field: 'address', title: '地址', minWidth: 120},
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
			var ret = {
					"code": d.status,
					"msg": d.msg
			}
			if(d.data != null){
				ret.count = d.data.count;
				ret.data = d.data.list;
			}
			return ret;
		}
	});
	
	//监听头工具栏事件
	table.on('toolbar(deptTable)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;
	    switch(obj.event){
	    	case 'add':
	    		deptDone();
    		break;
	    	case 'update':
		        if(data.length === 0){
		        	layer.msg('请选择一行');
		        } else if(data.length > 1){
		        	layer.msg('只能同时编辑一个');
		        } else {
		        	deptDone(checkStatus.data[0].id);
		        }
	        break;
	    	case 'delete':
		        if(data.length === 0){
		        	
		        } else {
		        	
		        }
	        break;
	    };
	});
});

function deptDone(deptNo){
	var url = '/dept/done';
	if(deptNo != null && deptNo != undefined && deptNo != ""){
		url = '/dept/done?deptNo=' + deptNo;
	}
	layer.open({
		type: 2,
		title: '部门操作',
		maxmin: true,
		shadeClose: true,
		area : ['500px' , '450px'],
		content: [url, 'no']
	});
}