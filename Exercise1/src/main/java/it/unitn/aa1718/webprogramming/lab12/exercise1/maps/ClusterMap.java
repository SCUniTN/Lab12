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
public class ClusterMap extends GoogleMap {

    @Override
    public StringBuilder generateHtml() {
        StringBuilder html = new StringBuilder();

        html.append(generateHeader());

        html.append("<style type='text/css'>\n");
        html.append(generateCommonStyle());       
        html.append("</style>\n");

        html.append(generateCommonScript());
        html.append("<script type='text/javascript'>\n");
        html.append("var markers=[];\n");
        html.append("var showMarketManager = false;\n");
        html.append("var mgr = null;\n");
        html.append("var mc = null;\n");

        html.append("function MarkerControl(markerControlDiv, map) {\n");

        html.append("markerControlDiv.style.padding = '5px 0px';\n");

        html.append("var controlUI = document.createElement('div');\n");
        html.append("controlUI.style.backgroundColor = 'white';\n");
        html.append("controlUI.style.borderStyle = 'solid';\n");
        html.append("controlUI.style.borderWidth = '1px';\n");
        html.append("controlUI.style.cursor = 'pointer';\n");
        html.append("controlUI.style.textAlign = 'center';\n");
        html.append("controlUI.title = 'Click to show/hide all markers';\n");
        html.append("markerControlDiv.appendChild(controlUI);\n");

        html.append("var controlText = document.createElement('div');\n");
        html.append("controlText.style.fontFamily = 'Arial,sans-serif';\n");
        html.append("controlText.style.fontSize = '13px';\n");
        html.append("controlText.style.padding = '2px 6px';\n");
        html.append("controlText.innerHTML = '<strong>Markers</strong>';\n");
        html.append("controlUI.appendChild(controlText);\n");

        html.append("google.maps.event.addDomListener(controlUI, 'click', function() {\n");

        html.append("if (showMarketManager) {\n");
        html.append("mgr.addMarkers(markers, 0, 15);\n");
        html.append("mc.addMarkers(markers);\n");
        html.append("} else {\n");
        html.append("mgr.clearMarkers();\n");
        html.append("mc.clearMarkers();\n");
        html.append("}\n");
        html.append("mgr.refresh();\n");
        html.append("showMarketManager = !showMarketManager;\n");
        html.append("});\n");
        html.append("}\n");

        html.append("var doc, infoWindow=null;\n");
        html.append("var swGps = new google.maps.LatLng(").append(swPoint[0]).append(", ").append(swPoint[1]).append(");\n");
        html.append("var neGps = new google.maps.LatLng(").append(nePoint[0]).append(", ").append(nePoint[1]).append(");\n");
        html.append("var options={zoom:").append(zoom).append(",\n");
        html.append("center: new google.maps.LatLngBounds(swGps, neGps).getCenter(), \n");
        html.append("mapTypeId:google.maps.MapTypeId.").append(typeMap).append("};\n");

        html.append("function init(){\n");
        html.append("var map = new google.maps.Map(document.getElementById('map'), options);\n");

        html.append("var markerControlDiv = document.createElement('div');\n");
        html.append("var markerControl = new MarkerControl(markerControlDiv, map);\n");

        html.append("markerControlDiv.index = 1;\n");
        html.append("map.controls[google.maps.ControlPosition.TOP_RIGHT].push(markerControlDiv);\n");

        html.append("infoWindow = new google.maps.InfoWindow({\n");
        html.append("content: '...'});\n");
        html.append("var lista = ").append(resources).append(";\n");
        html.append("google.maps.event.addListenerOnce(map, 'bounds_changed', function(){\n");
        html.append("for(var i = 0 ; i < lista.length; i++){\n");
        html.append("var lat = lista[i].lat;\n");
        html.append("var lng = lista[i].lng;\n");
        html.append("var latlng = new google.maps.LatLng(lat, lng);\n");

        html.append(generateInfoWindow());

        html.append("var marker = new google.maps.Marker({\n");
        html.append("position: latlng,\n");
        html.append("title: lista[i].nome,\n");
        html.append("html: content});\n");
        html.append("google.maps.event.addListener(marker, 'click', function(){\n");
        html.append("infoWindow.setContent(this.html);\n");
        html.append("infoWindow.open(map, this);});\n");
        html.append("markers.push(marker);}\n");

        html.append("mgr = new MarkerManager(map, {trackMarkers: true, maxZoom: 15});\n");
        html.append("google.maps.event.addListener(mgr, 'loaded', function() {\n");
        html.append("mgr.addMarkers(markers, 0, 15);\n");
        html.append("mgr.refresh();\n");
        html.append("});\n");
        html.append("mc = new MarkerClusterer(map, markers, {maxZoom: 50});\n");

        html.append("});\n");
        html.append("}\n");

        html.append(generateFooter());

        return html;
    }

    @Override
    public boolean isClusterView() {
        return true;
    }
}
