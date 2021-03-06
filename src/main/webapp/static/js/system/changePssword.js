$(function () {
    $("#dep_datagrid").datagrid({
        url: "/changePassword/list",
        fitColumns: true,
        fit: true,
        singleSelect: true,
        columns: [[ {field: 'x', checkbox: true},
            {field: 'id', title: 'ID', width: 50, align: "center"},
            {field: 'username', title: '用户名', width: 80, align: "center"},
            {field: 'realname', title: '真实姓名', width: 80, align: "center"},
        ]],
        toolbar: "#tb",
    })

    $("#dep_dialog").dialog({
        width: 400,
        height: 400,
        buttons: "#bb",
        closed: true,
    })


})
function add() {
    $("#editfrom").form("clear");
    $("#dep_dialog").dialog("open");
    $("#dep_dialog").dialog("setTitle", "部门添加");
}
function edit() {
    $("#editfrom").form("clear");
    // 必须是选中的
    var row = $("#dep_datagrid").datagrid("getSelected")
    if (!row) {
        $.messager.alert('温馨提示', '请选择需要修改密码的行')
    } else {
        // 打开对话框
        $("#dep_dialog").dialog("open");
        // 设置对话框为部门编辑
        $("#dep_dialog").dialog("setTitle", "修改密码");
        /*	if (row.department) {
         row["department.id"] = row.department.id;
         }*/
        $("#editfrom").form("load", row);
    }

}
function save() {
    // 获取id
    var id = $("#id").val();
    var controller = "/department/save";
    if (id) {
        controller = "/employee/changePassword";
    }
    // url:错误信息路径
    // 提交表单操作
    $("#editfrom").form("submit", {
        url: controller,
        success: function (data) {
            // 1:接受返回数据
            data = $.parseJSON(data);
            // 2判断操作是否成功
            // 3操作失败,提示用户
            if (!data.success) {
                $.messager.alert('温馨提示', data.msg);
            } else {
                // 4操作成功
                // 4.1提示用户操作成功
                // 4.2关闭当前对话框
                $("#dep_dialog").dialog("close");
                // 4.3刷新页面
                $("#dep_datagrid").datagrid("reload");
            }
        }
    })
}
// 改变状态操作
/*function changeState() {
 // 首先必须是选中的,
 var row = $("#dep_datagrid").datagrid("getSelected");
 // 如果没有选中给出提示
 if (!row) {
 $.messager.alert('温馨提示', '请选择要改变的行')
 } else {
 // 如果选中了使用ajax方式发起删除请求
 $.get("/department/changeState", {
 id : row.id
 }, function(data) {
 // 如果删除失败,给出提示信息
 if (!data.success) {
 $.messager.alert('温馨提示', data.msg);
 // 成功刷新页面
 } else {

 $("#dep_datagrid").datagrid("reload");
 }
 });
 }
 }*/
function changeState() {
    // 首先必须是选中的,
    var row = $("#dep_datagrid").datagrid("getSelected");
    // 如果没有选中给出提示
    if (!row) {
        $.messager.alert('温馨提示', '请选择要改变状态的行')
    }
    // 如果选中了使用ajax方式发起删除请求
    $.messager.confirm('确认', "您确定改变该部门的状态码", function (r) {
        if (r) {
            $.get("/department/changeState", {id: row.id}, function (data) {
                    if (data.success) {
                        $("#dep_datagrid").datagrid("reload");
                    } else {
                        $.messager.alert('温馨提示', data.msg);
                    }
                }
            )
        }

    })
}
// 刷新列表数据
function reload() {
    $("#dep_datagrid").datagrid("reload");
}
// 关闭对话框
function cancel() {
    $("#dep_dialog").dialog("close");
}
// 高级查询
function query() {
    var keyword = $("#keyword").textbox("getValue");

    $("dep_datagrid").datagrid("load", {
        keyword: keyword
    })
}
