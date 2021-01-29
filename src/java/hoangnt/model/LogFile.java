/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author user
 */
public class LogFile implements Serializable{
    private String productID;
    private String userID;
    private Date updateDate;
    private String description;

    public LogFile() {
    }

    public LogFile(String productID, String userID, Date updateDate, String description) {
        this.productID = productID;
        this.userID = userID;
        this.updateDate = updateDate;
        this.description = description;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
