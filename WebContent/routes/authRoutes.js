$(document).ready(function () {


    // Api
    var apiPath = "http://localhost:8080/Gamify/api";
    var apiKey;
    //Form Login
    var username;
    var password;


    // Login 
    $("#btnLogin").click(function () {
        username = $("#txtUsername").val();
        password = $("#txtPassword").val();
        sessionStorage.setItem("apiKey", "default");
        sessionStorage.setItem("username", username);


        var form_data = {
            userID: username,
            password: password
        }
        var url = apiPath + "/auth"
        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {
                var token = data
               // var token = data.code
                sessionStorage.setItem("apiKey", token);
                apiKey = sessionStorage.getItem("apiKey");
                //console.log(data); 
                // return an apiKey
                console.log("Logged")
                window.open("./home.html","_self");
            }
        })
    });

    //Logout
    $("#btnLogout").click(function () {
        apiKey = "";
        username = "";
        sessionStorage.removeItem("apiKey");
        sessionStorage.removeItem("username");
        console.log(sessionStorage.getItem("apiKey"));
        console.log(sessionStorage.getItem("username"));
        window.open("./login.html","_self");
    });


});