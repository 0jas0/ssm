<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="customEditForm" class="customForm" method="post">
		<input type="hidden" name="customId"/>
	    <table cellpadding="5">
            <input type="hidden" name="id">
			<tr>
				<td>教师编号:</td>
				<td>
					<input class="easyui-textbox" type="text" name="serialNumber" />
				</td>
			</tr>
			<tr>
				<td>登陆密码:</td>
				<td>
					<input class="easyui-textbox" type="password" name="password" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<td>教师名称:</td>
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
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitCustomEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
	function submitCustomEditForm(){
		if(!$('#customEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//同步文本框中的备注
		$.post("teacher/editUserName",$("#customEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改客户成功!','info',function(){
					$("#teacherEditWindow").window('close');
					$("#customList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示', data.msg);
			}
		});
	}
</script>
