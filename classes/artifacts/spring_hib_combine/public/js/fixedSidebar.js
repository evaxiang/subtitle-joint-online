
$(window).scroll(function(){
		var i = $(document).scrollTop();
		if (i>=480){
			$(".aside").css("top","0");	
		} else {
			$(".aside").css("top","480"-i +"px");
		}
	});