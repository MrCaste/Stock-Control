package com.stockcontrol.modelo;

public class Product {

    private String name;
    private int identificator;
    private int stock;
    
    public Product(int identificator, String name, int stock) {
        this.name = name;
        this.identificator = identificator;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdentificator() {
        return identificator;
    }

    public void setIdentificator(int identificator) {
        this.identificator = identificator;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
