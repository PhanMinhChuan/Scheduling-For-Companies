$(document).ready(function(){
    //$('select option[value="1"]').attr("selected",true);
    //$('select option[value="2"]').attr("selected",true);
    //$("div.id_100 select").val("1").change();
    $(".mul-select").select2({
        placeholder: "select id", //placeholder
        tags: true,
        tokenSeparators: ['/',',',';'," "],
    });
    $.ajax({
        type: 'PATCH',
        url: 'http://localhost:8080/jobs',
        contentType: 'application/json',
        headers: {"Authorization": tokenGet},
        success: function(data){
            var i;
            document.getElementById("dataListJob").innerHTML = "";
            for (i = 0; i < data.length; i++) {
                document.getElementById("dataListJob").innerHTML += "<option value=\""+ data[i].id +"\">"+  data[i].content +" (" +data[i].id + ")"+"</option>";
            }
            //$('select option[value="1"]').attr("selected",true).change();
        }
    });
});

function showList(idJob) {
    //alert(idJob);
    setTimeout(function(){
        $.ajax({
            type: 'PATCH',
            url: 'http://localhost:8080/jobs',
            contentType: 'application/json',
            headers: {"Authorization": tokenGet},
            success: function(data){
                var i;
                document.getElementById("dataListJob").innerHTML = "";
                for (i = 0; i < data.length; i++) {
                    document.getElementById("dataListJob").innerHTML += "<option value=\""+ data[i].id +"\">"+  data[i].content +" (" +data[i].id + ")"+"</option>";
                }
                for (i = 0; i < idJob.length; i++) {
                    $("select option[value='" + idJob[i] + "']").attr("selected",true).change();
                }
                
            }
        });
    },200);
}