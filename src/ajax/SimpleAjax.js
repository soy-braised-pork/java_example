
function hashMessage(){
    $.ajax({
        url:"...",
        type:"psot",
        dataType:"json",
        success:function (data){
            if (data.result=="success"){
                if (data.status){
                    $(".left-icon").append('<i class="xxx"></i>');
                }else {
                    $("#mymsg").remove();
                }
            }
        },
        error:function (data){

        }
    });
    setTimeout("hashMessage()",1000*100*100);
}
hashMessage();