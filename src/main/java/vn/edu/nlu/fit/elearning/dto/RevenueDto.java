package vn.edu.nlu.fit.elearning.dto;

public class RevenueDto {
    private String orderDate;
    private int totalOrders;
    private double dailyRevenue;
    private double revenueMillion;
    private double heightPercent;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getDailyRevenue() {
        return dailyRevenue;
    }

    public void setDailyRevenue(double dailyRevenue) {
        this.dailyRevenue = dailyRevenue;
    }

    public double getRevenueMillion() {
        return revenueMillion;
    }

    public void setRevenueMillion(double revenueMillion) {
        this.revenueMillion = revenueMillion;
    }

    public double getHeightPercent() {
        return heightPercent;
    }

    public void setHeightPercent(double heightPercent) {
        this.heightPercent = heightPercent;
    }

    @Override
    public String toString() {
        return "RevenueDto{" +
                "orderDate='" + orderDate + '\'' +
                ", totalOrders=" + totalOrders +
                ", dailyRevenue=" + dailyRevenue +
                ", revenueMillion=" + revenueMillion +
                ", heightPercent=" + heightPercent +
                '}' + '\n';
    }
}
