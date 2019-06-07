layui.use('element', function() {
	var element = layui.element;

	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	$.ajaxSetup({
	    error : function(jqXHR, textStatus, errorThrown) {
	        if(jqXHR.status == 408 && jqXHR.getResponseHeader("session_status") == "time_out"){
	        	location = '/login/index';
	        }
	    },
	    cache : false
	});
});