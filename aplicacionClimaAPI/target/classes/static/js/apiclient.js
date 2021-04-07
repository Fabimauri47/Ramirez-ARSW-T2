apiclient = (function () {

    function getCasesByCity(city,callback){
        // Request with country
        $.ajax ({
            dataType: "json",
            url: " http://localhost:8080/api/ciudades/"+city,
            success: function(data){
                console.log(data)
                callback(data)
            },error:function (xhr, ajaxOptions, thrownError){
                if(xhr.status==404 || errorThrown == 'Not Found') {
                    callback();
                    console.log('There was a 404 error because the path doesnt exist.');
                }
            }
        });
    }

    return {
        getCasesByCity: getCasesByCity
    }
})();
