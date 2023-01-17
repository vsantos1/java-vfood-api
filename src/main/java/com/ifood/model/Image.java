package com.ifood.model;

import javax.persistence.*;

@Embeddable
public class Image {
    private String url;

    @Column(name = "image_description")
    private String description;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

