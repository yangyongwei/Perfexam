// var isShow = true;
// var a;

$(function () {

    $("#pwd0").blur(function () {

    });

    $("#regUserName").blur(function () {
        var userName = $("#regUserName").val().trim();
        $.ajax({
            url: '/user/findBy',
            async: false,
            type: 'get',
            data: {userName: userName},
            success: function (data) {
                if (data) {
                    $("#regUserName").popover("show");
                } else {
                    $("#regUserName").popover("hide");
                }
            }
        });
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

