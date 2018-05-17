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
    <li><a href="#">添加成绩</a></li>
    </ul>
    </div>
    <div class="formbody">
    
    <div class="formtitle"><span>班级信息</span></div>
    
    <ul class="forminfo">
            <input type="hidden" name="courseId" value="${courseId}">
            <li><label>学生名称</label><div class="vocation">
                <select class="select1" name="studentId">
                    <c:forEach items="${studentModels}" var="studentModel">
                        <option value="${studentModel.id}">${studentModel.name}</option>
                    </c:forEach>
                </select>
            </div><i></i></li>
            <li><label>分数</label><input name="grade" type="text" class="dfinput" /><i></i></li>
            <li><label>所获学分</label><input name="accessCredits" type="text" class="dfinput" /><i></i></li>
            <li><label>挂科原因</label><input name="failReason" type="text" class="dfinput" /><i></i></li>
            <li><label>重修情况</label><input name="reworkSituation" type="text" class="dfinput"/><i></i></li>
            <li><label>是否重修</label><cite><input name="isRework" type="radio" value="1" checked="checked" />是
                &nbsp;&nbsp;&nbsp;&nbsp;<input name="isRework" type="radio" value="0" />否</cite>
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
    });
    function saveClass() {
        var courseId = $("input[name=\"courseId\"]").val();
        var studentId = $(".select1 option:selected").val();
        var grade = $("input[name=\"grade\"]").val();
        var accessCredits = $("input[name=\"accessCredits\"]").val();
        var failReason = $("input[name=\"failReason\"]").val();
        var reworkSituation = $("input[name=\"reworkSituation\"]").val();
        var isRework = $("input[name=\"isRework\"]:checked").val();
        $.ajax({
            type: 'POST',
            url: '/score/ajax-add-score',
            cache: false,
            data: {
                "courseId":courseId,
                "studentId":studentId,
                "grade":grade,
                "accessCredits":accessCredits,
                "failReason":failReason,
                "reworkSituation":reworkSituation,
                "isRework":isRework
            },
            success: function (result) {
                console.log(result)
                if (!result.status) {
                    location.href = "/score/score_detail?courseId="+courseId;
                } else {
                    location.href = "/error";
                }
            }
        })
    }
</script>
