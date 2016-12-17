<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Login page</title>
                <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
                <script type="text/javascript" src='<c:url value="static_resources/js/semantic.min.js"/>'></script>
                <link href="<c:url value='static_resources/css/semantic.min.css' />" rel="stylesheet"></link>
	</head>
        <style>
            .ui.input{
                margin: 4px;
            }
            .ui.input:hover{
               box-shadow: 0px 0px 5px 0px lavender;
            }
            .ui input{
               
            }
            .ui input:focus{
                border: 1px solid #7860f9 !important;
            }
            
        </style>

	<body>
            <div class="ui grid" style="margin-top:50px !important;" >
                <div class="column">
                    <div class="ui centered segment raised" style="text-align: center !important; width:20% !important; margin: 0px auto !important;">
                        <c:url var="loginUrl" value="/login" />
                        <!--<div class="ui violet segment" style="text-align:center !important;">-->
                        <form action="${loginUrl}" method="post">
                                <c:if test="${param.error != null}">
                                        <div class="ui label">
                                                <p>Invalid username and password.</p>
                                        </div>
                                </c:if>
                                <c:if test="${param.logout != null}">
                                        <div class="ui label">
                                                <p>You have been logged out successfully.</p>
                                        </div>
                                </c:if>
                                <div class="ui left icon input">
                                    <input type="text"  id="username" name="login" placeholder="логин">
                                    <i class="user icon"></i>
                                </div><br>
                                <div class="ui left icon input">
                                 <input type="password"  id="password" name="password" placeholder="пароль" required>
                                 <i class="home icon"></i>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                <br>
                                <input type="submit" value="Войти" class="ui violet button">
                        </form>
                        <!--</div>-->
                    </div>
                </div>
            </div>
	</body>
</html>