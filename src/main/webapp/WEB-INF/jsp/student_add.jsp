<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<link href="/css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="/js/select-ui.min.js"></script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>学生信息</span></div>
    
    <ul class="forminfo">
        <form id = "studentAdd">
            <li><label>学号</label><input name="studentId" type="text" class="dfinput" /><i></i></li>
            <li><label>密码</label><input name="password" type="password" class="dfinput" /><i></i></li>
            <li><label>确认密码</label><input name="rePassword" type="password" class="dfinput" /><i></i></li>
            <li><label>姓名</label><input name="name" type="text" class="dfinput" /><i></i></li>
            <li><label>图片</label><input name="name" type="file" /><i></i></li>
            <li><label>出生日期</label><input name="bornDate" type="date"/><i></i></li>
            <li><label>政治面貌</label><input name="politicalOutlook" type="text" class="dfinput" /><i></i></li>
            <li><label>籍贯</label><input name="nativePlace" type="text" class="dfinput" /><i></i></li>
            <li><label>名族</label><input name="nation" type="text" class="dfinput" /><i></i></li>
            <li><label>地址</label><input name="address" type="text" class="dfinput" /><i></i></li>
            <li><label>邮政编码</label><input name="postalcode" type="text" class="dfinput" /><i></i></li>
            <li><label>手机号</label><input name="mobile" type="text" class="dfinput" /><i></i></li>
            <li><label>身份证号</label><input name="identityCardNumber" type="text" class="dfinput" /><i></i></li>

            <li><label>学院</label><div class="vocation"><select class="select1" name="college"><option>--请选择--</option></select></div><i></i></li>
            <li><label>专业</label><div class="vocation"><select class="select2" name="major"><option>--请选择--</option></select></div><i></i></li>
            <li><label>班级</label><div class="vocation"><select class="select3" name="classId"><option>--请选择--</option></select></div><i></i></li>

            <li><label>性别</label><cite><input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="女" />女</cite></li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存"/></li>
        </form>
    </ul>
    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(e) {
        $(".select1").uedSelect({
            width : 150
        });
        $(".select2").uedSelect({
            width : 150
        });
        $(".select3").uedSelect({
            width : 150
        });
        $.ajax({
            type: 'post',
            url: '/student/ajax-get-student-by-page',
            success:function () {

            }
        });
    });
</script>
