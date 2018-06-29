$(document).ready(function(){
    // Set appropriate screen resolution.
    var p = ($(window).height() / 2160) + ($(window).width() / 3840);
    $(".mainSection").css("zoom", p);
    $(".mainSection").css("-moz-transform", "scale(" + p + ")");
    
    var k = 1;
    var url = new URL(window.location.href);
    var username = url.searchParams.get("u");
    receiveData(k, username);
    $('#btnNext').click(function(){
        if (k < 10) {
            receiveData(++k, username);
            $('#page').text(k + "/10");
            $('#status').hide();
        }
    })

    $('#btnBack').click(function(){
        if (k > 1) {
            receiveData(--k, username);
            $('#page').text(k + "/10");
            $('#status').hide();
        }
    })

    $('#btnSubmit').click(function() {
        var url = "submit?id=" + k + "&username=" + username + "&ans=" + $('.inputFields').val();
        $.get(url, function(responseText) {   
            if (responseText == "failed") {
                $('#status').text("Gửi thất bại, xảy ra lỗi.");
                $('#status').css("color", "red");
                $('#status').show();
            }      
            else {
                $('#status').text("Gửi thành công.");
                $('#status').css("color", "#0ab4b4");
                $('#status').show();
            }  
        });
    })

    function receiveData(k, username) {
        var url = "test?id=" + k + "&username=" + username;
        $.get(url, function(responseText) {   
            if (responseText == "failed") {
                alert("Xảy ra lỗi ngoài ý muốn");
            }      
            else {
                $('#qid').text("Câu hỏi thứ " + k + ":");
                var arr = responseText.split('`');
                $('.question').text(arr[0]);
                $('.inputFields').val(arr[1]);
            }  
        });
    }
    
})