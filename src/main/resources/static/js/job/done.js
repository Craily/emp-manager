layui.use('form', function() {
	var form = layui.form;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	var jobNo = $('#jobNo').val();
	if(jobNo != null && jobNo != undefined && jobNo != ""){
		$.post('/job/queryJob', {jobNo: jobNo}, function(date){
			if(date.status === 1){
				var job = date.data.list[0];
				//初始化表单数据
				form.val('job-form', {
				    "name": job.name,
				    "level": job.level
				});
			}else {
				layer.msg(date.msg, {icon: 5, anim: 6}); 
			}
		});
	}
	
	//监听提交
	form.on('submit(done)', function(data) {
		var url = "/job/createJob";
		if(jobNo != null && jobNo != undefined && jobNo != ""){
			url = "/job/editJob";
		}
		$.post(url, data.field, function(date){
			if(date.status === 1){
				//关闭窗口
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				
				// 表格重载
				var jobName = $('#jobName', parent.document).val();
				parent.layui.table.reload('jobTable',{
			        page: {
			        	curr: 1 //重新从第 1 页开始
			        },
			        where: {
			        	name: jobName
			        }
			    });
			}else {
				layer.msg(date.msg, {icon: 5, anim: 6}); 
			}
		});
		return false;
	});
});