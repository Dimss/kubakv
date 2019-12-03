$( document ).ready(function() {


    $("#red-square").on("click", function () {

        $.ajax({
            url: "/square/red",
            success: function(result){
                console.log(result);
            }
        });
    });

    $("#green-square").on("click", function () {
        $.ajax({
            url: "/square/green",
            success: function(result){
                console.log(result);
            }
        });
    });

    $("#blue-square").on("click", function () {
        $.ajax({
            url: "/square/blue",
            success: function(result){
                console.log(result);
            }
        });
    });

    setInterval(()=>{
        $.ajax({
            url: "/consumer",
            success: function(result){
                $("#kafka-image-container").empty();
                result.data.map((e, idx) =>{
                    let template = getViewTemplate();
                    template = template.replace(/consumer-name/g, e.id)
                    $("#kafka-image-container").append(template)
                    $("#"+e.id+"-img").attr("src", "data:image/png;base64, "+ e.kafkaStateImage);
                    $("#"+e.id+"-refresh-button").on("click",function(){
                            let id = $(this).data("consumer-id");
                            $.ajax({
                                url: "/consumer/"+id,
                                type: 'DELETE',
                                success: function(result){
                                    console.log(result);
                                }
                            });
                        });
                    console.log(template);
                });
            }
        });
    },1000)

    getViewTemplate = function() {
        return '<div class="col-md-4"><div class="card"><h5 class="card-title"><div>consumer-name</div></h5><img id="consumer-name-img" class="card-img-top" ><button data-consumer-id="consumer-name" id="consumer-name-refresh-button" type="button" class="btn btn-primary">refresh state</button></div></div>'
    }
});

