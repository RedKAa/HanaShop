/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangnt.model;

import java.sql.Date;

/**
 *
 * @author user
 */
public class Product {
    private String id;
    private String name;
    private String image;
    private String description;
    private float price;
    private Date createDate;
    private Date lastUpdate;
    private int quantity;
    private boolean status;
    private String categoryID;

    public Product() {
    }

    public Product(String id, String name, String image, String description, float price, Date createDate, Date lastUpdate, int quantity, boolean status, String categoryID) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;
        this.quantity = quantity;
        this.status = status;
        this.categoryID = categoryID;
    }
 public Product(String id, String name, String image, String description, float price, int quantity, boolean status, String categoryID) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.categoryID = categoryID;
    }
      public Product(String id, String name, String image, String description, float price, int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }
      public Product(String id, String name, String image, String description, float price, Date createDate,  int quantity, boolean status, String categoryID) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.createDate = createDate;
        this.quantity = quantity;
        this.status = status;
        this.categoryID = categoryID;
    }
      
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return this.name + "__Date: " + this.createDate;
    }
    
    
}
