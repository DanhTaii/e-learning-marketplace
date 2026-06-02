# Tài liệu Thiết kế & Triển khai Tính năng Import Excel

**Dự án:** Hệ thống E-Learning
**Tính năng:** Nhập dữ liệu hàng loạt từ file Excel (.xlsx)
**Công nghệ sử dụng:** Java Servlet, JSP, Apache POI, JDBI (MySQL)

---

## 1. Tổng quan Kiến trúc (Architecture Overview)

Tính năng Import Excel được thiết kế dựa trên nguyên tắc **SOLID** (đặc biệt là Single Responsibility) và **DRY** (Don't Repeat Yourself). Thay vì gộp toàn bộ logic vào Controller hoặc Service, hệ thống được chia thành 4 tầng riêng biệt:

1. **Tầng Giao diện (UI):** Form upload với thuộc tính `multipart/form-data`.
2. **Tầng Điều phối (Controller & Service):** Tiếp nhận file, điều phối luồng và trả về kết quả (thành công hoặc danh sách lỗi).
3. **Tầng Tiện ích & Xử lý (Core Utils & Parsers):** Xử lý luồng file trên RAM, bóc tách dữ liệu và kiểm tra tính hợp lệ.
4. **Tầng Dữ liệu (DAO):** Tối ưu hóa việc lưu trữ hàng loạt bằng JDBI Batch Insert.

---

## 2. Các thành phần cốt lõi (Core Components)

Để đảm bảo tính tái sử dụng cho các module khác (Học viên, Bài giảng...), kiến trúc được chia làm 2 nhóm: Nhóm dùng chung (Generic Utils) và Nhóm nghiệp vụ cụ thể (Specific Logic).

### 2.1. Nhóm Công cụ Dùng chung (Generic Utils)
* `ExcelReaderUtils.java`: Quản lý vòng lặp đọc file. Nhận vào một `InputStream` và một hàm Parser (Function). Nó thực hiện việc lặp qua các dòng (Row), bỏ qua dòng tiêu đề và thu thập các Object hợp lệ hoặc ghi nhận lỗi.
* `ExcelCellUtils.java`: Hỗ trợ lấy dữ liệu từ từng ô (Cell), tự động ép kiểu (String, int, boolean) và bọc sẵn try-catch để chống crash hệ thống.

### 2.2. Nhóm Xử lý Khóa học (Course Logic)
* `CourseExcelParser.java`: "Biên dịch viên". Đóng vai trò ánh xạ (map) dữ liệu từ dòng Excel (Row) sang đối tượng `Course` của Java.
* `CourseValidator.java`: "Cảnh sát". Kiểm tra các ràng buộc kinh doanh (Business rules) như tên không được để trống, giá tiền phải lớn hơn 0...

---

## 3. Luồng xử lý dữ liệu (Data Flow)

Luồng hoạt động tuân thủ nghiêm ngặt cơ chế **All-or-Nothing** (Xử lý an toàn trên bộ nhớ đệm trước khi can thiệp vào Database):

1. **Upload File:** Admin chọn file mẫu `.xlsx` và submit form.
2. **Tiếp nhận (Controller):** `CourseImportController` nhận file thông qua `request.getPart()`. Tạo một danh sách rỗng `List<String> errorMessages` ("chiếc giỏ") để hứng lỗi.
3. **Đọc và Xử lý trên RAM (Service -> Utils):**
    * `CourseAdminService` truyền luồng `InputStream` và `errorMessages` xuống `ExcelReaderUtils`.
    * Trình đọc lặp qua từng dòng, đẩy mỗi dòng cho `CourseExcelParser`.
    * Dữ liệu được trích xuất bằng `ExcelCellUtils`, ép kiểu và đưa qua `CourseValidator` kiểm tra.
    * Nếu hợp lệ -> Đưa vào danh sách chờ (`validItems`).
    * Nếu có lỗi (sai định dạng, vi phạm ràng buộc) -> Lưu câu thông báo vào `errorMessages`.
4. **Lưu trữ hàng loạt (DAO):** * Nếu quá trình đọc thành công, Service gọi `CourseDAO.createList(courses)`.
    * DAO sử dụng cơ chế `PreparedBatch` và `bindBean()` của JDBI để thực thi một lệnh INSERT duy nhất cho toàn bộ danh sách, giúp tốc độ lưu trữ tăng gấp nhiều lần.
5. **Phản hồi (UI):** Controller kiểm tra "giỏ lỗi" và số lượng thành công, gán vào Session và redirect về trang danh sách để hiển thị thông báo.

---

## 4. Những Điểm Nổi Bật (Best Practices & Optimizations)

* **Không lưu file vật lý (Zero Disk I/O):** File Excel tải lên được đọc trực tiếp từ `InputStream` trên RAM. Xử lý xong, bộ nhớ tự động được dọn dẹp, không tạo ra file rác trên Server/VPS.
* **Tối ưu tốc độ (Performance):** Sử dụng JDBI Batch Insert thay vì gọi lệnh INSERT từng dòng trong vòng lặp.
* **Bảo vệ toàn vẹn dữ liệu:** Bắt lỗi triệt để ở khâu ép kiểu và kiểm tra logic. Lỗi được gom lại và báo cáo chi tiết theo từng dòng (Ví dụ: "Dòng 5: Giá tiền phải là số dương"), giúp Admin dễ dàng chỉnh sửa.
* **Khả năng mở rộng cao (Scalability):** Bất kỳ Module nào cần Import Excel sau này chỉ cần viết thêm Parser và Validator riêng, sau đó tái sử dụng lại toàn bộ vòng lặp của `ExcelReaderUtils`.