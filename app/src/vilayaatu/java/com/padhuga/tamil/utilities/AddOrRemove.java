package com.padhuga.tamil.utilities;


import com.padhuga.tamil.models.Item;

import java.util.List;

public class AddOrRemove {

    public boolean removeItemToList(List<Item> l, Item it){
        return l.remove(it);
    }

    public void addItemToList(List<Item> l, Item it){
        l.add(it);
    }
}
