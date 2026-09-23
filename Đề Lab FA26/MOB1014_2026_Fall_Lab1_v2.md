# BÀI THỰC HÀNH 01

Các quy định về cấu trúc Repository, quy trình Git, video nộp bài, cách tính điểm và vấn đáp, cùng các chế tài áp dụng chung, được trình bày tại văn bản [Quy định chung toàn khóa](Quy_Dinh_Chung_Toan_Khoa.md). Văn bản này chỉ trình bày Yêu cầu bài tập riêng của Lab 1.

## MỤC TIÊU

Sau bài thực hành này, sinh viên có khả năng:

- Cài đặt thành thạo JDK (Java Development Kit), IDE NetBeans và phần mềm Git; tạo và quản lý Project Java with Maven.
- Khởi tạo Repository và quản lý tiến độ học tập môn học bằng Git/GitHub theo cấu trúc phân mục khoa học.
- Sử dụng thành thạo nhập/xuất trong Java (`System.out.println`/`printf` và lớp `Scanner`).
- Khai báo biến, hằng số (từ khóa `final`) và sử dụng các toán tử trong Java để xử lý bài toán tính toán.
- Quay video thao tác gõ code trực tiếp và diễn giải bản chất kỹ thuật của từng dòng lệnh.

---

## YÊU CẦU

**Vị trí lưu trữ chung:** Toàn bộ 4 bài của Lab 1 là 4 class nằm trong cùng một Project `Lab1`, package `com.fpoly.lab1` (xem [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 1).

### Bài 1 (2đ): Cài đặt môi trường và khởi tạo Repository

1. Tải và cài đặt JDK bản LTS mới nhất và IDE Apache NetBeans. Cài đặt phần mềm Git.
2. Tạo tài khoản GitHub nếu chưa có. Thiết lập thư mục gốc trên máy tính, đặt tên đúng cú pháp `STT_MSSV_Ten`.
3. Tại thư mục gốc, tạo file `.gitignore` theo nội dung tại [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 2.
4. Mở terminal (Git Bash) tại thư mục gốc, khởi tạo Git và liên kết với một Repository ở chế độ Public trên GitHub. Repository này được sử dụng chung cho tất cả các buổi Lab trong học kỳ.
5. Trong NetBeans, tạo Project mới: **File → New Project → Java with Maven → Java Application**, đặt Project Name là `Lab1`, Project Location là thư mục gốc `STT_MSSV_Ten`, Group Id là `com.fpoly`. Không tự tạo trước thư mục `Lab1` bằng tay.

**Kiểm chứng bản chất cấu trúc Java:** Trong class chính `Lab1.java` (NetBeans tự sinh), sinh viên viết chương trình gồm các nội dung sau:

- Khai báo `package`.
- Khai báo `public class Lab1`.
- Phương thức `public static void main(String[] args)`.
- Một câu lệnh `System.out.println()` in ra dòng chữ giới thiệu bản thân, gồm họ tên và MSSV.

Chương trình này được dùng để xác nhận JDK/NetBeans hoạt động đúng, và làm cơ sở để diễn giải trong video.

### Bài 2 (2đ): Nhập xuất dữ liệu với Scanner

Sinh viên tạo class `NhapHoTen.java` trong package `com.fpoly.lab1`.

**Yêu cầu:** Sử dụng lớp `Scanner` (`import java.util.Scanner;`) để nhập dữ liệu từ bàn phím theo **đúng thứ tự** sau: nhập **năm sinh** trước (dùng `sc.nextInt()`), sau đó nhập **họ tên** (dùng `sc.nextLine()`). Tính tuổi và in ra màn hình.

**Công thức tính toán:** `Tuoi = 2026 - NamSinh`

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Ho ten: [ho ten da nhap]
Tuoi: [so tuoi tu dong tinh = 2026 - Nam Sinh]
```

**Lưu ý kỹ thuật:** Sau khi gọi `sc.nextInt()`, ký tự xuống dòng (Enter) vẫn còn sót lại trong bộ đệm. Nếu gọi ngay `sc.nextLine()` thì lệnh này sẽ đọc phải ký tự xuống dòng đó và trả về chuỗi rỗng (lỗi trôi lệnh). Cần thêm một lệnh `sc.nextLine();` để bỏ qua ký tự xuống dòng trước khi nhập họ tên, tương tự cơ chế chống trôi lệnh đã học ở ngôn ngữ C.

### Bài 3 (2đ): Tính chu vi, diện tích hình tròn

Sinh viên tạo class `HinhTron.java` trong package `com.fpoly.lab1`.

**Yêu cầu:** Nhập vào bán kính (số thực, dùng `sc.nextDouble()`). Tính và hiển thị ra màn hình Chu vi và Diện tích hình tròn. Khai báo PI là hằng số:

```java
final double PI = 3.14159;
```

**Công thức tính toán:** `ChuVi = 2 × PI × banKinh`; `DienTich = PI × banKinh × banKinh`

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Chu vi hinh tron: [gia tri, lam tron 2 chu so thap phan]
Dien tich hinh tron: [gia tri, lam tron 2 chu so thap phan]
```

**Lưu ý kỹ thuật:** Dùng `System.out.printf()` với định dạng `%.2f` để làm tròn 2 chữ số thập phân, tương tự cách dùng `printf` trong C. Không sử dụng `Math.PI` cho bài này.

### Bài 4 (2đ): Tính chu vi, diện tích hình chữ nhật

Sinh viên tạo class `HinhChuNhat.java` trong package `com.fpoly.lab1`.

**Yêu cầu:** Nhập vào chiều dài và chiều rộng (số thực, dùng `sc.nextDouble()`). Tính và hiển thị ra màn hình Chu vi và Diện tích hình chữ nhật.

**Công thức tính toán:** `ChuVi = 2 × (chieuDai + chieuRong)`; `DienTich = chieuDai × chieuRong`

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Chu vi hinh chu nhat: [gia tri, lam tron 2 chu so thap phan]
Dien tich hinh chu nhat: [gia tri, lam tron 2 chu so thap phan]
```

**Lưu ý kỹ thuật:** Các biến chiều dài, chiều rộng, chu vi, diện tích khai báo kiểu `double` để tính chính xác phần thập phân; xuất kết quả bằng `printf` với định dạng `%.2f`.

**Tổng điểm Lab 1 = 8.0 điểm (4 bài) + điểm cộng: tối đa 2.0 điểm = 10.0 điểm.**
