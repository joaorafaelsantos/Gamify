$(document).ready(function () {
    // Api
    let apiPath = "http://localhost:8080/Gamify/api";
    let apiKey = sessionStorage.getItem("apiKey");

    let errors;

    var url = apiPath + "/errors?apiKey=" + apiKey;
    $.ajax({
        url: url,
        contenType:application/json,
        type: "GET",
        success: function (data) {
            console.log(data)
            errors = data;

            if ($("#errorsNumber") != undefined) {
                $("#errorsNumber").text(errors.length);
            }

            if ($("#tbodyErrors") != undefined) {
                $("#tbodyErrors").empty();
                let content;
                for (let i = 0; i < errors.length; i++) {
                    const element = errors[i];
                    content += "<tr><td>"+ element.errorID + "</td>" + "<td>"+ element.message + "</td>" + "<td>"+ element.link + "</td>" + "<td>"+ element.http_status + "</td>"+ "</tr>"
                }
                $("#tbodyErrors").append(content);
            }
        }
    })



});