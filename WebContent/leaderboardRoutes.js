$(document).ready(function () {


    // Api
    var apiPath = "http://localhost:8080/Gamify/api";
    //Form Login
    var username;
    var apiKey;
    var selectedApp;



    //Leaderboards
    //Gets
    var leaderboards = [{}];
    var leaderboardSearch;
    var leaderboardData;
    //Create
    var leaderboardID;
    var leaderboardName;
    var type;
    var description;
    //Change
    var leaderboardSearchChange;
    //Delete
    var leaderboardDelete;
    //Input
    var leaderboardInput;





    // Get all leaderboards
    $("#btnGetLeaderboards").click(function () {
        selectedApp = $("#txtAppforLeaderboard").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        console.log(selectedApp);
        var url = apiPath + "/apps/" + selectedApp + "/leaderboards?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
                //return an array of objects type leaderboard
                leaderboards = data;
            }
        })
    });

    //Get leaderboard
    $("#btnGetLeaderboard").click(function () {
        selectedApp = $("#txtAppforLeaderboard").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get leaderboardID from input
        leaderboardSearch = $("#txtLeaderboardIDSearch").val();
        var url = apiPath + "/apps/" + selectedApp + "/leaderboards/" + leaderboardSearch + "?apiKey=" + apiKey
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
                //return object type leaderboard
                leaderboardData = data;
            }
        })
    });


    //Create leaderboard
    $("#btnCreateLeaderboard").click(function () {
        selectedApp = $("#txtAppforLeaderboard").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");


        leaderboardID = $("#txtLeaderboardIDCreate").val();
        leaderboardName = $("#txtLeaderboardName").val();
        type = $("#txtLeaderboardType").val();
        description = $("#txtLeaderboardDescription").val();

        var form_data = {
            leaderboardID: leaderboardID,
            name: leaderboardName,
            type: type,
            description: description,
            apiKey: apiKey
        }

        console.log(form_data)
        var url = apiPath + "/apps/" + selectedApp + "/leaderboards?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {
                console.log("Created")

            }
        })
    });

    //Input leaderboard
    $("#btnInputLeaderboard").click(function () {
        selectedApp = $("#txtAppforLeaderboard").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get leaderboardID from input
        leaderboardInput = $("#txtLeaderboardIDSearchInput").val();

        name = $("#txtLeaderboardNameInput").val();
        score = $("#txtLeaderboardScore").val();

        var form_data = {
            name: name,
            score: score,
            apiKey: apiKey
        }

        console.log(selectedApp, form_data)
        var url = apiPath + "/apps/" + selectedApp + "/leaderboards/" + leaderboardInput + "?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {
                console.log(data);
                console.log("Inputed")
            }
        })
    });


    // Change leaderboard
    $("#btnChangeLeaderboard").click(function () {
        selectedApp = $("#txtAppforLeaderboard").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");

        leaderboardSearchChange = $("#txtLeaderboardIDSearchChange").val();
        console.log(leaderboardSearchChange)

        leaderboardName = $("#txtLeaderboardNameChange").val();
        type = $("#txtLeaderboardTypeChange").val();
        description = $("#txtLeaderboardDescriptionChange").val();

        var form_data = {
            name: leaderboardName,
            type: type,
            description: description,
            apiKey: apiKey
        }
        var url = apiPath + "/apps/" + selectedApp + "/leaderboards/" + leaderboardSearchChange + "?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "PUT",
            data: form_data,
            success: function (data) {
                console.log("changed")
            }
        })
    });

    //Delete  
    $("#btnDeleteLeaderboard").click(function () {
        selectedApp = $("#txtAppforLeaderboard").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");

        leaderboardDelete = $("#txtLeaderboardIDDelete").val();

        var url = apiPath + "/apps/" + selectedApp + "/leaderboards/" + leaderboardDelete + "?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "DELETE",
            success: function (data) {
                console.log(data);

            }
        })
    });


    // Reset Score
    $("#btnResetScoreLeaderboard").click(function () {
        selectedApp = $("#txtAppforLeaderboard").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get leaderboardID to reset
        leaderboardResetScore = $("#txtLeaderboardIDResetScore").val();

        var form_data = {
            apiKey: apiKey
        }

        console.log(selectedApp, form_data)
        var url = apiPath + "/apps/" + selectedApp + "/leaderboards/" + leaderboardResetScore + "/reset/score?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {
                console.log(data);
                console.log("Score Reset")
            }
        })
    });


    // Reset Total
    $("#btnResetTotalLeaderboard").click(function () {
        selectedApp = $("#txtAppforLeaderboard").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get leaderboardID to reset
        leaderboardResetTotal = $("#txtLeaderboardIDResetTotal").val();

        var form_data = {
            apiKey: apiKey
        }

        console.log(selectedApp, form_data)
        var url = apiPath + "/apps/" + selectedApp + "/leaderboards/" + leaderboardResetTotal + "/reset/total?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {
                console.log(data);
                console.log("Total Reset")
            }
        })
    });



});