var tokenGet = localStorage.getItem("tokenSet");  
$(document).ready(function(){
    //$('select option[value="1"]').attr("selected",true);
    //$('select option[value="2"]').attr("selected",true);
    setTimeout(function(){
        $.ajax({
            type: 'GET',
            url: 'http://localhost:8080/users',
            contentType: 'application/json',
            headers: {"Authorization": tokenGet},
            success: function(data){
                //alert(data[0].id +data[0].username + data[1].username);
                var i;
                for (i = 0; i < data.length; i++) {
                    if (data[i].role =="ROLE_ADMIN") {
                        document.getElementById("data1").innerHTML += "<tr style='color:red'><td >"+ data[i].id +"</td><td>"+ data[i].username 
                        + "</td><td>" + data[i].password+"</td><td>"+data[i].idJob
                        + "</td><td>" + data[i].role +"</td><td></td></tr>";
                    } else {
                        document.getElementById("data1").innerHTML += "<tr><td>"+ data[i].id +"</td><td>"+ data[i].username 
                        + "</td><td>" + data[i].password+"</td><td>"+data[i].idJob
                        + "</td><td>" + data[i].role +"</td><td><button id='UpdatedButton' onclick='GeUsernameFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='DeletedButton("+ data[i].id +")'>Deleted</button></td></tr>";
                    }
                }

                var numberOfPages = Math.ceil(data.length / 4);

                var str = "<ul class=\"pagination\"><li class=\"page-item\"><a class=\"page-link\" href=\"#\"  >Trước</a></li>";
                for (i = 1; i <= numberOfPages; i++) {
                    str += "<li class='page-item'><a class='page-link' href='#' onclick='loadPage("+ i +");chBackcolor();'>"+ i +"</a></li>";
                }
                str += "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">Sau</a></li></ul>";

                document.getElementById("data2").innerHTML = str;
            }
        });
    },200);
});

function chBackcolor() {
    document.body.style.background = red;
    //alert("hello");
}

function loadPage(i) {
    var index = i;
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/users?page='+(i-1) +'&size=7',
        contentType: 'application/json',
        headers: {"Authorization": tokenGet},
        success: function(data){
            var i;
            document.getElementById("data1").innerHTML = "";
            for (i = 0; i < data.length; i++) {
                if (data[i].role == "ROLE_ADMIN") {
                    document.getElementById("data1").innerHTML += "<tr style='color:red'><td>"+ data[i].id +"</td><td>"+ data[i].username 
                    + "</td><td>" + data[i].password+"</td><td>"+data[i].idJob
                    + "</td><td>" + data[i].role +"</td><td></td></tr>";
                } else {
                    document.getElementById("data1").innerHTML += "<tr><td>"+ data[i].id +"</td><td>"+ data[i].username 
                    + "</td><td>" + data[i].password+"</td><td>"+data[i].idJob
                    + "</td><td>" + data[i].role +"</td><td><button id='UpdatedButton' onclick='GeUsernameFunc("+ data[i].id +")'>Updated</button><button id='DeletedButton' onclick='DeletedButton("+ data[i].id +")'>Deleted</button></td></tr>";
                }
            }
        }
    });
};

function addUserFunc() {
    var us = document.getElementById("Username").value;
    var pd = document.getElementById("PasswordAdd").value;
    var pdConfirm = document.getElementById("PasswordComfirm").value;
    var listJob = JSON.parse("[" + $("#dataListJob").val() + "]");

    //alert([listJob])
    if (pd == pdConfirm && us != null && pd != null) {
        $.ajax(
            {    
                type: "POST",
                url: "http://localhost:8080/users",
                data: JSON.stringify({username: us, password: pd, idJob : listJob ,role: 'ROLE_STAFF'}),
                contentType: 'application/json',
                headers: {"Authorization": tokenGet},
                success: function(msg) {
                    alert("User: " +  us + " (msg: Add Success!)");
                    window.location.href = "User.html";
                }
            });
    } else {
        alert("Error: Xin Vui Lòng Thử lại Password!!");
    }
}

function DeletedButton(i) {
    if (confirm("Are you sure want to delete the jobId: " + i)) {
        var id = i;
        $.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/users/'+id,
            contentType: 'application/json',
            headers: {"Authorization": tokenGet},
            success: function(){
                window.location.href = "User.html";
            }
        });
    }
}

function GeUsernameFunc(id) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/users/'+id,
        contentType: 'application/json',
        headers: {"Authorization": tokenGet},
        success: function(obj){
            window.location.href = "UserAddAndUpdate.html?username="+obj.username+ "&id=" + obj.id+"&idjob=" + obj.idJob;
        }
    });
}

function UpdateUserFunc(id) {
    var us = document.getElementById("Username").value;
    var pd = document.getElementById("PasswordAdd").value;
    var pdConfirm = document.getElementById("PasswordComfirm").value;
    var listJob = JSON.parse("[" + $("#dataListJob").val() + "]");
    if (pd == pdConfirm && us != null && pd != null) {
        $.ajax(
            {    
                type: "POST",
                url: "http://localhost:8080/users/"+id,
                data: JSON.stringify({username: us, password: pd, idJob : listJob ,role: 'ROLE_STAFF'}),
                contentType: 'application/json',
                headers: {"Authorization": tokenGet},
                success: function(msg) {
                    alert("User: " +  us + " (msg: Update Success!)");
                    window.location.href = "User.html";
                }
            });
    } else {
        alert("Error: Xin Vui Lòng Thử lại Password!!");
    }
}


function hello() {
    alert("hello!");
}