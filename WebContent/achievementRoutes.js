$(document).ready(function () {


    // Api
    var apiPath = "http://localhost:8080/Gamify/api";
    //Form Login
    var username;
    var apiKey;
    var selectedApp;



    //Achievements
    //Gets
    var achievements = [{}];
    var achievementSearch;
    var achievementData;
    //Create
    var achievementID;
    var achievementName;
    var structure;
    var reward;
    var goal;
    var type;
    var description;
    //Change
    var achievementSearchChange;
    //Delete
    var achievementDelete;
    //Input
    var achievementInput;





    // Get all achievements
    $("#btnGetAchievements").click(function () {
        selectedApp = $("#txtAppforAchievement").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        console.log(selectedApp);
        var url = apiPath + "/apps/" + selectedApp + "/achievements?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
                //return an array of objects type achievement
                achievements = data;
            }
        })
    });

    //Get achievement
    $("#btnGetAchievement").click(function () {
        selectedApp = $("#txtAppforAchievement").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get achievementID from input
        achievementSearch = $("#txtAchievementIDSearch").val();
        var url = apiPath + "/apps/" + selectedApp + "/achievements/" + achievementSearch + "?apiKey=" + apiKey
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
                //return object type achievement
                achievementData = data;
            }
        })
    });

    //Create achievement
    $("#btnCreateAchievement").click(function () {
        selectedApp = $("#txtAppforAchievement").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");


        achievementID = $("#txtAchievementIDCreate").val();
        achievementName = $("#txtAchievementName").val();
        reward = $("#txtAchievementReward").val();
        goal = $("#txtAchievementGoal").val();
        structure = $("#txtAchievementStructure").val();
        type = $("#txtAchievementType").val();
        description = $("#txtAchievementDescription").val();

        var form_data = {
            achievementID: achievementID,
            name: achievementName,
            reward: reward,
            goal: goal,
            type: type,
            structure: structure,
            description: description,
            apiKey: apiKey
        }

        console.log(form_data)
        var url = apiPath + "/apps/" + selectedApp + "/achievements?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {
                console.log("Created")

            }
        })
    });


    //Input achievement
    $("#btnInputAchievement").click(function () {
        selectedApp = $("#txtAppforAchievement").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get achievementID from input
        achievementInput = $("#txtAchievementIDSearchInput").val();

        name = $("#txtAchievementNameInput").val();
        score = $("#txtAchievementScore").val();

        var form_data = {
            name: name,
            score: score,
            apiKey: apiKey
        }

        console.log(selectedApp, form_data)
        var url = apiPath + "/apps/" + selectedApp + "/achievements/" + achievementInput + "?apiKey=" + apiKey;
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


    // Change achievement 
    $("#btnChangeAchievement").click(function () {
        selectedApp = $("#txtAppforAchievement").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");

        achievementSearchChange = $("#txtAchievementIDSearchChange").val();
        console.log(achievementSearchChange)

        achievementName = $("#txtAchievementNameChange").val();
        type = $("#txtAchievementTypeChange").val();
        reward = $("#txtAchievementRewardChange").val();
        goal = $("#txtAchievementGoalChange").val();
        description = $("#txtAchievementDescriptionChange").val();

        var form_data = {
            name: achievementName,
            reward: reward,
            goal: goal,
            type: type,
            description: description,
            apiKey: apiKey
        }
        var url = apiPath + "/apps/" + selectedApp + "/achievements/" + achievementSearchChange + "?apiKey=" + apiKey;
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
    $("#btnDeleteAchievement").click(function () {
        selectedApp = $("#txtAppforAchievement").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");

        achievementDelete = $("#txtAchievementIDDelete").val();

        var url = apiPath + "/apps/" + selectedApp + "/achievements/" + achievementDelete + "?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "DELETE",
            success: function (data) {
                console.log(data);

            }
        })
    });



});