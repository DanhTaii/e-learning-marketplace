# Hệ Thống Quản Lý Chứng Chỉ PDF

Tài liệu này giải thích quy trình tạo và quản lý chứng chỉ PDF trong dự án e-learning.

## Tổng quan

Hệ thống cho phép tạo ra các chứng chỉ PDF một cách tự động cho học viên khi hoàn thành khóa học. Chức năng cốt lõi được xử lý bởi lớp `vn.edu.nlu.fit.elearning.common.helper.PdfHelper.java`, sử dụng thư viện `iTextPDF` để thực hiện các thao tác với file PDF.

## Quy Trình Tạo Chứng Chỉ

Quy trình được thực hiện hoàn toàn ở phía server. Lớp `PdfHelper` chịu trách nhiệm tạo file PDF và lưu nó vào hệ thống file của server.

### 1. Khởi tạo

- Quá trình bắt đầu bằng việc gọi phương thức `PdfHelper.generateCertificate()`.
- Phương thức này nhận vào các tham số động bao gồm:
  - `realPath`: Đường dẫn vật lý của ứng dụng web trên server.
  - `userName`: Tên của học viên.
  - `courseName`: Tên khóa học đã hoàn thành.
  - `completionDate`: Ngày hoàn thành.
  - `certCode`: Một mã định danh duy nhất cho chứng chỉ.

### 2. Thiết Lập Đường Dẫn và File

- **Thư mục lưu trữ**: Các file chứng chỉ PDF được lưu tại một đường dẫn cố định trên server, được định nghĩa trong hằng số `CERTIFICATE_STORAGE` (ví dụ: `/opt/elearning/certificates`).
- **Tên file**: Tên file PDF được tạo ra từ `certCode` (ví dụ: `CERT-09-XRLM-2026000.pdf`).
- **Kiểm tra thư mục**: Hệ thống sẽ tự động kiểm tra nếu thư mục lưu trữ tồn tại hay chưa. Nếu chưa, nó sẽ được tạo ra.

### 3. Tạo Tài Liệu PDF

- Một tài liệu PDF mới được tạo ra với kích thước là **A4 xoay ngang** (`PageSize.A4.rotate()`).
- Một file ảnh mẫu (`certificate-template.png`) được sử dụng làm nền. Ảnh này được kéo dãn để vừa khít với kích thước của trang giấy.

### 4. Thêm Nội Dung Động

Đây là bước quan trọng nhất, nơi thông tin của học viên và khóa học được ghi đè lên trên ảnh nền.

- **Font chữ**: Một font chữ tùy chỉnh (`Roboto-Semibold.ttf`) được tải để đảm bảo tính nhất quán về mặt hình thức.
- **Ghi Tên Học Viên**:
  - Tên học viên được căn giữa.
  - Hệ thống sử dụng hàm `renderTextAutoFit` để tự động giảm kích thước font chữ nếu tên quá dài, đảm bảo nó luôn nằm gọn trong một dòng và không bị tràn ra ngoài.
- **Ghi Tên Khóa Học**:
  - Tên khóa học được hiển thị với màu xanh đặc trưng của thương hiệu.
  - Do tên khóa học có thể rất dài, hệ thống sử dụng hàm `renderTextMultiLine` để xử lý. Hàm này sẽ:
    1. "Vẽ nháp" để tính toán xem văn bản sẽ chiếm bao nhiêu dòng.
    2. Dựa trên số dòng, tính toán vị trí theo chiều dọc để khối văn bản luôn được căn giữa một cách hoàn hảo.
    3. "Vẽ thật" để hiển thị văn bản đã được ngắt dòng và căn chỉnh.
- **Ghi Ngày Tháng và Mã Chứng Chỉ**:
  - Ngày hoàn thành được đặt ở góc dưới bên trái.
  - Mã chứng chỉ được đặt ở góc dưới bên phải.

### 5. Hoàn Tất và Xác Thực

- Sau khi tất cả nội dung đã được ghi, tài liệu PDF được đóng lại và lưu vào file.
- Hệ thống kiểm tra lại để chắc chắn rằng file đã được tạo thành công và không bị rỗng.
- Phương thức trả về một đối tượng `PdfResult` chứa đường dẫn tương đối của file (ví dụ: `CERT-09-XRLM-2026000.pdf`) để lưu vào cơ sở dữ liệu.

## Quy Trình Tải Xuống Chứng Chỉ

Lớp `PdfHelper` chỉ chịu trách nhiệm **tạo** file. Việc tải xuống được xử lý bởi một thành phần khác (thường là một Controller trong mô hình MVC).

1.  Người dùng yêu cầu tải chứng chỉ từ giao diện web.
2.  Controller nhận yêu cầu, truy vấn cơ sở dữ liệu để lấy tên file PDF tương ứng với chứng chỉ của người dùng.
3.  Controller sử dụng tên file này để xác định đường dẫn đầy đủ đến file trên server (ví dụ: `/opt/elearning/certificates/CERT-09-XRLM-2026000.pdf`).
4.  Cuối cùng, Controller đọc file từ hệ thống file của server và gửi (stream) nội dung của file về trình duyệt của người dùng dưới dạng một file tải xuống.
