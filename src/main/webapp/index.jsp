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
    <form id="formDate"  method="post" enctype=”multipart/form-data”>
        <input type="file" name="image">
        <input type="button" onclick="aa()" value="提交">
    </form>
</body>
</html>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
    function aa() {
        console.log("aaa");
        $.ajax({
            type: 'POST',
            url: '/file/ajax/only-upload-file',
            cache: false,
            data: new FormData($("#formDate")[0]),
            processData: false,
            contentType: false,
            success: function (result) {
                if (!result.status) {
                    imageUrl = result.data;
                    $("#upload_result").text("图片上传成功");
                    $("#picUrl").text("图片URL：" + imageUrl);
                } else {
                    $("#upload_result").text(result.msg);
                }
            },
            error:function(){
                $("#upload_result").text("图片上传失败");
            }
        })
    }
</script>
