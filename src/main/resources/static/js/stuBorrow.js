var currentpage;    //当前页
var maxpage;        //总页数
var total;          //总记录数
var pagenum;        //一页最大记录数

$(function () {
    toPage(1);
})

//点击分页栏页码
function toPage(pn) {
    var stuId = $("#hidden_btn").val();
    $.ajax({
        url: "/selectStudentBorrows",
        data: {pn:pn, stuId:stuId},
        type: "GET",
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
        var btn1 = $("<button></button>").addClass("btn btn-info btn btn-primary btn-sm btn_return")
            .append($("<span></span>").addClass("glyphicon glyphicon-tag")).append("提前还书");
        btn1.attr("return_bookId", item.bookId);
        var btn = $("<td></td>").append(btn1);
        $("<tr></tr>").append(bookId).append(bookName).append(author)
            .append(press).append(btn).appendTo("#tbody");
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


//还书按钮
$(document).on("click", ".btn_return", function () {
    var bookId = $(this).attr("return_bookId");
    var stuId = $("#hidden_btn").val();
    if (confirm("是否提前还书?")) {
        $.ajax({
            url: "/deleteBorrow",
            type: "DELETE",
            data: {bookId:bookId, stuId: stuId},
            success: function (result) {
                alert("还书成功");
                toPage(currentpage);
            }
        })
    }
});
