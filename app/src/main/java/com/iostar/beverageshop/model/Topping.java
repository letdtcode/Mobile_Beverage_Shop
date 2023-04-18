package com.iostar.beverageshop.model;

import java.io.Serializable;

public class Topping implements Serializable {
    private Long Id;
    private String toppingName;
    private Integer toppingPrice;

    public Topping(Long id, String toppingName, Integer toppingPrice) {
        this.Id = id;
        this.toppingName = toppingName;
        this.toppingPrice = toppingPrice;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public Integer getToppingPrice() {
        return toppingPrice;
    }

    public void setToppingPrice(Integer toppingPrice) {
        this.toppingPrice = toppingPrice;
    }
}
