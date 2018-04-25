<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<table class="easyui-datagrid" id="customList" title="学生列表" data-options="singleSelect:false,collapsible:true,
		pagination:true,rownumbers:true,url:'paper/paperList', method:'get',pageSize:30, fitColumns:true,
		toolbar:toolbar_sutdent">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'phase',width:100,align:'center'">阶段</th>
        	<th data-options="field:'fileName',width:100,align:'center'">文件名</th>
            <th data-options="field:'path',width:100,align:'center'">文件</th>
            <th data-options="field:'status',width:100,align:'center'">状态</th>
        </tr>
    </thead>
</table>

<div  id="toolbar_sutdent" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
		    <div style="float: left;">
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="custom_add()">新增</a>  
		    </div>  
		    <div style="float: left;">
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="custom_edit()">编辑</a>  
		    </div>  
		    <div style="float: left;">
		        <a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel"
		        	 onclick="custom_delete()">删除</a>  
		    </div>  
	<div class="datagrid-btn-separator"></div>
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="custom_reload()">刷新</a>  
	</div>

</div>


<div id="paperEditWindow" class="easyui-window" title="修改论文附件" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'paper/paperStdudentEdit'" style="width:65%;height:80%;padding:10px;">
</div>

<script>
	//根据index拿到该行值
	
	function getCustomSelectionsIds(){
		var customList = $("#customList");
		var sels = customList.datagrid("getSelections");
		var ids = [];
		for(var i in sels){
			ids.push(sels[i].id);
		}
		ids = ids.join(","); 
		
		return ids;
	}

    
    function custom_edit(){
        var ids = getCustomSelectionsIds();

        /*if(ids.length == 0){
            $.messager.alert('提示','必须选择一个客户才能编辑!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一个客户!');
            return ;
        }*/
        $("#paperEditWindow").window("open");
    }
    
    function custom_reload(){
    	$("#customList").datagrid("reload");
    }
</script>