$(function () {
    if($("#username").val() != ""){
        $("#password").focus();
    }
    // 2秒后消失提示框
    setTimeout(
        function () {
            $(".alert").alert('close');
        }, 2000
    );
});