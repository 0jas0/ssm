<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<script src="js/jquery.uploadfile.js"></script>
<link href="css/uploadfile.css" rel="stylesheet">
<div style="padding:10px 10px 10px 10px">
	<form id="customAddForm" class="customForm" method="post">
	    <table cellpadding="5" >
	        <tr>
	            <td>开题报告:</td>
	            <td>
					<div id="openReportUploader">上传文件</div>
					<input type="hidden" id="openReport" name="file"/>
	            </td>
	        </tr>
			<tr>
				<td>任务书:</td>
				<td>
					<div id="TaskBook">上传文件</div>
					<input type="hidden" name="file"/>
				</td>
			</tr>
	        <tr>
	            <td>中期报告:</td>
	            <td>
					<div id="midReport">上传文件</div>
					<input type="hidden" name="file"/>
	            </td>
	        </tr>
			<tr>
	            <td>论文:</td>
	            <td>
					<div id="paper">上传文件</div>
					<input type="hidden" name="file"/>
	            </td>
	        </tr>
	    </table>
	    <input type="hidden" name="customParams"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitCustomAddForm()">提交</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearCustomAddForm()">重置</a>
	</div>
</div>
<script type="text/javascript">
	$(function () {
	    console.log("aa")
        initOrderAddFileUpload();
        TAOTAO.init({fun:function(node){
                //根据订单的分类id取订单 的规格模板，生成规格信息。第四天内容。
                TAOTAO.changeItemParam(node, "customAddForm");
            }});
    });
	//提交表单
	function submitCustomAddForm(){
		//有效性验证
		if(!$('#customAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//ajax的post方式提交表单
		//$("#customAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("student/addUserName",$("#customAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增学生成功!');
				clearCustomAddForm();
				$("#studentAddWindow").window('close');
				$("#customList").datagrid("reload");
			}else{
				$.messager.alert('提示',data.msg);
			}
		});
	}
	
	function clearCustomAddForm(){
		$('#customAddForm').form('reset');
		customAddEditor.html('');
	}
    function initOrderAddFileUpload(){
        $("#openReportUploader").uploadFile({
            url:"/paper/file/upload",
            maxFileCount: 5,                //上传文件个数（多个时修改此处
            returnType: 'json',              //服务返回数据
            allowedTypes: 'doc,docx,excel,sql,txt,ppt,pdf',  //允许上传的文件式
            showDone: false,                     //是否显示"Done"(完成)按钮
            showDelete: true,                  //是否显示"Delete"(删除)按钮
            deleteCallback: function(data,pd)
            {
                //文件删除时的回调方法。
                //如：以下ajax方法为调用服务器端删除方法删除服务器端的文件
                var fileUrl = data.url;
                $.ajax({
                    cache: false,
                    url: "/paper/file/delete",
                    dataType: "json",
                    data: {fileName:data.url},
                    success: function(data)
                    {
                        if(data.data=="success"){
                            pd.statusbar.hide();        //删除成功后隐藏进度条等
                            $('#image').val('');
                            var urls = $('#customAddForm [name=file]').val().split(",");  //将删除的文件url从urls中移除
                            var deletedUrls = [];
                            for(var i in urls){
                                if(urls[i] != fileUrl){
                                    deletedUrls.push(urls[i]);
                                }
                            }
                            deletedUrls = deletedUrls.join(",");
                            $('#customAddForm [name=file]').val(deletedUrls);
                        }else{
                            console.log(data.message);  //打印服务器返回的错误信息
                        }
                    }
                });
            },
            onSuccess: function(files,data,xhr,pd)
            {
                //上传成功后的回调方法。本例中是将返回的文件名保到一个hidden类开的input中，以便后期数据处理
                if(data&&data.error==0){
                    $.messager.alert('提示','上传完成!');
                    if( $('#customAddForm [name=file]').val() != null && $('#customAddForm [name=file]').val() != ''){
                        /* alert($('#orderAddForm [name=file]').val()); */
                        $('#customAddForm [name=file]').val($('#customAddForm [name=file]').val()+","+data.url);
                    }else{
                        $('#customAddForm [name=file]').val(data.url);
                    }
                }
            }
        });
    }
</script>
