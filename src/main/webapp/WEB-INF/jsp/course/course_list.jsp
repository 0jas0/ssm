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
    <li><a href="#">首页</a></li>
    <li><a href="#">数据表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="AddCourse"><span><img src="/images/t01.png" /></span>添加</li>
        <li class="editCourse"><span><img src="/images/t02.png" /></span>修改</li>
        <li class="removeCourse"><span><img src="/images/t03.png" /></span>删除</li>
        </ul>

    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th></th>
        <th>课程号<i class="sort"><img src="/images/px.gif" /></i></th>
        <th>教师</th>
        <th>课程名称</th>
        <th>课程开始时间</th>
        <th>课程结束时间</th>
        <th>开课学期</th>
        <th>开课学院</th>
        <th>学时</th>
        <th>学分</th>
        <th>课程类型</th>
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
        $(".AddCourse").click(function () {
            location.href = "/course/course_add";
        });

        $(".editCourse").click(function () {
            var  length = $("input[name='selectFlag']:checked").length;
            if(length != 1){
                alert("请选择一行");
                return;
            }
            $("input[name='selectFlag']:checked").each(function () {
                var id = $(this).closest("tr").find("td:eq(1)").text();
                location.href = "/course/course_edit?courseId="+id;
            });
        });


        $(".removeCourse").click(function () {
            var  length = $("input[name='selectFlag']:checked").length;
            if (length < 1){
                alert("至少选择一行");
                return;
            }

            if (confirm("确认删除这些数据？") == true){
                $("input[name='selectFlag']:checked").each(function () {
                    var id = $(this).closest("tr").find("td:eq(1)").text();
                    console.log(id);
                    $.ajax({
                        type: 'post',
                        cache: false,
                        url: '/course/ajax/delete-course?id='+id,
                        dataType: "json",
                        success: function (res) {
                            console.log(res)
                        }
                    });
                });
                location.href = "/course/course_list";
            }
        });



        page(1,10);

    });
    function page(currentPage,pageSize) {
        $.ajax({
            type: 'post',
            url: '/course/ajax-get-course-by-page',
            data: {
                'currentPage': currentPage,
                'pageSize':pageSize
            },
            success: function (res) {
                if (res.status == 0){
                    var data = res.data.data;
                    var html = "";
                    data.forEach(function (item, index) {
                        html += '<tr><td><input name=\"selectFlag\" type=\"checkbox\" /></td>';
                        html += "<td>"+item.id+"</td>";
                        html += '<td>'+item.teacherName+'</td>';
                        html += "<td>"+item.name+"</td>";
                        html += "<td>"+item.courseStart+"</td>";
                        html += "<td>"+item.courseEnd+"</td>";
                        html += "<td>"+item.semesterName+"</td>";
                        html += "<td>"+item.college+"</td>";
                        html += "<td>"+item.period+"</td>";
                        html += "<td>"+item.credit+"</td>";
                        html += "<td>"+item.typeName+"</td>";
                        html += "<td><a href='/course/course_time_place_list?courseId="+item.id+"'>课程安排<a/></td>";
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
