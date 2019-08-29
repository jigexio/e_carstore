package com.jigexio.store.domain;

/**
 * 商品页面
 */
public class Goods {

    /* 商品id */
    private long id;
    /* 商品名称 */
    private String name;
    /* 单价 */
    private double price;
    /* 产品描述 */
    private String description;
    /* 汽车品牌 */
    private String brand;
    /* 款型 */
    private String style ;
    /* 车型 */
    private String car_type;
    /* 汽车排量*/
    private String displacement;
    /* 硬盘容量*/
    private String mileage;
    /* 汽车图片*/
    private String image;

//    private List<OrderLineItem> orderLineItems;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
