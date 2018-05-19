/*
 * AA 2017-2018
 * Introduction to Web Programming
 * Lab 12 - WebServices
 * UniTN
 */
package it.unitn.aa1718.webprogramming.lab12.exercise1.maps;

/**
 *
 * @author Stefano Chirico &lt;s dot chirico at unitn dot it&gt;
 * @since 1.0.0.180518
 * @version 1.0.0.180518
 */
public class SinglePinMap extends GoogleMap {

    @Override
    public StringBuilder generateHtml() {
        StringBuilder html = new StringBuilder();

        html.append(generateHeader());

        html.append("\t\t<style type='text/css'>\n");
        html.append(generateCommonStyle());

        html.append("\t\t</style>\n");

        html.append(generateCommonScript());
        html.append("\t\t<script type='text/javascript'>\n");
        html.append("\t\t\tgoogle.maps.visualRefresh=true;\n");
        html.append("\t\t\tvar markers=[];\n");
        html.append("\t\t\tvar showMarketManager = false;\n");
        html.append("\t\t\tvar mgr = null;\n");
        
        html.append("\t\t\tfunction MarkerControl(markerControlDiv, map) {\n");

        html.append("\t\t\t\tmarkerControlDiv.style.padding = '5px 0px';\n");
        html.append("\t\t\t\t\n");
        html.append("\t\t\t\tvar controlUI = document.createElement('div');\n");
        html.append("\t\t\t\tcontrolUI.style.backgroundColor = 'white';\n");
        html.append("\t\t\t\tcontrolUI.style.borderStyle = 'solid';\n");
        html.append("\t\t\t\tcontrolUI.style.borderWidth = '1px';\n");
        html.append("\t\t\t\tcontrolUI.style.cursor = 'pointer';\n");
        html.append("\t\t\t\tcontrolUI.style.textAlign = 'center';\n");
        html.append("\t\t\t\tcontrolUI.title = 'Click to show/hide all markers';\n");
        html.append("\t\t\t\t\n");
        html.append("\t\t\t\tmarkerControlDiv.appendChild(controlUI);\n");
        html.append("\t\t\t\t\n");
        html.append("\t\t\t\tvar controlText = document.createElement('div');\n");
        html.append("\t\t\t\tcontrolText.style.fontFamily = 'Arial,sans-serif';\n");
        html.append("\t\t\t\tcontrolText.style.fontSize = '13px';\n");
        html.append("\t\t\t\tcontrolText.style.padding = '2px 6px';\n");
        html.append("\t\t\t\tcontrolText.innerHTML = '<strong>Markers</strong>';\n");
        html.append("\t\t\t\t\n");
        html.append("\t\t\t\tcontrolUI.appendChild(controlText);\n");
        html.append("\t\t\t\t\n");
        html.append("\t\t\t\tgoogle.maps.event.addDomListener(controlUI, 'click', function() {\n");
        html.append("\t\t\t\t\tif (showMarketManager) {\n");
        html.append("\t\t\t\t\t\tmgr.addMarkers(markers, 0, 15);\n");
        html.append("\t\t\t\t\t} else {\n");
        html.append("\t\t\t\t\t\tmgr.clearMarkers();\n");
        html.append("\t\t\t\t\t}\n");
        html.append("\t\t\t\t\tmgr.refresh();\n");
        html.append("\t\t\t\t\tshowMarketManager = !showMarketManager;\n");
        html.append("\t\t\t\t});\n");
        html.append("\t\t\t}\n");
        html.append("\t\t\t\n");
        html.append("\t\t\tvar doc, infoWindow=null;\n");
        html.append("\t\t\tvar swGps = new google.maps.LatLng(").append(swPoint[0]).append(", ").append(swPoint[1]).append(");\n");
        html.append("\t\t\tvar neGps = new google.maps.LatLng(").append(nePoint[0]).append(", ").append(nePoint[1]).append(");\n");        
        html.append("\t\t\tvar WORLD_DIM = { height: 256, width: 256 };\n");
        html.append("\t\t\tvar ZOOM_MAX = 21;\n");
        html.append("\t\t\t\n");
        html.append("\t\t\tfunction latRad(lat) {\n");
        html.append("\t\t\t\tvar sin = Math.sin(lat * Math.PI / 100);\n");
        html.append("\t\t\t\tvar radX2 = Math.log((1 + sin) / (1 - sin)) / 2;\n");
        html.append("\t\t\t\treturn Math.max(Math.min(radX2, Math.PI), -Math.PI) / 2;\n");
        html.append("\t\t\t};\n");
        html.append("\t\t\tfunction zoom(mapPx, worldPx, fraction) {\n");
        html.append("\t\t\treturn Math.floor(Math.log(mapPx / worldPx / fraction) / Math.LN2);\n");
        html.append("\t\t\t};\n");
        html.append("\t\t\tfunction init(){\n");
        html.append("\t\t\t\tvar divElement= document.getElementById('map');\n");
        html.append("\t\t\t\tvar latFraction = (latRad(neGps.lat()) - latRad(swGps.lat())) / Math.PI;\n");
        html.append("\t\t\t\tvar lngFraction = (latRad(neGps.lng()) - latRad(swGps.lng())) / Math.PI;\n");
	html.append("\t\t\t\tif (lngFraction < 0) {\n");
	html.append("\t\t\t\t\tlngFraction = -1*lngFraction;\n");
	html.append("\t\t\t\t}\n");
        html.append("\t\t\t\tvar latZoom = zoom(divElement.offsetHeight, WORLD_DIM.height, latFraction);\n");
        html.append("\t\t\t\tvar lngZoom = zoom(divElement.offsetWidth, WORLD_DIM.width, lngFraction);\n");
        html.append("\t\t\t\tvar calculatedZoom = Math.min(latZoom, lngZoom, ZOOM_MAX);\n");

        html.append("\t\t\t\tvar options={\n");
        html.append("\t\t\t\t\tzoom: calculatedZoom").append(",\n");
        if (centerPoint != null) {
            html.append("\t\t\t\t\tcenter: new google.maps.LatLng(").append(centerPoint[0]).append(", ").append(centerPoint[1]).append("),\n");
        } else {
            html.append("\t\t\t\t\tcenter: new google.maps.LatLngBounds(swGps, neGps).getCenter(),\n");
        }
        html.append("\t\t\t\t\tmapTypeId:google.maps.MapTypeId.").append(typeMap).append("\n");
        html.append("\t\t\t\t};\n");
        html.append("\t\t\t\tvar map = new google.maps.Map(divElement, options);\n");
        html.append("\t\t\t\t\n");

        html.append("\t\t\t\tvar markerControlDiv = document.createElement('div');\n");
        html.append("\t\t\t\tvar markerControl = new MarkerControl(markerControlDiv, map);\n");
        html.append("\t\t\t\tmarkerControlDiv.index = 1;\n");
        html.append("\t\t\t\tmap.controls[google.maps.ControlPosition.TOP_RIGHT].push(markerControlDiv);\n");

        html.append("\t\t\t\tinfoWindow = new google.maps.InfoWindow({content: '...'});\n");
        html.append("\t\t\t\t\n");
        html.append("\t\t\t\tvar lista = ").append(resources).append(";\n");
        html.append("\t\t\t\t\n");
        html.append("\t\t\t\tgoogle.maps.event.addListenerOnce(map, 'bounds_changed', function(){\n");
        html.append("\t\t\t\t\tfor(var i = 0 ; i < lista.length; i++){\n");
        html.append("\t\t\t\t\t\tvar lat = lista[i].lat;\n");
        html.append("\t\t\t\t\t\tvar lng = lista[i].lng;\n");
        html.append("\t\t\t\t\t\tvar latlng = new google.maps.LatLng(lat, lng);\n");
        html.append(generateInfoWindow());
        html.append("\t\t\t\t\t\tvar marker = new google.maps.Marker({\n");
        html.append("\t\t\t\t\t\t\tposition: latlng,\n");
        html.append("\t\t\t\t\t\t\ttitle: lista[i].nome,\n");
        html.append("\t\t\t\t\t\t\t//icon: iconPath,\n");
        html.append("\t\t\t\t\t\t\thtml: content\n");
        html.append("\t\t\t\t\t\t});\n");
        html.append("\t\t\t\t\t\tgoogle.maps.event.addListener(marker, 'click', function(){\n");
        html.append("\t\t\t\t\t\t\tinfoWindow.setContent(this.html);\n");
        html.append("\t\t\t\t\t\t\tinfoWindow.open(map, this);\n");
        html.append("\t\t\t\t\t\t});\n");
        html.append("\t\t\t\t\t\t");
        html.append("\t\t\t\t\t\tmarkers.push(marker);\n");
        html.append("\t\t\t\t\t}\n");
        html.append("\t\t\t\t\t\n");
        html.append("\t\t\t\t\tmgr = new MarkerManager(map, {trackMarkers: true, maxZoom: 15});\n");
        html.append("\t\t\t\t\tgoogle.maps.event.addListener(mgr, 'loaded', function() {\n");
        html.append("\t\t\t\t\t\tmgr.addMarkers(markers, 0, 15);\n");
        html.append("\t\t\t\t\t\tmgr.refresh();\n");
        html.append("\t\t\t\t\t});\n");
        html.append("\t\t\t\t});\n");
        html.append("\t\t\t}\n");

        html.append(generateFooter());

        return html;
    }

    @Override
    public boolean isClusterView() {
        return true;
    }

}
