<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/commons/commons.jsp"></jsp:include>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/js/system/course.js"></script>

</head>

<body class="easyui-layout">

<div data-options="region:'west',split:true" style="width:230px;">
    <div id="cc" class="easyui-calendar" name="study_date" style="width:220px;height:280px;" ></div>
</div>

<div data-options="region:'center'" style="padding:5px;">
    <div id="tb">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-add'" data-cmd="add">添加</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-reload'" data-cmd="reload">刷新</a>
        <input  class="easyui-combobox" name="grade.id" prompt="班级" id="gradeId"
                data-options="width:100,valueField:'id',textField:'name',url:'/grade/queryGrades'"/>

        <input  class="easyui-combobox" name="classTeacher.id" prompt="老师" id="empId"
                data-options="width:100,valueField:'id',textField:'realname',url:'/employee/queryTeachers' " />

        <input  class="easyui-combobox" name="classRoom.id" prompt="教室" id="classRoomId"
                data-options="width:100,valueField:'id',textField:'name',url:'/classRoom/queryClassRooms' " />
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="queryfast">快查</a>

        <input class="easyui-datebox" name="begindate" id="begindate"
               data-options="required:true,showSeconds:true" style="width:150px"/>~
        <input class="easyui-datebox" name="enddate" id="enddate"
               data-options="required:true,showSeconds:true" style="width:150px"/>
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="query">按日期段查询</a>

    </div>
    <table id="course_datagrid"></table>

    <div id="course_dialog">
        <form id="editForm" method="post" style="padding: 35px 0px 0px 35px">
            <input type="hidden" id="courseId" name="id">
            <table style="border-collapse: separate; border-spacing: 10px;">
                <tr>
                    <td>顺序</td>
                    <td><input type="text" class="easyui-textbox" name="sequence"></td>
                </tr>
                <tr>
                    <td>日期</td>
                    <td><input class="easyui-datebox" name="courseDate"/></td>
                </tr>
                <tr>
                    <td>星期</td>
                    <td><input type="text" class="easyui-textbox" name="week"></td>
                </tr>
                <tr>
                    <td>班级</td>
                    <td><input type="text" class="easyui-combobox" name="grade.id"
                               data-options="url:'/grade/queryGrades',valueField:'id',textField:'name',panelHeight:'auto',required:true">
                    </td>
                </tr>
                <tr>
                    <td>课程名称</td>
                    <td><input type="text" class="easyui-combobox" name="courseName.id"
                               data-options="url:'/courseName/queryCours',valueField:'id',textField:'name',panelHeight:'auto',required:true">
                    </td>
                </tr>
                <tr>
                    <td>班主任</td>
                    <td><input type="text" class="easyui-combobox" name="classTeacher.id"
                               data-options="url:'/employee/queryTeachers',valueField:'id',textField:'realname',panelHeight:'auto',required:true">
                    </td>
                </tr>
                <tr>
                    <td>上课教师</td>
                    <td><input type="text" class="easyui-combobox" name="courseTeacher.id"
                               data-options="url:'/employee/queryTeachers',valueField:'id',textField:'realname',panelHeight:'auto',required:true">
                    </td>
                </tr>
                <tr>
                    <td>教室</td>
                    <td><input type="text" class="easyui-combobox" name="classroom.id"
                               data-options="url:'/classRoom/queryClassRooms',valueField:'id',textField:'name',panelHeight:'auto',required:true">
                    </td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td><input type="text" class="easyui-combobox" name="state"
                               data-options="data:[{'value':'1','text':'已上'},{'value':'0','text':'未上'}],
				valueField:'value',textField:'text',panelHeight:'auto'"></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><input type="text" class="easyui-textbox" name="remark" multiline="true"></td>
                </tr>
            </table>
        </form>
    </div>

    <div id="bb">
        <a class="easyui-linkbutton" data-options="iconCls:'icon-save'" data-cmd="save">保存</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" data-cmd="cancel">取消</a>
    </div>
</div>
</body>

</html>
