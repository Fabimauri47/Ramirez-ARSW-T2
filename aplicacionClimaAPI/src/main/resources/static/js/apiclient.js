apiclient = (function () {
    let url = " http://localhost:8080/api/ciudades/";
    return {
        getCasesByCity: function (ciudad, callback) {
            $.getJSON(url + ciudad, (data) => {
                callback(JSON.parse(data));
            }, null)
        },
    }
})();
