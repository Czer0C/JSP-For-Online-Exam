$(document).ready(function(){
    // Set appropriate screen resolution.
    var p = ($(window).height() / 2160) + ($(window).width() / 3840);
    $(".mainBox").css("zoom", p);
    $(".mainBox").css("-moz-transform", "scale(" + p + ")");
    
    
	var k = 1;
	var username = "";
	$('.close').on('click', function(e){
		k = 1;
		e.preventDefault();
		$('.detail, html, body').toggleClass('open');
	})

	$('.btn.btnPrimary').on('click', function(e) {
		e.preventDefault();
		$('.detail, html, body').toggleClass('open');
		k = 1;
		username = $(this).attr('id');
		$('#btnBack').prop('disabled', true);
        $('#username').text("Các câu trả lời của: " + username);
		getData(k, username);
	});

	$('#btnNext').click(function(){
        if (k == 10) {
        	$('#btnNext').prop('disabled', true);	
        }
        else {
        	k++;
        	$('#btnBack').prop('disabled', false);
        	getData(k, username);
        }
    });

    $('#btnBack').click(function(){
        if (k == 1) {
        	$('#btnBack').prop('disabled', true);			
        }
        else {
        	k--;
        	$('#btnNext').prop('disabled', false);	
        	getData(k, username);
        }
    })
})


function getData(k, username) {
    var url = "answer?id=" + k + "&u=" + username;
    $.get(url, function(responseText) {   
        if (responseText === "failed") {
        	alert("Xảy ra lỗi ngoài ý muốn!");
        }      
        else {
            var responseArr = responseText.split('~');
            $('#outputQuestionID').text("Câu hỏi số " + k + ":");   
            $('#outputQuestionDetail').text(responseArr[2]);
            $('#outputStudentAnswer').text(responseArr[0]);
            $('#outputValidAnswer').text(responseArr[1]);          
        }
 	})
}