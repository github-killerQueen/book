var currentpage;    //当前页
var maxpage;        //总页数
var total;          //总记录数
var pagenum;        //一页最大记录数

$(function () {
    toPage(1);
})

//点击分页栏页码
function toPage(pn) {
    $.ajax({
        url: "/selectStudents",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            //1.解析并显示学生的数据
            studentsShow(result);
            //2.解析并显示分页信息
            pageShow(result);
            //3.解析并显示分页栏
            pageInfoShow(result);
        }
    })
}

//解析并显示学生的数据
function studentsShow(result) {
    $("#tbody").empty();
    var list = result.list;
    $.each(list, function (index, item) {
        var stuId = $("<td></td>").append(item.stuId);
        var stuName = $("<td></td>").append(item.stuName);
        var gender = $("<td></td>").append(item.gender);
        var phone = $("<td></td>").append(item.phone);
        var borrowCount = $("<td></td>").append(item.borrowCount);
        var btn1 = $("<button></button>").addClass("btn btn-info btn btn-primary btn-sm btn_update")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("修改");
        btn1.attr("update_stuId", item.stuId);
        var btn2 = $("<button></button>").addClass("btn btn-danger btn btn-primary btn-sm btn_delete")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
        btn2.attr("delete_stuId", item.stuId);
        var btn = $("<td></td>").append(btn1).append(" ").append(btn2);
        $("<tr></tr>").append(stuId).append(stuName).append(gender)
            .append(phone).append(borrowCount).append(btn).appendTo("#tbody");
    })
}

//解析并显示分页信息
function pageShow(result) {
    $("#span").empty();
    currentpage = result.pageNum;
    maxpage = result.pages;
    total = result.total;
    pagenum = result.pageSize;
    $("#span").append("当前第" + currentpage + "页，" +
        "总共" + maxpage + "页，总共" + total + "条记录");
}

//解析并显示分页栏
function pageInfoShow(result) {
    $("#pi").empty();
    var ul = $("<ul></ul>").addClass("pagination");
    var firstpage = $("<li></li>").append($("<a></a>").append("首页"));
    var beforepage = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.hasPreviousPage == false) {
        firstpage.addClass("disabled");
        beforepage.addClass("disabled");
    } else {
        firstpage.click(function () {
            toPage(1);
        })
        beforepage.click(function () {
            toPage(currentpage - 1);
        })
    }
    var afterpage = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastpage = $("<li></li>").append($("<a></a>").append("尾页"));
    if (result.hasNextPage == false) {
        afterpage.addClass("disabled");
        lastpage.addClass("disabled");
    } else {
        afterpage.click(function () {
            toPage(currentpage + 1);
        })
        lastpage.click(function () {
            toPage(maxpage);
        })
    }
    ul.append(firstpage).append(beforepage);
    $.each(result.navigatepageNums, function (index, item) {
        var everypage = $("<li></li>").append($("<a></a>").append(item));
        everypage.click(function () {
            toPage(item);
        })
        if (item == currentpage) {
            everypage.addClass("active");
        }
        ul.append(everypage);
    })
    ul.append(afterpage).append(lastpage);
    $("<nav></nav>").append(ul).appendTo("#pi");
}

//新增按钮绑定事件弹出模态框
$("#btn_add").click(function () {
    $("#modal_name").text("新增学生");
    $("#save").css("display", "none");
    $("#increase").css("display", "");
    $("#in_stuName").val("");
    $("#div input[name=gender]").val(["男"]);
    $("#in_phone").val("");
    $("#in_borrowCount").val("");
    $("#modal").modal({
        backdrop: "static"
    })
})

//添加按纽
$("#increase").click(function () {
    $.ajax({
        url: "/addStudent",
        type: "POST",
        data: $("#modal_form").serialize(),
        success: function (result) {
            $("#modal").modal('hide');
            alert("添加成功");
            toPage(parseInt(total / pagenum + 1));  //取整
        }
    })
})

//编辑按钮弹出模态框
$(document).on("click", ".btn_update", function () {
    $("#modal_name").text("修改学生信息");
    $("#increase").css("display", "none");
    $("#save").css("display", "");
    var stuId = $(this).attr("update_stuId");
    $("#save").attr("s_id", stuId);
    $.ajax({
        url: "/selectStudent/" + stuId,
        type: "GET",
        success: function (result) {
            $("#in_stuName").val(result.stuName);
            $("#div input[name=gender]").val([result.gender]);
            $("#in_phone").val(result.phone);
            $("#in_borrowCount").val(result.borrowCount);
        }
    })
    $("#modal").modal({
        backdrop: "static"
    })
});

//更新按钮
$("#save").click(function () {
    var stuId = $(this).attr("s_id");
    $.ajax({
        url: "/updateStudent/" + stuId,
        type: "PUT",
        data: $("#modal_form").serialize(),
        success: function (result) {
            $("#modal").modal('hide');
            alert("更新成功");
            toPage(currentpage);
        }
    })
})

//删除图书
$(document).on("click", ".btn_delete", function () {
    var stuId = $(this).attr("delete_stuId");
    if (confirm("是否删除该学生?")) {
        $.ajax({
            url: "/deleteStudent/" + stuId,
            type: "DELETE",
            success: function (result) {
                alert("删除成功");
                toPage(currentpage);
            }
        })
    }
});
