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
    <li><a href="#">添加班级</a></li>
    </ul>
    </div>
    <div class="formbody">
    
    <div class="formtitle"><span>班级信息</span></div>
    
    <ul class="forminfo">
            <li><label><b style="color: red">*</b>教师名称</label><div class="vocation">
                <select class="select3" name="teacherId">
                    <option>----请选择----</option>
                </select>
            </div><i></i></li>
            <li><label><b style="color: red">*</b>课程名称</label><input name="name" type="text" class="dfinput" /><i></i></li>
            <li><label><b style="color: red">*</b>课程开始时间</label><input name="courseStart" type="date" class="dfinput" /><i></i></li>
            <li><label><b style="color: red">*</b>课程结束时间</label><input name="courseEnd" type="date" class="dfinput"/><i></i></li>
            <li><label><b style="color: red">*</b>开课学期</label><div class="vocation"><select class="select1" name="semester">
                <option value="1">大一上学期</option>
                <option value="2">大一下学期</option>
                <option value="3">大二上学期</option>
                <option value="4">大二下学期</option>
                <option value="5">大三上学期</option>
                <option value="6">大三下学期</option>
                <option value="7">大四上学期</option>
                <option value="8">大四下学期</option>
            </select></div><i></i></li>
            <li><label><b style="color: red">*</b>开课学院</label><div class="vocation">
                <select class="select2" name="college"><option>----请选择----</option></select>
            </div><i></i></li>
            <li><label><b style="color: red">*</b>学时</label><input name="period" type="text" class="dfinput" /><i></i></li>
            <li><label><b style="color: red">*</b>学分</label><input name="credit" type="text" class="dfinput" /><i></i></li>
            <li><label>课程类型</label><cite><input name="type" type="radio" value="1" checked="checked" />必修
                &nbsp;&nbsp;&nbsp;&nbsp;<input name="type" type="radio" value="0" />选修</cite>
            </li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="saveClass()"/></li>
    </ul>
    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(e) {
        $(".select1").uedSelect({
            width : 200
        });
        $(".select2").uedSelect({
            width : 200
        });
        $(".select3").uedSelect({
            width : 200
        });
        $.ajax({
            type: 'post',
            url: '/collegeMajor/ajax/get-college',
            success:function (res) {
                if (res.status == 0){
                    var data = res.data;
                    var option = "";
                    data.forEach(function (value, index) {
                        option += "<option value='"+value.id+"'>"+value.name+"</option>";
                    })
                    $(".select2").append(option);
                }else {
                    location.href = "/error";
                }
            }
        });
        $.ajax({
            type: 'post',
            url: '/teacher/ajax/get-all-teacher',
            success:function (res) {
                if (res.status == 0){
                    var data = res.data;
                    var option = "";
                    data.forEach(function (value, index) {
                        option += "<option value='"+value.teacherId+"'>"+value.name+"</option>";
                    })
                    $(".select3").append(option);
                }else {
                    location.href = "/error";
                }
            }
        });

    });
    function saveClass() {
        var courseStart = $("input[name=\"courseStart\"]").val();
        var name = $("input[name=\"name\"]").val();
        var courseEnd = $("input[name=\"courseEnd\"]").val();
        var period = $("input[name=\"period\"]").val();
        var credit = $("input[name=\"credit\"]").val();
        var type = $("input[name=\"type\"]:checked").val();
        var teacherId = $(".select3 option:selected").val();
        var college = $(".select2 option:selected").val();
        var semester = $(".select1 option:selected").val();
        $.ajax({
            type: 'POST',
            url: '/course/ajax/add-course',
            cache: false,
            data: {
                "courseStart":courseStart,
                "name":name,
                "courseEnd":courseEnd,
                "period":period,
                "credit":credit,
                "type":type,
                "teacherId":teacherId,
                "college":college,
                "semester":semester
            },
            success: function (result) {
                console.log(result)
                if (!result.status) {
                    location.href = "/course/course_list";
                } else {
                    location.href = "/error";
                }
            }
        })
    }
</script>
