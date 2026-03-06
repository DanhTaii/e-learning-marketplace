package vn.edu.nlu.fit.elearning.feature.cart.model;

import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Cart {
    Map<Integer, CartItem> data;

    public Cart() {
        data = new HashMap<>();
    }


    public void addCourse(CourseCardDto c) {
        if (data.containsKey(c.getId())) {
            CartItem ci = data.get(c.getId());
            if (!ci.isSelected()) {
                ci.setSelected(true);
            }
        } else {
            data.put(c.getId(), new CartItem(c, c.getPrice(), true));
        }
    }

    public CartItem deleteCourse(int id) {
        return data.remove(id);
    }

    public void removeSelected() {
        data.entrySet().removeIf(entry -> entry.getValue().isSelected());
    }

    public List<CartItem> getSelectedItems() {
        List<CartItem> selected = new ArrayList<>();
        for (CartItem item : data.values()) {
            if (item.isSelected()) {
                selected.add(item);
            }
        }
        return selected;
    }

    public void selectAll(boolean isSelected) {

        data.values().forEach(item -> item.setSelected(isSelected));
    }

    public void selectOnly(int courseId) {
        data.values().forEach(item -> {
            if (item.getCourse().getId() == courseId) {
                item.setSelected(true);
            } else {
                item.setSelected(false);
            }
        });
    }


    public int getTotalQuantity() {
        return data.size();
    }

    public List<CartItem> getList() {
        return new ArrayList<>(data.values());
    }

    public double getTotal() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().forEach(p -> {
            if (p.isSelected()) {
                total.updateAndGet(v -> (v + p.getCourse().getPrice()));
            }

        });
        return total.get();
    }


    public double getFinalPriceTotal() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().forEach(p -> {
            if (p.isSelected()) {
                total.updateAndGet(v -> (v + p.getPrice()));
            }
        });
        return total.get();
    }
    public double getDiscountPriceTotal() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().forEach(p -> {
            if (p.isSelected()) {
                total.updateAndGet(v -> (v +  p.getCourse().getDiscountPrice()));
            }
        });
        return total.get();
    }

    public int getSelectedQuantity() {
        int count = 0;
        for (CartItem c : data.values()) {
            if (c.isSelected()) {
                count++;
            }
        }
        return count;
    }
}
