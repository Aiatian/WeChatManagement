<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2019/5/19
  Time: 0:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script src="${path}/js/jquery-3.1.1.min.js" ></script>
</head>
<body>
    <form  action="${path}/User/queryUser" method="post">
         <input type="submit" id="inpID2" value="查詢"/>
    </form>

    <input type="submit" id="inpID" value="查詢"/>

    <input type="submit" id="inpID3" value="查詢"/>

    <c:if test="${user!=null}" >
            <table border="1">
                <tr>
                    <td>ID</td>
                    <td>姓名</td>
                    <td>年龄</td>
                    <td>课程</td>
                </tr>
                <c:forEach items="${user}" var="user">
                    <tr>
                        <th>${user.id}</th>
                        <th>${user.username}</th>
                        <th>${user.age}</th>
                        <th>${user.course}</th>
                    </tr>
                </c:forEach>
            </table>

    </c:if>


<script type="text/javascript">

    // beforeSubmit:function(){
    //     var chackunameflag =  checkFrom($("#unameid"),/^[\u4e00-\u9fa5]{2,8}$/,$("#unamespanid"),"请输入员工名","员工名必须是2~8个汉子",true);
    //     var chackphoneflag =  checkFrom($("#phoneid"),/^(1[3568]\d{9})$/,$("#phonespanid"),"电话号码不能为空","请输入正确的电话号码",true);
    //     return chackunameflag&&chackphoneflag;
    // }

    var user;
$("#inpID").click(function () {
    $.ajax({
        url:"${path}/User/queryUser2",
        type:"post",
        //dataType:"application/json",
        success:function (data) {
           if (data!=null){
               console.log(data.id);
               console.log(data.username);
               console.log(data.age);
               console.log(data.course);
            for (var i=0;i<data.length;i++){

            }
           }
        }

    })

})


    $("#inpID3").click(function () {
        $.ajax({
            url:"${path}/User/queryUser3",
            type:"post",
            //dataType:"application/json",
            success:function (data) {
                if (data!=null){
                    for (var i=0;i<data.length;i++){
                        console.log(data[i].id);
                        console.log(data[i].username);
                        console.log(data[i].age);
                        console.log(data[i].course);
                    }
                }
            }

        })

    })


  var vm = new Vue({

    })

</script>
</body>
</html>
