/**
 * Created by glenn on 15/08/15.
 */




$(document).ready(function(){

    $("#numberOfButtonsRequired").change(function(){
        //alert('running');
        var numberOfButtonsRequired = $("#numberOfButtonsRequired").val();
        var divForButtonStyleTemplate = $("#divForBtnStyleTemplate");

        $("#divForAllButtonConfigs").empty();

        for(i=0; i< numberOfButtonsRequired; i++)
        {
            var clone = (divForButtonStyleTemplate).clone().prop({ id: "styleForBtn" + i, name: "styleForBtn" + i});
            $("#divForAllButtonConfigs").append(clone);

/*            $("#divForAllButtonConfigs").append('<div id="btnDiv' + i + '" class="divForEachButtonConfig" > div'+ i
                + 'Select button style:      <select id="selectBtnStyle' + i + '" ></select><br/>'

                + 'Select button link<br/>'
                + ' </div>');*/
        }
    });


});


