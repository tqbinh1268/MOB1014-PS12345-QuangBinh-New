# BÀI THỰC HÀNH 06

Các quy định về cấu trúc Repository, quy trình Git, video nộp bài, cách tính điểm và vấn đáp, cùng các chế tài áp dụng chung, được trình bày tại văn bản [Quy định chung toàn khóa](Quy_Dinh_Chung_Toan_Khoa.md). Văn bản này chỉ trình bày Yêu cầu bài tập riêng của Lab 6.

## MỤC TIÊU

Sau bài thực hành này, sinh viên có khả năng:

- Xây dựng quan hệ kế thừa giữa hai lớp bằng từ khóa `extends`; hiểu mọi lớp trong Java đều ngầm kế thừa lớp `Object`.
- Phân biệt đặc tả truy xuất `private` và `protected` trong quan hệ cha – con.
- Sử dụng từ khóa `super` để gọi hàm tạo và phương thức của lớp cha.
- Ghi đè phương thức (Method Overriding) với chú thích `@Override`, bao gồm ghi đè `toString()`.
- Lưu trữ đối tượng lớp con trong danh sách kiểu lớp cha; sử dụng `instanceof` và ép kiểu xuống (downcasting).
- Quay video thao tác gõ code trực tiếp và diễn giải bản chất kỹ thuật của quan hệ kế thừa.

---

## YÊU CẦU

**Vị trí lưu trữ chung:** Toàn bộ các class của Lab 6 nằm trong cùng một Project `Lab6`, package `com.fpoly.lab6` (xem [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 1). Project được tạo bằng **File → New Project → Java with Maven → Java Application**, Project Name `Lab6`, Project Location là thư mục gốc `STT_MSSV_Ten`, Group Id `com.fpoly`.

**Đặc thù của Lab 6:** Lab này gồm hai lớp mô hình `Circle.java` (lớp cha) và `Cylinder.java` (lớp con), không có `main()`, cùng 4 class có `main()` để chạy từng bài.

Cấu trúc thư mục của Lab 6:

```
Lab6/src/main/java/com/fpoly/lab6/
├── Circle.java            (lớp cha - Bài 1)
├── Cylinder.java          (lớp con - Bài 2)
├── HinhTronDemo.java      (main của Bài 1)
├── HinhTruDemo.java       (main của Bài 2)
├── QuanLyHinhTru.java     (main của Bài 3)
└── DanhSachHinh.java      (main của Bài 4)
```

Sơ đồ quan hệ kế thừa:

```
┌───────────────────────────────────┐
│              Circle               │
├───────────────────────────────────┤
│ # radius: double                  │
│ # color: String                   │
├───────────────────────────────────┤
│ + Circle()                        │
│ + Circle(radius, color)           │
│ + getter/setter                   │
│ + getPerimeter(): double          │
│ + getArea(): double               │
│ + toString(): String              │
└───────────────────────────────────┘
                  ▲
                  │ extends
┌───────────────────────────────────┐
│             Cylinder              │
├───────────────────────────────────┤
│ - height: double                  │
├───────────────────────────────────┤
│ + Cylinder(radius, color, height) │
│ + getHeight() / setHeight(height) │
│ + getArea(): double   (ghi đè)    │
│ + getVolume(): double             │
│ + toString(): String  (ghi đè)    │
└───────────────────────────────────┘
```

Ký hiệu `#` là `protected`, `-` là `private`, `+` là `public`.

### Bài 1 (2đ): Lớp cha Circle và ghi đè toString()

**Yêu cầu 1 — Lớp `Circle.java`:** Khai báo theo sơ đồ trên:

- Hai thuộc tính `radius`, `color` có đặc tả `protected`.
- Hàm tạo không tham số gán mặc định `radius = 1.0`, `color = "Trang"`; hàm tạo có tham số gọi `setRadius()` để kiểm tra dữ liệu.
- `setRadius()` chỉ nhận `radius > 0`, ngược lại in `Ban kinh khong hop le` và giữ nguyên giá trị cũ.
- `getPerimeter()` trả về chu vi, `getArea()` trả về diện tích, dùng hằng số `Math.PI`.
- Ghi đè `toString()` trả về chuỗi theo định dạng bên dưới.

**Công thức tính toán:** `ChuVi = 2 × π × radius`; `DienTich = π × radius × radius`

**Yêu cầu 2 — Class `HinhTronDemo.java`:** Trong `main()`:

1. Tạo `c1` bằng hàm tạo không tham số, `c2` bằng hàm tạo có tham số `(2.5, "Do")`.
2. In cả hai bằng `System.out.println(c1);` và `System.out.println(c2);` (không gọi `toString()` tường minh).
3. Gọi `c2.setRadius(-3)` rồi in lại `c2`.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Hinh tron [ban kinh=1.00, mau=Trang, chu vi=6.28, dien tich=3.14]
Hinh tron [ban kinh=2.50, mau=Do, chu vi=15.71, dien tich=19.63]
Ban kinh khong hop le
Hinh tron [ban kinh=2.50, mau=Do, chu vi=15.71, dien tich=19.63]
```

**Lưu ý kỹ thuật:** Dùng `String.format("%.2f", ...)` để làm tròn trong `toString()`. `toString()` là phương thức của lớp `Object`, lớp cha ngầm định của mọi lớp Java; `println(c1)` tự động gọi `c1.toString()`. Sinh viên tạm xóa phương thức `toString()` để quan sát kết quả dạng `com.fpoly.lab6.Circle@1b6d3586` (tên lớp và mã băm) và giải thích trong video. Đặt `@Override` phía trên `toString()`: nếu gõ sai tên (ví dụ `tostring()`), trình biên dịch báo lỗi ngay thay vì âm thầm tạo ra một phương thức mới.

### Bài 2 (2đ): Lớp con Cylinder, từ khóa super

**Yêu cầu 1 — Lớp `Cylinder.java`:** Khai báo `public class Cylinder extends Circle` theo sơ đồ trên:

- **Không** khai báo lại `radius` và `color`; chỉ bổ sung thuộc tính `private double height`.
- Hàm tạo `Cylinder(radius, color, height)` gọi `super(radius, color)` để khởi tạo phần thuộc tính của lớp cha, sau đó gọi `setHeight(height)`.
- `setHeight()` chỉ nhận `height > 0`, ngược lại in `Chieu cao khong hop le` và giữ nguyên giá trị cũ.
- Ghi đè `getArea()` để trả về **diện tích toàn phần** của hình trụ.
- `getVolume()` trả về thể tích, **phải** tính bằng `super.getArea() * height`.
- Ghi đè `toString()` theo định dạng bên dưới.

**Công thức tính toán:** `DienTichToanPhan = 2 × π × radius × height + 2 × π × radius × radius`; `TheTich = π × radius × radius × height`

**Yêu cầu 2 — Class `HinhTruDemo.java`:** Trong `main()`, tạo `Cylinder cy = new Cylinder(2, "Xanh", 3);` rồi in `cy`. Sau đó gọi `cy.setHeight(0)` và in lại `cy`.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Hinh tru [ban kinh=2.00, mau=Xanh, chieu cao=3.00, dien tich toan phan=62.83, the tich=37.70]
Chieu cao khong hop le
Hinh tru [ban kinh=2.00, mau=Xanh, chieu cao=3.00, dien tich toan phan=62.83, the tich=37.70]
```

**Lưu ý kỹ thuật:** Lệnh `super(...)` phải là **câu lệnh đầu tiên** trong hàm tạo của lớp con. Nếu không gọi `super(...)`, Java tự chèn `super()` (hàm tạo không tham số của lớp cha); sinh viên thử xóa hàm tạo không tham số trong `Circle` và bỏ dòng `super(...)` trong `Cylinder` để quan sát lỗi biên dịch, giải thích nguyên nhân. Vì `Cylinder` đã ghi đè `getArea()`, nếu `getVolume()` viết `getArea() * height` thì sẽ gọi **phiên bản của `Cylinder`** (diện tích toàn phần) và cho kết quả sai (`188.50` thay vì `37.70`); `super.getArea()` buộc gọi phiên bản của `Circle` (diện tích đáy). Sinh viên chạy thử cả hai cách và giải thích trong video. `Cylinder` truy cập trực tiếp được `radius`, `color` vì chúng là `protected`; nếu đổi thành `private` thì lớp con phải dùng `getRadius()`, `getColor()`.

### Bài 3 (2đ): Quản lý danh sách hình trụ

Sinh viên tạo class `QuanLyHinhTru.java` trong package `com.fpoly.lab6`.

**Yêu cầu:** Khai báo `List<Cylinder> list = new ArrayList<>();` và thêm sẵn 5 hình trụ sau bằng hàm tạo, theo đúng thứ tự:

| STT | radius | color | height |
|---|---|---|---|
| 1 | 1.0 | Do | 2.0 |
| 2 | 2.0 | Xanh | 3.0 |
| 3 | 1.5 | Vang | 4.0 |
| 4 | 3.0 | Do | 1.0 |
| 5 | 2.5 | Xanh | 2.0 |

Sau đó thực hiện lần lượt:

1. Xuất danh sách bằng vòng lặp `for-each`.
2. Tính tổng thể tích của tất cả các hình trụ.
3. Tìm và xuất hình trụ có thể tích lớn nhất.
4. Nhập một màu, xuất các hình trụ có màu đó (không phân biệt hoa thường); nếu không có thì in `Khong co hinh tru mau [mau]`.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
=== DANH SACH HINH TRU ===
[dong toString() cua tung hinh tru]
Tong the tich: [gia tri, lam tron 2 chu so thap phan]
Hinh tru co the tich lon nhat:
[dong toString() cua hinh tru do]
=== HINH TRU MAU [mau] ===
[dong toString() cua tung hinh tru] | Khong co hinh tru mau [mau]
```

Kết quả đúng với dữ liệu mẫu: tổng thể tích `139.80`; hình trụ có thể tích lớn nhất là hình trụ số 5 (`the tich=39.27`); nhập màu `do` cho kết quả hình trụ số 1 và số 4.

**Lưu ý kỹ thuật:** Biến tổng thể tích khai báo kiểu `double` và khởi tạo bằng `0` trước vòng lặp. Biến lưu hình trụ lớn nhất khởi tạo bằng `list.get(0)` (cùng nguyên tắc với Lab 3, Lab 4). Đối tượng `Cylinder` gọi được `getColor()` dù phương thức này chỉ được khai báo trong `Circle`: lớp con thừa hưởng toàn bộ phương thức `public`/`protected` của lớp cha. Hình trụ số 3 và số 4 có cùng thể tích (`28.27`); sinh viên giải thích vì sao kết quả tìm lớn nhất không bị ảnh hưởng.

### Bài 4 (2đ): Danh sách chứa cả lớp cha và lớp con

Sinh viên tạo class `DanhSachHinh.java` trong package `com.fpoly.lab6`.

**Yêu cầu:** Khai báo **một** danh sách `List<Circle> shapes = new ArrayList<>();` và thêm lần lượt 4 đối tượng:

```java
shapes.add(new Circle(1, "Do"));
shapes.add(new Cylinder(1, "Do", 2));
shapes.add(new Circle(2, "Trang"));
shapes.add(new Cylinder(2, "Xanh", 3));
```

Sau đó thực hiện lần lượt:

1. Duyệt danh sách, với mỗi phần tử in kết quả `toString()` và giá trị `getArea()`.
2. Đếm số hình tròn và số hình trụ trong danh sách bằng toán tử `instanceof`.
3. Tính tổng thể tích của các hình trụ trong danh sách (hình tròn không có thể tích).

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
[dong toString() cua phan tu] -> getArea() = [gia tri, lam tron 2 chu so thap phan]
...
So hinh tron: [so luong]
So hinh tru: [so luong]
Tong the tich cac hinh tru: [gia tri, lam tron 2 chu so thap phan]
```

Kết quả đúng: `So hinh tron: 2`, `So hinh tru: 2`, `Tong the tich cac hinh tru: 43.98`.

**Lưu ý kỹ thuật:** Danh sách kiểu `Circle` chứa được `Cylinder` vì hình trụ **là một** (is-a) hình tròn theo quan hệ kế thừa; ngược lại `List<Cylinder>` không thêm được `Circle`. Khi duyệt `for (Circle s : shapes)`, lệnh `s.getArea()` gọi phiên bản của **đối tượng thực tế** lúc chạy: hình tròn in diện tích, hình trụ in diện tích toàn phần, dù biến `s` khai báo kiểu `Circle`; sinh viên giải thích hiện tượng này trong video. Lệnh `s.getVolume()` báo lỗi biên dịch vì kiểu khai báo `Circle` không có phương thức này; phải kiểm tra `s instanceof Cylinder` rồi ép kiểu `((Cylinder) s).getVolume()`. Khi đếm, phải kiểm tra `instanceof Cylinder` **trước**: nếu kiểm tra `s instanceof Circle` trước thì mọi hình trụ cũng thỏa điều kiện và bị đếm nhầm thành hình tròn. Java không cho phép một lớp `extends` hai lớp cha cùng lúc.

**Tổng điểm Lab 6 = 8.0 điểm (4 bài) + điểm cộng: tối đa 2.0 điểm = 10.0 điểm.**
