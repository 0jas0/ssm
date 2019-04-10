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
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="AddClass"><span><img src="/images/t01.png" /></span>添加</li>
        <li class="editClass"><span><img src="/images/t02.png" /></span>修改</li>
        <li class="removeClass"><span><img src="/images/t03.png" /></span>删除</li>
        <input id="classKeyword" type="text" name="keyword" style="margin:4px 2px;border:0.5px solid #060305;height: 28px">
        <button onclick="searchList()">搜索</button>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th></th>
        <th>序号<i class="sort"><img src="/images/px.gif" /></i></th>
        <th>班级号</th>
        <th>班级名称</th>
        <th>班级人数</th>
        <th>辅导员</th>
        <th>所属学院</th>
        <th>所属专业</th>
        <th>查看班级成绩列表</th>
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
        $(".AddClass").click(function () {
            location.href = "/class/class_add";
        });

        $(".editClass").click(function () {
            var  length = $("input[name='selectFlag']:checked").length;
            if(length != 1){
                alert("请选择一行");
                return;
            }
            $("input[name='selectFlag']:checked").each(function () {
                var id = $(this).closest("tr").find("td:eq(1)").text();
                location.href = "/class/class_edit?id="+id;
            });
        });


        $(".removeClass").click(function () {
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
                        url: '/class/ajax-delete-class',
                        cache: false,
                        data:{"id": id},
                        success: function (res) {
                            console.log(res)
                        }
                    });
                });
                location.href = "/class/class_list";
            }
        });
        page(1,10,'');
    });
    function searchList() {
        var keyword = $("#classKeyword").val();
        page(1, 10, keyword);
    }
    function page(currentPage, pageSize, keyword) {
        $.ajax({
            type: 'post',
            url: '/class/ajax-get-class-by-page',
            data: {
                'currentPage': currentPage,
                'pageSize':pageSize,
                'keyword':keyword
            },
            success: function (res) {
                if (res.status == 0){
                    var data = res.data.data;
                    var html = "";
                    data.forEach(function (item, index) {
                        html += '<tr><td><input name=\"selectFlag\" type=\"checkbox\" /></td>';
                        html += "<td>"+item.id+"</td>";
                        html += '<td>'+item.classId+'</td>';
                        html += "<td>"+item.name+"</td>";
                        html += "<td>"+item.classNumber+"</td>";
                        html += "<td>"+item.instructor+"</td>";
                        html += "<td>"+item.collegeName+"</td>";
                        html += "<td>"+item.majorName+"</td>";
                        html += "<td><a href='/score/class_score_list?classId="+item.id+"'>查看成绩<a/></td>";
                        html+="</tr>";
                    });
                    $("#totalRecord").text(res.data.totalRecord);
                    $("#currentPage").text(res.data.currentPage);
                    $(".paginList").empty();
                    if(res.data.currentPage != 1){
                        $(".paginList").append("<li class=\"paginItem\"><a href=\"javascript:;\" onclick='page("+(res.data.currentPage-1)+",10,\""keyword+"\")'><span class=\"pagepre\"></span></a></li>");
                    }

                    var begin = ((res.data.currentPage - 2) < 1) ? 1: res.data.currentPage - 2;
                    var end = ((res.data.currentPage + 2) > res.data.totalPage)? res.data.totalPage :res.data.currentPage + 2;
                    for(var i = begin; i<= end; i++){
                        if(i == res.data.currentPage){
                            $(".paginList").append("<li class='paginItem current'><a href='javascript:;' onclick='page("+i+",10,\""+keyword+"\")'>"+i+"</a></li>");
                        }else {
                            $(".paginList").append("<li class='paginItem'><a href='javascript:;' onclick='page("+i+",10,\""+keyword+"\")'>"+i+"</a></li>");
                        }
                    }

                    if(res.data.currentPage != res.data.totalPage){
                        $(".paginList").append("<li class='paginItem'><a href='javascript:;' onclick='page("+(res.data.currentPage+1)+",10,\""+keyword+"\")'><span class=\"pagenxt\"></span></a></li>");
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
