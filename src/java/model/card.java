/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class card {

    private List<item> item;

    public card() {
        item = new ArrayList<>();
    }

    public List<item> getItem() {
        return item;
    }

    public void setItem(List<item> item) {
        this.item = item;
    }

    public card(List<item> item) {
        this.item = item;
    }

    private item getItemById(int id) {
        for (item i : item) {
            if (i.getBook().getId() == id) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }
    //them vao gio
    public void addItem(item t)
    {
    //co o card r
        if(getItemById(t.getBook().getId())!=null)
        {
            item i = getItemById(t.getBook().getId());
            i.setQuantity(i.getQuantity()+t.getQuantity());
        }else{
        //chua co
        item.add(t);
        }
    }
    public void remoteItem(int id){
       if(getItemById(id)!=null)
       {
       item.remove(getItemById(id));
       }
    }
    public int getTotalMoney(){
    int t =0;
        for (item o : item) {
            t+=(o.getQuantity()*o.getBook().getPrice());
        }
        return t;
    }
}
