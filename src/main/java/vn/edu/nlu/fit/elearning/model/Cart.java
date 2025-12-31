package vn.edu.nlu.fit.elearning.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

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
    public List<CartItem> getList(){
        return new ArrayList<>(data.values());
    }
    public double getTotal() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().forEach(p -> {
            if (p.isSelected()) {
                total.updateAndGet(v -> (v + p.getPrice()));
            }

        });
        return total.get();
    }



    public double getDiscountPrice() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().forEach(p -> {
            if (p.isSelected()) {
                total.updateAndGet(v -> (v + p.getPrice() - p.getCourse().getDiscountPrice()));
            }
        });
        return total.get();
    }

    public int getSelectedQuantity(){
        int count = 0;
        for (CartItem c : data.values()){
            if(c.isSelected()){
                count++;
            }
        }
        return count;
    }
}
