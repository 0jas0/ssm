<%--
  Created by IntelliJ IDEA.
  User: jas
  Date: 2018/3/8
  Time: 上午11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form  method="POST" enctype="multipart/form-data" action="/user/ajax/uploadFile">
    <input type="file" name="uploadFile" id="uploadFile"/>
    <input type="submit" name="sure" value="提交"/>
</form>
</body>
</html>
