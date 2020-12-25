$(document).ready(function(){
    //$('select option[value="1"]').attr("selected",true);
    //$('select option[value="2"]').attr("selected",true);
    //$("div.id_100 select").val("1").change();
    
    $(".mul-select").select2({
        placeholder: "select country", //placeholder
        tags: true,
        tokenSeparators: ['/',',',';'," "],
    });
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
                    document.getElementById("dataListUser").innerHTML += "<option value=\""+ data[i].id +"\">"+  data[i].username +" (" +data[i].id + ")"+"</option>";
                }
            }
            //$('select option[value="1"]').attr("selected",true).change();
        }
    });
});


function showJobList(idUser) {
    setTimeout(function(){
        $.ajax({
            type: 'PATCH',
            url: 'http://localhost:8080/users',
            contentType: 'application/json',
            headers: {"Authorization": tokenGet},
            success: function(data){
                var i;
                document.getElementById("dataListUser").innerHTML = "";
                for (i = 0; i < data.length; i++) {
                    document.getElementById("dataListUser").innerHTML += "<option value=\""+ data[i].id +"\">"+  data[i].username +" (" +data[i].id + ")"+"</option>";
                }
                for (i = 0; i < idUser.length; i++) {
                    $("select option[value='" + idUser[i] + "']").attr("selected",true).change();
                }
                
            }
        });
    },200);
}