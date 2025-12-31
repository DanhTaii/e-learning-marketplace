package vn.edu.nlu.fit.elearning.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Cart {
     Map<Integer,CartItem> data;

    public Cart() {
        data = new HashMap<>();
        }


    public void addCourse(Course c) {
        if(data.containsKey(c.getId())){
            CartItem ci = data.get(c.getId());
            if( !ci.isSelected()){
                ci.setSelected(true);
            }
        }else {
            data.put(c.getId(),new CartItem(c,c.getPrice(),true));
        }
    }

    public int getTotalQuantity(){
        return data.size();
    }

}
