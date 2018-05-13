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
        <li class="AddStudent"><span><img src="/images/t01.png" /></span>添加</li>
        <li class="editStudent"><span><img src="/images/t02.png" /></span>修改</li>
        <li><span><img src="/images/t03.png" /></span>删除</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="/images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>学号<i class="sort"><img src="/images/px.gif" /></i></th>
        <th>姓名</th>
        <th>图片</th>
        <th>出生日期</th>
        <th>性别</th>
        <th>政治面貌</th>
        <th>班级</th>
        <th>学院</th>
        <th>专业</th>
        <th>手机号</th>
        </tr>
        </thead>
        <tbody id="studentList">
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>
    
    
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    </div>
    
    
    
    
    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        $(".AddStudent").click(function () {
            location.href = "/student/student_add";
        });
        
        $.ajax({
            type: 'post',
            url: '/student/ajax-get-student-by-page',
            data: {
                'currentPage': 1,
                'pageSize':10
            },
            success: function (res) {
                if (res.status == 0){
                    var data = res.data.data;
                    var html = "";
                    data.forEach(function (item, index) {
                        html += '<tr><td><input name=\"\" type=\"checkbox\" /></td>';
                        html += "<td>"+item.studentId+"</td>";
                        html += '<td>'+item.name+'</td>';
                        html += "<td>"+item.photo+"</td>";
                        html += "<td>"+item.sex+"</td>";
                        html += "<td>"+item.bornDate+"</td>";
                        html += "<td>"+item.politicalOutlook+"</td>";
                        html += "<td>"+item.className+"</td>";
                        html += "<td>"+item.collegeName+"</td>";
                        html += "<td>"+item.majorName+"</td>";
                        html += "<td>"+item.mobile+"</td>";
                        html+="</tr>";
                    })
                    $("#studentList").empty();
                    $("#studentList").html(html);
                }else {
                    location.href = "/error";
                }
            }
        })
    });
    $('.tablelist tbody tr:odd').addClass('odd');

</script>
