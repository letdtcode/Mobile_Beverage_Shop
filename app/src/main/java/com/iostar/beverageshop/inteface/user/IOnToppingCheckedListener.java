package com.iostar.beverageshop.inteface.user;

import com.iostar.beverageshop.model.Topping;

import java.util.List;

public interface IOnToppingCheckedListener {
    public void onChecked(String toppingName);
    public void onUnchecked(String toppingName);
}
