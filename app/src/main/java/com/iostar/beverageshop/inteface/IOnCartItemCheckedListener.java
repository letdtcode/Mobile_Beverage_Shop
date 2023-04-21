package com.iostar.beverageshop.inteface;

import java.math.BigDecimal;

public interface IOnCartItemCheckedListener {
    public void onChecked(Long cardItemId, BigDecimal totalItem);
    public void onUnchecked(Long cardItemId, BigDecimal totalItem);
}
