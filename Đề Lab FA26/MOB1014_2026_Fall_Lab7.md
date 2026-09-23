# BÀI THỰC HÀNH 07

Các quy định về cấu trúc Repository, quy trình Git, video nộp bài, cách tính điểm và vấn đáp, cùng các chế tài áp dụng chung, được trình bày tại văn bản [Quy định chung toàn khóa](Quy_Dinh_Chung_Toan_Khoa.md). Văn bản này chỉ trình bày Yêu cầu bài tập riêng của Lab 7.

## MỤC TIÊU

Sau bài thực hành này, sinh viên có khả năng:

- Khai báo lớp trừu tượng (`abstract class`) và phương thức trừu tượng; hiểu vì sao không thể tạo đối tượng từ lớp trừu tượng.
- Cài đặt các lớp con cụ thể ghi đè toàn bộ phương thức trừu tượng của lớp cha.
- Vận dụng tính đa hình: gọi cùng một phương thức trên danh sách kiểu lớp cha, phiên bản được thực thi do đối tượng thực tế quyết định lúc chạy.
- Khai báo và cài đặt `interface`; phân biệt `interface` với `abstract class`.
- Tổ chức chương trình theo mô hình interface dịch vụ (`Service`) và lớp cài đặt (`ServiceImpl`).
- Quay video thao tác gõ code trực tiếp và diễn giải bản chất kỹ thuật của trừu tượng và đa hình.

---

## YÊU CẦU

**Vị trí lưu trữ chung:** Toàn bộ các class của Lab 7 nằm trong cùng một Project `Lab7`, package `com.fpoly.lab7` (xem [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 1). Project được tạo bằng **File → New Project → Java with Maven → Java Application**, Project Name `Lab7`, Project Location là thư mục gốc `STT_MSSV_Ten`, Group Id `com.fpoly`.

**Đặc thù của Lab 7:** Lab này gồm các lớp mô hình và interface (không có `main()`), cùng 4 class có `main()` để chạy từng bài.

Cấu trúc thư mục của Lab 7:

```
Lab7/src/main/java/com/fpoly/lab7/
├── Employee.java              (lớp trừu tượng - Bài 1)
├── FullTimeEmployee.java      (lớp con - Bài 1)
├── PartTimeEmployee.java      (lớp con - Bài 1)
├── Payment.java               (interface - Bài 3)
├── CashPayment.java           (cài đặt Payment - Bài 3)
├── CardPayment.java           (cài đặt Payment - Bài 3)
├── EmployeeService.java       (interface - Bài 4)
├── EmployeeServiceImpl.java   (cài đặt EmployeeService - Bài 4)
├── NhanVienDemo.java          (main của Bài 1)
├── QuanLyNhanVien.java        (main của Bài 2)
├── ThanhToanDemo.java         (main của Bài 3)
└── DichVuNhanVien.java        (main của Bài 4)
```

**Dữ liệu mẫu nhân viên** dùng cho Bài 2 (tạo bằng hàm tạo, theo đúng thứ tự):

| Loại | id | name | Thông số lương |
|---|---|---|---|
| FullTimeEmployee | FT01 | Nguyen Van An | basicSalary = 12000000 |
| PartTimeEmployee | PT01 | Tran Thi Binh | workHours = 80, salaryPerHour = 50000 |
| FullTimeEmployee | FT02 | Le Van Cuong | basicSalary = 15500000 |
| PartTimeEmployee | PT02 | Pham Thi Dung | workHours = 120, salaryPerHour = 60000 |
| PartTimeEmployee | PT03 | Hoang Van Em | workHours = 200, salaryPerHour = 90000 |

### Bài 1 (2đ): Lớp trừu tượng Employee

**Yêu cầu 1 — Các lớp mô hình:** Khai báo theo sơ đồ sau (chữ nghiêng *abstract* là thành phần trừu tượng):

```
┌──────────────────────────────────────┐
│       <<abstract>> Employee          │
├──────────────────────────────────────┤
│ # id: String                         │
│ # name: String                       │
├──────────────────────────────────────┤
│ + Employee(id, name)                 │
│ + getter/setter cho id, name         │
│ + abstract getSalary(): double       │
│ + abstract toString(): String        │
└──────────────────────────────────────┘
          ▲                     ▲
          │ extends             │ extends
┌─────────────────────┐ ┌──────────────────────────────┐
│  FullTimeEmployee   │ │      PartTimeEmployee        │
├─────────────────────┤ ├──────────────────────────────┤
│ - basicSalary:double│ │ - workHours: int             │
│                     │ │ - salaryPerHour: double      │
├─────────────────────┤ ├──────────────────────────────┤
│ + FullTimeEmployee( │ │ + PartTimeEmployee(id, name, │
│   id, name,         │ │   workHours, salaryPerHour)  │
│   basicSalary)      │ │ + getter/setter              │
│ + getter/setter     │ │ + getSalary(): double        │
│ + getSalary():double│ │ + toString(): String         │
│ + toString():String │ │                              │
└─────────────────────┘ └──────────────────────────────┘
```

**Công thức tính toán:** Lương nhân viên chính thức `= basicSalary`; Lương nhân viên bán thời gian `= workHours × salaryPerHour`

**Yêu cầu 2 — Class `NhanVienDemo.java`:** Trong `main()`, tạo `FullTimeEmployee("FT01", "Nguyen Van An", 12000000)` và `PartTimeEmployee("PT01", "Tran Thi Binh", 80, 50000)`. Với mỗi nhân viên, in `toString()` và lương.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
[Chinh thuc] ID: FT01 | Ho ten: Nguyen Van An | Luong co ban: 12000000
Luong: 12000000
[Ban thoi gian] ID: PT01 | Ho ten: Tran Thi Binh | So gio: 80 | Luong/gio: 50000
Luong: 4000000
```

Các giá trị tiền in không lấy phần thập phân (`%.0f`).

**Lưu ý kỹ thuật:** Phương thức trừu tượng chỉ có phần khai báo và **kết thúc bằng dấu `;`**, không có thân `{ }`. Lớp chứa ít nhất một phương thức trừu tượng bắt buộc phải khai báo `abstract`. Lớp trừu tượng vẫn có hàm tạo (được lớp con gọi qua `super(id, name)`) nhưng **không** thể tạo đối tượng: sinh viên thử viết `new Employee("X", "Y")` trong `main()` để quan sát lỗi biên dịch, giải thích rồi xóa dòng này. Thử tạm xóa `getSalary()` trong `PartTimeEmployee` để quan sát lỗi `PartTimeEmployee is not abstract and does not override abstract method getSalary()`. `toString()` vốn đã có thân trong lớp `Object`; việc khai báo lại thành `abstract` trong `Employee` là cách **bắt buộc** mọi lớp con phải tự viết `toString()`, thay vì thừa hưởng bản mặc định in ra dạng `TenLop@mabam`.

### Bài 2 (2đ): Đa hình với danh sách nhân viên

Sinh viên tạo class `QuanLyNhanVien.java` trong package `com.fpoly.lab7`.

**Yêu cầu:** Khai báo `List<Employee> list = new ArrayList<>();` và thêm 5 nhân viên theo bảng **Dữ liệu mẫu nhân viên**. Sau đó thực hiện lần lượt:

1. Duyệt danh sách, in `toString()` và lương của từng nhân viên (gọi `getSalary()`).
2. Tính và in tổng quỹ lương.
3. Tìm và in nhân viên có lương cao nhất.
4. Sắp xếp danh sách theo lương **giảm dần** bằng `Collections.sort()` với `Comparator`, xuất lại danh sách.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
=== DANH SACH NHAN VIEN ===
[toString() cua nhan vien] -> Luong: [luong]
...
Tong quy luong: [tong]
Nhan vien luong cao nhat: [toString() cua nhan vien do] -> Luong: [luong]
=== SAP XEP THEO LUONG GIAM DAN ===
[toString() cua nhan vien] -> Luong: [luong]
...
```

Kết quả đúng với dữ liệu mẫu: tổng quỹ lương `56700000`; lương cao nhất là `PT03` (`18000000`); thứ tự sau sắp xếp là `PT03, FT02, FT01, PT02, PT01`.

**Lưu ý kỹ thuật:** Trong vòng lặp `for (Employee e : list)`, lệnh `e.getSalary()` không cần `instanceof` hay ép kiểu như Lab 6 Bài 4: `getSalary()` được khai báo ở lớp cha `Employee` nên trình biên dịch chấp nhận, còn phiên bản nào được chạy (chính thức hay bán thời gian) do **đối tượng thực tế quyết định lúc chạy** — đây là tính đa hình. Sinh viên giải thích trong video: nếu sau này có thêm loại nhân viên mới (ví dụ thực tập sinh), đoạn tính tổng quỹ lương và sắp xếp có cần sửa không, vì sao. Trong `Comparator`, sắp xếp giảm dần bằng cách đảo thứ tự tham số: `Double.compare(b.getSalary(), a.getSalary())`, không dùng `(int)(b - a)` (lý do đã nêu ở Lab 5).

### Bài 3 (2đ): Interface Payment

**Yêu cầu 1 — Interface và các lớp cài đặt:**

```
┌────────────────────────────────────┐
│       <<interface>> Payment        │
├────────────────────────────────────┤
│ TAX: double = 0.1                  │
├────────────────────────────────────┤
│ pay(amount: double): double        │
└────────────────────────────────────┘
          ▲                   ▲
          │ implements        │ implements
┌───────────────────┐ ┌───────────────────┐
│   CashPayment     │ │   CardPayment     │
├───────────────────┤ ├───────────────────┤
│ + pay(amount)     │ │ + pay(amount)     │
└───────────────────┘ └───────────────────┘
```

- `Payment`: hằng số `TAX = 0.1` (thuế 10%) và phương thức `pay(double amount)` trả về số tiền phải thanh toán sau thuế.
- `CashPayment` (tiền mặt): không giảm giá.
- `CardPayment` (thẻ): giảm giá 5% trên giá trị đơn hàng, sau đó mới tính thuế.

**Công thức tính toán:** Tiền mặt `= amount + amount × TAX`; Thẻ `= (amount × 0.95) + (amount × 0.95) × TAX`

**Yêu cầu 2 — Class `ThanhToanDemo.java`:** Trong `main()`:

1. Khai báo mảng giá trị đơn hàng `double[] donHang = {100000, 250000, 1000000};`.
2. Khai báo `List<Payment> methods = new ArrayList<>();` và thêm một `CashPayment`, một `CardPayment`.
3. Với mỗi đơn hàng, duyệt danh sách `methods` và in số tiền phải thanh toán theo từng hình thức.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Don hang [gia tri]: Tien mat = [so tien] | The = [so tien]
```

Kết quả đúng:

```
Don hang 100000: Tien mat = 110000 | The = 104500
Don hang 250000: Tien mat = 275000 | The = 261250
Don hang 1000000: Tien mat = 1100000 | The = 1045000
```

**Lưu ý kỹ thuật:** Thuộc tính trong interface **ngầm định** là `public static final` (hằng số), phương thức ngầm định là `public abstract`. Sinh viên thử gán `TAX = 0.2;` trong `CashPayment` để quan sát lỗi `cannot assign a value to final variable TAX`. Khi cài đặt `pay()` trong lớp, bắt buộc ghi `public`: nếu bỏ `public`, trình biên dịch báo lỗi `attempting to assign weaker access privileges` vì phương thức ghi đè không được kém công khai hơn bản gốc (Slide 6). Không thể viết `new Payment()`. Khai báo danh sách kiểu `Payment` (interface) chứa được cả hai lớp cài đặt, cùng lời gọi `p.pay(amount)` cho ra kết quả khác nhau — đa hình qua interface. Số thực `double` có sai số biểu diễn (ví dụ `100000 * 1.1` cho ra `110000.00000000001`), vì vậy kết quả tiền phải in bằng `printf` với `%.0f`, không in trực tiếp.

### Bài 4 (2đ): Mô hình Service – ServiceImpl

**Yêu cầu 1 — Interface `EmployeeService` và lớp `EmployeeServiceImpl`:**

```
┌──────────────────────────────────────────┐
│       <<interface>> EmployeeService      │
├──────────────────────────────────────────┤
│ add(e: Employee): boolean                │
│ findById(id: String): Employee           │
│ getAll(): List<Employee>                 │
│ getTotalSalary(): double                 │
└──────────────────────────────────────────┘
                    ▲
                    │ implements
┌──────────────────────────────────────────┐
│           EmployeeServiceImpl            │
├──────────────────────────────────────────┤
│ - list: List<Employee> = new ArrayList<>()│
├──────────────────────────────────────────┤
│ cài đặt toàn bộ 4 phương thức            │
└──────────────────────────────────────────┘
```

- `add(e)`: nếu đã tồn tại nhân viên có `id` trùng (không phân biệt hoa thường) thì **không** thêm và trả về `false`; ngược lại thêm vào danh sách và trả về `true`.
- `findById(id)`: trả về nhân viên có `id` tương ứng (không phân biệt hoa thường), không tìm thấy thì trả về `null`.
- `getAll()`: trả về danh sách nhân viên.
- `getTotalSalary()`: trả về tổng lương của toàn bộ nhân viên.

**Yêu cầu 2 — Class `DichVuNhanVien.java`:** Trong `main()`, khai báo `EmployeeService service = new EmployeeServiceImpl();` rồi thực hiện lần lượt:

1. Gọi `service.add(...)` với 4 nhân viên: `FT01`, `PT01`, `FT02` theo bảng dữ liệu mẫu, và `FullTimeEmployee("ft01", "Ngo Van Giang", 9000000)`. Với mỗi lần thêm, in kết quả thành công hay thất bại.
2. Tìm nhân viên có id `pt01` và `XX99`, in kết quả.
3. Xuất toàn bộ danh sách và tổng lương.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Them thanh cong: [id] | Them that bai, trung ID: [id]
Tim thay: [toString() cua nhan vien] | Khong tim thay nhan vien [id]
=== DANH SACH NHAN VIEN ===
[toString() cua nhan vien] -> Luong: [luong]
...
Tong luong: [tong]
```

Kết quả đúng: 3 lần thêm thành công, lần thứ tư `Them that bai, trung ID: ft01`; tìm `pt01` thấy `Tran Thi Binh`, tìm `XX99` không thấy; danh sách có 3 nhân viên, tổng lương `31500000`.

**Lưu ý kỹ thuật:** `main()` chỉ làm việc qua kiểu interface `EmployeeService`, không biết bên trong lưu trữ bằng `ArrayList` hay cách khác (cùng nguyên tắc `List<T> list = new ArrayList<>()` ở Lab 5). Thuộc tính `list` trong `EmployeeServiceImpl` để `private`, bên ngoài chỉ thao tác qua các phương thức của interface. Giá trị trả về `null` của `findById()` phải được kiểm tra `!= null` trước khi dùng; nếu gọi thẳng `service.findById("XX99").getName()` sẽ ném lỗi `NullPointerException` — sinh viên thử và giải thích trong video. Phân biệt trong video: một lớp chỉ `extends` được **một** lớp cha (kể cả lớp trừu tượng) nhưng có thể `implements` **nhiều** interface; interface không có hàm tạo và không chứa biến thường.

**Tổng điểm Lab 7 = 8.0 điểm (4 bài) + điểm cộng: tối đa 2.0 điểm = 10.0 điểm.**
