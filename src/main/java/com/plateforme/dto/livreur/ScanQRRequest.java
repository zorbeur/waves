package com.plateforme.dto.livreur;

public class ScanQRRequest {
    private String token;
    private String geolocalisationJson;

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getGeolocalisationJson() { return geolocalisationJson; }
    public void setGeolocalisationJson(String geolocalisationJson) { this.geolocalisationJson = geolocalisationJson; }
}
