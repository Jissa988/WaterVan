package com.example.watervan.Sale;

import java.util.List;

public class ProductListArray {
    List<ProductList> productListList;

    public ProductListArray(List<ProductList> productListList) {
        this.productListList = productListList;
    }



    public List<ProductList> getProductListList() {
        return productListList;
    }

    public void setProductListList(List<ProductList> productListList) {
        this.productListList = productListList;
    }


}
