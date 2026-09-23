# BÀI THỰC HÀNH 04

Các quy định về cấu trúc Repository, quy trình Git, video nộp bài, cách tính điểm và vấn đáp, cùng các chế tài áp dụng chung, được trình bày tại văn bản [Quy định chung toàn khóa](Quy_Dinh_Chung_Toan_Khoa.md). Văn bản này chỉ trình bày Yêu cầu bài tập riêng của Lab 4.

## MỤC TIÊU

Sau bài thực hành này, sinh viên có khả năng:

- Phân biệt lớp (class) và đối tượng (object); khai báo lớp với thuộc tính và phương thức theo mô hình cho trước.
- Tạo đối tượng bằng toán tử `new`, truy xuất thành viên bằng dấu chấm `.`, hiểu bản chất biến đối tượng là biến tham chiếu.
- Xây dựng hàm tạo (constructor) không tham số và có tham số; sử dụng từ khóa `this`.
- Áp dụng tính đóng gói: đặc tả truy xuất `private`/`public`, phương thức getter/setter có kiểm soát dữ liệu.
- Quản lý danh sách đối tượng bằng mảng đối tượng, kết hợp kiến thức vòng lặp và sắp xếp của Lab 3.
- Quay video thao tác gõ code trực tiếp và diễn giải bản chất kỹ thuật của từng thành phần trong lớp.

---

## YÊU CẦU

**Vị trí lưu trữ chung:** Toàn bộ các class của Lab 4 nằm trong cùng một Project `Lab4`, package `com.fpoly.lab4` (xem [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 1). Project được tạo bằng **File → New Project → Java with Maven → Java Application**, Project Name `Lab4`, Project Location là thư mục gốc `STT_MSSV_Ten`, Group Id `com.fpoly`.

**Đặc thù của Lab 4:** Lab này gồm một lớp mô hình `Student.java` (không có `main()`) được **nâng cấp dần qua từng bài**, và 4 class có `main()` để chạy từng bài. Mỗi lần nâng cấp `Student` xong một bài, sinh viên commit riêng (Atomic Commit) để lịch sử Git thể hiện rõ từng bước. Các class `main()` của bài trước phải tiếp tục biên dịch và chạy đúng sau khi `Student` được nâng cấp ở bài sau.

Cấu trúc thư mục của Lab 4:

```
Lab4/src/main/java/com/fpoly/lab4/
├── Student.java            (lớp mô hình, dùng chung cho cả 4 bài)
├── NhapXuatSinhVien.java   (main của Bài 1)
├── KhoiTaoSinhVien.java    (main của Bài 2)
├── DongGoiSinhVien.java    (main của Bài 3)
└── QuanLySinhVien.java     (main của Bài 4)
```

### Bài 1 (2đ): Xây dựng lớp và tạo đối tượng

**Yêu cầu 1 — Lớp `Student.java`:** Khai báo lớp theo mô hình sau (dấu `+` là `public`):

```
┌──────────────────────────────┐
│           Student            │
├──────────────────────────────┤
│ + id: String                 │
│ + name: String               │
│ + age: int                   │
│ + gpa: double                │
├──────────────────────────────┤
│ + input(sc: Scanner): void   │
│ + output(): void             │
│ + rank(): String             │
└──────────────────────────────┘
```

- `input(Scanner sc)`: nhập lần lượt `id`, `name`, `age`, `gpa` từ bàn phím.
- `rank()`: trả về xếp loại dựa trên `gpa` theo bảng dưới.
- `output()`: in thông tin sinh viên trên một dòng, gồm cả xếp loại (gọi `rank()`).

| Điều kiện | Xếp loại |
|---|---|
| `gpa >= 9.0` | Excellent |
| `gpa >= 8.0` | Very Good |
| `gpa >= 6.5` | Good |
| `gpa >= 5.0` | Average |
| Còn lại | Fail |

**Yêu cầu 2 — Class `NhapXuatSinhVien.java`:** Trong `main()`, tạo **2 đối tượng** `sv1`, `sv2` bằng toán tử `new`, gọi `input()` để nhập từ bàn phím, sau đó gọi `output()` để xuất thông tin cả hai.

**Dữ liệu xuất ra màn hình** (phương thức `output()`), theo đúng định dạng mẫu sau:

```
ID: [id] | Ho ten: [name] | Tuoi: [age] | GPA: [gpa, lam tron 2 chu so thap phan] | Xep loai: [rank]
```

Ví dụ:

```
ID: PS001 | Ho ten: Nguyen Van An | Tuoi: 19 | GPA: 8.25 | Xep loai: Very Good
```

**Lưu ý kỹ thuật:** Phương thức `input()` nhận `Scanner` làm **tham số** thay vì tự tạo `new Scanner(System.in)` bên trong: cả chương trình chỉ nên dùng một đối tượng `Scanner` đọc từ `System.in`. Nhập `name` bằng `nextLine()` để đọc được họ tên có dấu cách; chú ý lỗi trôi lệnh sau `nextInt()`/`nextDouble()` đã học ở Lab 1. `rank()` xét điều kiện từ cao xuống thấp như Lab 2. Cuối `main()`, sinh viên viết thêm `Student sv3 = sv1; sv3.name = "Test";` rồi gọi `sv1.output()` để quan sát và giải thích trong video vì sao `sv1` cũng bị đổi tên (biến đối tượng là biến tham chiếu, tương tự mảng ở Lab 3).

### Bài 2 (2đ): Hàm tạo và từ khóa this

**Yêu cầu 1 — Nâng cấp `Student.java`:** Bổ sung 2 hàm tạo:

```
+ Student()
+ Student(id: String, name: String, age: int, gpa: double)
```

Hàm tạo có tham số dùng từ khóa `this` để gán giá trị tham số cho thuộc tính cùng tên (`this.name = name;`).

**Yêu cầu 2 — Class `KhoiTaoSinhVien.java`:** Trong `main()`:

1. Tạo `sv1` bằng hàm tạo có tham số với dữ liệu cố định `"PS001"`, `"Nguyen Van An"`, `19`, `8.25`, rồi gọi `output()`.
2. Tạo `sv2` bằng hàm tạo không tham số, gọi `output()` **ngay** để quan sát giá trị mặc định, sau đó gọi `input()` nhập từ bàn phím và gọi `output()` lần nữa.

**Dữ liệu xuất ra màn hình** dùng định dạng `output()` của Bài 1. Kết quả `sv2` trước khi nhập:

```
ID: null | Ho ten: null | Tuoi: 0 | GPA: 0.00 | Xep loai: Fail
```

**Lưu ý kỹ thuật:** Hàm tạo trùng tên lớp và **không có kiểu trả về** (kể cả `void`; nếu viết `public void Student()` thì đó là một phương thức thường chứ không phải hàm tạo). Khi lớp chưa khai báo hàm tạo nào, Java tự sinh hàm tạo mặc định không tham số; nhưng khi đã khai báo hàm tạo có tham số, Java **không** tự sinh nữa, nên nếu thiếu `Student()` thì `new Student()` ở Bài 1 sẽ báo lỗi biên dịch. Sinh viên thử xóa `this.` trong một câu gán (ví dụ `name = name;`) rồi chạy lại để thấy thuộc tính vẫn là `null`, và giải thích nguyên nhân: tham số cục bộ che khuất (shadow) thuộc tính cùng tên. Giá trị mặc định của thuộc tính: `String` là `null`, `int` là `0`, `double` là `0.0`.

### Bài 3 (2đ): Đóng gói với getter/setter

**Yêu cầu 1 — Nâng cấp `Student.java`:** Chuyển toàn bộ 4 thuộc tính sang `private` (dấu `-` trong mô hình) và bổ sung getter/setter cho tất cả thuộc tính:

```
┌──────────────────────────────────────────┐
│                 Student                  │
├──────────────────────────────────────────┤
│ - id: String                             │
│ - name: String                           │
│ - age: int                               │
│ - gpa: double                            │
├──────────────────────────────────────────┤
│ + Student()                              │
│ + Student(id, name, age, gpa)            │
│ + getId() / setId(id)                    │
│ + getName() / setName(name)              │
│ + getAge() / setAge(age)                 │
│ + getGpa() / setGpa(gpa)                 │
│ + input(sc: Scanner): void               │
│ + output(): void                         │
│ + rank(): String                         │
└──────────────────────────────────────────┘
```

Setter phải **kiểm soát dữ liệu** trước khi gán:

| Setter | Điều kiện hợp lệ | Khi không hợp lệ |
|---|---|---|
| `setAge(int age)` | `age > 0` | In `Tuoi khong hop le`, giữ nguyên giá trị cũ |
| `setGpa(double gpa)` | `0 <= gpa <= 10` | In `GPA khong hop le`, giữ nguyên giá trị cũ |

Hàm tạo có tham số phải gọi `setAge()` và `setGpa()` thay vì gán trực tiếp, để dữ liệu truyền qua hàm tạo cũng được kiểm tra.

**Yêu cầu 2 — Class `DongGoiSinhVien.java`:** Trong `main()`:

1. Tạo `sv` bằng hàm tạo có tham số: `"PS002"`, `"Tran Thi Binh"`, `20`, `7.0`.
2. Gọi `sv.setGpa(12)`, sau đó in `GPA hien tai: ` kèm `sv.getGpa()` (làm tròn 2 chữ số thập phân).
3. Gọi `sv.setGpa(9.2)`, sau đó gọi `sv.output()`.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
GPA khong hop le
GPA hien tai: 7.00
ID: PS002 | Ho ten: Tran Thi Binh | Tuoi: 20 | GPA: 9.20 | Xep loai: Excellent
```

**Lưu ý kỹ thuật:** Sinh viên được phép dùng chức năng **Insert Code → Getter and Setter** của NetBeans để sinh khung getter/setter, nhưng phần kiểm tra dữ liệu trong setter phải tự gõ và giải thích. Trong `main()`, thử viết `sv.gpa = 15;` để quan sát lỗi biên dịch `gpa has private access in Student`, giải thích trong video rồi xóa dòng này. Lưu ý các phương thức `input()`, `output()`, `rank()` nằm **bên trong** lớp `Student` nên vẫn truy cập trực tiếp thuộc tính `private` được; `private` chỉ chặn truy cập từ **bên ngoài** lớp. Sau khi nâng cấp, chạy lại `NhapXuatSinhVien` và `KhoiTaoSinhVien` để kiểm tra vẫn hoạt động (riêng dòng `sv3.name = "Test";` ở Bài 1 phải sửa thành `sv3.setName("Test");`).

### Bài 4 (2đ): Quản lý danh sách sinh viên bằng mảng đối tượng

Sinh viên tạo class `QuanLySinhVien.java` trong package `com.fpoly.lab4`.

**Yêu cầu:** Nhập số lượng sinh viên `n` (dùng `do...while` bắt nhập lại nếu `n <= 0`). Khai báo mảng `Student[] ds = new Student[n];`, dùng vòng lặp `for` tạo và nhập thông tin cho từng sinh viên. Sau đó thực hiện lần lượt:

1. Xuất danh sách sinh viên vừa nhập.
2. Tìm và xuất sinh viên có GPA cao nhất (nếu nhiều sinh viên cùng GPA cao nhất, xuất sinh viên xuất hiện đầu tiên).
3. Sắp xếp danh sách theo GPA **giảm dần** bằng thuật toán Bubble Sort (Lab 3) và xuất lại danh sách.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
=== DANH SACH SINH VIEN ===
[dong output() cua tung sinh vien]
=== SINH VIEN CO GPA CAO NHAT ===
[dong output() cua sinh vien do]
=== DANH SACH SAU KHI SAP XEP GIAM DAN THEO GPA ===
[dong output() cua tung sinh vien]
```

**Lưu ý kỹ thuật:** Câu lệnh `new Student[n]` chỉ tạo ra `n` **ô tham chiếu có giá trị `null`**, chưa tạo đối tượng nào. Nếu gọi `ds[i].input(sc)` mà chưa có `ds[i] = new Student();` thì chương trình ném lỗi `NullPointerException`; sinh viên cố ý bỏ dòng `new` để quan sát và giải thích lỗi này trong video. Vì thuộc tính đã `private`, khi so sánh phải dùng `ds[j].getGpa()`, không dùng `ds[j].gpa`. Khi hoán đổi trong Bubble Sort, hoán đổi **tham chiếu** của hai phần tử bằng một biến tạm kiểu `Student` (`Student tmp = ds[j];`), không cần hoán đổi từng thuộc tính. Biến lưu sinh viên GPA cao nhất khởi tạo bằng `ds[0]` (cùng lý do với `max = a[0]` ở Lab 3) và chỉ cập nhật khi gặp GPA **lớn hơn hẳn** (`>`), để giữ sinh viên xuất hiện đầu tiên khi có nhiều người cùng GPA cao nhất.

**Tổng điểm Lab 4 = 8.0 điểm (4 bài) + điểm cộng: tối đa 2.0 điểm = 10.0 điểm.**
