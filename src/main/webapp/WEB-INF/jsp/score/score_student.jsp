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
            url: '/score/ajax-get-score-by-student-id',
            success: function (res) {
                if (res.status == 0){
                    var data = res.data;
                    var html = "";
                    $.each(data,function (key,values) {
                        html += "<tr><td colspan='5'>"+key+"</td><tr>";
                        html += "<tr bgcolor=\"#b0f0ff\"><td>课程序号</td><td>课程名</td><td>学分</td><td>课程属性</td><td>成绩</td><tr>";
                        $.each(values,function (k,v) {
                            var grade = "";
                            if(v.grade != null){
                                grade = v.grade;
                            }
                            html +="<tr>";
                            html +="<td>"+v.id+"</td>";
                            html +="<td>"+v.name+"</td>";
                            html +="<td>"+v.credit+"</td>";
                            html +="<td>"+v.typeName+"</td>";
                            html +="<td>"+grade+"</td>";
                            html +="</tr>";
                        })
                    })
                    $("#studentList").empty();
                    $("#studentList").html(html);
                }else {
                    location.href = "/error";
                }
            }
        })
    });

</script>
