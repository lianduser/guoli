package com.example.shoppingmall2.vo;

import cn.bmob.v3.BmobObject;
import okhttp3.Address;


public class Orders extends BmobObject{

    private String userId;//用户编号
    private String bookInfoId;//图书ID
    private int status;//状态 (1未付款，2已付款，3已发货，4已收货，5已评价)
    private double subtotal;//小计
    private int total;//购买数量
    private double discountPrice;//单价
    private String bookName;//书名
    private String orderId;// 订单编号
    private String buyDate;//购买时间
    private double freight;//运费
    private Address address;//收货地址

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getBookInfoId() {
        return bookInfoId;
    }

    public void setBookInfoId(String bookInfoId) {
        this.bookInfoId = bookInfoId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Orders{" +
                ", userId='" + userId + '\'' +
                ", bookInfoId='" + bookInfoId + '\'' +
                ", status=" + status +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", discountPrice=" + discountPrice +
                ", bookName='" + bookName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", buyDate='" + buyDate + '\'' +
                '}';
    }
}
