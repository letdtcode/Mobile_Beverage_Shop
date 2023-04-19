package com.iostar.beverageshop.model;

import java.math.BigDecimal;
import java.util.List;

public class CartItem {
    private Long Id;
    private String productName;
    private List<String> toppingName;
    private String sizeName;
    private Integer quantity;
    private BigDecimal totalPriceItem;
}
