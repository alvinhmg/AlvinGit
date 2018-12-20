<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>登录页面</title>
    <!-- 获取项目名称 -->
    <%
        pageContext.setAttribute("PATH", request.getContextPath());
    %>

    <!--
    web路径
    不以/开始的相对路径，找资源以当前路径为基准（容易出问题）
    以/开始的相对路径，找资源以服务器的路径（http://localhost:8080）为标准,需要加项目名
     -->
    <script type="text/javascript" src="${PATH}/static/jquery/jquery-1.12.4.min.js"></script>
    <link href="${PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <%-- <link href="${PATH }/static/css/login.css" rel="stylesheet"> --%>

    <style type="text/css">
        .container{
            display:table;
            height:100%;
        }

        .row{
            display: table-cell;
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <form action="login" method="post">
                <div class="form-group">
                    <label>用户名</label>
                    <input type="text" class="form-control input-sm" name="username">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control input-sm" name="password">
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox">记住用户名密码
                    </label>
                </div>
                <button type="submit" class="btn btn-default">提交</button>
                <div>${msg}</div>
            </form>
        </div>
    </div>
</div>
</body>
</html>