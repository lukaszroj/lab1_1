/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

/**
 *
 * @author 180273
 */
public class Money {
    public BigDecimal totalCost;

    public String currency;

    // discount
    public String discountCause;

    public BigDecimal discount;
    
}
