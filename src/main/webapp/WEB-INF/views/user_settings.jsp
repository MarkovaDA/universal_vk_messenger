<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </head>
    <script>
        $(document).ready(function(){
                $('#status_apply').hide();
            $('#btn_apply').click(function(){                 
                  $.ajax({
                    'type': 'POST',
                    'url': 'change_signature',
                    'data': {
                        'signature': $('#text_signature').val()
                    },
                    'success': function(data){
                        $('#status_apply').fadeIn(100);
                    }
                });
            });        
        });
    </script>
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
                            href="<c:url value="/user"/>">В личный кабинет                    
                         </a>
                    </div>                   
                </div>
            </div>
            <div class="ten wide column" style="text-align: left !important;">                                 
                <div class="row" style="height: 300px;">
                    <p>Изменение подписи:</p>
                    <div class="ui input"  style="width: 100%;">
                        <input type="text" id="text_signature" value="${signature}" placeholder="введите подпись">
                    </div>
                    <br><br>
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
