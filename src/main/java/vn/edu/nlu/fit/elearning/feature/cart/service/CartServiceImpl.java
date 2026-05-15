package vn.edu.nlu.fit.elearning.feature.cart.service;

import vn.edu.nlu.fit.elearning.common.utils.security.HashUtils;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting.formatAndConvert;

public class CartServiceImpl implements CartService {
    Map<Integer, CartItem> data;
    private String cartHash;
    private Timestamp updatedAt;
    public CartServiceImpl() {
        data = new HashMap<>();
        this.cartHash = "";
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
    private void updateMetadata() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());

        List<Integer> courseIds = new ArrayList<>(data.keySet());
        Collections.sort(courseIds);

        this.cartHash = HashUtils.md5(courseIds.toString());
    }

    @Override
    public void addCourse(CourseCardDto c) {
        if (data.containsKey(c.getId())) {
            CartItem ci = data.get(c.getId());
            if (!ci.isSelected()) {
                ci.setSelected(true);
            }
        } else {
            data.put(c.getId(), new CartItem(c, c.getPrice(), true));
        }
        updateMetadata();
    }

    @Override
    public CartItem deleteCourse(int id) {
        CartItem removed = data.remove(id);
        if (removed != null) {
            updateMetadata();
        }
        return removed;
    }

    @Override
    public void removeSelected() {
        boolean isRemoved = data.entrySet().removeIf(entry -> entry.getValue().isSelected());
        if (isRemoved) {
            updateMetadata();
        }
    }

    @Override
    public List<CartItem> getSelectedItems() {
        List<CartItem> selected = new ArrayList<>();
        for (CartItem item : data.values()) {
            if (item.isSelected()) {
                selected.add(item);
            }
        }
        return selected;
    }

    @Override
    public void selectAll(boolean isSelected) {

        data.values().forEach(item -> item.setSelected(isSelected));
    }

    @Override
    public void selectOnly(int courseId) {
        data.values().forEach(item -> {
            if (item.getCourse().getId() == courseId) {
                item.setSelected(true);
            } else {
                item.setSelected(false);
            }
        });
    }


    @Override
    public int getTotalQuantity() {
        return data.size();
    }

    @Override
    public List<CartItem> getList() {
        return new ArrayList<>(data.values());
    }

    @Override
    public double getTotal() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().forEach(p -> {
            if (p.isSelected()) {
                total.updateAndGet(v -> (v + p.getCourse().getPrice()));
            }

        });
        return total.get();
    }


    @Override
    public double getFinalPriceTotal() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().forEach(p -> {
            if (p.isSelected()) {
                total.updateAndGet(v -> (v + p.getPrice()));
            }
        });
        return total.get();
    }
    @Override
    public double getDiscountPriceTotal() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        data.values().forEach(p -> {
            if (p.isSelected()) {
                total.updateAndGet(v -> (v +  p.getCourse().getDiscountPrice()));
            }
        });
        return total.get();
    }

    @Override
    public String getFormatedTotal() {
        return formatAndConvert(getTotal());
    }

    @Override
    public String getFormatedFinalPriceTotal() {
        return formatAndConvert(getFinalPriceTotal());
    }

    @Override
    public String getFormatedDiscountPriceTotal() {
        return formatAndConvert(getDiscountPriceTotal());
    }

    @Override
    public int getSelectedQuantity() {
        int count = 0;
        for (CartItem c : data.values()) {
            if (c.isSelected()) {
                count++;
            }
        }
        return count;
    }
    public String getCartHash() { return cartHash; }
    public void setCartHash(String cartHash) { this.cartHash = cartHash; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
    public void setData(Map<Integer, CartItem> data) { this.data = data; }
}
