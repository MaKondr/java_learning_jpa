package org.example.springjpa.core;

import org.example.springjpa.menu.Menu;
import org.example.springjpa.menu.MenuCreator;
import org.example.springjpa.menu.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Main {

    @Autowired
    private MenuCreator menuCreator;

    public void main(){
        Menu menu = menuCreator.createMenu();

        int selectedItemId = menu.renderMenu(null);
        MenuItem selectedItem = null;

        while (!menu.isExit){
            try{
                selectedItem = menu.selectMenuItem(selectedItemId);
                if (selectedItem == null){
                    throw new Exception("No such menu item");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                selectedItemId = menu.renderMenu(null);
                continue;

            }
            if (selectedItem.getFc() != null){
                selectedItem = selectedItem.getFc().func();
                if(selectedItem == null) menu.exit();
            }
            if(selectedItem != null && !selectedItem.getChildrenItems().isEmpty()){
                selectedItemId = menu.renderMenu(selectedItem.getChildrenItems());
            }

        }
    }
}
