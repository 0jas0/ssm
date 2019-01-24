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
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>课程号</th>
        <th>课程名</th>
        <th>教师工号</th>
        <th>教师名称</th>
        <th>评价</th>
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
        $.ajax({
            type: 'post',
            url: '/teacher/ajax/student_evaluation_teacher_list',
            success: function (res) {
                if (res.status == 0){
                    var data = res.data;
                    var html = "";
                    var studentId = "";
                    $.each(data,function (key,values) {
                        studentId = values.studentId;
                        html += "<tr>";
                        html += "<td>"+values.courseId+"</td>";
                        html += "<td>"+values.courseName+"</td>";
                        html += "<td>"+values.teacherId+"</td>";
                        html += "<td>"+values.teacherName+"</td>";
                        html += "<td class='radio-item'>";
                        var arr = new Array();
                        arr[0] = "差";
                        arr[1] = "一般";
                        arr[2] = "好";
                        arr[3] = "很好";
                        arr[4] = "非常好";
                        for (var i = 0; i < arr.length; i++){
                            if(values.evaluationGrade == i){
                                html += "<input type = 'radio' checked='checked' name='evaluationGrade"+key+"' value='"+i+"'/>"+arr[i]+" &nbsp;&nbsp;";
                            }else {
                                html += "<input type = 'radio' name='evaluationGrade"+key+"' value='"+i+"'/>"+arr[i]+" &nbsp;&nbsp;";
                            }
                        }
                        html += "</td>";
                        html += "</tr>";
                    })
                    html += "<input type='hidden' name='studentId' value='"+studentId+"'>";
                    $("#studentList").empty();
                    $("#studentList").html(html);
                }else {
                    location.href = "/error";
                }
            }
        });
        $("#studentList").on('click','.radio-item input[type=\'radio\']', function () {
            var $tr = $(this).closest("tr");
            var evaluationGrade = $(this).val();
            var courseId = $tr.find("td").eq(0).text();
            var teacherId = $tr.find("td").eq(2).text();
            var studentId = $("input[name='studentId']").val();
            $.ajax({
                type: 'POST',
                url: '/teacher/ajax/teacher-evaluation',
                cache: false,
                data: {
                    "studentId":studentId,
                    "teacherId":teacherId,
                    "courseId":courseId,
                    "evaluationGrade":evaluationGrade
                },
                success: function (result) {
                    console.log(result);
                }
            });
        });
    });

</script>
