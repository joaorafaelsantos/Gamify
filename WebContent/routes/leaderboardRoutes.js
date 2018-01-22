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
        selectedApp = $("#selectApp").val();
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

                if ($("#leaderboardsNumber") != undefined) {
                    $("#leaderboardsNumber").text(leaderboards.length);
                }
    
                if ($("#tbodyLeaderboards") != undefined) {
                    $("#tbodyLeaderboards").empty();
                    let content;
                    let contentLb;
                    
                    for (let i = 0; i < leaderboards.length; i++) {
                        const element = leaderboards[i];
                        content += "<tr><td>" + element.leaderboardID + "</td>" + "<td>" + element.appID + "</td>" + "<td>" + element.name + "</td>"  + "<td>" + element.type + "</td>" + "<td>" + element.description + "</td>" + "<td>" + element.inputs.length + "</td>" + "</tr>"
                        contentLb+='<option value=' + element.leaderboardID + '>' + element.leaderboardID + '</option>';
                    }
                    $("#tbodyLeaderboards").append(content);
                    if(contentLb==null ){
                        contentLb="";
                    }
                    $("#selectLb").html(contentLb)
                    $("#selectLbInput").html(contentLb)
                   
                    
                }
            }
        })
    });

    //Get leaderboard
    $("#btnGetLeaderboard").click(function () {
        selectedApp = $("#selectApp").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        var content;
        var contentInput;
        leaderboardSearch = $("#txtLeaderboardIDSearch").val();
        var url = apiPath + "/apps/" + selectedApp + "/leaderboards/" + leaderboardSearch + "?apiKey=" + apiKey
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
                //return object type leaderboard
                leaderboardData = data;

                content += "<tr><td>" + leaderboardData.leaderboardID + "</td>" + "<td>" + leaderboardData.appID + "</td>" + "<td>" + leaderboardData.name + "</td>"  + "<td>" + leaderboardData.type + "</td>" + "<td>" + leaderboardData.description + "</td>" + "<td>" + leaderboardData.inputs.length + "</td>" + "</tr>"
                $("#tbodyLeaderboard").html(content);
                console.log(leaderboardData.inputs.length);
                for (let i = 0; i < leaderboardData.inputs.length; i++) {
                    contentInput += "<tr><td>" + leaderboardData.inputs[i].name + "</td>" + "<td>" + leaderboardData.inputs[i].score  + "</td>"  + "</tr>";
                }
                
                $("#tinputLeaderboard").html(contentInput);
            }
        })
    });


    //Create leaderboard
    $("#btnCreateLeaderboard").click(function () {
        selectedApp = $("#selectApplb").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");


        //leaderboardID = $("#txtLeaderboardIDCreate").val();
        leaderboardName = $("#txtLeaderboardName").val();
        type = $("#txtLeaderboardType").val();
        description = $("#txtLeaderboardDescription").val();

        var form_data = {
           
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
        selectedApp = $("#selectApp").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get leaderboardID from input
        leaderboardInput = $("#selectLbInput").val();

        name = $("#txtLeaderboardNameInput").val();
        score = $("#txtLeaderboardScore").val();

        var form_data = {
            name: name,
            score: score,
            apiKey: apiKey
        }
        console.log(name);
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
        selectedApp = $("#selectApp").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");

        leaderboardSearchChange = $("#selectLb").val();
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
        selectedApp = $("#selectApp").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");

        leaderboardDelete = $("#selectLb").val();

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
        selectedApp = $("#selectApp").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get leaderboardID to reset
        leaderboardResetScore = $("#selectLb").val();

        var form_data = {
            apiKey: apiKey
        }

        console.log(selectedApp,leaderboardResetScore, form_data)
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
        selectedApp = $("#selectApp").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get leaderboardID to reset
        leaderboardResetTotal = $("#selectLb").val();

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