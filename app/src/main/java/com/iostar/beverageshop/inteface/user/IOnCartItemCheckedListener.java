package com.iostar.beverageshop.inteface.user;

import com.iostar.beverageshop.model.CartItem;

import java.math.BigDecimal;

public interface IOnCartItemCheckedListener {
    public void onChecked(CartItem cartItem, String pathImage);
    public void onUnchecked(CartItem cartItem, String pathImage);
}
