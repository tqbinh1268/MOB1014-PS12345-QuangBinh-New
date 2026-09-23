# BÀI THỰC HÀNH 05

Các quy định về cấu trúc Repository, quy trình Git, video nộp bài, cách tính điểm và vấn đáp, cùng các chế tài áp dụng chung, được trình bày tại văn bản [Quy định chung toàn khóa](Quy_Dinh_Chung_Toan_Khoa.md). Văn bản này chỉ trình bày Yêu cầu bài tập riêng của Lab 5.

## MỤC TIÊU

Sau bài thực hành này, sinh viên có khả năng:

- Phân biệt mảng và `ArrayList`; phân biệt interface `List` và lớp `ArrayList`; khai báo danh sách có định kiểu theo cách khuyến nghị `List<T> list = new ArrayList<>();`.
- Sử dụng các phương thức thường dùng của `ArrayList`: `add()`, `get()`, `set()`, `remove()`, `size()`, `isEmpty()`.
- Lưu trữ và quản lý danh sách đối tượng: nhập, xuất, lọc, tìm kiếm, cập nhật, xóa.
- Sắp xếp danh sách đối tượng bằng Bubble Sort với `compareToIgnoreCase()` và bằng `Collections.sort()` kết hợp `Comparator`.
- Quay video thao tác gõ code trực tiếp và diễn giải bản chất kỹ thuật của từng thao tác trên danh sách.

---

## YÊU CẦU

**Vị trí lưu trữ chung:** Toàn bộ các class của Lab 5 nằm trong cùng một Project `Lab5`, package `com.fpoly.lab5` (xem [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 1). Project được tạo bằng **File → New Project → Java with Maven → Java Application**, Project Name `Lab5`, Project Location là thư mục gốc `STT_MSSV_Ten`, Group Id `com.fpoly`.

**Đặc thù của Lab 5:** Tương tự Lab 4, Lab này gồm một lớp mô hình `Laptop.java` (không có `main()`) dùng chung, và 4 class có `main()` để chạy từng bài.

Cấu trúc thư mục của Lab 5:

```
Lab5/src/main/java/com/fpoly/lab5/
├── Laptop.java            (lớp mô hình, dùng chung cho cả 4 bài)
├── NhapXuatLaptop.java    (main của Bài 1)
├── TimKiemLaptop.java     (main của Bài 2)
├── CapNhatLaptop.java     (main của Bài 3)
└── SapXepLaptop.java      (main của Bài 4)
```

**Dữ liệu mẫu dùng cho Bài 2, 3, 4:** Để tránh phải nhập tay nhiều lần, ở đầu `main()` của Bài 2, 3, 4, sinh viên tạo danh sách và thêm sẵn 5 laptop sau bằng **hàm tạo có tham số** (`list.add(new Laptop(...))`), theo đúng thứ tự:

| id | name | type | price | quantity |
|---|---|---|---|---|
| LT01 | Dell Inspiron 15 | Van phong | 15500000 | 10 |
| LT02 | Asus TUF Gaming F15 | Gaming | 22990000 | 5 |
| LT03 | MacBook Air M2 | Van phong | 24990000 | 8 |
| LT04 | Lenovo Legion 5 | Gaming | 32500000 | 0 |
| LT05 | HP Pavilion 14 | Van phong | 17800000 | 0 |

### Bài 1 (2đ): Lớp Laptop và nhập xuất danh sách bằng ArrayList

**Yêu cầu 1 — Lớp `Laptop.java`:** Khai báo lớp theo mô hình sau, áp dụng tính đóng gói đã học ở Lab 4:

```
┌──────────────────────────────────────────┐
│                  Laptop                  │
├──────────────────────────────────────────┤
│ - id: String                             │
│ - name: String                           │
│ - type: String                           │
│ - price: double                          │
│ - quantity: int                          │
├──────────────────────────────────────────┤
│ + Laptop()                               │
│ + Laptop(id, name, type, price, quantity)│
│ + getter/setter cho tất cả thuộc tính    │
│ + input(sc: Scanner): void               │
│ + output(): void                         │
└──────────────────────────────────────────┘
```

Setter phải kiểm soát dữ liệu: `setPrice()` chỉ nhận `price > 0`, `setQuantity()` chỉ nhận `quantity >= 0`; nếu không hợp lệ thì in `Gia khong hop le` / `So luong khong hop le` và giữ nguyên giá trị cũ. Hàm tạo có tham số gọi các setter này.

**Yêu cầu 2 — Class `NhapXuatLaptop.java`:** Trong `main()`, khai báo `List<Laptop> list = new ArrayList<>();`. Dùng vòng lặp `do...while` nhập lần lượt từng laptop và thêm vào danh sách bằng `add()`; sau mỗi laptop hỏi `Tiep tuc nhap? (y/n): `, nhập `y` (không phân biệt hoa thường) thì nhập tiếp, ký tự khác thì dừng. Sau đó xuất số lượng và toàn bộ danh sách bằng vòng lặp `for-each`.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
=== DANH SACH LAPTOP ([so luong] may) ===
[dong output() cua tung laptop]
```

Định dạng phương thức `output()`:

```
ID: [id] | Ten: [name] | Loai: [type] | Gia: [price, khong lay phan thap phan] | SL: [quantity]
```

Ví dụ:

```
ID: LT01 | Ten: Dell Inspiron 15 | Loai: Van phong | Gia: 15500000 | SL: 10
```

**Lưu ý kỹ thuật:** Khác với mảng ở Lab 4 (`new Student[n]` phải biết trước `n`), `ArrayList` tự tăng kích thước khi `add()`, nên chương trình không cần hỏi trước số lượng. Khai báo kiểu biến là `List` (interface) còn đối tượng là `ArrayList` (lớp cài đặt) để lập trình theo interface. Phần `<Laptop>` là định kiểu: danh sách chỉ nhận đối tượng `Laptop`, lấy ra không cần ép kiểu. Câu lệnh `new Laptop()` phải đặt **bên trong** vòng lặp: nếu tạo một đối tượng duy nhất bên ngoài rồi `add()` nhiều lần, mọi phần tử trong danh sách cùng trỏ tới một đối tượng và in ra giống hệt laptop nhập cuối cùng. Sinh viên cố ý thử lỗi này và giải thích trong video. Dùng `equalsIgnoreCase("y")` để so sánh chuỗi.

### Bài 2 (2đ): Lọc theo khoảng giá và tìm kiếm

Sinh viên tạo class `TimKiemLaptop.java` trong package `com.fpoly.lab5`, khởi tạo danh sách từ dữ liệu mẫu.

**Yêu cầu:**

1. Nhập giá `min` và `max`. Nếu `min > max` thì in `Khoang gia khong hop le`. Ngược lại, xuất các laptop có `min <= price <= max`.
2. Nhập từ khóa `keyword`. Xuất các laptop có `id` **trùng khớp** với từ khóa (không phân biệt hoa thường) **hoặc** có `name` **chứa** từ khóa (không phân biệt hoa thường).

Với cả hai yêu cầu, nếu không có laptop nào thỏa mãn thì in `Khong tim thay laptop nao`.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
=== LAPTOP GIA TU [min] DEN [max] ===
[dong output() cua tung laptop] | Khong tim thay laptop nao
=== KET QUA TIM KIEM "[keyword]" ===
[dong output() cua tung laptop] | Khong tim thay laptop nao
```

Ví dụ nhập `min = 15000000`, `max = 20000000`, `keyword = gaming`:

```
=== LAPTOP GIA TU 15000000 DEN 20000000 ===
ID: LT01 | Ten: Dell Inspiron 15 | Loai: Van phong | Gia: 15500000 | SL: 10
ID: LT05 | Ten: HP Pavilion 14 | Loai: Van phong | Gia: 17800000 | SL: 0
=== KET QUA TIM KIEM "gaming" ===
ID: LT02 | Ten: Asus TUF Gaming F15 | Loai: Gaming | Gia: 22990000 | SL: 5
```

**Lưu ý kỹ thuật:** Không dùng toán tử `==` để so sánh chuỗi: `==` so sánh **địa chỉ tham chiếu**, chuỗi nhập từ `Scanner` là đối tượng mới nên `==` trả về `false` dù nội dung giống nhau; phải dùng `equals()` / `equalsIgnoreCase()`. Phương thức `contains()` có phân biệt hoa thường, nên cần đưa cả hai chuỗi về cùng dạng bằng `toLowerCase()` trước khi so sánh. Dùng biến cờ `boolean timThay` như Lab 3 để quyết định có in `Khong tim thay laptop nao` hay không. Nhập `keyword` bằng `nextLine()` và xử lý trôi lệnh sau `nextDouble()`.

### Bài 3 (2đ): Cập nhật và xóa phần tử

Sinh viên tạo class `CapNhatLaptop.java` trong package `com.fpoly.lab5`, khởi tạo danh sách từ dữ liệu mẫu.

**Yêu cầu:**

1. Nhập `id` cần sửa và tên mới. Tìm laptop theo `id` (không phân biệt hoa thường); nếu tìm thấy thì cập nhật `name` bằng `setName()` và in `Da cap nhat laptop [id]`, ngược lại in `Khong tim thay laptop co ID [id]`.
2. Xóa **tất cả** các laptop đã hết hàng (`quantity == 0`) khỏi danh sách, in số lượng laptop đã xóa.
3. Xuất danh sách sau khi cập nhật và xóa.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Da cap nhat laptop [id] | Khong tim thay laptop co ID [id]
Da xoa [so luong] laptop het hang
=== DANH SACH SAU CAP NHAT ([so luong] may) ===
[dong output() cua tung laptop]
```

Ví dụ nhập `id = lt02`, tên mới `Asus TUF Gaming A15`:

```
Da cap nhat laptop lt02
Da xoa 2 laptop het hang
=== DANH SACH SAU CAP NHAT (3 may) ===
ID: LT01 | Ten: Dell Inspiron 15 | Loai: Van phong | Gia: 15500000 | SL: 10
ID: LT02 | Ten: Asus TUF Gaming A15 | Loai: Gaming | Gia: 22990000 | SL: 5
ID: LT03 | Ten: MacBook Air M2 | Loai: Van phong | Gia: 24990000 | SL: 8
```

**Lưu ý kỹ thuật:** Dữ liệu mẫu cố ý đặt hai laptop hết hàng **liền kề nhau** (LT04, LT05). Nếu duyệt `for (int i = 0; i < list.size(); i++)` từ đầu và gọi `list.remove(i)`, các phần tử phía sau bị dồn lên một vị trí nên LT05 bị **bỏ sót**; sinh viên chạy thử cách sai này, quan sát kết quả còn lại LT05 và giải thích trong video. Cách đúng là **duyệt ngược** từ `list.size() - 1` về `0`. Không xóa phần tử bên trong vòng `for-each`: Java sẽ ném lỗi `ConcurrentModificationException`. Phân biệt `remove(int index)` (xóa theo vị trí) và `remove(Object o)` (xóa theo đối tượng). Khi cập nhật tên, sau khi tìm thấy dùng `break` để dừng vì `id` là duy nhất.

### Bài 4 (2đ): Sắp xếp danh sách đối tượng

Sinh viên tạo class `SapXepLaptop.java` trong package `com.fpoly.lab5`, khởi tạo danh sách từ dữ liệu mẫu.

**Yêu cầu:**

1. Sắp xếp danh sách theo `name` **tăng dần** (A → Z, không phân biệt hoa thường) bằng thuật toán **Bubble Sort** tự viết, sử dụng `compareToIgnoreCase()`. Xuất danh sách.
2. Sắp xếp danh sách theo `price` **giảm dần** bằng `Collections.sort()` với một `Comparator` so sánh theo `price` tăng dần, sau đó dùng `Collections.reverse()` để đảo ngược. Xuất danh sách.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
=== SAP XEP THEO TEN (A-Z) ===
[dong output() cua tung laptop]
=== SAP XEP THEO GIA GIAM DAN ===
[dong output() cua tung laptop]
```

Kết quả đúng với dữ liệu mẫu: theo tên là `LT02, LT01, LT05, LT04, LT03`; theo giá giảm dần là `LT04, LT03, LT02, LT05, LT01`.

**Lưu ý kỹ thuật:** `compareToIgnoreCase()` trả về số âm, `0` hoặc số dương tương ứng chuỗi thứ nhất đứng trước, bằng, hoặc đứng sau chuỗi thứ hai theo thứ tự từ điển; Bubble Sort tăng dần hoán đổi khi kết quả `> 0`. Hoán đổi hai phần tử của `ArrayList` bằng `get()` và `set()` với biến tạm (hoặc `Collections.swap(list, i, j)`), không dùng cú pháp `list[i]` như mảng. Trong `Comparator`, so sánh hai giá trị `double` bằng `Double.compare(a, b)`; **không** viết `return (int) (a - b);` vì phép ép kiểu cắt bỏ phần thập phân (hiệu `0.5` thành `0`, hai giá trị khác nhau bị coi là bằng nhau). Cần `import java.util.Collections;` và `import java.util.Comparator;`.

**Tổng điểm Lab 5 = 8.0 điểm (4 bài) + điểm cộng: tối đa 2.0 điểm = 10.0 điểm.**
