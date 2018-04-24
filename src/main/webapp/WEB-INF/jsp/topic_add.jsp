<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="customAddForm" class="customForm" method="post">
	    <table cellpadding="5" >
	        <tr>
	            <td>毕设题目:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="title" data-options="required:true"/>
	            </td>
	        </tr>
			<tr>
				<td>专业:</td>
				<td>
					<input class="easyui-textbox" type="text" name="major" data-options="required:true"/>
				</td>
			</tr>
	        <tr>
	            <td>限选人数:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" onkeyup="value=value.replace(/[^\d]/g,'')" name="limitStudent" data-options="required:true"/>
	            </td>
	        </tr>
			<tr>
				<td>说明:</td>
				<td>
					<textarea style="width:800px;height:300px;visibility:hidden;" name="remark"></textarea>
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
    var toppicAddEditor ;
    //页面初始化完毕后执行此方法
    $(function(){
        //创建富文本编辑器
        //customAddEditor = TAOTAO.createEditor("#customAddForm [name=file]");
        toppicAddEditor = KindEditor.create("#customAddForm [name=remark]", TT.kingEditorParams);
    });
	//提交表单
	function submitCustomAddForm(){
		//有效性验证
		if(!$('#customAddForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
        toppicAddEditor.sync();
		//ajax的post方式提交表单
		//$("#customAddForm").serialize()将表单序列号为key-value形式的字符串
		$.post("topic/addTopic",$("#customAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','新增学生成功!');
				clearCustomAddForm();
				$("#topicAddWindow").window('close');
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
</script>
