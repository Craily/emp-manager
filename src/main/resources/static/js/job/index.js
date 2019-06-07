layui.use('table', function() {
	var table = layui.table;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	var tableIns = table.render({
		elem: '#jobTable',
		url: '/job/queryJob',
		method: 'post',
		toolbar: 'default',
		defaultToolbar: [],
		cols: [[
			{type: 'checkbox', fixed: 'left'},
			{field: 'jobNo', title: '编号', minWidth: 80, hide: true},
			{field: 'name', title: '名称', minWidth: 120},
			{field: 'level', title: '等级', minWidth: 120},
			{fixed: 'right', width: 165, align:'center', toolbar: '#jobTableBar'}
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
	table.on('toolbar(jobTable)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;
	    switch(obj.event){
	    	case 'add':
	    		jobDone();
    		break;
	    	case 'update':
		        if(data.length === 0){
		        	layer.msg('请选择一行', {icon: 5, anim: 6}); 
		        } else if(data.length > 1){
		        	layer.msg('只能同时编辑一个', {icon: 5, anim: 6}); 
		        } else {
		        	jobDone(data[0].jobNo);
		        }
	        break;
	    	case 'delete':
		        if(data.length === 0){
		        	layer.msg('请至少选择一行', {icon: 5, anim: 6});
		        } else {
		        	layer.confirm('是否确认删除所选数据？', {icon: 3, title:'提示'}, function(index){
		        		delJob(index, data, $, tableIns);
		        	});
		        }
	        break;
	    };
	});
	
	//监听行工具事件
	table.on('tool(jobTable)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
		var data = obj.data //获得当前行数据
		,layEvent = obj.event; //获得 lay-event 对应的值
		if(layEvent === 'del'){
			layer.confirm('是否确认删除所选数据？', {icon: 3, title:'提示'}, function(index){
        		delJob(index, [data], $, tableIns);
        	});
	    } else if(layEvent === 'edit'){
	    	jobDone(data.jobNo);
	    }
	});
	
	var active = {
		    reload: function(){
		    	var jobName = $('#jobName');
			    //执行重载
		    	table.reload('jobTable', {
			        page: {
			        	curr: 1 //重新从第 1 页开始
			        },
			        where: {
			        	name: jobName.val()
			        }
			    });
		    }
	}
		  
	$('.layui-inline .layui-btn').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
});

function jobDone(jobNo){
	var url = '/job/done';
	if(jobNo != null && jobNo != undefined && jobNo != ""){
		url = '/job/done?jobNo=' + jobNo;
	}
	layer.open({
		type: 2,
		title: '职位操作',
		maxmin: true,
		shadeClose: true,
		area : ['500px' , '350px'],
		content: [url, 'no']
	});
}

function delJob(index, data, $, tableIns){

	var jobNoArray = [];
	for (let job of data) {
		jobNoArray.push(job.jobNo);
	}
	
	$.ajax({
		url: '/job/delJob',
		type: 'POST',
		async: false,
		traditional: true,
		dataType: 'json',
		data: {
			jobNos: jobNoArray
		},
		success: function(data){
			if(data.status === 1){
				var jobName = $('#jobName');
				//执行重载
				tableIns.reload('jobTable', {
			        page: {
			        	curr: 1 //重新从第 1 页开始
			        },
			        where: {
			        	name: jobName.val()
			        }
			    });
		    	layer.close(index);
			}
		}
	});
}