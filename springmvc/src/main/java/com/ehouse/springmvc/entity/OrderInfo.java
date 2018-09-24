package com.ehouse.springmvc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="springmvc_orderInfo")
public class OrderInfo {

    @Id
    @GeneratedValue
    private Integer id;

    private String orderId;

    private String description;

    public String versionNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String toString(){
        return "OrderInfo-- id:" + id + ", orderId:" + orderId + ", description:" + description;
    }
}
