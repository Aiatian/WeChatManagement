<%--
  Created by IntelliJ IDEA.
  User: Aiatian
  Date: 2019/7/3
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>你好啊</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<img src="images/1.jpg">

<script  type="application/javascript">
    //var user =

    window.onload=function() {
        $.ajax({
            url: "$[path]/Hello/getIP",
            dataType: 'json',
            type: 'post',
            data: {year:2017}

        })
    }
</script>
</body>
</html>
