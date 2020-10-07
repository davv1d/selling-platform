package com.pik.advertisement;

public class Photo {
    private String publicId;
    private String url;

    public Photo() {
    }

    public Photo(String publicId, String url) {
        this.publicId = publicId;
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "publicId='" + publicId + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
