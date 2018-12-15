package jp.co.realiine.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PersonDto {
    private String appId;
    private String appKey;
    private Integer id;
    private Integer actionIine;
    private Integer fashionIine;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public String createLogString() {
        List<String> itemList = new ArrayList<String>();
        itemList.add("id:" + String.valueOf(id));
        itemList.add("actionIine:" + String.valueOf(actionIine));
        itemList.add("fashionIine:" + String.valueOf(fashionIine));
        //itemList.add("imageUrl:" + imageUrl);
        itemList.add("latitude:" + String.valueOf(latitude));
        itemList.add("longitude:" + String.valueOf(longitude));
        return "[ " + String.join(", ",itemList) + " ]";
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getActionIine() {
        return actionIine;
    }

    public void setActionIine(Integer actionIine) {
        this.actionIine = actionIine;
    }

    public Integer getFashionIine() {
        return fashionIine;
    }

    public void setFashionIine(Integer fashionIine) {
        this.fashionIine = fashionIine;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    */

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
