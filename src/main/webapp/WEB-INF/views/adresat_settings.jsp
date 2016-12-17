<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--страничка настройки параметров адресата-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User tools</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
        <script type="text/javascript" src='<c:url value="/static_resources/js/semantic.min.js"/>'></script>
        <script type="text/javascript" src='<c:url value="/static_resources/js/customer.select.js"/>'></script>
        <script type="text/javascript" src='<c:url value="/static_resources/js/data.injection.js"/>'></script>
        <link href="<c:url value='/static_resources/css/custom_style.css' />" rel="stylesheet"></link>
        <link href="<c:url value='/static_resources/css/semantic.min.css' />" rel="stylesheet"></link>
        <script>
            $(document).ready(function(){
                $('#status_apply').hide();
                //активация вкладок
                $('.demo.menu .item').tab();
                var settings = new Object();
                //262591631
                //by_mail, by_vk
                $('#btn_apply').click(function(){
                    settings.by_mail = $('#by_mail').prop("checked");
                    settings.by_vk = $('#by_vk').prop("checked");
                    settings.mail = $('#mail_input input').val();
                    settings.old_password = $('#old_password_input input').val();
                    settings.new_password = $('#new_password_input input').val();
                    settings.login = $('#login').html();
                    console.log(JSON.stringify(settings));
                    
                    $.ajax({
                        headers: { 
                        'Accept': 'application/json',
                        'Content-Type': 'application/json' 
                        },
                        'type': 'POST',
                        'url': 'update_settings',
                        'data': JSON.stringify(settings),
                        'dataType': 'json',
                        'success': function(data){
                            console.log(data);
                         }
                    })
                    .done(function(data) {
                        console.log("done");
                    });
                    
                    $('#status_apply').fadeIn(200);
                });             
            });
        </script>
        <style>
            .simple_href:hover{
                text-decoration: underline !important;
            }
            .input{
              width: 100% !important;             
            }
           
            
        </style>
        
    </head>
    <body>
        <div class="ui centered grid raised segment" style="width: 80%; margin: 0px auto !important; margin-top:50px !important;" >               
            <div class="sixteen wide column">
                <div class="ui violet clearing segment">
                    <div class="ui left floated segment" style="margin: 0px;padding:0px; border:none; box-shadow: none;">
                        <i class="large settings icon"></i>                       
                        <span id="login">${user}</span>                          
                    </div>
                    <div class="ui right floated segment" style="margin: 0px;padding:0px; border:none; box-shadow: none;">
                        <i class="large home icon"></i>
                         <a class="violet_href" 
                            href="<c:url value="/adresat"/>">В личный кабинет                    
                         </a>
                    </div>                   
                </div>
            </div>
            <div class="six wide column" style="text-align: left !important;">                                 
                <div class="row" style="height: 300px;">                    
                    <div class="ui pointing secondary demo menu">
                      <a class="active violet item" data-tab="first">
                          <i class="user icon"></i>
                          Личные данные
                      </a>
                      <a class="violet item" data-tab="second">
                          <i class="alarm icon"></i>
                          Оповещения
                      </a>
                    </div>
                    <!--1 вкладка - настройка личных параметров-->
                    <div class="ui active tab segment" data-tab="first">
                        <i class="write icon"></i>
                        <a class="simple_href mail_href"  style="color: #242424;">
                            Моя почта</a><br><br>
                        <div class="ui input" id="mail_input">
                            <input type="text" value = ${mail}>
                        </div>
                        <br><br>
                        <i class="lock icon"></i>
                        <a class="simple_href pass_href"  style="color: #242424;">Пароль</a><br><br>
                            <div class="ui input" id="old_password_input">
                                <input type="password" placeholder="старый пароль">
                            </div><br><br>
                            <div class="ui input" id="new_password_input">
                                <input type="password" placeholder="новый пароль">
                            </div>
                    </div>
                    <!--2 вкладка - настройка оповещений-->
                    <div class="ui tab segment" data-tab="second">
                        <p>получать уведомления:</p>
                        <div class="ui checked checkbox">
                            <input type="checkbox" id="by_mail">                    
                            <label> <i class="mail outline icon"></i> по почте</label>
                        </div>
                        <br><br>
                        <div class="ui checked checkbox">
                            <input type="checkbox" checked="" id="by_vk">
                            <label> <i class="vk outline icon"></i> через "вконтакте"</label>
                        </div> 
                        <br>
                    </div> 
                    
                    <a class="ui label" id="status_apply" style="width: 100%; text-align: center;">Настройки применены успешно</a>
                </div>
            </div>
         
            <div class="sixteen wide column" style="text-align:right !important;">
                <button class="ui violet button" id="btn_apply">применить</button>  
                <button class="ui violet basic button">отмена</button>
            </div>
        </div>     
    </body>
</html>


