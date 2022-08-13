package com.practice.domain;

public class Goods {
    private Integer id;
    private String goods_name;
    private double price;

    public Goods(Integer id, String goods_name, double price) {
        this.id = id;
        this. goods_name = goods_name;
        this. price = price;
    }

    public Goods(){ //设立无参构造

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n"+ "actor{" +
                "id=" + id +
                ", goods_name='" + goods_name + '\'' +
                ", price='" + price + '\'' +
                '}'+"\n";
    }
}
