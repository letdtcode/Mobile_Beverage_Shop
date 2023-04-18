package com.iostar.beverageshop.model;

import java.io.Serializable;

public class Size implements Serializable {
    private Long id;
    private String sizeName;
    private Integer pricePlus;

    public Size(Long id, String sizeName, Integer pricePlus) {
        id = id;
        this.sizeName = sizeName;
        this.pricePlus = pricePlus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getPricePlus() {
        return pricePlus;
    }

    public void setPricePlus(Integer pricePlus) {
        this.pricePlus = pricePlus;
    }
}
