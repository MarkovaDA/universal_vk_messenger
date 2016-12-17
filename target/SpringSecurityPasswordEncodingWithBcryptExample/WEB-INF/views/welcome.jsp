<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script type="text/javascript" src='<c:url value="static_resources/js/semantic.min.js"/>'></script>
	<link href="<c:url value='static_resources/css/semantic.min.css' />" rel="stylesheet"></link>
</head>
<style>
   p{
     text-align: left;
   }
   span{
       text-align: left;
   }
</style>
<body>    
    <div class="ui grid" style="margin-top:50px !important;" >
        <div class="column">
            <div class="ui centered segment raised" style="width:80% !important; margin: 0px auto !important;">
              <a class="ui violet right ribbon label" style="font-size: 1.1em !important" href="entrance">Вход</a>
              <span>Добро пожаловать в приложение <b>ВКИнформер</b>!</span><br>
              <div class="ui violet segment">             
                <p>
                    Наша система разработана для преподавателей ВУЗов и нацелена
                    на массовую рассылку сообщений студентам.               
                    Если Вы - преподаватель,
                    и  Вам  необходимо связаться с какой-либо категорией обучающихся
                    (курсом, группой, отдельными студентами),
                    то данное приложение поможет донести Вашу информацию до целевой аудитории.
                </p> 
              </div>
            </div>
        </div>
    </div>
</body>
</html>