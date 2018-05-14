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
        <li class="AddTeacher"><span><img src="/images/t01.png" /></span>添加</li>
        <li class="editTeacher"><span><img src="/images/t02.png" /></span>修改</li>
        <li class="removeTeacher"><span><img src="/images/t03.png" /></span>删除</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="/images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>教师工号<i class="sort"><img src="/images/px.gif" /></i></th>
        <th>教师名称</th>
        <th>出生日期</th>
        <th>性别</th>
        <th>学历</th>
        <th>职称</th>
        <th>住址</th>
        <th>联系电话</th>
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
        $(".AddTeacher").click(function () {
            location.href = "/teacher/teacher_add";
        });

        $(".editTeacher").click(function () {
            var  length = $("input[name='selectFlag']:checked").length;
            if(length != 1){
                alert("请选择一行");
                return;
            }
            $("input[name='selectFlag']:checked").each(function () {
                var id = $(this).closest("tr").find("td:eq(1)").text();
                location.href = "/teacher/teacher_edit?teacherId="+id;
            });
        });


        $(".removeTeacher").click(function () {
            var  length = $("input[name='selectFlag']:checked").length;
            if (length < 1){
                alert("至少选择一行");
                return;
            }

            if (confirm("确认删除这些数据？") == true){
                $("input[name='selectFlag']:checked").each(function () {
                    var teacherId = $(this).closest("tr").find("td:eq(1)").text();
                    $.ajax({
                        type: 'post',
                        url: '/teacher/ajax-delete-teacher?teacherId='+teacherId,
                        success: function (res) {
                            console.log(res)
                        }
                    });
                });
            }
            location.href = "/teacher/teacher_list";
        });



        page(1,10);

    });
    function page(currentPage,pageSize) {
        $.ajax({
            type: 'post',
            url: '/teacher/ajax-get-teacher-by-page',
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
                        html += "<td>"+item.teacherId+"</td>";
                        html += '<td>'+item.name+'</td>';
                        html += "<td>"+item.bornDate+"</td>";
                        html += "<td>"+item.sex+"</td>";
                        html += "<td>"+item.education+"</td>";
                        html += "<td>"+item.position+"</td>";
                        html += "<td>"+item.address+"</td>";
                        html += "<td>"+item.mobile+"</td>";
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
