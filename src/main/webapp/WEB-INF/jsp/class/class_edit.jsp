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
    <li><a href="#">添加班级</a></li>
    </ul>
    </div>
    <div class="formbody">
    
    <div class="formtitle"><span>班级信息</span></div>
    
    <ul class="forminfo">
            <input type="hidden" name="id" value="${classDO.id}"/>
            <li><label>班级号</label><input name="classId" type="text" class="dfinput" value="${classDO.classId}" readonly/><i></i></li>
            <li><label>班级名称</label><input name="name" type="text" class="dfinput" value="${classDO.name}"/><i></i></li>
            <li><label>班级人数</label><input name="classNumber" type="text" class="dfinput" value="${classDO.classNumber}"/><i></i></li>
            <li><label>辅导员</label><input name="instructor" type="text" class="dfinput" value="${classDO.instructor}"/><i></i></li>
            <li><label>学院</label><div class="vocation"><select class="select1" name="college">
                <c:forEach items="${collegeList}" var = "college">
                    <c:if test="${college.id != classDO.college}">
                        <option value="${college.id}">${college.name}</option>
                    </c:if>
                    <c:if test="${college.id == classDO.college}">
                        <option value="${college.id}" selected>${college.name}</option>
                    </c:if>
                </c:forEach>
            </select></div><i></i></li>
            <li><label>专业</label><div class="vocation"><select class="select2" name="major">
                <c:forEach items="${majorList}" var = "major">
                    <c:if test="${major.id == classDO.major}">
                        <option value="${major.id}" selected>${major.name}</option>
                    </c:if>
                    <c:if test="${major.id != classDO.major}">
                        <option value="${major.id}">${major.name}</option>
                    </c:if>
                </c:forEach>
            </select></div><i></i></li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="editClass()"/></li>
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
        $(".select1").change(function (){
            var parentId = $(".select1 option:selected").val();
            $.ajax({
                type: 'post',
                url: '/collegeMajor/ajax/get-major-by-parentId',
                data:{"parentId":parentId},
                success:function (res) {
                    if (res.status == 0){
                        var data = res.data;
                        var option = "";
                        data.forEach(function (value, index) {
                            option += "<option value='"+value.id+"'>"+value.name+"</option>";
                        })
                        $(".select2").empty();
                        $(".select2").html(option);
                    }else {
                        location.href = "/error";
                    }
                }
            });
        });

    });
    function editClass() {
        var id = $("input[name=\"id\"]").val();
        var classId = $("input[name=\"classId\"]").val();
        var name = $("input[name=\"name\"]").val();
        var classNumber = $("input[name=\"classNumber\"]").val();
        var instructor = $("input[name=\"instructor\"]").val();
        var college = $(".select1 option:selected").val();
        var major = $(".select2 option:selected").val();
        $.ajax({
            type: 'POST',
            url: '/class/ajax-modify-class',
            cache: false,
            data: {
                "id":id,
                "classId":classId,
                "name":name,
                "classNumber":classNumber,
                "instructor":instructor,
                "college":college,
                "major":major
            },
            success: function (result) {
                console.log(result)
                if (!result.status) {
                    location.href = "/class/class_list";
                } else {
                    location.href = "/error";
                }
            }
        })
    }
</script>
