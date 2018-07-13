$(function(){
    var clientExam_datagrid=$("#clientExam_datagrid");
    var clientExam_dialog=$("#clientExam_dialog");
    var clientTrace_dialog=$("#clientTrace_dialog");
    clientExam_datagrid.datagrid({
        fit:true,
        url:"/clientMajor/list",
        fitColumns:true,
        striped:true,
        pagination:true,
        rownumbers:true,
        toolbar:"#tb",
        singleSelect:true,
        columns:[[
            {field: 'x', checkbox: true},
            {field: 'id', title: 'id', width: 90, align: "center",hidden:'true'},
            {field: 'name', title: '学校名称', width: 90, align: "center"},
            {
                field: 'saleMan', title: '营销人员', width: 90, align: "center", formatter: function (value) {
                if (value) {
                    return value.username;
                }}
            },
            {field: 'traceMan', title: '跟踪人员', width: 90, align: "center", formatter: function (value) {
                if (value) {
                    return value.username;
                }}},
            {field: 'pervTraceDate', title: '最后跟踪时间', width: 100, align: "center"},
            {field: 'nextTraceTime', title: '下次跟踪时间', width: 100, align: "center"},
            {field: 'tel', title: '学校电话', width: 110, align: "center"},
            {
                field: 'contactPerson', title: '联系人', width: 110, align: "center", formatter: function (value) {
                if (value) {
                    return value.name;
                }
            }
            },
            {field: 'importantDegree', title: '重要程度', width: 100, align: "center", formatter: function (value) {
                if (value) {
                    return value.name;
                }
            }
            },
            {field: 'intentionClass', title: '意向学科', width: 100, align: "center", formatter: function (value) {
                if (value) {
                    return value.name;
                }
            }},
            {field: 'clientState', title: '客户当前状态', width: 100, align: "center", formatter: function (value, row, index) {
                if (value) {
                    return "<font color='green'>已签约</font>";
                } else {
                    return "<font color='red'>未签约</font>";
                }
            }},
            {
                field: 'traceState', title: '跟踪状态', width: 100, align: "center", formatter: function (value, row, index) {
                if (value) {
                    return "<font color='green'>已跟进</font>";
                } else {
                    return "<font color='red'>未跟进</font>";
                }
            }
            },
            {field: 'remark', title: '备注', width: 100, align: "center"}
        ]]
    });




// 初始化一个弹框 点击添加或者编辑的时候才打开
    clientExam_dialog.dialog({
        width:850,
        height:550,
        buttons:"#bb",
        // 一开始就是关闭的状态
        closed:true
    });


    var cmdObj= {
        //添加操作
        add: function () {
            $("#clientExam_form").form("clear");
            clientExam_dialog.dialog("open");
            clientExam_dialog.dialog("setTitle", "潜在客户添加");
        },


        //编辑操作
        edit: function () {
            //编辑需要回显数据
            //从datagrid中获取编辑的那一行数据
            var row = clientExam_datagrid.datagrid("getSelected");
            alert(row.id)
            if (!row) {
                //如果不为true 说明没有选择数据 让用户选择数据
                $.messager.alert("温馨提示", "请选择要编辑的数据");
            } else {
                alert("进来======")
                //将选中的行的数据加载到对话框中的form表单中
                clientExam_dialog.dialog("open");
                clientExam_dialog.dialog("setTitle", "大客户编辑");
                $("#clientExam_form").form("clear");
                $("#clientExam_form").form("load", row);
                console.log(row)

            }
        },

        //保存操作
        save: function () {
            alert("点击保存是否有反应")
            // 点击保存 提交表单
            // 获取id 能够获取到的就是更新 不能获取的是保存
            var id = $("#clientMajorId").val();
            var url = "/clientMajor/save";
            if (id) {
                url = "/clientMajor/update";
            }
            alert(url)
            $("#clientExam_form").form("submit", {
                url: url,
                success: function (data) {
                    console.log(data);
                    // 接受返回的数据
                    // 操作失败 提示用户
                    // 操作成功,提示用户 关闭当前对话框,刷新页面
                    var data1 = $.parseJSON(data);
                    console.log("解析之后的"+data1)
                    if (!data1.success) {
                        $.messager.alert("温馨提示", data1.errorMsg);
                    } else {
                        alert("是否进来了=========")
                        $.messager.alert("温馨提示", "保存成功");
                        clientExam_dialog.dialog("close");
                        clientExam_datagrid.datagrid("load");

                    }
                }
            })
        },
        //高级查询
        query: function () {
            var keyword = $("#keyword").textbox("getValue");
            var beginDate = $("#beginDate").datebox("getValue");
            var endDate = $("#endDate").datebox("getValue");
            //将数据通过load
            clientExam_datagrid.datagrid("load", {
                keyword: keyword,
                beginDate: beginDate,
                endDate: endDate
            })
        },
        //删除大客户功能
        remove: function () {
            var row = clientExam_datagrid.datagrid("getSelected");
            if (!row) {
                //如果不为true 说明没有选择数据 让用户选择数据
                $.messager.alert("温馨提示", "请选择要删除的客户");
            } else {
                $.messager.confirm('确认', '您确认将该客户删除吗？', function (r) {
                    if (r) {
                        //发起请求
                        $.get("/clientMajor/delete", {id: row.id}, function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", "删除成功");
                                clientExam_datagrid.datagrid("load");
                            } else {
                                $.messager.alert("温馨提示", data.errorMsg);
                            }
                        })
                    }
                });

            }
        },
        //取消对话框
        cancel: function () {
            clientExam_dialog.dialog("close");
        },
        //刷新
        reload: function () {
            clientExam_datagrid.datagrid("reload");
        }
    }


    //调用方法
    $("a[data-cmd]").click(function(){
        var cmd=$(this).data("cmd");
        cmdObj[cmd]();
    })



})

