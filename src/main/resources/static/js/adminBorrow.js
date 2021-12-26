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
        url: "/selectBorrows",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            //1.解析并显示借阅数据
            borrowsShow(result);
            //2.解析并显示分页信息
            pageShow(result);
            //3.解析并显示分页栏
            pageInfoShow(result);
        }
    })
}

//解析并显示书的数据
function borrowsShow(result) {
    $("#tbody").empty();
    var list = result.list;
    $.each(list, function (index, item) {
        var bookId = $("<td></td>").append(item.bookId);
        var bookName = $("<td></td>").append(item.bookName);
        var stuId = $("<td></td>").append(item.stuId);
        var stuName = $("<td></td>").append(item.stuName);
        var borrowDate = $("<td></td>").append(item.borrowDate);
        var returnDate = $("<td></td>").append(item.returnDate);
        var btn1 = $("<button></button>").addClass("btn btn-info btn btn-primary btn-sm btn_update")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("修改");
        btn1.attr("update_bookId", item.bookId);
        var btn = $("<td></td>").append(btn1);
        $("<tr></tr>").append(bookId).append(bookName).append(stuId).append(stuName)
            .append(borrowDate).append(returnDate).append(btn).appendTo("#tbody");
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


//编辑按钮弹出模态框
$(document).on("click", ".btn_update", function () {
    $("#modal_name").text("编辑借阅");
    var bookId = $(this).attr("update_bookId");
    $("#save").attr("s_id", bookId);
    $.ajax({
        url: "/selectBorrow/" + bookId,
        type: "GET",
        success: function (result) {
            $("#in_bookName").text(result.bookName);
            $("#in_stuName").text(result.stuName);
            $("#in_borrowDate").val(result.borrowDate);
            $("#in_returnDate").val(result.returnDate);
        }
    })
    $("#modal").modal({
        backdrop: "static"
    })
});

//更新按钮
$("#save").click(function () {
    var bookId = $(this).attr("s_id");
    $.ajax({
        url: "/updateBorrow/" + bookId,
        type: "PUT",
        data: $("#modal_form").serialize(),
        success: function (result) {
            $("#modal").modal('hide');
            alert("更新成功");
            toPage(currentpage);
        }
    })
})