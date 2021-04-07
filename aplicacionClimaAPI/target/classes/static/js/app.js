let app = (() => {
    let client = apiclient;
    var mapPaises = (funciones) => {
        document.getElementById("name").innerText = funciones.name;
        document.getElementById("visibility").innerText = funciones.visibility;
        document.getElementById("dt").innerText = funciones.dt;
        document.getElementById("weather").innerText = funciones.weather.description;
        document.getElementById("wind").innerText = funciones.wind.speed;
        plotMarkers(funciones.coord);
    }
    var initMap = () => {
        map = new google.maps.Map(document.getElementById("map"), {
            zoom: 2,
            center: {lat: 35.717, lng: 139.731},
        });
    }

    function plotMarkers(m) {
        console.log(m)
        markers = [];
        bounds = new google.maps.LatLngBounds();
        console.log(m.latitude, m.longitude);
        var position = new google.maps.LatLng(m.lat, m.lon);
        console.log(position);
        markers.push(
            new google.maps.Marker({
                position: position,
                map: map,
                animation: google.maps.Animation.DROP
            })
        );
        bounds.extend(position);
        map.fitBounds(bounds);
        map.setZoom(4);
    }

    function init() {
        initMap();
    }

    return {
        init: init,
        consultarCiudad(ciudad) {
            client.getCasesByCity(ciudad, mapPaises);
        }
    }
})();
