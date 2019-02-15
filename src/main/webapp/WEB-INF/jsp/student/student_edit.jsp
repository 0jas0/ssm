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
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>学生信息</span></div>
    
    <ul class="forminfo">
            <li><label><b style="color: red">*</b>学号</label><input name="studentId" type="text" class="dfinput" value="${studentModel.studentId}" readonly/><i></i></li>
            <li><label><b style="color: red">*</b>密码</label><input name="password" type="password" class="dfinput"/><i></i></li>
            <li><label><b style="color: red">*</b>姓名</label><input name="name" type="text" class="dfinput" value="${studentModel.name}"/><i></i></li>
            <li><label>出生日期</label><input name="bornDate" class="dfinput" type="date" value="${studentModel.bornDate}"/><i></i></li>
            <li><label>政治面貌</label><input name="politicalOutlook" type="text" class="dfinput" value="${studentModel.politicalOutlook}"/><i></i></li>
            <li><label><b style="color: red">*</b>籍贯</label><input name="nativePlace" type="text" class="dfinput" value="${studentModel.nativePlace}"/><i></i></li>
            <li><label><b style="color: red">*</b>民族</label><input name="nation" type="text" class="dfinput" value="${studentModel.nation}"/><i></i></li>
            <li><label><b style="color: red">*</b>地址</label><input name="address" type="text" class="dfinput" value="${studentModel.address}" /><i></i></li>
            <li><label>邮政编码</label><input name="postalcode" type="text" class="dfinput" value="${studentModel.postalcode}"/><i></i></li>
            <li><label><b style="color: red">*</b>手机号</label><input name="mobile" type="text" class="dfinput" value="${studentModel.mobile}"/><i></i></li>
            <li><label><b style="color: red">*</b>身份证号</label><input name="identityCardNumber" type="text" class="dfinput" value="${studentModel.identityCardNumber}"/><i></i></li>

            <li><label><b style="color: red">*</b>学院</label><div class="vocation"><select class="select1" name="college">
                <c:forEach items="${collegeList}" var = "college">
                    <c:if test="${college.id != studentModel.college}">
                        <option value="${college.id}">${college.name}</option>
                    </c:if>
                    <c:if test="${college.id == studentModel.college}">
                        <option value="${college.id}" selected>${college.name}</option>
                    </c:if>
                </c:forEach>
            </select></div><i></i></li>
            <li><label><b style="color: red">*</b>专业</label><div class="vocation"><select class="select2" name="major">
                <c:forEach items="${majorList}" var = "major">
                    <c:if test="${major.id == studentModel.major}">
                        <option value="${major.id}" selected>${major.name}</option>
                    </c:if>
                    <c:if test="${major.id != studentModel.major}">
                        <option value="${major.id}">${major.name}</option>
                    </c:if>
                </c:forEach>
            </select></div><i></i></li>
            <li><label><b style="color: red">*</b>班级</label><div class="vocation"><select class="select3" name="classId">
                <c:forEach items="${classList}" var = "class">
                    <c:if test="${class.id == studentModel.classId}">
                        <option value="${class.id}" selected>${class.name}</option>
                    </c:if>
                    <c:if test="${class.id != studentModel.classId}">
                        <option value="${class.id}">${class.name}</option>
                    </c:if>
                </c:forEach>
            </select></div><i></i></li>

            <li><label>性别</label><cite><input name="sex" type="radio" value="男" <c:if test="${studentModel.sex == '男'}">checked</c:if> />男&nbsp;&nbsp;&nbsp;&nbsp;
                <input name="sex" type="radio" value="女" <c:if test="${studentModel.sex == '女'}">checked</c:if>/>女</cite></li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认修改" onclick="modifyStudent()"/></li>
        </form>
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
        $(".select2").change(function (){
            var collegeId = $(".select1 option:selected").val();
            var majorId = $(".select2 option:selected").val();
            $.ajax({
                type: 'post',
                url: '/class/ajax-get-class-by-collegeMajor',
                data:{"collegeId":collegeId,"majorId":majorId},
                success:function (res) {
                    if (res.status == 0){
                        var data = res.data;
                        var option = "";
                        data.forEach(function (value, index) {
                            option += "<option value='"+value.id+"'>"+value.name+"</option>";
                        })
                        $(".select3").empty();
                        $(".select3").html(option);
                    }else {
                        location.href = "/error";
                    }
                }
            });
        });
    });
    var fullPath = "";
    $("input[name='image']").change(function () {
        $.ajax({
            type: 'POST',
            url: '/student/ajax-reupload-file',
            cache: false,
            data: new FormData($("#studentModify")[0]),
            processData: false,
            contentType: false,
            success: function (result) {
                fullPath = result.data;
                console.log(fullPath+"---"+result);
            }
        });
    });
    function modifyStudent() {
        var studentId = $("input[name=\"studentId\"]").val();
        var password = $("input[name=\"password\"]").val();
        var name = $("input[name=\"name\"]").val();
        var bornDate = $("input[name=\"bornDate\"]").val();
        var politicalOutlook = $("input[name=\"politicalOutlook\"]").val();
        var nativePlace = $("input[name=\"nativePlace\"]").val();
        var nation = $("input[name=\"nation\"]").val();
        var address = $("input[name=\"address\"]").val();
        var postalcode = $("input[name=\"postalcode\"]").val();
        var mobile = $("input[name=\"mobile\"]").val();
        var identityCardNumber = $("input[name=\"identityCardNumber\"]").val();
        var college = $(".select1 option:selected").val();
        var major = $(".select2 option:selected").val();
        var classId = $(".select3 option:selected").val();
        var sex = $("input[name=\"sex\"]:checked").val();
        $.ajax({
            type: 'POST',
            url: '/student/ajax-modify-student',
            data: {
                "studentId":studentId,
                "password":password,
                "name":name,
                "bornDate":bornDate,
                "politicalOutlook":politicalOutlook,
                "nativePlace":nativePlace,
                "nation":nation,
                "address":address,
                "postalcode":postalcode,
                "mobile":mobile,
                "identityCardNumber":identityCardNumber,
                "college":college,
                "major":major,
                "classId":classId,
                "sex":sex,
                "photo":fullPath
            },
            success: function (result) {
                console.log(result)
                if (!result.status) {
                    location.href = "/student/student_list";
                } else {
                    location.href = "/error";
                }
            }
        })
    }
</script>
