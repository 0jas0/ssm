<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<link href="js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="js/kindeditor-4.1.10/lang/zh_CN.js"></script>
<table class="easyui-datagrid" id="customList" title="学生成绩列表" data-options="singleSelect:false,collapsible:true,
		pagination:true,rownumbers:true,url:'scoreManagement/getByTeacher', method:'get',pageSize:30, fitColumns:true,
		toolbar:toolbar_teacher">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
			<th data-options="field:'studentName',width:200,align:'center'">学生姓名</th>
			<th data-options="field:'topicName',width:200,align:'center'">课题名称</th>
			<th data-options="field:'topicId',width:200,align:'center'">课题编号</th>
            <th data-options="field:'openingReport',width:100,align:'center'">开题报告</th>
        	<th data-options="field:'commitments',width:100,align:'center'">任务书</th>
            <th data-options="field:'midtermCheck',width:100,align:'center'">中期检查</th>
            <th data-options="field:'paper',width:200,align:'center'">论文</th>
			<th data-options="field:'total',width:200,align:'center'">总成绩</th>

        </tr>
    </thead>
</table>

<div  id="toolbar_teacher" style=" height: 22px; padding: 3px 11px; background: #fafafa;">  
	
		    <%--<div style="float: left;">--%>
		        <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="custom_add()">新增</a>  --%>
		    <%--</div>  --%>
		    <%--<div style="float: left;">--%>
		        <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="custom_edit()">编辑</a>  --%>
		    <%--</div>  --%>
		    <%--<div style="float: left;">--%>
		        <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-cancel"--%>
		        	 <%--onclick="custom_delete()">删除</a>  --%>
		    <%--</div>  --%>
	<div class="datagrid-btn-separator"></div>
	
	<div style="float: left;">  
		<a href="#" class="easyui-linkbutton" plain="true" icon="icon-reload" onclick="custom_reload()">刷新</a>  
	</div>  
	
    <%--<div id="search_custom" style="float: right;">--%>
        <%--<input id="search_text_custom" class="easyui-searchbox"  --%>
            <%--data-options="searcher:doSearch_custom,prompt:'请输入...',menu:'#menu_custom'"  --%>
            <%--style="width:250px;vertical-align: middle;">--%>
        <%--</input>--%>
        <%--<div id="menu_custom" style="width:120px"> --%>
			<%--<div data-options="keyword:'keyword'"></div>--%>
		<%--</div>--%>
    <%--</div>--%>

</div>


<div id="teacherEditWindow" class="easyui-window" title="修改老师信息" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'teacher/editView'" style="width:65%;height:80%;padding:10px;">
</div>
<div id="teacherAddWindow" class="easyui-window" title="添加老师" data-options="modal:true,closed:true,resizable:true,
	iconCls:'icon-save',href:'teacher/addView'" style="width:65%;height:80%;padding:10px;">
</div>

<script>

function doSearch_custom(name){ //用户输入用户名,点击搜素,触发此函数
	if(name == null || name == ''){
		$("#customList").datagrid({
	        title:'教师列表', singleSelect:false, collapsible:true, pagination:true, rownumbers:true,
	        	method:'get', nowrap:true,  
	        toolbar:"toolbar_teacher", url:'teacher/getData', method:'get', loadMsg:'数据加载中......',
	        	fitColumns:true,//允许表格自动缩放,以适应父容器  
	        columns : [[
	             	{field : 'ck', checkbox:true }, 
	             	{field : 'id', width : 100, title : 'Id', align:'center'},
	             	{field : 'serial_number', width : 100, title : '教师编号', align:'center'},
	             	{field : 'name', width : 100, align : 'center', title : '教师名称'},
	             	{field : 'password', width : 200, align : 'center', title : '密码'},
	             	{field : 'sex', width : 200, title : '性别', align:'center'},
	             	{field : 'email', width : 100, title : '邮箱', align:'center'},
	            	{field : 'major', width : 70, title : '专业', align:'center'},
	             	{field : 'mobile', width : 60, title : '联系电话', align:'center'},
	        ]],
	    });
	}
}	
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
	
	function custom_add(){
		$("#teacherAddWindow").window("open");
    }
    
    function custom_edit(){
        var ids = getCustomSelectionsIds();

        if(ids.length == 0){
            $.messager.alert('提示','必须选择一个客户才能编辑!');
            return ;
        }
        if(ids.indexOf(',') > 0){
            $.messager.alert('提示','只能选择一个客户!');
            return ;
        }

        $("#teacherEditWindow").window({
            onLoad :function(){
                //回显数据
                var data = $("#customList").datagrid("getSelections")[0];
                $("#teacherEditWindow").form("load", data);
                customEditEditor.html(data.note);
            }
        }).window("open");
    }
    
    function custom_delete(){
		var ids = getCustomSelectionsIds();
		if(ids.length == 0){
			$.messager.alert('提示','未选中客户!');
			return ;
		}
		$.messager.confirm('确认','确定删除ID为 '+ids+' 的客户吗？',function(r){
			if (r){
				var params = {"ids":ids};
				$.post("teacher/deleteUserName",params, function(data){
					if(data.status == 200){
						$.messager.alert('提示','删除客户成功!',undefined,function(){
							$("#customList").datagrid("reload");
						});
					}
				});
			}
		});
    }
    
    function custom_reload(){
    	$("#customList").datagrid("reload");
    }
</script>