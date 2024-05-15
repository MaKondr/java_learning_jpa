package org.example.springjpa.menu;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;


@Getter
@Setter
@Component
public class Menu {

    ArrayList<MenuItem> menuItems;
    public boolean isExit = false;

    public Menu(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }


    public MenuItem selectMenuItem(int menuItemId) {
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getId() == menuItemId) {
                return menuItem;
            }
        }
        return null;
    }

    public int renderMenu(ArrayList<MenuItem> menuItems) {
        if (menuItems != null) {
            this.menuItems = menuItems;
        }
        for (MenuItem menuItem : this.menuItems) {
            System.out.println(menuItem.getId() + " " + menuItem.getName());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the number you would like to see: ");
        return scanner.nextInt();
    }

    public void exit(){
        isExit = true;
    }

}
