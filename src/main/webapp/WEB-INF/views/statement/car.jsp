<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/static/js/plugins/echarts/echarts-all.js">
</script>
</head>
<body>
<div id="main" style= "height:400px" > </div>

<script type="text/javascript">
    var myChart = echarts.init(document.getElementById("main"));
    var option = {
        title : {
            text: '付款总额报表',
            subtext: '${groupByType}',
            x:'center'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['销售总额'],
            x:'left'
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : ${groupByTypes}
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'销售总额',
                type:'bar',
                data: ${receiptmoneys},
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            }

        ]
    };
    myChart.setOption(option);


</script>
</body>
</html>