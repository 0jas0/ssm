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
            <input type="hidden" name="courseId" value="${courseId}">
            <li><label>第几节课</label><div class="vocation"><select class="select1" name="courseTime">
                <option value="1">第一大节课</option>
                <option value="2">第二大节课</option>
                <option value="3">第三大节课</option>
                <option value="4">第四大节课</option>
            </select></div><i></i></li>
            <li><label>星期</label><div class="vocation"><select class="select2" name="courseWeek">
                <option value="1">周一</option>
                <option value="2">周二</option>
                <option value="3">周三</option>
                <option value="4">周四</option>
                <option value="5">周五</option>
                <option value="6">周六</option>
                <option value="7">周日</option>
            </select></div><i></i></li>
            <li><label>课程地点</label><input name="coursePlace" type="text" class="dfinput" /><i></i></li>
            <li><label>班级号</label><div class="vocation">
                <select class="select3" name="classId"></select>
            </div><i></i></li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" onclick="saveClass()"/></li>
    </ul>
    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(e) {
        $(".select1").uedSelect({
            width : 200
        });
        $(".select2").uedSelect({
            width : 200
        });
        $(".select3").uedSelect({
            width : 200
        });
        $.ajax({
            type: 'post',
            url: '/class/ajax/get-class-by-collegeId?collegeId='+${collegeId},
            success:function (res) {
                if (res.status == 0){
                    var data = res.data;
                    var option = "";
                    data.forEach(function (value, index) {
                        option += "<option value='"+value.id+"'>"+value.name+"</option>";
                    })
                    $(".select3").append(option);
                }else {
                    location.href = "/error";
                }
            }
        });

    });
    function saveClass() {
        var courseId = $("input[name=\"courseId\"]").val();
        var coursePlace = $("input[name=\"coursePlace\"]").val();
        var courseTime = $(".select1 option:selected").val();
        var courseWeek = $(".select2 option:selected").val();
        var classId = $(".select3 option:selected").val();
        $.ajax({
            type: 'POST',
            url: '/course/ajax/add-course-time-place',
            cache: false,
            data: {
                "courseId":courseId,
                "coursePlace":coursePlace,
                "courseTime":courseTime,
                "courseWeek":courseWeek,
                "classId":classId
            },
            success: function (result) {
                console.log(result)
                if (!result.status) {
                    location.href = "/course/course_time_place_list?courseId="+courseId;
                } else {
                    location.href = "/error";
                }
            }
        })
    }
</script>
