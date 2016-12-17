<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--страничка пользователя приложения, рассылающего сообщения-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User tools</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script type="text/javascript" src='<c:url value="static_resources/js/semantic.min.js"/>'></script>
        <script type="text/javascript" src='<c:url value="static_resources/js/customer.select.js"/>'></script>
        <script type="text/javascript" src='<c:url value="static_resources/js/data.injection.js"/>'></script>
        <link href="<c:url value='static_resources/css/semantic.min.css' />" rel="stylesheet"></link>
        <link href="<c:url value='static_resources/css/customer.select.css' />" rel="stylesheet"></link>
        <link href="<c:url value='static_resources/css/custom_style.css' />" rel="stylesheet"></link>
    </head>
    <body>
        <div class="ui centered grid raised segment" style="width: 80%; margin: 0px auto !important; margin-top:50px !important;" >               
            <div class="sixteen wide column">
                <div class="ui violet clearing segment">
                    <div class="ui left floated segment" style="margin: 0px;padding:0px; border:none; box-shadow: none;">
                        <i class="large user icon"></i>
                        Вы вошли в систему как
                        <a style="font-size: 1.1em !important; color: #4f20b5;">${user}</a>                        
                    </div>
                    <div class="ui right floated segment" style="margin: 0px;padding:0px; border:none; box-shadow: none;">
                        <i class="large setting icon"></i>
                        <a class="violet_href" href="user/settings">Настройки</a>
                        <i class="large unlock alternate icon"></i>
                        <a class="violet_href" href="app_logout">Выход</a>
                    </div>                   
                </div>
            </div>
                <div class="eight wide column" style="text-align: left !important;">
                    <!-- выбор факультета-->
                    <div class="wrapper_select" id="select_faculties">
                        <div class="select_header">
                            <input type="text" placeholder="выберите факультет" id="faculty_value">
                            <button class="mini ui icon violet basic button">
                                <i class="angle down icon"></i>
                            </button>
                        </div>
                        <div class="select_items">
                            <c:forEach var="faculty" items="${faculties}">				
                                <div fac_id="${faculty.id}">${faculty.title}</div>                              
                            </c:forEach>
                        </div>
                    </div>
                    <br>
                    <!-- выбор курса-->
                    <div class="wrapper_select" id="select_courses">
                        <div class="select_header">
                            <input type="text" placeholder="выберите курс" id="course_value">
                            <button class="mini ui icon violet basic button">
                                <i class="angle down icon"></i>
                            </button>
                        </div>
                        <div class="select_items">    
                        </div>
                    </div>
                    <br>
                    <!--выбор группы-->
                    <div class="wrapper_select" id="selected_groups">
                        <div class="select_header">
                            <input type="text" placeholder="выберите группу" id="groupe_id">
                            <button class="mini ui icon violet basic button">
                                <i class="angle down icon"></i>
                            </button>
                        </div>
                        <div class="select_items">
                        </div>
                    </div>
                    <br>
                    <div class="ui checkbox">
                        <input type="checkbox" id="only_captain">
                        <label>Отправить только старосте</label>
                    </div>
                    <br>        
                    <!--для визуального отображения фильтров-->
                    <div id="filters">                        
                    </div>  
                    <br>                  
                    <button class="ui violet button" id="add_filter_btn">добавить</button>
                    
                </div>
                <!--вторая колонка-->
                <div class="eight wide column" style="text-align: left !important;">
                    <textarea style="width:100%;" rows="9" id="text_message"></textarea>
                    <br>                    
                    <div class="ui checkbox">
                        <input type="checkbox" id="add_signature">
                        <label>Добавить подпись</label>                      
                    </div>                   
                    <br><br>
                    <button class="ui violet button" id="send_filters">отправить</button>
                    <button class="ui violet button" style="float:right !important;">прикрепить файл</button>
                </div>                                    
        </div>
        <!-- 
        --79204282824
            csf_bot
        --
        -->    
        <!--<a href="https://oauth.vk.com/authorize?client_id=5529263&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=offline,messages&response_type=token&v=5.37">get key</a>-->     
    </body>
</html>
