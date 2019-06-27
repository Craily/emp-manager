layui.use(['table', 'form'], function() {
	var table = layui.table;
	var form = layui.form;
	
	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	initJobSelected($, form);
	
	//查询一级菜单
	var menuData = [];
	if($('#selectedJobNo').val() != null && $('#selectedJobNo').val() != undefined && $('#selectedJobNo').val() != ""){
		menuData = getMenuData($, $('#selectedJobNo').val());
	}
	
	//一级菜单
	table.render({
		elem: '#menuTable',
		id: 'menuTable',
		toolbar: 'default',
		defaultToolbar: [],
		cols: [[
			{type: 'radio'},
			{field: 'menu_no', title: '编号', hide: true},
			{field: 'name', title: '名称', minWidth: 120}
		]],
		data: menuData,
		loading: true
	});
	
	//监听头工具栏事件
	table.on('toolbar(menuTable)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;
	    switch(obj.event){
	    	case 'add':
	    		menuDone(null, null, $('#selectedJobNo').val());
    		break;
	    	case 'update':
		        if(data.length === 0){
		        	layer.msg('请选择一行', {icon: 5, anim: 6});
		        } else if(data.length > 1){
		        	layer.msg('只能同时编辑一个', {icon: 5, anim: 6});
		        } else {
		        	menuDone(checkStatus.data[0].menu_no, null, $('#selectedJobNo').val());
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
	
	//监听一级菜单击事件
	table.on('row(menuTable)', function(obj){
		var selectedData = obj.data;
		var childrenMenu = [];
		childrenMenu = getMenuData($, $('#selectedJobNo').val(), selectedData.menu_no);
		//重载二级菜单
    	table.reload('childrenMenuTable', {
    		data: childrenMenu
	    });
    	
    	//获取权限
    	var data = getOperationsData($, $('#selectedJobNo').val(), selectedData.menu_no);
    	//重载权限
    	table.reload('haveOperationTable', {
    		data: data
	    });
	});
	
	// 二级菜单
	table.render({
		elem: '#childrenMenuTable',
		id: 'childrenMenuTable',
		toolbar: 'default',
		defaultToolbar: [],
		cols: [[
			{type: 'checkbox'},
			{field: 'menu_no', title: '编号', hide: true},
			{field: 'name', title: '名称', minWidth: 120}
		]],
		data: [],
		loading: true
	});
	
	//监听头工具栏事件
	table.on('toolbar(childrenMenuTable)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;
	    switch(obj.event){
	    	case 'add':
	    		var checkStatus = table.checkStatus('menuTable');
	    		var length = checkStatus.data.length;
	    		if(length == 0){
	    			layer.msg('请在父级菜单选择一行', {icon: 5, anim: 6});
	    			return;
	    		}
	    		
	    		menuDone(null, checkStatus.data[0].menu_no, $('#selectedJobNo').val());
    		break;
	    	case 'update':
		        if(data.length === 0){
		        	layer.msg('请选择一行', {icon: 5, anim: 6});
		        } else if(data.length > 1){
		        	layer.msg('只能同时编辑一个', {icon: 5, anim: 6});
		        } else {
		        	menuDone(checkStatus.data[0].menu_no, null, $('#selectedJobNo').val());
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
	
	//监听二级菜单击事件
	table.on('row(childrenMenuTable)', function(obj){
		var selectedData = obj.data;
    	//获取权限
    	var data = getOperationsData($, $('#selectedJobNo').val(), selectedData.menu_no);
    	//重载权限
    	alert(data[0].LAY_CHECKED);
    	table.reload('haveOperationTable', {
    		data: data
	    });
	});
	
	//权限
	table.render({
		elem: '#haveOperationTable',
		id: 'haveOperationTable',
		toolbar: 'default',
		defaultToolbar: [],
		cols: [[
			{type: 'checkbox'},
			{field: 'operationsNo', title: '编号', hide: true},
			{field: 'operationsName', title: '名称', minWidth: 120}
		]],
		data: [],
		loading: true
	});
	
	//监听头工具栏事件
	table.on('toolbar(haveOperationTable)', function(obj) {
		var checkStatus = table.checkStatus(obj.config.id);
		var data = checkStatus.data;
	    switch(obj.event){
	    	case 'add':
	    		var checkStatus1 = table.checkStatus('menuTable');
	    		var checkStatus2 = table.checkStatus('childrenMenuTable');
	    		var length1 = checkStatus1.data.length;
	    		var length2 = checkStatus2.data.length;
	    		if(length1 == 0 && length2 == 0){
	    			layer.msg('请在父级菜单或二级菜单选择一行', {icon: 5, anim: 6});
	    			return;
	    		}
	    		if(length2 == 0){
	    			operationDone(checkStatus1.data[0].menu_no, null, $('#selectedJobNo').val());
	    		}else {
	    			operationDone(checkStatus2.data[0].menu_no, null, $('#selectedJobNo').val());
	    		}
    		break;
	    	case 'update':
		        if(data.length === 0){
		        	layer.msg('请选择一行', {icon: 5, anim: 6});
		        } else if(data.length > 1){
		        	layer.msg('只能同时编辑一个', {icon: 5, anim: 6});
		        } else {
		        	var checkStatus1 = table.checkStatus('menuTable');
		    		var checkStatus2 = table.checkStatus('childrenMenuTable');
		    		var length1 = checkStatus1.data.length;
		    		var length2 = checkStatus2.data.length;
		    		if(length1 == 0 && length2 == 0){
		    			layer.msg('请在父级菜单或二级菜单选择一行', {icon: 5, anim: 6});
		    			return;
		    		}
		    		if(length2 == 0){
		    			operationDone(checkStatus1.data[0].menu_no, checkStatus.data.operationsNo, $('#selectedJobNo').val());
		    		}else {
		    			operationDone(checkStatus2.data[0].menu_no, checkStatus.data.operationsNo, $('#selectedJobNo').val());
		    		}
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
	
	var active = {
		    reload: function(){
		    	var jobNo = $('#selectedJobNo').val();
		    	if(jobNo != null && jobNo != undefined && jobNo != ""){
		    		var data = getMenuData($, jobNo);
				    //执行重载
			    	table.reload('menuTable', {
			    		data: data
				    });
		    	}else{
		    		table.reload('menuTable', {
			    		data: []
				    });
		    	}
		    	//重置其他表格
		    	table.reload('childrenMenuTable', {
		    		data: []
			    });
		    },
		    save: function(){
		    	var jobNo = $('#selectedJobNo').val();
		    	var firstMenu = table.checkStatus('menuTable');
		    	var secondMenu = table.checkStatus('childrenMenuTable');
		    	var operations = table.checkStatus('haveOperationTable');
		    	
		    	if(operations.data.length == 0){
		    		layer.msg('请选择权限', {icon: 5, anim: 6});
		    		return;
		    	}
		    	
		    	var firstMenuNo = firstMenu.data[0].menu_no;
		    	//判断二级菜单是否选中
		    	var secondMenuLength = secondMenu.data.length;
		    	
		    	var secondMenuArray = [];
		    	if(secondMenuLength != 0){
		    		// 遍历二级菜单
		    		// 二级菜单选中
		    		for (var i = 0; i < secondMenu.data.length; i++) {
		    			alert(secondMenu.data[i].menu_no);
		    			secondMenuArray.push(secondMenu.data[i].menu_no);
		    		}
		    	}
		    	
		    	//遍历权限
		    	var operationsNameArray = [];
		    	for (var i = 0; i < operations.data.length; i++) {
		    		operationsNameArray.push(operations.data[i].operationsName);
	    		}
		    	
		    	saveAuthority($, jobNo, firstMenuNo, secondMenuArray, operationsNameArray);
		    }
	}
		  
	$('.layui-inline .layui-btn').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	
});

function initJobSelected($, form){
	$('#selectedJobNo').empty();
	$('#selectedJobNo').append("<option value=\"\">请选择</option>");
	$.ajax({
		url: '/job/queryJob',
		type: 'POST',
		async: false,
		traditional: true,
		dataType: 'json',
		success: function(d){
			if(d.status === 1) {
				var list = d.data.list;
				var options = "";
				for (var i = 0; i < list.length; i++) {
					options += option = "<option value=\"" + list[i].jobNo + "\">" + list[i].name + "</option>";
				}
				$('#selectedJobNo').append(options);
			}
		}
	});
	form.render('select');
}

/**
 * 获取菜单
 */
function getMenuData($, jobNo, menuNo){
	var data = [];
	$.ajax({
		url: '/authority/queryMenu',
		type: 'POST',
		async: false,
		data: {
			selectedJobNo: jobNo,
			selectedMenuNo: menuNo
		},
		traditional: true,
		dataType: 'json',
		success: function(d){
			if(d.status === 1) {
				data = d.data.list;
			}
		}
	});
	return data;
}

/**
 * 获取操作
 */
function getOperationsData($, jobNo, menuNo){
	var data = null;
	$.ajax({
		url: '/authority/queryOperations',
		type: 'POST',
		async: false,
		data: {
			selectedJobNo: jobNo,
			selectedMenuNo: menuNo
		},
		traditional: true,
		dataType: 'json',
		success: function(d){
			if(d.status === 1) {
				data = d.data.list;
			}
		}
	});
	return data;
}

function menuDone(menuNo, parentMenuNo, selectedJobNo){
	var url = '/authority/mdone?parentMenuNo=' + parentMenuNo + '&selectedJobNo=' + selectedJobNo;
	if(menuNo != null && menuNo != undefined && menuNo != ""){
		url += '&menuNo=' + menuNo;
	}
	layer.open({
		type: 2,
		title: '菜单操作',
		maxmin: true,
		shadeClose: true,
		area : ['500px' , '450px'],
		content: [url, 'no']
	});
}

function operationDone(menuNo, operationsNo, selectedJobNo){
	var url = '/authority/odone?menuNo=' + menuNo + '&selectedJobNo=' + selectedJobNo;
	if(operationsNo != null && operationsNo != undefined && operationsNo != ""){
		url += '&operationsNo=' + operationsNo;
	}
	layer.open({
		type: 2,
		title: '权限操作',
		maxmin: true,
		shadeClose: true,
		area : ['500px' , '450px'],
		content: [url, 'no']
	});
}

function saveAuthority($, jobNo, firstMenu, secondMenuArray, operationsNameArray){
	$.ajax({
		url: '/authority/saveAuthority',
		type: 'POST',
		async: false,
		data: {
			selectedJobNo: jobNo,
			firstMenu: firstMenu,
			secondMenuArray: secondMenuArray,
			operationsNameArray: operationsNameArray
		},
		traditional: true,
		dataType: 'json',
		success: function(d){
			if(d.status != 1) {
				layer.msg(d.data, {icon: 5, anim: 6});
			}
		}
	});
}