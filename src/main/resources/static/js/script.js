var isShow = true;
var a;
$(function () {
    $("[data-toggle='popover']").popover();

    $("#abc").click(function () {
        a = isShow?"show":"hide";
        $("#username").popover(a);
        isShow = !isShow;
    });
});
