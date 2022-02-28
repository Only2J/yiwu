package com.javaee.yiwu.entity;


import java.util.List;

public class Productinfo {

    public String msg;
    public int code;
    public List<DataDTO> data;

    public static class DataDTO {
        public int id;
        public String productname;
        public String productprice;
        public String productImagesrc;
        public String productType;
        public String productDescribe;
        public String productUploadTime;
        public boolean urgent;
        public String service;
    }
}
