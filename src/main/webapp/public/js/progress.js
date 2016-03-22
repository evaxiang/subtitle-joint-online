
//圆形进度条
$(document).ready(function(){
	$('.progress_circle').each(function(){
		var width = $(this).data('width');
		var className = 'rate_'+ Math.ceil(width/5);
		$(this).html(width+'%');
		if(width>=1&&width<=3)
		{
			$(this).addClass('rate_min');
		}
		else if( width == 0)
		{ 
			$(this).addClass('rate_0');
		}
		else if( width == 100)
		{
			 $(this).addClass('rate_100'); 
		}
		else 
		{
			$(this).addClass(className);
		}
	});
	
});	


//横条形进度条
$(document).ready(function(){
	$('.progress').each(function(){
		var width = $(this).data('width');
		$(this).html('<span style="width:'+width+'%"></span>');
	});
	
});	