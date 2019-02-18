<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>选课管理系统</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/js/jquery.js"></script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">成绩管理</a></li>
    <li><a href="#">成绩录入</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>&nbsp;课程号<i class="sort"><img src="/images/px.gif" /></i></th>
        <th>课程名称</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody id="studentList">
        </tbody>
    </table>
    <div class="pagin">
    	<div class="message">共<i class="blue" id="totalRecord"></i>条记录，当前显示第&nbsp;<i class="blue" id="currentPage"></i>页</div>
        <ul class="paginList">
        </ul>
    </div>

    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        page(1,10);
    });
    function page(currentPage,pageSize) {
        $.ajax({
            type: 'post',
            url: '/score/ajax/score-list-by-teacherId',
            data: {
                'currentPage': currentPage,
                'pageSize':pageSize
            },
            success: function (res) {
                if (res.status == 0){
                    var data = res.data.data;
                    var html = "";
                    data.forEach(function (item, index) {
                        html +="<tr>";
                        html += "<td>"+item.id+"</td>";
                        html += '<td>'+item.name+'</td>';
                        html += "<td><a href='/score/score_detail?courseId="+item.id+"'>管理成绩<a/></td>";
                        html+="</tr>";
                    });
                    $("#totalRecord").text(res.data.totalRecord);
                    $("#currentPage").text(res.data.currentPage);
                    $(".paginList").empty();
                    if(res.data.currentPage != 1){
                        $(".paginList").append("<li class=\"paginItem\"><a href=\"javascript:;\" onclick='page("+(res.data.currentPage-1)+",10)'><span class=\"pagepre\"></span></a></li>");
                    }

                    var begin = ((res.data.currentPage - 2) < 1) ? 1: res.data.currentPage - 2;
                    var end = ((res.data.currentPage + 2) > res.data.totalPage)? res.data.totalPage :res.data.currentPage + 2;
                    for(var i = begin; i<= end; i++){
                        if(i == res.data.currentPage){
                            $(".paginList").append("<li class='paginItem current'><a href='javascript:;' onclick='page("+i+",10)'>"+i+"</a></li>");
                        }else {
                            $(".paginList").append("<li class='paginItem'><a href='javascript:;' onclick='page("+i+",10)'>"+i+"</a></li>");
                        }
                    }

                    if(res.data.currentPage != res.data.totalPage){
                        $(".paginList").append("<li class='paginItem'><a href='javascript:;' onclick='page("+(res.data.currentPage+1)+",10)'><span class=\"pagenxt\"></span></a></li>");
                    }
                    $("#studentList").empty();
                    $("#studentList").html(html);
                }else {
                    location.href = "/error";
                }
            }
        })
    }
    $('.tablelist tbody tr:odd').addClass('odd');

</script>
