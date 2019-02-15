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
    <li><a href="#">添加教师</a></li>
    </ul>
    </div>
    <div class="formbody">
    
    <div class="formtitle"><span>教师信息</span></div>
    
    <ul class="forminfo">
            <li><label><b style="color: red">*</b>教师工号</label><input name="teacherId" type="text" class="dfinput" /><i></i></li>
            <li><label><b style="color: red">*</b>密码</label><input name="password" type="password" class="dfinput" /><i></i></li>
            <li><label><b style="color: red">*</b>教师名称</label><input name="name" type="text" class="dfinput" /><i></i></li>
            <li><label>出生日期</label><input name="bornDate" type="date" class="dfinput"/><i></i></li>
            <li><label>性别</label><cite><input name="sex" type="radio" value="男" checked="checked" />男&nbsp;&nbsp;&nbsp;&nbsp;<input name="sex" type="radio" value="女" />女</cite></li>
            <li><label>学历</label><input name="education" type="text" class="dfinput"/><i></i></li>
            <li><label><b style="color: red">*</b>职称</label><input name="position" type="text" class="dfinput"/><i></i></li>
            <li><label><b style="color: red">*</b>民族</label><input name="nation" type="text" class="dfinput"/><i></i></li>
            <li><label><b style="color: red">*</b>住址</label><input name="address" type="text" class="dfinput"/><i></i></li>
            <li><label>邮政编码</label><input name="postalcode" type="text" class="dfinput"/><i></i></li>
            <li><label><b style="color: red">*</b>联系电话</label><input name="mobile" type="text" class="dfinput"/><i></i></li>
            <li><label><b style="color: red">*</b>身份证号</label><input name="identityCardNumber" type="text" class="dfinput"/><i></i></li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="saveTeacher()"/></li>
    </ul>
    </div>
</body>
</html>
<script type="text/javascript">
    function saveTeacher() {
        var teacherId = $("input[name=\"teacherId\"]").val();
        var password = $("input[name=\"password\"]").val();
        var name = $("input[name=\"name\"]").val();
        var instructor = $("input[name=\"instructor\"]").val();
        var bornDate = $("input[name=\"bornDate\"]").val();
        var sex = $("input[name=\"sex\"]:checked").val();
        var education = $("input[name=\"education\"]").val();
        var position = $("input[name=\"position\"]").val();
        var nation = $("input[name=\"nation\"]").val();
        var address = $("input[name=\"address\"]").val();
        var postalcode = $("input[name=\"postalcode\"]").val();
        var mobile = $("input[name=\"mobile\"]").val();
        var identityCardNumber = $("input[name=\"identityCardNumber\"]").val();
        $.ajax({
            type: 'POST',
            url: '/teacher/ajax-add-teacher',
            cache: false,
            data: {
                "teacherId":teacherId,
                "password":password,
                "name":name,
                "instructor":instructor,
                "bornDate":bornDate,
                "sex":sex,
                "education":education,
                "position":position,
                "nation":nation,
                "address":address,
                "postalcode":postalcode,
                "mobile":mobile,
                "identityCardNumber":identityCardNumber
            },
            success: function (result) {
                console.log(result)
                if (!result.status) {
                    location.href = "/teacher/teacher_list";
                } else {
                    location.href = "/error";
                }
            }
        })
    }
</script>
