//JavaScript代码区域
layui.use('element', function() {
	var element = layui.element;

	var $ = layui.$ // 由于layer弹层依赖jQuery，所以可以直接得到
	, layer = layui.layer;
	
	element.on('nav(navigation)', function(elem) {
		
		$.ajaxSetup({
			cache : false
		});
		var elem = $(elem);
		var url = elem.attr('url');
		var tabTitle = elem.attr('title');
		var tabId = elem.attr('id');
		
		if (url != "" && url != undefined) {
			//清空面包屑
			$('.layui-breadcrumb').empty();
			if(tabTitle == "首页"){
				$('.layui-breadcrumb').append("<a href=''>首页</a>");
			}else {
				var parentTitle = elem.parents('dl').siblings('a').text();
				$('.layui-breadcrumb').append("<a href=''>首页</a>").append("<a href=''>" + parentTitle + "</a>").append("<a><cite>" + tabTitle + "</cite></a>");
			}
			//重新渲染面包屑
			element.render('breadcrumb');
			
			//加载数据
			$.get(url, function(date) {
				$('.layui-body-content').html(date);
			});
		}
	});

});