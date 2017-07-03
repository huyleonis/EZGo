/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.ezgo.Constant;

/**
 *
 * @author hp
 */
public enum ExternalLink {
    SAIGONTOURS_DOMESTIC("http://saigontours.asia/danh-muc-tour/du-lich-trong-nuoc.html"),
    SAIGONTOURS_ABROAD("http://saigontours.asia/danh-muc-tour/du-lich-ngoai-nuoc.html");
    
    private String url;

    private ExternalLink(String url) {
        this.url = url;
    }
    
    public String url() {
        return url;
    }
    
}
