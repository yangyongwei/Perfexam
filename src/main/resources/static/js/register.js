function updateGroup() {
    var deptName = $('#deptList').val().trim();
    if (deptName == '') {
        return;
    }
    $.ajax({
        url: '/group/findGroupBy',
        async: false,
        type: 'get',
        data: {deptName: deptName},
        success: function (data) {
            $('#groupList').editableSelect('clear');
            $('#groupList').val("");

            for (var i = 0; i < data.length; i++) {
                $('#groupList').editableSelect('add', data[i], data[i]);
            }
            $('#groupList').val(data[0]);
            // $('#grouplist').editableSelect('select', $(".es-list:eq(1) > li:first"));
            // $('#grouplist').editableSelect('show');
        }
    });
};

$('#groupList').editableSelect({
    filter: false
});

$('#deptList').editableSelect({
    filter: false,
}).on('select.editable-select', function (e, li) {
    // var deptName = li.text();
    updateGroup();
});

$("#pwd0").blur(function () {
    if ($("#pwd1").val() == '') {
        return;
    } else if ($("#pwd0").val() == $("#pwd1").val()) {
        $("#pwd1").popover("hide");
    }else{
        $("#pwd1").popover("show");
    }
});

$("#pwd1").blur(function () {
    if ($("#pwd0").val() == '') {
        return;
    } else if ($("#pwd0").val() == $("#pwd1").val()) {
        $("#pwd1").popover("hide");
    }else{
        $("#pwd1").popover("show");
    }
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

$("form").submit(function (e) {
    if($("#pwd0").val() != $("#pwd1").val()){
        $("#pwd1").focus();
        e.preventDefault();
    }
});

$(function () {

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

