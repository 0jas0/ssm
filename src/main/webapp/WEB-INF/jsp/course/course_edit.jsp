<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <li><a href="#">课程管理</a></li>
    <li><a href="#">编辑课程信息</a></li>
    </ul>
    </div>
    <div class="formbody">
    
    <div class="formtitle"><span>班级信息</span></div>
    
    <ul class="forminfo">
            <input type="hidden" value="${courseModel.id}" name="id">
            <li><label><b style="color: red">*</b>教师名称</label><div class="vocation">
                <select class="select3" name="teacherId">
                    <c:forEach items="${teacherALl}" var="teacher">
                        <c:if test="${teacher.teacherId == courseModel.teacherId}">
                            <option value="${teacher.teacherId}" selected>${teacher.name}</option>
                        </c:if>
                        <c:if test="${teacher.teacherId != courseModel.teacherId}">
                            <option value="${teacher.teacherId}">${teacher.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div><i></i></li>
            <li><label><b style="color: red">*</b>课程名称</label><input name="name" value="${courseModel.name}" type="text" class="dfinput" /><i></i></li>
            <li><label><b style="color: red">*</b>课程开始时间</label><input name="courseStart" value="${courseModel.courseStart}" type="date" class="dfinput" /><i></i></li>
            <li><label><b style="color: red">*</b>课程结束时间</label><input name="courseEnd" value="${courseModel.courseEnd}" type="date" class="dfinput"/><i></i></li>
            <li><label><b style="color: red">*</b>开课学期</label><div class="vocation"><select class="select1" name="semester">
                <option value="1" <c:if test="${courseModel.semester == 1}">selected</c:if>>大一上学期</option>
                <option value="2" <c:if test="${courseModel.semester == 2}">selected</c:if>>大一下学期</option>
                <option value="3" <c:if test="${courseModel.semester == 3}">selected</c:if>>大二上学期</option>
                <option value="4" <c:if test="${courseModel.semester == 4}">selected</c:if>>大二下学期</option>
                <option value="5" <c:if test="${courseModel.semester == 5}">selected</c:if>>大三上学期</option>
                <option value="6" <c:if test="${courseModel.semester == 6}">selected</c:if>>大三下学期</option>
                <option value="7" <c:if test="${courseModel.semester == 7}">selected</c:if>>大四上学期</option>
                <option value="8" <c:if test="${courseModel.semester == 8}">selected</c:if>>大四下学期</option>
            </select></div><i></i></li>
            <li><label><b style="color: red">*</b>开课学院</label><div class="vocation">
                <select class="select2" name="college">
                    <c:forEach items="${collegeList}" var="college">
                        <c:if test="${college.id == courseModel.college}">
                            <option value="${college.id}" selected>${college.name}</option>
                        </c:if>
                        <c:if test="${college.id != courseModel.college}">
                            <option value="${college.id}">${college.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div><i></i></li>
            <li><label><b style="color: red">*</b>学时</label><input name="period" value="${courseModel.period}" type="text" class="dfinput" /><i></i></li>
            <li><label><b style="color: red">*</b>学分</label><input name="credit" value="${courseModel.credit}" type="text" class="dfinput" /><i></i></li>
        <li><label>课程类型</label><cite><input name="type" type="radio"  value="1" <c:if test="${courseModel.type == 1}">checked</c:if> />必修
                &nbsp;&nbsp;&nbsp;&nbsp;<input name="type" type="radio" value="0" <c:if test="${courseModel.type == 0}">checked</c:if>/>选修</cite>
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
    });
    function saveClass() {
        var id = $("input[name=\"id\"]").val();
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
            url: '/course/ajax/modify-course',
            cache: false,
            data: {
                "id":id,
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
