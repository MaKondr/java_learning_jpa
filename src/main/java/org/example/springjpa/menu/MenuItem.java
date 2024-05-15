package org.example.springjpa.menu;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter
@Setter
public class MenuItem {
    public int id;
    public String name;
    public MenuItem parentItem;
    public FunctionCaller fc;
    public ArrayList<MenuItem> childrenItems = new ArrayList<>();

    public MenuItem(int id, String name, MenuItem parentItem, FunctionCaller fc) {
        this.id = id;
        this.name = name;
        this.parentItem = parentItem;
        this.fc = fc;
    }


    public void addChildItem(int id, String name, MenuItem parentItem, FunctionCaller fc) {
        MenuItem item = new MenuItem(id, name, parentItem, fc);
        this.childrenItems.add(item);
    }

    public void addChildItem(MenuItem item) {
        this.childrenItems.add(item);
    }

}
