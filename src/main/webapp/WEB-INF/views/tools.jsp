<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tools</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    </head>
    <body>
        <h1>Tools page</h1>
        <br>
        <select id="select_city" style="width: 200px">
            <c:forEach items="${cities}" var="city"> 
                <option value="${city.id}">${city.title}</option>
            </c:forEach>
        </select>
        <br><br>
        <select id="select_univ" style="width: 200px">
        </select>
        <br><br>
        <select id="select_fac" style="width: 200px">
        </select>
        <input type="hidden" value="${accessToken}" id="token_field">
        <!-- добавить еще параметр года выпуска и возраста,профессия (работа)
        разобраться, почему факультеты не прогружаются
        -->
        
    </body>
    <script>
        $(document).ready(function(){
            var criteria = new Object();
            $("#select_city").click(function(){
                console.log("city_id="+$(this).val());
                $('#select_univ').empty();
                $.get("api/get_universities?token="+$('#token_field').val()+"&city_id="+$(this).val(), 
                    function(data){
                        var university;
                        for(var i=0; i < data.length; i++){
                            university = data[i];
                            $('#select_univ').append($('<option>', {
                                value: university.id,
                                text: university.title
                            }));
                        }
                    }
                );
            });
            $('#select_univ').click(function(){
                console.log("university_id="+$(this).val()); //выводим значение университета(?) не работает
                $('#select_fac').empty();
                $.get("api/get_faculties?token="+$('#token_field').val()+"&univ_id="+$(this).val(), 
                    function(data){
                        var faculty;
                        for(var i=0; i < data.length; i++){
                            faculty = data[i];
                            $('#select_fac').append($('<option>', {
                                value: faculty.id,
                                text: faculty.title
                            }));
                        }
                    }
                );
            });
        });
    </script>
</html>
