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
        <h2>Формирование условий для отбора адресатов</h2>
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
        <br><br>
        <input type="number" id="year_field" placeholder="год выпуска" min="1980" max="2030" step="1" value="2015"></input>
        <br><br>
        <span>возраст</span>
        <br><br>
        <span>от </span><input type="number" id="age_from_field" min="18" max="50" step="1" value="20"></input>
        <br><br>
        <span>до </span><input type="number" id="age_to_field" min="18" max="50" step="1" value="20"></input>
        <br><br>
        <textarea placeholder="профессия" id="job_field"></textarea>
        <br><br>
        <textarea placeholder="сообщение" id="message_field" cols="50" rows="5"></textarea>
        <br><br>
        <button id="btn_add">добавить</button>
    </body>
    <script>
        $(document).ready(function(){
           
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
            $('#btn_add').click(function(){
                 var criteria = new Object();
                 criteria.university = $('#select_univ').val();
                 criteria.university_faculty = $('#select_fac').val();
                 criteria.university_year = $('#year_field').val();
                 criteria.age_from = $('#age_from_field').val();
                 criteria.age_to = $('#age_to_field').val();
                 criteria.position = $('#job_field').val();
                 criteria.message = $('#message_field').val();
                 console.log(criteria);
                 
                $.ajax({
                    headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                    },
                    'type': 'POST',
                    'url': 'save_criteria',
                    'data': JSON.stringify(criteria),
                    'dataType': 'json',
                    'success': function(data){
                        console.log(data);
                    }
                });
            });
        });
    </script>
</html>
