layui.use('table', function() {
	var table = layui.table;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	var tableIns = table.render({
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
		        	layer.msg('请选择一行', {icon: 5, anim: 6}); 
		        } else if(data.length > 1){
		        	layer.msg('只能同时编辑一个', {icon: 5, anim: 6}); 
		        } else {
		        	deptDone(data[0].deptNo);
		        }
	        break;
	    	case 'delete':
		        if(data.length === 0){
		        	layer.msg('请至少选择一行', {icon: 5, anim: 6});
		        } else {
		        	layer.confirm('是否确认删除所选数据？', {icon: 3, title:'提示'}, function(index){
		        		delDept(index, data, $, tableIns);
		        	});
		        }
	        break;
	    };
	});
	
	
	var active = {
		    reload: function(){
		    	var deptName = $('#deptName');
			    //执行重载
		    	table.reload('deptTable', {
			        page: {
			        	curr: 1 //重新从第 1 页开始
			        },
			        where: {
			        	name: deptName.val()
			        }
			    });
		    }
	}
		  
	$('.layui-inline .layui-btn').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
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
		area : ['500px' , '350px'],
		content: [url, 'no']
	});
}

function delDept(index, data, $, tableIns){

	var deptNoArray = [];
	for (let dept of data) {
		deptNoArray.push(dept.deptNo);
	}
	
	$.ajax({
		url: '/dept/delDept',
		type: 'POST',
		async: false,
		traditional: true,
		dataType: 'json',
		data: {
			deptNos: deptNoArray
		},
		success: function(data){
			if(data.status === 1){
				var deptName = $('#deptName');
				//执行重载
				tableIns.reload('deptTable', {
			        page: {
			        	curr: 1 //重新从第 1 页开始
			        },
			        where: {
			        	name: deptName.val()
			        }
			    });
		    	layer.close(index);
			}
		}
	});
}