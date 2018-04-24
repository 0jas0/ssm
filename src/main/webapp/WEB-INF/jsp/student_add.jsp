<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="customAddForm" class="customForm" method="post">
	    <table cellpadding="5" >
	        <tr>
	            <td>学生编号:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="serialNumber" data-options="required:true"/>
	            </td>
	        </tr>
			<tr>
				<td>登陆密码:</td>
				<td>
					<input class="easyui-textbox" type="password" name="password" data-options="required:true"/>
				</td>
			</tr>
	        <tr>
	            <td>学生名称:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="name" data-options="required:true"/>
	            </td>
	        </tr>

	        <tr>
	            <td>性别:</td>
	            <td>

					<select class="easyui-combobox" name="sex" style="width:280px;">
						<option value="男" selected>男</option>
						<option value="女">女</option>
					</select>
	            </td>
	        </tr>
	        <tr>
	            <td>专业:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="major" style="width: 280px;" data-options="required:true"/>
	            </td>
	        </tr>
	        <tr>
	            <td>电话:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="mobile" data-options="required:true"/>
	            </td>
	        </tr>
	        <tr>
	            <td>邮箱:</td>
	            <td>
	            	<input class="easyui-textbox" type="text" name="email"/>
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
</script>
