layui.use('table', function(){
	var table = layui.table;
	
	table.render({
		elem: '#demo',
		url: 'emp/queryEmp',
	});
});