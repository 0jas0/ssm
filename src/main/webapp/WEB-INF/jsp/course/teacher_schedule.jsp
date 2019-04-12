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
    <li><a href="#">课程管理</a></li>
    <li><a href="#">课程表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th></th>
        <th>周一</th>
        <th>周二</th>
        <th>周三</th>
        <th>周四</th>
        <th>周五</th>
        <th>周六</th>
        <th>周日</th>
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
            url: '/course/ajax/cousre-schedule-byId',
            success: function (res) {
                if (res.status == 0){
                    var data = res.data;
                    var html = "";
                    $.each(data,function (key,values) {
                        html += "<tr>"
                        html +="<td>"+key+"</td>";
                        $.each(values,function (k,v) {
                            html +="<td>"+v+"</td>";
                        })
                        html += "</tr>"
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
