package com.example.gaming;

public class POJO {
    public String id;
    public String name;
    public String mobile;
    public String relationName;
    public String umar;
    public String ling;
    public String password;
    public String cpassword;

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
        name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUmar() {
        return umar;
    }

    public void setUmar(String umar) {
        this.umar = umar;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }







    public void setPassword(String password) {
        this.password = password;
    }

    public String getLing() {
        return ling;
    }

    public void setLing(String ling) {
        this.ling = ling;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public POJO()
    {

    }

 public POJO(String id, String Name,String RelationName,String mobile,String Sex,String ge,String password)
    {
        this.id=id;
        this.name=Name;
        this.relationName=RelationName;
        this.mobile=mobile;
        this.umar=ge;
        this.ling=Sex;
        this.password=password;

    }

}
