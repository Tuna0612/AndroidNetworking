
package com.sean.anw.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("secret")
    @Expose
    private String secret;
    @SerializedName("server")
    @Expose
    private String server;
    @SerializedName("farm")
    @Expose
    private Integer farm;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("ispublic")
    @Expose
    private Integer ispublic;
    @SerializedName("isfriend")
    @Expose
    private Integer isfriend;
    @SerializedName("isfamily")
    @Expose
    private Integer isfamily;
    @SerializedName("ownername")
    @Expose
    private String ownername;
    @SerializedName("dateadded")
    @Expose
    private String dateadded;
    @SerializedName("license")
    @Expose
    private Integer license;
    @SerializedName("tags")
    @Expose
    private String tags;
    @SerializedName("media")
    @Expose
    private String media;
    @SerializedName("media_status")
    @Expose
    private String mediaStatus;
    @SerializedName("url_q")
    @Expose
    private String urlQ;
    @SerializedName("height_q")
    @Expose
    private String heightQ;
    @SerializedName("width_q")
    @Expose
    private String widthQ;
    @SerializedName("url_n")
    @Expose
    private String urlN;
    @SerializedName("height_n")
    @Expose
    private String heightN;
    @SerializedName("width_n")
    @Expose
    private String widthN;
    @SerializedName("url_c")
    @Expose
    private String urlC;
    @SerializedName("height_c")
    @Expose
    private String heightC;
    @SerializedName("width_c")
    @Expose
    private String widthC;
    @SerializedName("url_l")
    @Expose
    private String urlL;
    @SerializedName("height_l")
    @Expose
    private String heightL;
    @SerializedName("width_l")
    @Expose
    private String widthL;
    @SerializedName("url_h")
    @Expose
    private String urlH;
    @SerializedName("height_h")
    @Expose
    private String heightH;
    @SerializedName("width_h")
    @Expose
    private String widthH;
    @SerializedName("url_o")
    @Expose
    private String urlO;
    @SerializedName("height_o")
    @Expose
    private String heightO;
    @SerializedName("width_o")
    @Expose
    private String widthO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public Integer getFarm() {
        return farm;
    }

    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIspublic() {
        return ispublic;
    }

    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    public Integer getIsfriend() {
        return isfriend;
    }

    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    public Integer getIsfamily() {
        return isfamily;
    }

    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getDateadded() {
        return dateadded;
    }

    public void setDateadded(String dateadded) {
        this.dateadded = dateadded;
    }

    public Integer getLicense() {
        return license;
    }

    public void setLicense(Integer license) {
        this.license = license;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMediaStatus() {
        return mediaStatus;
    }

    public void setMediaStatus(String mediaStatus) {
        this.mediaStatus = mediaStatus;
    }

    public String getUrlQ() {
        return urlQ;
    }

    public void setUrlQ(String urlQ) {
        this.urlQ = urlQ;
    }

    public String getHeightQ() {
        return heightQ;
    }

    public void setHeightQ(String heightQ) {
        this.heightQ = heightQ;
    }

    public String getWidthQ() {
        return widthQ;
    }

    public void setWidthQ(String widthQ) {
        this.widthQ = widthQ;
    }

    public String getUrlN() {
        return urlN;
    }

    public void setUrlN(String urlN) {
        this.urlN = urlN;
    }

    public String getHeightN() {
        return heightN;
    }

    public void setHeightN(String heightN) {
        this.heightN = heightN;
    }

    public String getWidthN() {
        return widthN;
    }

    public void setWidthN(String widthN) {
        this.widthN = widthN;
    }

    public String getUrlC() {
        return urlC;
    }

    public void setUrlC(String urlC) {
        this.urlC = urlC;
    }

    public String getHeightC() {
        return heightC;
    }

    public void setHeightC(String heightC) {
        this.heightC = heightC;
    }

    public String getWidthC() {
        return widthC;
    }

    public void setWidthC(String widthC) {
        this.widthC = widthC;
    }

    public String getUrlL() {
        return urlL;
    }

    public void setUrlL(String urlL) {
        this.urlL = urlL;
    }

    public String getHeightL() {
        return heightL;
    }

    public void setHeightL(String heightL) {
        this.heightL = heightL;
    }

    public String getWidthL() {
        return widthL;
    }

    public void setWidthL(String widthL) {
        this.widthL = widthL;
    }

    public String getUrlH() {
        return urlH;
    }

    public void setUrlH(String urlH) {
        this.urlH = urlH;
    }

    public String getHeightH() {
        return heightH;
    }

    public void setHeightH(String heightH) {
        this.heightH = heightH;
    }

    public String getWidthH() {
        return widthH;
    }

    public void setWidthH(String widthH) {
        this.widthH = widthH;
    }

    public String getUrlO() {
        return urlO;
    }

    public void setUrlO(String urlO) {
        this.urlO = urlO;
    }

    public String getHeightO() {
        return heightO;
    }

    public void setHeightO(String heightO) {
        this.heightO = heightO;
    }

    public String getWidthO() {
        return widthO;
    }

    public void setWidthO(String widthO) {
        this.widthO = widthO;
    }

}
