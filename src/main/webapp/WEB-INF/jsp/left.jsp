<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>导航栏</div>
    
    <dl class="leftmenu">
    <dd>
        <div class="title">
            <span><img src="/images/leftico01.png" /></span>首页
        </div>
        <ul class="menuson">
            <li class="active"><cite></cite><a href="#">首页</a><i></i></li>
        </ul>
    </dd>
    <dd>
    <div class="title">
    <span><img src="/images/leftico01.png" /></span>学生管理
    </div>
    	<ul class="menuson">
        <li><cite></cite><a href="/student/student_list" target="rightFrame">学生列表</a><i></i></li>
        </ul>
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="/images/leftico02.png" /></span>班级管理
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="/class/class_list" target="rightFrame">班级列表</a><i></i></li>
        </ul>
    </dd> 
    
    
    <dd><div class="title"><span><img src="/images/leftico03.png" /></span>教师管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="/teacher/teacher_list" target="rightFrame">教师列表</a><i></i></li>
    </ul>
    </dd>

    <dd><div class="title"><span><img src="/images/leftico03.png" /></span>课程管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="/course/course_list" target="rightFrame">课程列表</a><i></i></li>
    </ul>
    </dd>
    </dl>
</body>
</html>
