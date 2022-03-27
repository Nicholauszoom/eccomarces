package com.example.eccomarce.global;

import com.example.eccomarce.model.Product;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
public class GlobalData {
    public static List<Product> cart;
    static{
        cart=new ArrayList<Product>();
    }
    public static List<Product> users;
    static{
        users=new ArrayList<Product>();
    }
}
