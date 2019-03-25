//JavaScript代码区域
layui.use('element', function() {
	var element = layui.element;

	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;

	element.on('nav(navigation)', function(elem) {
		$.ajaxSetup ({cache: false});
		var elem = $(elem);
		var url = elem.attr('url');
		var title = elem.attr('title');
		var tabId = elem.attr('id');
		
		if (url != "" && url != undefined) {
			//判断标签是否已存在
			if($('.layui-tab-title li[lay-id=' + tabId + ']').length == 0){
				//请求内容
				$.get(url, function(date){
					element.tabAdd('tabpages', {
						title: title,
						content: "<script type=\"text/javascript\" src=\"/js/emp/index.js\"></script><table id=\"empTable\" lay-filter=\"empTable\"></table>",
						id: tabId
					});
					
					element.tabChange('tabpages', tabId);
				});
			}
		}
	});

});