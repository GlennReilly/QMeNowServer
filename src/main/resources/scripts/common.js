/**
 * Created by glenn on 27/02/16.
 */

/*$(document).ready(function(){

    if( $("#logoDiv").length ){
        //alert("page contain logo");
    }
    else{
        //alert("page doesn't contain logo");
    }

/!*    $("#numberOfButtonsRequired").change(function(){

        var numberOfButtonsRequired = $("#numberOfButtonsRequired").val();
        var divForButtonStyleTemplate = $("#divForBtnStyleTemplate");

        $("#divForAllButtonConfigs").empty();

        for(var i=0; i< numberOfButtonsRequired; i++)
        {
            var clone = (divForButtonStyleTemplate).clone().prop({ id: "styleForBtn" + i, name: "styleForBtn" + i});
            var rowLabel = clone.children(".BtnRowStyleTitle").html();
            clone.children(".BtnRowStyleTitle").html(rowLabel + (i+1));

            $("#divForAllButtonConfigs").append(clone);


        }
    });*!/


});*/

function checkLogoExists(logoFileName){
    if( $("#logoDiv").length && $("#logoImg").length){
        var imageBase = "/resources/images/";
        var logoURL = imageBase + logoFileName;
        //logoURL = "http://localhost:8080/resources/images/Frank's%20Futons_1_futon.jpg";
        //alert("logoURL:" + logoURL);

        var img = new Image();
/*        img.onload = function(){
            alert("successful loading");
        };*/
        img.onerror = function(){
            //alert("doesn't exist or error loading");
            $("#logoImg").attr("src",imageBase + "noLogo.png");
        };
        img.src = logoURL;
    }

}

