package com.example.bookshop.ds;

import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@Builder
public class CartItem {
    private int id;
    private String title;
    private String author;
    private double price;
    private int quantity = 1;
    private boolean renderer;
    private double sum;

}
