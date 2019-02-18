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
    <li><a href="#">课程管理</a></li>
    <li><a href="#">选泽课程</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
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
        page();

    });
    function page() {
        $.ajax({
            type: 'post',
            url: '/course/ajax/select-course-list',
            success: function (res) {
                if (res.status == 0){
                    var data = res.data;
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
                        if(item.isSelected == 1){
                            html += "<td><a href='javascript:;' onclick='concelCourse(this)'>取消</a></td>";
                        }else {
                            html += "<td><a href='javascript:;' onclick='selectCourse(this)'>选择</a></td>";
                        }
                        html+="</tr>";
                    });
                    $("#totalRecord").text(res.data.totalRecord);
                    $("#currentPage").text(res.data.currentPage);
                    $("#studentList").empty();
                    $("#studentList").html(html);
                }else {
                    location.href = "/error";
                }
            }
        })
    }
    function concelCourse(obj) {
        var $tr = $(obj).closest("tr");
        var courseId = $tr.find("td").eq(1).text();
        $.ajax({
            type: 'POST',
            url: '/course/ajax/concel-course',
            cache: false,
            data: {"courseId":courseId},
            success:function (res) {
                if(res.status == 0){
                    $(obj).attr("onclick","selectCourse(this)");
                    $(obj).text("选择");
                }
            }
        });
    }
    function selectCourse(obj) {
        var $tr = $(obj).closest("tr");
        var courseId = $tr.find("td").eq(1).text();
        $.ajax({
            type: 'POST',
            url: '/course/ajax/choice-course',
            cache: false,
            data: {"courseId":courseId},
            success:function (res) {
                if(res.status == 0){
                    $(obj).attr("onclick","concelCourse(this)");
                    $(obj).text("取消");
                }
            }
        });

    }
    $('.tablelist tbody tr:odd').addClass('odd');

</script>
