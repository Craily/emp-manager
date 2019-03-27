layui.use(['form', 'laydate'], function(){
	var form = layui.form
	,laydate = layui.laydate;
  
	laydate.render({
		elem: '#birthday',
		position: 'fixed'
	});
  
//  //监听提交
//  form.on('submit(formDemo)', function(data){
//    layer.msg(JSON.stringify(data.field));
//    return false;
//  });
});