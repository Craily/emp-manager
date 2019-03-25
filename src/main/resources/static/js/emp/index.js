layui.use('table', function() {
	var table = layui.table;
	
	table.render({
		elem: '#empTable',
		url: '/emp/queryEmp',
		method: 'post',
		cols: [[
			{field: 'emp_no', title: '编号'},
			{field: 'emp_name', title: '姓名'},
			{field: 'sex', title: '性别', templet: function(d){
				return d.sex == "1" ? "男" : d.sex == "0" ? "女" : "-";
			}},
			{field: 'mobile_phone', title: '电话'},
			{field: 'birthday', title: '生日'},
			{field: 'dept_name', title: '部门'},
			{field: 'job_name', title: '职位'},
		]],
//		page: {
//			limit: 10,
//			limits: [10, 20, 30]
//		},
		loading: true,
		response: {
			statusCode: 1
		},
		parseData: function(d) {
			"code": d.status,
			"msg": d.msg,
			"count": d.data.count,
			"data": d.data.list
		}
	});
});