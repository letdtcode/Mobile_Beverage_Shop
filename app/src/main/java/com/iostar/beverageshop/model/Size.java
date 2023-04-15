package com.iostar.beverageshop.model;

import java.io.Serializable;

public class Size implements Serializable {
    private Long Id;
    private String sizeName;
    private Integer pricePlus;

    public Size(Long id, String sizeName, Integer pricePlus) {
        Id = id;
        this.sizeName = sizeName;
        this.pricePlus = pricePlus;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
