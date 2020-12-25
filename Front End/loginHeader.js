var tokenGet = localStorage.getItem("tokenSet");  

function getUsername() {
    setTimeout(function(){
        // var queryString = window.location.search;
        // const urlParams = new URLSearchParams(queryString);
        // var Un = urlParams.get('Un');
        // if (Un != null) {
        //     document.getElementById("Un").innerHTML += "<img src=\"plugins/images/users/varun.jpg\" alt=\"user-img\" width=\"36\" class=\"img-circle\"><span class=\"text-white font-medium\">"+ Un +"</span></a>";
        // } else {
        //     //document.getElementById("buttonMe").innerHTML = "";
        //     document.getElementById("Un").innerHTML += "<img src=\"plugins/images/users/varun.jpg\" alt=\"user-img\" width=\"36\" class=\"img-circle\"><span class=\"text-white font-medium\">Guest</span></a>";
        // }
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/jwt',
            contentType: 'application/json',
            headers: {"Authorization": tokenGet},
            success: function(id){
                //alert(id);
                //$('select option[value="1"]').attr("selected",true).change();
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/users/'+id,
                    contentType: 'application/json',
                    headers: {"Authorization": tokenGet},
                    success: function(obj){
                        //alert(obj.username);
                        if (obj.username != null) {
                            if (obj.username == "admin") {
                                document.getElementById("Un").innerHTML += "<img src=\"plugins/images/users/varun.jpg\" alt=\"user-img\" width=\"36\" class=\"img-circle\"><span class=\"text-white font-medium\">"+ obj.username +"</span></a>";
                            } else {
                                document.getElementById("Un").innerHTML += "<img src=\"plugins/images/users/1.jpg\" alt=\"user-img\" width=\"36\" class=\"img-circle\"><span class=\"text-white font-medium\">"+ obj.username +"</span></a>";
                            }
                            
                            document.getElementById("Un").innerHTML += "<a href=\"#\" class=\"btn LogoutButton \" id='logOutBtn' onclick='logoutFunc()'>Logout</a>"
                        } else {
                            //document.getElementById("buttonMe").innerHTML = "";
                            document.getElementById("Un").innerHTML += "<span class=\"text-white font-medium\">Guest</span></a>";
                        }
                    }
                });
            }
        });
    },300);
}

function logoutFunc() {
    window.localStorage.clear();
    window.location.href = "Login.html"
}