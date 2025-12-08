    package vn.edu.nlu.fit.elearning.model;

    import java.io.Serializable;
    import java.text.NumberFormat;
    import java.util.Locale;

    public class CartItem implements Serializable {
        private int id;                 // c.id (ID khóa học)
        private int userId;
        private int courseId;
        private String title;           // c.title
        private String thumbnailUrl;    // c.thumbnail_url
        private boolean isSelected;     // ci.is_selected (Trạng thái checkbox)
        private double rating;          // c.rating (Dùng double hoặc float)
        private long priceOld;          // c.price AS price_old
        private long priceNew;          // (c.price - c.discount_price) AS price_new (Đây là giá khuyến mãi/giá bán)
        private String level;           // c.level
        private double durationHours;   // SUM(l.duration_minutes) / 60.0 AS duration_hours

        public CartItem(int id, String title, String thumbnailUrl, boolean isSelected, double rating, long priceOld, long priceNew, String level, double durationHours) {
            this.id = id;
            this.title = title;
            this.thumbnailUrl = thumbnailUrl;
            this.isSelected = isSelected;
            this.rating = rating;
            this.priceOld = priceOld;
            this.priceNew = priceNew;
            this.level = level;
            this.durationHours = durationHours;
        }

        public CartItem() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnailUrl() {
            return thumbnailUrl;
        }

        public void setThumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public long getPriceOld() {
            return priceOld;
        }

        public void setPriceOld(long priceOld) {
            this.priceOld = priceOld;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public long getPriceNew() {
            return priceNew;
        }

        public void setPriceNew(long priceNew) {
            this.priceNew = priceNew;
        }

        public double getDurationHours() {
            return durationHours;
        }

        public void setDurationHours(double durationHours) {
            this.durationHours = durationHours;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPriceNewFormatted() {
            Locale vietnam = new Locale("vi", "VN");
            NumberFormat f = NumberFormat.getCurrencyInstance(vietnam);
            String formatted = f.format(this.priceNew);
            // Mặc định nó ra "300.000 đ", nếu muốn bỏ chữ "đ" đi để tự thêm sau thì:
            return formatted.replace(" ₫", "").replace("₫", "").trim();
        }

        // Làm tương tự cho giá cũ
        public String getPriceOldFormatted() {
            Locale vietnam = new Locale("vi", "VN");
            NumberFormat f = NumberFormat.getCurrencyInstance(vietnam);
            String formatted = f.format(this.priceOld);
            return formatted.replace(" ₫", "").replace("₫", "").trim();
        }

    }
