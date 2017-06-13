/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;

public class OfferItem {

    // product
    
    private Product product;
    
   /* private String productId;

    private BigDecimal productPrice;

    private String productName;

    private Date productSnapshotDate;

    private String productType;*/

    private int quantity;

    private BigDecimal totalCost;

    private String currency;

    // discount
    private String discountCause;

    private BigDecimal discount;

    public OfferItem(String productId, BigDecimal productPrice, String productName,
            Date productSnapshotDate, String productType, int quantity) {
        this(productId, productPrice, productName, productSnapshotDate, productType, quantity, null, null);
    }

    public OfferItem(String productId, BigDecimal productPrice, String productName,
            Date productSnapshotDate, String productType, int quantity,
            BigDecimal discount, String discountCause) {
        product.productId = productId;
        product.productPrice = productPrice;
        product.productName = productName;
        product.productSnapshotDate = productSnapshotDate;
        product.productType = productType;

        this.quantity = quantity;
        this.discount = discount;
        this.discountCause = discountCause;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null) {
            discountValue = discountValue.subtract(discount);
        }

        this.totalCost = productPrice
                .multiply(new BigDecimal(quantity)).subtract(discountValue);
    }

    public String getProductId() {
        return product.productId;
    }
/*
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public Date getProductSnapshotDate() {
        return productSnapshotDate;
    }

    public String getProductType() {
        return productType;
    }
*/
    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return currency;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDiscountCause() {
        return discountCause;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((discount == null) ? 0 : discount.hashCode());
        result = prime * result + ((product.productName == null) ? 0 : product.productName.hashCode());
        result = prime * result + ((product.productPrice == null) ? 0 : product.productPrice.hashCode());
        result = prime * result
                + ((product.productId == null) ? 0 : product.productId.hashCode());
        result = prime * result + ((product.productType == null) ? 0 : product.productType.hashCode());
        result = prime * result + quantity;
        result = prime * result
                + ((totalCost == null) ? 0 : totalCost.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        if (discount == null) {
            if (other.discount != null) {
                return false;
            }
        } else if (!discount.equals(other.discount)) {
            return false;
        }
        if (product.productName == null) {
            if (other.product.productName != null) {
                return false;
            }
        } else if (!product.productName.equals(other.product.productName)) {
            return false;
        }
        if (product.productPrice == null) {
            if (other.product.productPrice != null) {
                return false;
            }
        } else if (!product.productPrice.equals(other.product.productPrice)) {
            return false;
        }
        if (product.productId == null) {
            if (other.product.productId != null) {
                return false;
            }
        } else if (!product.productId.equals(other.product.productId)) {
            return false;
        }
        if (!product.productType.equals(other.product.productType)) {
            return false;
        }
        if (quantity != other.quantity) {
            return false;
        }
        if (totalCost == null) {
            if (other.totalCost != null) {
                return false;
            }
        } else if (!totalCost.equals(other.totalCost)) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param item
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (product.productName == null) {
            if (other.product.productName != null) {
                return false;
            }
        } else if (!product.productName.equals(other.product.productName)) {
            return false;
        }
        if (product.productPrice == null) {
            if (other.product.productPrice != null) {
                return false;
            }
        } else if (!product.productPrice.equals(other.product.productPrice)) {
            return false;
        }
        if (product.productId == null) {
            if (other.product.productId != null) {
                return false;
            }
        } else if (!product.productId.equals(other.product.productId)) {
            return false;
        }
        if (!product.productType.equals(other.product.productType)) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        BigDecimal max, min;
        if (totalCost.compareTo(other.totalCost) > 0) {
            max = totalCost;
            min = other.totalCost;
        } else {
            max = other.totalCost;
            min = totalCost;
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(new BigDecimal(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
