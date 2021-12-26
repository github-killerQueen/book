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
        url: "/selectBooks",
        data: "pn=" + pn,
        type: "get",
        success: function (result) {
            //1.解析并显示书的数据
            booksShow(result);
            //2.解析并显示分页信息
            pageShow(result);
            //3.解析并显示分页栏
            pageInfoShow(result);
        }
    })
}

//解析并显示书的数据
function booksShow(result) {
    $("#tbody").empty();
    var list = result.list;
    $.each(list, function (index, item) {
        var bookId = $("<td></td>").append(item.bookId);
        var bookName = $("<td></td>").append(item.bookName);
        var author = $("<td></td>").append(item.author);
        var press = $("<td></td>").append(item.press);
        var status = $("<td></td>").append(item.status);
        var btn1 = $("<button></button>").addClass("btn btn-info btn btn-primary btn-sm btn_update")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("修改");
        btn1.attr("update_bookId", item.bookId);
        var btn2 = $("<button></button>").addClass("btn btn-danger btn btn-primary btn-sm btn_delete")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
        btn2.attr("delete_bookId", item.bookId);
        var btn = $("<td></td>").append(btn1).append(" ").append(btn2);
        $("<tr></tr>").append(bookId).append(bookName).append(author)
            .append(press).append(status).append(btn).appendTo("#tbody");
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
    $("#modal_name").text("新增图书");
    $("#save").css("display", "none");
    $("#increase").css("display", "");
    $("#in_bookName").val("");
    $("#in_author").val("");
    $("#in_press").val("");
    $("#div input[name=status]").val(["未借出"]);
    $("#modal").modal({
        backdrop: "static"
    })
})

//添加按纽
$("#increase").click(function () {
    $.ajax({
        url: "/addBook",
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
    $("#modal_name").text("编辑图书");
    $("#increase").css("display", "none");
    $("#save").css("display", "");
    var bookId = $(this).attr("update_bookId");
    $("#save").attr("s_id", bookId);
    $.ajax({
        url: "/selectBook/" + bookId,
        type: "GET",
        success: function (result) {
            $("#in_bookName").val(result.bookName);
            $("#in_author").val(result.author);
            $("#in_press").val(result.press);
            $("#div input[name=status]").val([result.status]);
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
        url: "/updateBook/" + bookId,
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
    var bookId = $(this).attr("delete_bookId");
    if (confirm("是否删除该图书?")) {
        $.ajax({
            url: "/deleteBook/" + bookId,
            type: "DELETE",
            success: function (result) {
                alert("删除成功");
                toPage(currentpage);
            }
        })
    }
});
