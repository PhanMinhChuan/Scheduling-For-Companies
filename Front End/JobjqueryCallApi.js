var tokenGet = localStorage.getItem("tokenSet");

$(document).ready(function(){
    setTimeout(function(){
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/jobs',
            contentType: 'application/json',
            headers: {"Authorization": tokenGet},
            success: function(data){
                var i;
                document.getElementById("data3").innerHTML = "";
                for (i = 0; i < data.length; i++) {
                    if (data[i].status == 'DA_HOAN_THANH') {
                        document.getElementById("data3").innerHTML += "<tr style='color:deeppink'><td >"+ data[i].id +"</td><td>"+ data[i].content 
                        + "</td><td>" + data[i].location+"</td><td>"+data[i].idUser
                        + "</td><td>"+ data[i].deadline +"</td><td>" + data[i].comment +"</td><td>"+ data[i].status +"</td><td>"
                        + "<button id='UpdatedButton' onclick='GetJobFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='removeJob("+ data[i].id +")'>Deleted</button></td></tr>";
                    } else {
                        document.getElementById("data3").innerHTML += "<tr><td >"+ data[i].id +"</td><td>"+ data[i].content 
                        + "</td><td>" + data[i].location+"</td><td>"+data[i].idUser
                        + "</td><td>"+ data[i].deadline +"</td><td>" + data[i].comment +"</td><td>"+ data[i].status +"</td><td>"
                        + "<button id='UpdatedButton' onclick='GetJobFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='removeJob("+ data[i].id +")'>Deleted</button></td></tr>";
                    }
                }

                document.getElementById("data4").innerHTML = "";

                var numberOfPages = Math.ceil(data.length / 4);
                //alert(Math.ceil(1.25));
                var str = "<ul class=\"pagination\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\">Trước</a></li>";
                for (i = 1; i <= numberOfPages; i++) {
                    str += "<li class='page-item'><a class='page-link' href='#' onclick='loadPage("+ i +");'>"+ i +"</a></li>";
                }
                str += "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">Sau</a></li></ul>";

                document.getElementById("data4").innerHTML = str;
            }
        });
    },200);
});

function loadPage(i) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/jobs?page='+(i-1) +'&size=7',
        contentType: 'application/json',
        headers: {"Authorization": tokenGet},
        success: function(data){
            var i;
            document.getElementById("data3").innerHTML = "";
            for (i = 0; i < data.length; i++) {
                if (data[i].status == 'DA_HOAN_THANH') {
                    document.getElementById("data3").innerHTML += "<tr style='color:deeppink'><td >"+ data[i].id +"</td><td>"+ data[i].content 
                    + "</td><td>" + data[i].location+"</td><td>"+data[i].idUser
                    + "</td><td>"+ data[i].deadline +"</td><td>" + data[i].comment +"</td><td>"+ data[i].status +"</td><td>"
                    + "<button id='UpdatedButton' onclick='GetJobFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='removeJob("+ data[i].id +")'>Deleted</button></td></tr>";
                } else {
                    document.getElementById("data3").innerHTML += "<tr><td >"+ data[i].id +"</td><td>"+ data[i].content 
                    + "</td><td>" + data[i].location+"</td><td>"+data[i].idUser
                    + "</td><td>"+ data[i].deadline +"</td><td>" + data[i].comment +"</td><td>"+ data[i].status +"</td><td>"
                    + "<button id='UpdatedButton' onclick='GetJobFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='removeJob("+ data[i].id +")'>Deleted</button></td></tr>";
                }
            }
        }
    });
};

function showJobNotDone() {
    $.ajax({
        type: 'PUT',
        url: 'http://localhost:8080/jobs',
        contentType: 'application/json',
        headers: {"Authorization": tokenGet},
        success: function(data){
            var i;

            document.getElementById("data3").innerHTML = "";
            for (i = 0; i < data.length; i++) {
                document.getElementById("data3").innerHTML += "<tr><td >"+ data[i].id +"</td><td>"+ data[i].content 
                + "</td><td>" + data[i].location+"</td><td>"+data[i].idUser
                + "</td><td>"+ data[i].deadline +"</td><td>" + data[i].comment +"</td><td>"+ data[i].status +"</td><td>"
                + "<button id='UpdatedButton' onclick='GetJobFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='removeJob("+ data[i].id +")'>Deleted</button></td></tr>";
            }

            document.getElementById("data4").innerHTML = "";
            var numberOfPages = Math.floor(data.length / 4);
            var str = "<ul class=\"pagination\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\">Trước</a></li>";
            for (i = 1; i <= numberOfPages; i++) {
                str += "<li class='page-item'><a class='page-link' href='#' onclick='loadPage("+ i +");'>"+ i +"</a></li>";
            }
            str += "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">Sau</a></li></ul>";

            document.getElementById("data4").innerHTML = str;
        }
        
    });
}


function findJobByIdUser (){
        $.ajax({
            type: 'PATCH',
            url: 'http://localhost:8080/users',
            contentType: 'application/json',
            headers: {"Authorization": tokenGet},
            success: function(data){
                var i;
                document.getElementById("dataListUser").innerHTML = "";
                for (i = 0; i < data.length; i++) {
                    if (data[i].role != "ROLE_ADMIN") {
                        document.getElementById("dataListUser").innerHTML += "<li><a class=\"dropdown-item\" id=\"dropDownCssMe\" onclick=\"valueFunc("+data[i].id+")\">"+data[i].username+" ("+ data[i].id +")" + "</a></li>";
                    }
                }
            }
        });
}

function valueFunc(id) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/jobs/' +id,
        contentType: 'application/json',
        headers: {"Authorization": tokenGet},
        success: function(data){
            var i;
            document.getElementById("data3").innerHTML = "";
            for (i = 0; i < data.length; i++) {
                if (data[i].status == 'DA_HOAN_THANH') {
                    document.getElementById("data3").innerHTML += "<tr style='color:deeppink'><td >"+ data[i].id +"</td><td>"+ data[i].content 
                    + "</td><td>" + data[i].location+"</td><td>"+data[i].idUser
                    + "</td><td>"+ data[i].deadline +"</td><td>" + data[i].comment +"</td><td>"+ data[i].status +"</td><td>"
                    + "<button id='UpdatedButton' onclick='GetJobFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='removeJob("+ data[i].id +")'>Deleted</button></td></tr>";
                } else {
                    document.getElementById("data3").innerHTML += "<tr><td >"+ data[i].id +"</td><td>"+ data[i].content 
                    + "</td><td>" + data[i].location+"</td><td>"+data[i].idUser
                    + "</td><td>"+ data[i].deadline +"</td><td>" + data[i].comment +"</td><td>"+ data[i].status +"</td><td>"
                    + "<button id='UpdatedButton' onclick='GetJobFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='removeJob("+ data[i].id +")'>Deleted</button></td></tr>";
                }
            }

            document.getElementById("data4").innerHTML = "";
            var numberOfPages = Math.floor(data.length / 4);
            var str = "<ul class=\"pagination\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\">Trước</a></li>";
            for (i = 1; i <= numberOfPages; i++) {
                str += "<li class='page-item'><a class='page-link' href='#' onclick='loadPage("+ i +");'>"+ i +"</a></li>";
            }
            str += "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">Sau</a></li></ul>";

            document.getElementById("data4").innerHTML = str;
        }
    });
}

function removeJob(i) {
    if (confirm("Are you sure want to delete the jobId: " + i)) {
        var id = i;
            $.ajax({
                type: 'DELETE',
                url: 'http://localhost:8080/jobs/'+id,
                contentType: 'application/json',
                headers: {"Authorization": tokenGet},
                success: function(){
                    alert("Deleted!!");
                    window.location.href = "Job.html";
                }
            });
    }
}

function addJobFunc() {
    var ctent = document.getElementById("exampleInputContent").value;
    var ltion = document.getElementById("exampleInputLocation").value;
    var listIdUser = JSON.parse("[" + $("#dataListUser").val() + "]");
    var timeSet = document.getElementById("deadline").value;
    var status = $("#dataStatus").val();
    //alert("Hello" + timeSet);
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/jobs',
        data: JSON.stringify({content: ctent, location: ltion, idUser : listIdUser ,deadline: timeSet, comment : [], status: status}),
        contentType: 'application/json',
        headers: {"Authorization": tokenGet},
        success: function(){
            alert("Add Working!");
            window.location.href = "Job.html";
        }
    });
}


function GetJobFunc(id) {
    $.ajax({
        type: 'PATCH',
        url: 'http://localhost:8080/jobs/' + id,
        contentType: 'application/json',
        headers: {"Authorization": tokenGet},
        success: function(jobObj){
            window.location.href = "JobUpdate.html?content="+jobObj.content+ "&id=" + jobObj.location
                                + "&idUser=" + jobObj.idUser +"&deadline=" + jobObj.deadline+"&comment=" + jobObj.comment
                                +"&status=" + jobObj.status +"&asd=" + jobObj.id;
        }
    });
}

function updateJobFunc(asd){
    var ctent = document.getElementById("exampleInputContent").value;
    var ltion = document.getElementById("exampleInputLocation").value;
    var listIdUser = JSON.parse("[" + $("#dataListUser").val() + "]");
    var timeSet = document.getElementById("deadline").value;
    var status = $("#dataStatus").val();
    //alert(status);
    if (ctent != null && ltion!= null && listIdUser!= null && timeSet!= null && status!= null) {
        $.ajax(
            {    
                type: "POST",
                url: "http://localhost:8080/jobs/"+asd,
                data: JSON.stringify({content: ctent, location: ltion, idUser : listIdUser ,deadline: timeSet, comment : [], status: status}),
                contentType: 'application/json',
                headers: {"Authorization": tokenGet},
                success: function(msg) {
                    alert("(msg: Update Success!)");
                    window.location.href = "Job.html";
                }
            });
    } else {
        alert("Error: Xin Vui Lòng Thử lại Data!!");
    }
}