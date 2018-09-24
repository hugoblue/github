package com.ehouse.springmvc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="springmvc_versionInfo")
public class VersionInfo {

    @Id
    @GeneratedValue
    private Integer id;

    private Date createDate;

    public String versionNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String toString(){
        return "VersionNo-- id:" + id + ", createDate:" + createDate + ", versionNo:" + versionNo;
    }
}
