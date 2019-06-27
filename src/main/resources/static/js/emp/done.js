layui.use(['form', 'laydate'], function() {
	var form = layui.form, laydate = layui.laydate;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;

	laydate.render({
		elem: '#joinTime'
	});
	
	//初始化部门下拉框
	initDeptSelected($, form);
	
	var empNo = $('#empNo').val();
	if(empNo != null && empNo != undefined && empNo != ""){
		$.post('/emp/queryEmp', {empNo: empNo}, function(d){
			if(d.status === 1){
				var emp = d.data.list[0];
				//初始化表单数据
				form.val('emp-form', {
				    "name": emp.emp_name,
				    "sex": emp.sex,
				    "mobilePhone": emp.mobile_phone,
				    "joinTime": emp.join_time,
				    "deptNo": emp.dept_no
				});
			}else {
				layer.msg(date.msg, {icon: 5, anim: 6}); 
			}
		});
	}

	//监听提交
	form.on('submit(done)', function(data) {
		var url = "/emp/createEmp";
		if(empNo != null && empNo != undefined && empNo != ""){
			url = "/emp/editEmp";
		}
		$.post(url, data.field, function(d){
			if(d.status === 1){
				//关闭窗口
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
				
				// 表格重载
				parent.layui.table.reload('empTable',{page:{curr:1}});
			}else {
				layer.msg(d.msg, {icon: 6, anim: 6}); 
			}
		});
		return false;
	});
});