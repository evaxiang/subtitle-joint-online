
/*选项卡*/

$(document).ready(function(){
	$('.tabs').children('a').click(function(){ //className为tabs的元素内部的A标签出发click效果
		var indexNum = $(this).index();
		var detailsName = $(this).parent().data('mydetails');
		$(this).siblings().removeClass('active');
		$(this).addClass('active');
		$('#'+detailsName).children('div').hide();
		$('#'+detailsName).children('div').eq(indexNum).show();
	});
});	
