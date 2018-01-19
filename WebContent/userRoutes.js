$(document).ready(function () {

    // Api
    var apiPath = "http://localhost:8080/Gamify/api";
    //Form Login
    var username;
    var apiKey;

    //Apps
    //Gets
    var users = [{}];
    var userSearch;
    var userData;
    //Create
    var userID;
    var password;
    var email;
    //Change
    var userSearchChange;
    //Delete
    var userDelete;










    //Get all Users
    $("#btnGetUsers").click(function () {
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        var url = apiPath + "/users" + "?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
                //return an array of objects type User
                users = data;
            }
        })
    });

    //Get User
    $("#btnGetUser").click(function () {
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get userID from input
        userSearch = $("#txtUserIDSearch").val();
        var url = apiPath + "/users/" + userSearch + "/?apiKey=" + apiKey
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
                //return object type App
                userData = data;
            }
        })
    });

    //Create User
    $("#btnCreateUser").click(function () {
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        userID = $("#txtUserID").val();
        userPassword = $("#txtUserPassword").val();
        email = $("#txtEmail").val();

        var form_data = {
            userID: userID,
            password: userPassword,
            email: email
        }
        var url = apiPath + "/users" + "?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {

                console.log(data);

                console.log("Created")

            }
        })
    });

    // Change User
    $("#btnChangeUser").click(function () {
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        userSearchChange = $("#txtUserIDSearchChange").val();
        console.log(userSearchChange)

        userPassword = $("#txtUserPasswordChange").val();
        email = $("#txtUserEmailChange").val();


        var form_data = {
            password: userPassword,
            email: email,
            apiKey: apiKey
        }
        var url = apiPath + "/users/" + userSearchChange + "/?apiKey=" + apiKey
        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {
                console.log(data)
            }
        })
    });

    //Delete  
    $("#btnDeleteUser").click(function () {
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        var url = apiPath + "/users/" + username + "?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "DELETE",
            success: function (data) {
                console.log(data);
            }
        });
    });


});