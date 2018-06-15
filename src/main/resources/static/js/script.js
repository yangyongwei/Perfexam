// var isShow = true;
// var a;

$(function () {
    // $("[data-toggle='popover']").popover();

    $("#abc").click(function () {
        a = isShow ? "show" : "hide";
        $("#username").popover(a);
        isShow = !isShow;
    });

    $("#pwd0").blur(function () {
        $("#username").popover("show");
    });

});

// <script>
// function showPopover(target, msg) {
//     target.attr("data-original-title", msg);
//     $('[data-toggle="tooltip"]').tooltip();
//     target.tooltip('show');
//     target.focus();
//
//     //2秒后消失提示框
//     var id = setTimeout(
//         function () {
//             target.attr("data-original-title", "");
//             target.tooltip('hide');
//         }, 2000
//     );
// }
// showPopover($("#tableName"),"表名已存在");
// </script>