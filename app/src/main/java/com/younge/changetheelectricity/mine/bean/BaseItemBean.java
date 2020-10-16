package com.younge.changetheelectricity.mine.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseItemBean {

   private String id;
   private String name;


   public BaseItemBean(){

   }

    public BaseItemBean(String id,String name){
       this.id = id;
       this.name = name;
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
}
