package com.example.surinklietuva.DataStructures;

import java.util.List;

public class Magnet {

    String area;
    String name;
    List<String> listOfShops;

    public Magnet() {
        this.area = "";
        this.name = "";
        this.listOfShops = null;
    }

    public Magnet(String area, String name, List<String> listOfShops) {
        this.area = area;
        this.name = name;
        this.listOfShops = listOfShops;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListOfShops() {
        return listOfShops;
    }

    public void setListOfShops(List<String> listOfShops) {
        this.listOfShops = listOfShops;
    }
}
