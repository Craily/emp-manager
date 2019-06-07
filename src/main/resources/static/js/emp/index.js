layui.use(['table', 'form'], function() {
	var table = layui.table;
	var form = layui.form;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	//初始化部门下拉框
	initDeptSelected($, form);
	
	var tableIns = table.render({
		elem: '#empTable',
		id: 'empTable',
		url: '/emp/queryEmp',
		method: 'post',
		toolbar: 'default',
		defaultToolbar: [],
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'emp_no', title: '编号', minWidth: 80, hide: true},
			{field: 'emp_name', title: '姓名', minWidth: 120, fixed: 'left'},
			{field: 'sex', title: '性别', align:'center', width: 80, templet: function(d){
				return d.sex == "1" ? "男" : d.sex == "0" ? "女" : "-";
			}},
			{field: 'mobile_phone', title: '电话', width: 120},
			{field: 'join_time', title: '入职时间', align:'center', width: 120},
			{field: 'dept_name', title: '部门', minWidth: 150},
			{field: 'job_name', title: '职位', align:'center', width: 90},
			{fixed: 'right', align:'center', width: 180, toolbar: '#empTableBar'}
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
	    		empDone();
    		break;
	    	case 'update':
		        if(data.length === 0){
		        	layer.msg('请选择一行', {icon: 5, anim: 6});
		        } else if(data.length > 1){
		        	layer.msg('只能同时编辑一个', {icon: 5, anim: 6});
		        } else {
		        	empDone(checkStatus.data[0].emp_no);
		        }
	        break;
	    	case 'delete':
	    		if(data.length === 0){
		        	layer.msg('请至少选择一行', {icon: 5, anim: 6});
		        } else {
		        	layer.confirm('是否确认删除所选数据？', {icon: 3, title:'提示'}, function(index){
		        		delEmp(index, data, $, tableIns);
		        	});
		        }
	        break;
	    };
	});
	
	//监听行工具事件
	table.on('tool(empTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		var data = obj.data //获得当前行数据
		,layEvent = obj.event; //获得 lay-event 对应的值
		if(layEvent === 'beRole'){
			
		} else if(layEvent === 'del'){
			layer.confirm('是否确认删除所选数据？', {icon: 3, title:'提示'}, function(index){
        		delEmp(index, data, $, tableIns);
        	});
		} else if(layEvent === 'edit'){
			empDone(data.emp_no);
		}
	});
	
	var active = {
		    reload: function(){
		    	var empName = $('#empName');
		    	
			    //执行重载
		    	table.reload('empTable', {
			        page: {
			        	curr: 1 //重新从第 1 页开始
			        },
			        where: {
			        	name: empName.val()
			        }
			    });
		    }
	}
		  
	$('.layui-inline .layui-btn').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});


function empDone(empNo){
	var url = '/emp/done';
	if(empNo != null && empNo != undefined && empNo != ""){
		url = '/emp/done?empNo=' + empNo;
	}
	layer.open({
		type: 2,
		title: '员工操作',
		maxmin: true,
		shadeClose: true,
		area : ['500px' , '450px'],
		content: [url, 'no']
	});
}

function delEmp(index, data, $, tableIns){

	var empNoArray = [];
	for (let emp of data) {
		empNoArray.push(emp.emp_no);
	}
	
	$.ajax({
		url: '/emp/delEmp',
		type: 'POST',
		async: false,
		traditional: true,
		dataType: 'json',
		data: {
			empNos: empNoArray
		},
		success: function(data){
			if(data.status === 1){
				var empName = $('#empName');
				//执行重载
				tableIns.reload('empTable', {
			        page: {
			        	curr: 1 //重新从第 1 页开始
			        },
			        where: {
			        	name: empName.val()
			        }
			    });
		    	layer.close(index);
			}
		}
	});
}