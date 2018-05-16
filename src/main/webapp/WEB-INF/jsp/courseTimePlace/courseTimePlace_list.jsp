<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>网站后台管理系统HTML模板--模板之家 www.cssmoban.com</title>
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
        
        
        <ul class="toolbar1">
        <li><span><img src="/images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th></th>
        <th>序号<i class="sort"><img src="/images/px.gif" /></i></th>
        <th>课程名</th>
        <th>星期</th>
        <th>第几节课</th>
        <th>课程地点</th>
        <th>班级</th>
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
        $(".AddCourse").click(function () {
            location.href = "/course/course_time_place_add?courseId="+${courseId};
        });

        $(".editCourse").click(function () {
            var  length = $("input[name='selectFlag']:checked").length;
            if(length != 1){
                alert("请选择一行");
                return;
            }
            $("input[name='selectFlag']:checked").each(function () {
                var id = $(this).closest("tr").find("td:eq(1)").text();
                location.href = "/course/course_time_place_edit?courseTimePlaceId="+id;
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
                    $.ajax({
                        type: 'post',
                        url: '/course/ajax/delete-course-time-place?id='+id,
                        success: function (res) {
                            console.log(res)
                        }
                    });
                });
            }
            location.href = "/course/course_time_place_list?courseId="+${courseId};
        });
        page(${courseId});

    });
    function page(courseId) {
        $.ajax({
            type: 'post',
            url: '/course/ajax/get-course-time-place-by-courseId',
            data: {
                'courseId': courseId,
            },
            success: function (res) {
                if (res.status == 0){
                    var data = res.data;
                    var html = "";
                    data.forEach(function (item, index) {
                        html += '<tr><td><input name=\"selectFlag\" type=\"checkbox\" /></td>';
                        html += "<td>"+item.id+"</td>";
                        html += '<td>'+item.courseName+'</td>';
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
