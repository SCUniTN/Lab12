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
 * @since 1.0.0.180517
 * @version 1.0.0.180518
 */
public abstract class GoogleMap implements InfoMapConstants {
    public static final String START_WITH_ICONS_PIN = "pin_";
    public static final String START_WITH_ICONS_PIN_LEGEND = "legend_pin_";
    public static final String START_WITH_ICONS_COLS_LEGEND = "legend_cols_";

    private static final Integer BALOON_WIDTH = 300;
    private static final Integer BALOON_HEIGHT = 300;
    private static final Integer IMG_BALOON_WIDTH = 100;
    private static final Integer IMG_BALOON_HEIGHT = 100;
    
    private String title;
    protected Integer divWidth;
    protected Integer divHeight;
    protected Integer zoom;
    protected String resources;
    protected double[] swPoint;
    protected double[] nePoint;
    protected double[] centerPoint;
    protected String typeMap;
    
    public void setMapOptions(String title, Integer typeMap, Integer zoom) {
        this.title = title;
        if (typeMap == HYBRID_ID) {
            this.typeMap = HYBRID_NAME;
        } else if (typeMap == ROADMAP_ID) {
            this.typeMap = ROADMAP_NAME;
        }
        this.zoom = zoom;
    }

    public void setCenterPoint(double[] centerPoint) {
        this.centerPoint = centerPoint;
    }

    public void setLatLngBounds(double[] swPoint, double[] nePoint) {
        this.swPoint = swPoint;
        this.nePoint = nePoint;
    }

    public void setDivDimension(Integer divWidth, Integer divHeight) {
        this.divHeight = divHeight;
        this.divWidth = divWidth;
    }

    public void setResources(String resouces) {
        this.resources = resouces;
    }

    public abstract StringBuilder generateHtml();

    public boolean isClusterView() {
        return false;
    }
    
    public StringBuilder generateHeader() {
        StringBuilder header = new StringBuilder();
        header.append("<html>\n");
        header.append("\t<head>");
        header.append("\t\t<title>").append(title).append("</title>\n");
        header.append("\t\t<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />\n");

        //meta for IE
        header.append("\t\t<meta http-equiv='X-UA-Compatible' content='IE=8' />\n");

        return header;
    }
    
    public StringBuilder generateCommonStyle() {
        StringBuilder style = new StringBuilder();
        style.append("\t\t\thtml{\n");
        style.append("\t\t\t\theight: 100%\n");
        style.append("\t\t\t}\n");
        style.append("\t\t\tbody{\n");
        style.append("\t\t\t\theight: 100%;\n");
        style.append("\t\t\t\tmargin: 0px;\n");
        style.append("\t\t\t\tpadding: 0px;\n");
        style.append("\t\t\t}\n");
        style.append("\t\t\t#map{\n");
        style.append("\t\t\t\theight: ").append(divHeight).append("%;\n");
        style.append("\t\t\t\twidth: ").append(divWidth).append("%;\n");
        style.append("\t\t\t}\n");
        style.append("\t\t\t#info{\n");
        style.append("\t\t\t\theight: ").append(BALOON_HEIGHT).append("px;\n");
        style.append("\t\t\t\twidth: ").append(BALOON_WIDTH).append("px;\n");
        style.append("\t\t\t}\n");
        style.append("\t\t\t#info img{\n");
        style.append("\t\t\t\tfloat: left;\n");
        style.append("\t\t\t\theight:").append(IMG_BALOON_HEIGHT).append("px;\n");
        style.append("\t\t\t\twidth:").append(IMG_BALOON_WIDTH).append("px;\n");
        style.append("\t\t\t}\n");
        style.append("\t\t\t#info h2{\n");
        style.append("\t\t\t\tmargin-top: 0px;\n");
        style.append("\t\t\t}\n");
        return style;
    }
    
    public StringBuilder generateCommonScript() {
        StringBuilder script = new StringBuilder();
        script.append("\t\t<script type='text/javascript' src='http://maps.google.com/maps/api/js?sensor=false&language=it'></script>\n");
        script.append("\t\t<script type='text/javascript' src='http://google-maps-utility-library-v3.googlecode.com/svn/tags/markermanager/1.0/src/markermanager.js'></script>\n");

        //cluster library
        if (isClusterView()) {
            script.append("\t\t<script type='text/javascript' src='").append("../../files/markerclusterer_compiled.js'></script>\n");

        }

        return script;
    }
    
    public StringBuilder generateFooter() {
        StringBuilder footer = new StringBuilder();
        footer.append("\t\t</script>\n");
        footer.append("\t</head>\n");
        footer.append("\t<body onload='init()'>");
        footer.append("\t\t<div id='map'></div>");
        footer.append("\t</body>\n");
        footer.append("</html>");

        return footer;
    }
    
    /**
     * common WindowInfo code for the different maps
     *
     * @return the html code for the infoWindow
     */
    public StringBuilder generateInfoWindow() {
        StringBuilder infoWindow = new StringBuilder();

        infoWindow.append("\t\t\t\t\t\tvar name = lista[i].").append(NAME).append(";\n");
        infoWindow.append("\t\t\t\t\t\tvar web = lista[i].").append(WEB).append(";\n");
        infoWindow.append("\t\t\t\t\t\tvar street = lista[i].").append(STREET).append(";\n");
        infoWindow.append("\t\t\t\t\t\tvar streetNr = lista[i].").append(STREET_NUMBER).append(";\n");
        infoWindow.append("\t\t\t\t\t\tvar zip = lista[i].").append(ZIP_CODE).append(";\n");
        infoWindow.append("\t\t\t\t\t\tvar city = lista[i].").append(CITY).append(";\n");
        infoWindow.append("\t\t\t\t\t\tvar province = lista[i].").append(PROVINCE).append(";\n");
        infoWindow.append("\n");

        infoWindow.append("\t\t\t\t\t\tvar title;").append("\n");
        infoWindow.append("\t\t\t\t\t\tif (name) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\ttitle = name;").append("\n");
        infoWindow.append("\t\t\t\t\t\t} else {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\ttitle = 'Ristorante senza nome';").append("\n");
        infoWindow.append("\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\n");
        infoWindow.append("\t\t\t\t\t\tvar internet;").append("\n");
        infoWindow.append("\t\t\t\t\t\tif (web) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tinternet  = '<ul>';").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tif (web) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tif (web.substring(0, 7) === \"http://\") {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\t\tweb = web.substring(7);").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tif (web) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\t\tinternet += '<li>Indirizzo web: <a href=\"http://' + web + '\" target=\"_blank\">' + web + '</a></li>';").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tinternet += '</ul>';").append("\n");
        infoWindow.append("\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\n");
        infoWindow.append("\t\t\t\t\t\tvar streetAddress = \"\";").append("\n");
        infoWindow.append("\t\t\t\t\t\tvar cityAddress = \"\";").append("\n");
        infoWindow.append("\t\t\t\t\t\tif (street || streetNr || zip || city || province) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tif (street && streetNr) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tstreetAddress += street + ', ' + streetNr;").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t} else if (street) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tstreetAddress += street;").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t} else if (streetNr) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tstreetAddress += streetNr;").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\n");
        infoWindow.append("\t\t\t\t\t\t\tif (zip && city && province) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tcityAddress += zip + ' ' + city + ' (' + province + ')';").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t} else if (zip && city) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tcityAddress += zip + ' ' + city;").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t} else if (zip && province) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tcityAddress += zip + ' (' + province + ')';").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t} else if (city && province) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tcityAddress += city + ' (' + province + ')';").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t} else if (zip) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tcityAddress += zip;").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t} else if (city) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tcityAddress += city;").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t} else if (province) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tcityAddress += province;").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\n");
        infoWindow.append("\t\t\t\t\t\tvar content = '<div id=\"info\">';").append("\n");
        infoWindow.append("\t\t\t\t\t\tcontent    += '<h2>' + title + '</h2>';").append("\n");
        infoWindow.append("\t\t\t\t\t\tif (internet) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tcontent += internet;").append("\n");
        infoWindow.append("\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\t\t\t\t\t\tif (streetAddress || cityAddress) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tcontent += '<p id=\"address\">';").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tif (streetAddress) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tcontent += streetAddress + '<br />';").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tif (cityAddress) {").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t\tcontent += cityAddress;").append("\n");
        infoWindow.append("\t\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\t\t\t\t\t\t\tcontent += '</p>';").append("\n");
        infoWindow.append("\t\t\t\t\t\t}").append("\n");
        infoWindow.append("\n");
        infoWindow.append("\t\t\t\t\t\tcontent    += '</div>';").append("\n");

        return infoWindow;
    }

    public String getCssName(String key) {
        return key.replace(" ", "").toLowerCase();
    }
}
