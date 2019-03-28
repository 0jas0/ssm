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
    <li><a href="#">班级管理</a></li>
    <li><a href="#">班级列表</a></li>
    <li><a href="#">成绩列表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th></th>
        <th>课程序号</th>
        <th>课程名称</th>
        <th>学生姓名</th>
        <th>学分</th>
        <th>课程属性</th>
        <th>成绩</th>
        </tr>
        </thead>
        <tbody id="studentList">
        </tbody>
    </table>
    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        page(${classId});

    });
    function page(classId) {
        $.ajax({
            type: 'post',
            url: '/score/ajax/get-score-list-by-classId',
            data: {
                'classId': classId,
            },
            success: function (res) {
                if (res.status == 0){
                    var data = res.data;
                    var html = "";
                    data.forEach(function (item, index) {
                        html += '<tr><td><input name=\"selectFlag\" type=\"checkbox\" /></td>';
                        html += "<td>"+item.id+"</td>";
                        html += '<td>'+item.courseName+'</td>';
                        html += '<td>'+item.studentName+'</td>';
                        html += "<td>"+item.courseWeekName+"</td>";
                        html += "<td>"+item.courseTimeName+"</td>";
                        html += "<td>"+item.coursePlace+"</td>";
                        html += "<td>"+item.className+"</td>";
                        html+="</tr>";
                    });
                    $("#studentList").html(html);
                }else {
                    location.href = "/error";
                }
            }
        })
    }
    $('.tablelist tbody tr:odd').addClass('odd');

</script>
