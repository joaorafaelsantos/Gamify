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
        selectedApp = $("#selectApp").val();
        
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

                if ($("#achievementsNumber") != undefined) {
                    $("#achievementsNumber").text(achievements.length);
                }
    
                if ($("#tbodyAchievements") != undefined) {
                    $("#tbodyAchievements").empty();
                    let content;
                    let contentAch;
                    
                    for (let i = 0; i < achievements.length; i++) {
                        const element = achievements[i];
                        content += "<tr><td>" + element.achievementID + "</td>" + "<td>" + element.appID + "</td>" + "<td>" + element.name + "</td>" + "<td>" + element.structure + "</td>" +"<td>" + element.reward + "</td>" +"<td>" + element.goal + "</td>" + "<td>" + element.type + "</td>" + "<td>" + element.description + "</td>" + "<td>" + element.inputs.length + "</td>" + "</tr>"
                        contentAch+='<option value=' + element.achievementID + '>' + element.achievementID + '</option>';
                    }
                    $("#tbodyAchievements").append(content);
                    if(contentAch==null ){
                        contentAch="";
                    }
                    $("#selectAch").html(contentAch)
                    $("#selectAchInput").html(contentAch)
                   
                    
                }
            }
        })
    });

    //Get achievement
    $("#btnGetAchievement").click(function () {
        selectedApp = $("#selectApp").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        var done;
        var content;
        var contentInput;
        achievementSearch = $("#txtAchievementIDSearch").val();
        var url = apiPath + "/apps/" + selectedApp + "/achievements/" + achievementSearch + "?apiKey=" + apiKey
        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                console.log(data);
                //return object type achievement
                achievementData = data;
                content += "<tr><td>" + achievementData.achievementID + "</td>" + "<td>" + achievementData.appID + "</td>" + "<td>" + achievementData.name + "</td>" + "<td>" + achievementData.structure + "</td>" +"<td>" + achievementData.reward + "</td>" +"<td>" + achievementData.goal + "</td>" + "<td>" + achievementData.type + "</td>" + "<td>" + achievementData.description + "</td>" + "<td>" + achievementData.inputs.length + "</td>" + "</tr>";
                $("#tbodyAchievement").html(content);
                console.log(achievementData.inputs.length);
                for (let i = 0; i < achievementData.inputs.length; i++) {
                    if (achievementData.inputs[i].score >= achievementData.goal){
                        done=achievementData.inputs[i].score+ " -Achievement Done"   ;
                        
                    }else{
                        done = achievementData.inputs[i].score;
                    }
                    
                    contentInput += "<tr><td>" + achievementData.inputs[i].name + "</td>" + "<td>" + done  + "</td>"  + "</tr>";
                }
                
                $("#tinputAchievement").html(contentInput);
            } 
            
        })
    });

    //Create achievement
    $("#btnCreateAchievement").click(function () {
        selectedApp = $("#selectAppach").val();
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        

        
        achievementName = $("#txtAchievementName").val();
        reward = $("#txtAchievementReward").val();
        goal = $("#txtAchievementGoal").val();
        structure = $("#AchievementStructure").val();
        type = $("#txtAchievementType").val();
        description = $("#txtAchievementDescription").val();

        var form_data = {
            name: achievementName,
            reward: reward,
            goal: goal,
            type: type,
            structure: structure,
            description: description,
            apiKey: apiKey
        }

        console.log(form_data,selectedApp)

        var url = apiPath + "/apps/" + selectedApp + "/achievements?apiKey=" + apiKey;

        $.ajax({
            url: url,
            type: "POST",
            data: form_data,
            success: function (data) {
                
                console.log(data);

            }
        })
    });


    //Input achievement
    $("#btnInputAchievement").click(function () {
        selectedApp = $("#selectApp").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");
        //Get achievementID from input
        achievementInput = $("#selectAchInput").val();

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
        selectedApp = $("#selectApp").val();
        
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");

        achievementSearchChange = $("#selectAch").val();
        console.log(selectedApp,achievementSearchChange)
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
        selectedApp = $("#selectApp").val()
        username = sessionStorage.getItem("username");
        apiKey = sessionStorage.getItem("apiKey");

        achievementDelete = $("#selectAch").val();
        var form_data = {
            
            apiKey: apiKey
        }
        var url = apiPath + "/apps/" + selectedApp + "/achievements/" + achievementDelete + "?apiKey=" + apiKey;
        $.ajax({
            url: url,
            type: "DELETE",
            data: form_data,
            success: function (data) {
                console.log(data);

            }
        })
    });



});