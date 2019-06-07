function initDeptSelected($, form){
	$('#deptNo').empty();
	$('#deptNo').append("<option value=\"\">请选择</option>");
	$.ajax({
		url: '/dept/queryDept',
		type: 'POST',
		async: false,
		traditional: true,
		dataType: 'json',
		success: function(d){
			if(d.status === 1) {
				var list = d.data.list;
				var options = "";
				for (var i = 0; i < list.length; i++) {
					options += option = "<option value=\"" + list[i].deptNo + "\">" + list[i].name + "</option>";
				}
				$('#deptNo').append(options);
			}
		}
	});
	form.render('select');
}