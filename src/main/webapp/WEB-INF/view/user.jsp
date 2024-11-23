<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
    <script src = "http://code.jquery.com/jquery-latest.min.js"></script>
    <script>
        $(document).on("click", "#ajax-button", function () {
                // alert("Alert");
                $.get("/oris_cw_16_11/ajax/weather?city=" + $('#city').val(), function (response) {
                    $("#ajax-response").text(response)
                })
            }
        )
    </script>

</head>
<body>
<form>
    <label>Введите город</label>
    <input type="text" id="city" name="city" required>
    <button type="button" id="ajax-button">Узнать погоду</button>
</form>
<div id="ajax-response">

</div>
</body>
</html>
