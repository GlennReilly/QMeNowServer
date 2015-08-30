/**
 * Created by glenn on 15/08/15.
 */




$(document).ready(function(){

    $("#numberOfButtonsRequired").change(function(){

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
    });


});


