function myFunction() {
    var us =  document.getElementById("name").value;
    var pd =  document.getElementById("pass").value;
    var token = '';
    $.ajax(
        {    
            type: "POST",
            url: "http://localhost:8080/users/login",
            data: JSON.stringify({username: us, password: pd}),
            contentType: 'application/json',
            success: function(resultData) {
                 token = resultData;
                 localStorage.setItem("tokenSet", token);  
                 alert("Token:" + token);
                //  $.ajax({
                //     type: 'PUT',
                //     url: 'http://localhost:8080/users',
                //     contentType: 'application/json',
                //     headers: {"Authorization": token},
                //     success: function(data){
                //         alert("working!!");
                        window.location.href = "User.html?Un=" + us;
                        //window.location.replace("./User.html");
                        //$("#data").html("<tr><td>1</td><td>sterben</td><td>ákdja</td><td>@</td><td>admin</td><td><button>Updated</button><button>Deleted</button></td></tr>");
                        //document.getElementById("data").innerHTML = "<tr><td>1</td><td>sterben</td><td>ákdja</td><td>@</td><td>admin</td><td><button>Updated</button><button>Deleted</button></td></tr>" ;
                //    }
                //});
            }
         }
     )
};



