package com.stockcontrol.modelo;

import java.time.LocalDate;

public class Product {

    private String name;
    private String fecha;
    private int identificator;
    private int stock;
    
    public Product(int identificator, String name, int stock, LocalDate fecha) {
        this.name = name;
        this.identificator = identificator;
        this.stock = stock;
        this.fecha = fecha.toString();
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
