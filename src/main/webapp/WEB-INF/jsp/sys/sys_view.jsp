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
            <input type="hidden" name="id" value="${systemModel.id}">
            <li><label>选课开始时间</label><input name="choiceStartStr" class="dfinput" type="date" value="${systemModel.choiceStartStr}"/><i></i></li>
            <li><label>选课结束时间</label><input name="choiceEndStr" class="dfinput" type="date" value="${systemModel.choiceEndStr}"/><i></i></li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="saveClass()"/></li>
    </ul>
    </div>
</body>
</html>
<script type="text/javascript">
    function saveClass() {
        var id = $("input[name=\"id\"]").val();
        var choiceStartStr = $("input[name=\"choiceStartStr\"]").val();
        var choiceEndStr = $("input[name=\"choiceEndStr\"]").val();
        $.ajax({
            type: 'POST',
            url: '/sys/ajax-modify-sys',
            cache: false,
            data: {
                "id":id,
                "choiceStartStr":choiceStartStr,
                "choiceEndStr":choiceEndStr
            },
            success: function (result) {
                console.log(result)
                if (!result.status) {
                    location.href = "/sys/sys_view";
                } else {
                    location.href = "/error";
                }
            }
        })
    }
</script>
