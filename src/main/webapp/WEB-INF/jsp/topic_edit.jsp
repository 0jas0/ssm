<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="customEditForm" class="customForm" method="post">
		<input type="hidden" name="customId"/>
	    <table cellpadding="5">
            <input type="hidden" name="topicId">
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
					<textarea style="width:800px;height:300px;visibility:visible;" name="remark"></textarea>
				</td>
			</tr>
	    </table>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitCustomEditForm()">提交</a>
	</div>
</div>
<script type="text/javascript">
    var toppicEditEditor ;
    //页面初始化完毕后执行此方法
    $(function(){
        //创建富文本编辑器
        //customAddEditor = TAOTAO.createEditor("#customAddForm [name=file]");
        toppicEditEditor = KindEditor.create("#customEditForm [name=remark]", TT.kingEditorParams);
    });
    toppicEditEditor.sync();
    function submitCustomEditForm(){

		if(!$('#customEditForm').form('validate')){
			$.messager.alert('提示','表单还未填写完成!');
			return ;
		}
		//同步文本框中的备注
        customAddEditor.sync();
		$.post("topic/editTopic",$("#customEditForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('提示','修改课题成功!','info',function(){
					$("#topicEditWindow").window('close');
					$("#customList").datagrid("reload");
				});
			}else{
				$.messager.alert('提示', data.msg);
			}
		});
	}
</script>
