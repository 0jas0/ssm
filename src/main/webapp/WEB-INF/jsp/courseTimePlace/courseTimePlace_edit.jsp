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
        <li><a href="#">课程列表</a></li>
        <li><a href="#">编辑课程安排</a></li>
    </ul>
    </div>
    <div class="formbody">
    
    <div class="formtitle"><span>班级信息</span></div>
    
    <ul class="forminfo">
            <input type="hidden" name="id" value="${courseTimePlaceModel.id}">
            <input type="hidden" name="courseId" value="${courseTimePlaceModel.courseId}">
            <li><label><b style="color: red">*</b>课程时间</label><div class="vocation"><select class="select1" name="courseTime">
                <option value="1" <c:if test="${courseTimePlaceModel.courseTime == 1}">selected</c:if>>第1～2节课</option>
                <option value="2" <c:if test="${courseTimePlaceModel.courseTime == 2}">selected</c:if>>第3～4节课</option>
                <option value="3" <c:if test="${courseTimePlaceModel.courseTime == 3}">selected</c:if>>第5～6节课</option>
                <option value="4" <c:if test="${courseTimePlaceModel.courseTime == 4}">selected</c:if>>第7～8节课</option>
            </select></div><i></i></li>
            <li><label><b style="color: red">*</b>星期</label><div class="vocation"><select class="select2" name="courseWeek">
                <option value="1" <c:if test="${courseTimePlaceModel.courseWeek == 1}">selected</c:if>>周一</option>
                <option value="2" <c:if test="${courseTimePlaceModel.courseWeek == 2}">selected</c:if>>周二</option>
                <option value="3" <c:if test="${courseTimePlaceModel.courseWeek == 3}">selected</c:if>>周三</option>
                <option value="4" <c:if test="${courseTimePlaceModel.courseWeek == 4}">selected</c:if>>周四</option>
                <option value="5" <c:if test="${courseTimePlaceModel.courseWeek == 5}">selected</c:if>>周五</option>
                <option value="6" <c:if test="${courseTimePlaceModel.courseWeek == 6}">selected</c:if>>周六</option>
                <option value="7" <c:if test="${courseTimePlaceModel.courseWeek == 7}">selected</c:if>>周日</option>
            </select></div><i></i></li>
            <li><label><b style="color: red">*</b>课程地点</label><input name="coursePlace" type="text" class="dfinput" value="${courseTimePlaceModel.coursePlace}"/><i></i></li>
            <li><label><b style="color: red">*</b>班级号</label><div class="vocation">
                <select class="select3" name="classId">
                    <c:forEach items="${classModels}" var="classModel">
                        <c:if test="${classModel.id == courseTimePlaceModel.classId}">
                            <option value="${classModel.id}" selected>${classModel.name}</option>
                        </c:if>
                        <c:if test="${classModel.id != courseTimePlaceModel.classId}">
                            <option value="${classModel.id}">${classModel.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div><i></i></li>
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
        var courseId = $("input[name=\"courseId\"]").val();
        var coursePlace = $("input[name=\"coursePlace\"]").val();
        var courseTime = $(".select1 option:selected").val();
        var courseWeek = $(".select2 option:selected").val();
        var classId = $(".select3 option:selected").val();
        $.ajax({
            type: 'POST',
            url: '/course/ajax/modify-course-time-place',
            cache: false,
            data: {
                "id":id,
                "courseId":courseId,
                "coursePlace":coursePlace,
                "courseTime":courseTime,
                "courseWeek":courseWeek,
                "classId":classId
            },
            success: function (result) {
                console.log(result)
                if (!result.status) {
                    location.href = "/course/course_time_place_list?courseId="+courseId;
                } else {
                    location.href = "/error";
                }
            }
        })
    }
</script>
