# BÀI THỰC HÀNH 08

Các quy định về cấu trúc Repository, quy trình Git, video nộp bài, cách tính điểm và vấn đáp, cùng các chế tài áp dụng chung, được trình bày tại văn bản [Quy định chung toàn khóa](Quy_Dinh_Chung_Toan_Khoa.md). Văn bản này chỉ trình bày Yêu cầu bài tập riêng của Lab 8.

## MỤC TIÊU

Sau bài thực hành này, sinh viên có khả năng:

- Sử dụng các phương thức xử lý chuỗi của lớp `String`: `trim()`, `toUpperCase()`, `toLowerCase()`, `length()`, `substring()`, `charAt()`, `replaceAll()`, `split()`; hiểu tính bất biến (immutable) của `String`.
- Viết biểu thức chính quy (Regex) để kiểm tra định dạng dữ liệu bằng `String.matches()` và bằng cặp lớp `Pattern`/`Matcher`.
- Bắt và xử lý ngoại lệ bằng `try...catch...finally`, sử dụng nhiều khối `catch`; chủ động ném ngoại lệ bằng `throw`.
- Kết hợp vòng lặp với `try...catch` để bắt người dùng nhập lại khi dữ liệu sai.
- Tự định nghĩa ngoại lệ (Custom Exception); phân biệt ngoại lệ Checked và Unchecked.
- Quay video thao tác gõ code trực tiếp và diễn giải bản chất kỹ thuật của xử lý chuỗi và ngoại lệ.

---

## YÊU CẦU

**Vị trí lưu trữ chung:** Toàn bộ các class của Lab 8 nằm trong cùng một Project `Lab8`, package `com.fpoly.lab8` (xem [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 1). Project được tạo bằng **File → New Project → Java with Maven → Java Application**, Project Name `Lab8`, Project Location là thư mục gốc `STT_MSSV_Ten`, Group Id `com.fpoly`.

Cấu trúc thư mục của Lab 8:

```
Lab8/src/main/java/com/fpoly/lab8/
├── XuLyHoTen.java                  (main của Bài 1)
├── KiemTraSinhVien.java            (main của Bài 2)
├── NhapTuoi.java                   (main của Bài 3)
├── InvalidPasswordException.java   (ngoại lệ tự định nghĩa - Bài 4)
└── KiemTraMatKhau.java             (main của Bài 4)
```

### Bài 1 (2đ): Chuẩn hóa họ tên

Sinh viên tạo class `XuLyHoTen.java` trong package `com.fpoly.lab8`.

**Yêu cầu:** Nhập vào họ tên (dùng `sc.nextLine()`). Nếu sau khi xóa khoảng trắng đầu/cuối mà chuỗi rỗng thì in `Ho ten khong duoc de trong` và kết thúc. Ngược lại, thực hiện lần lượt:

1. Xóa khoảng trắng đầu và cuối chuỗi bằng `trim()`, in chuỗi kết quả kèm số ký tự.
2. Thay mọi chuỗi nhiều khoảng trắng liên tiếp giữa các từ thành **một** khoảng trắng bằng `replaceAll("\\s+", " ")`.
3. Chuyển toàn bộ họ tên sang chữ IN HOA.
4. Tách họ tên thành mảng các từ bằng `split(" ")`, in số từ.
5. Viết hoa chữ cái đầu mỗi từ, các chữ còn lại viết thường, ghép lại thành họ tên chuẩn.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Sau trim: [chuoi] ([so ky tu] ky tu)
Chuan hoa khoang trang: [chuoi]
In hoa: [chuoi]
So tu: [so tu]
Ho ten chuan: [chuoi]
```

Ví dụ nhập `   nGUYEN   van    AN  ` (có nhiều khoảng trắng ở đầu, giữa và cuối):

```
Sau trim: nGUYEN   van    AN (18 ky tu)
Chuan hoa khoang trang: nGUYEN van AN
In hoa: NGUYEN VAN AN
So tu: 3
Ho ten chuan: Nguyen Van An
```

**Lưu ý kỹ thuật:** `String` là **bất biến** (immutable): các phương thức như `trim()`, `toUpperCase()` không sửa chuỗi gốc mà trả về chuỗi mới. Nếu chỉ viết `s.trim();` mà không gán lại `s = s.trim();` thì `s` không thay đổi; sinh viên thử lỗi này và giải thích trong video. Nếu gọi `split(" ")` **trước** khi chuẩn hóa khoảng trắng, mảng kết quả chứa các phần tử rỗng `""` và số từ bị đếm sai (ví dụ trên cho ra `8` thay vì `3`). Chuỗi `"\\s+"` trong Java tương ứng với regex `\s+` (một hoặc nhiều ký tự trắng); phải viết hai dấu `\` vì `\` là ký tự thoát trong chuỗi Java. Ghép chữ hoa đầu từ bằng `w.substring(0, 1).toUpperCase() + w.substring(1).toLowerCase()`.

### Bài 2 (2đ): Kiểm tra dữ liệu sinh viên bằng Regex

Sinh viên tạo class `KiemTraSinhVien.java` trong package `com.fpoly.lab8`.

**Yêu cầu:** Nhập lần lượt 4 thông tin của sinh viên. Mỗi thông tin được nhập trong một vòng lặp `do...while`: nếu không khớp biểu thức chính quy thì in thông báo lỗi tương ứng và bắt nhập lại, chỉ chuyển sang thông tin tiếp theo khi hợp lệ.

| Thông tin | Quy tắc | Biểu thức chính quy | Thông báo lỗi |
|---|---|---|---|
| `id` | Bắt đầu bằng `SV`, theo sau là đúng 3 chữ số | `^SV[0-9]{3}$` | `Ma SV phai co dang SVxxx (x la chu so)` |
| `name` | Chỉ chứa chữ cái và khoảng trắng | `^[a-zA-Z ]+$` | `Ho ten chi duoc chua chu cai va khoang trang` |
| `phone` | Bắt đầu bằng `0`, tổng cộng 10 chữ số | `^0[0-9]{9}$` | `So dien thoai phai bat dau bang 0 va co 10 chu so` |
| `email` | Đúng định dạng email cơ bản | `^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$` | `Email khong dung dinh dang` |

Kiểm tra `id`, `name`, `email` bằng `String.matches()`. Riêng `phone` kiểm tra bằng `Pattern.compile(...)` và `matcher(...).matches()` (`import java.util.regex.Pattern;`).

**Dữ liệu xuất ra màn hình** sau khi nhập hợp lệ cả 4 thông tin, theo đúng định dạng mẫu sau:

```
=== THONG TIN SINH VIEN HOP LE ===
Ma SV: [id]
Ho ten: [name]
So dien thoai: [phone]
Email: [email]
```

**Bộ dữ liệu kiểm thử bắt buộc:** Trong video, sinh viên lần lượt nhập các giá trị sau và giải thích vì sao từng giá trị bị từ chối hoặc được chấp nhận:

| Thông tin | Giá trị bị từ chối | Giá trị hợp lệ |
|---|---|---|
| `id` | `sv001`, `SV01`, `SV0012` | `SV001` |
| `name` | `Nguyen Van 2`, `Nguyễn Văn An` | `Nguyen Van An` |
| `phone` | `912345678`, `09123456789`, `09123abc78` | `0912345678` |
| `email` | `an.nv@fpt`, `an nv@fpt.vn`, `@fpt.vn` | `an.nv@fpt.edu.vn` |

**Lưu ý kỹ thuật:** `String.matches()` luôn so khớp **toàn bộ** chuỗi, nên `^` và `$` ở đây không bắt buộc nhưng vẫn nên viết để biểu thức rõ ràng khi dùng ở công cụ khác. Regex có phân biệt hoa thường, vì vậy `sv001` bị từ chối. Tập `[a-zA-Z]` chỉ gồm chữ cái không dấu, nên `Nguyễn Văn An` bị từ chối; sinh viên giải thích nguyên nhân và nêu cách sửa bằng `\\p{L}` (mọi chữ cái Unicode). Trong regex email, `.` bên ngoài `[ ]` nghĩa là "một ký tự bất kỳ", nên dấu chấm trước tên miền phải viết `\\.`; còn trong `[\\w.-]`, dấu `.` là ký tự chấm thật. `Pattern.compile()` biên dịch biểu thức một lần và dùng lại được nhiều lần, phù hợp khi kiểm tra trong vòng lặp.

### Bài 3 (2đ): Bắt ngoại lệ và nhập lại

Sinh viên tạo class `NhapTuoi.java` trong package `com.fpoly.lab8`.

**Yêu cầu:** Nhập tuổi sinh viên (số nguyên) bằng `sc.nextInt()`, kết hợp vòng lặp `while (true)` với `try...catch...finally` để xử lý:

1. Nếu nhập không phải số nguyên (ví dụ `abc`, `20.5`): bắt `InputMismatchException`, in `Loi: Tuoi phai la so nguyen!`.
2. Nếu tuổi `<= 0`: tự ném ngoại lệ bằng `throw new IllegalArgumentException("Tuoi phai lon hon 0");`, bắt lại bằng một khối `catch` riêng và in `Loi: ` kèm `e.getMessage()`.
3. Nếu hợp lệ: thoát vòng lặp bằng `break`.
4. Khối `finally` in `Da xu ly lan nhap thu [k]` (k là số thứ tự lần nhập).
5. Sau vòng lặp, in `Ket thuc qua trinh nhap du lieu. Tuoi hop le: [tuoi]`.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau. Ví dụ lần lượt nhập `abc`, `-5`, `20`:

```
Nhap tuoi: abc
Loi: Tuoi phai la so nguyen!
Da xu ly lan nhap thu 1
Nhap tuoi: -5
Loi: Tuoi phai lon hon 0
Da xu ly lan nhap thu 2
Nhap tuoi: 20
Da xu ly lan nhap thu 3
Ket thuc qua trinh nhap du lieu. Tuoi hop le: 20
```

**Lưu ý kỹ thuật:** Khi `nextInt()` ném `InputMismatchException`, chuỗi sai (`abc`) **vẫn nằm lại** trong bộ đệm của `Scanner`; nếu không gọi `sc.nextLine()` trong khối `catch` để bỏ chuỗi đó đi, vòng lặp sau lại đọc đúng chuỗi cũ và rơi vào **vòng lặp vô hạn**. Sinh viên thử bỏ lệnh này để quan sát và giải thích trong video. Khối `finally` vẫn chạy ở lần nhập thứ 3 dù lệnh `break` đã được gọi trong `try`: `finally` luôn được thực thi trước khi thoát khỏi khối `try`. Khi dùng nhiều `catch`, lớp ngoại lệ cụ thể phải đặt **trước** lớp tổng quát: nếu đặt `catch (Exception e)` trước `catch (InputMismatchException e)` thì trình biên dịch báo lỗi vì khối sau không bao giờ được chạy tới. Cần `import java.util.InputMismatchException;`.

### Bài 4 (2đ): Ngoại lệ tự định nghĩa – kiểm tra mật khẩu

**Yêu cầu 1 — Class `InvalidPasswordException.java`:** Khai báo lớp ngoại lệ tự định nghĩa `public class InvalidPasswordException extends Exception`, có hàm tạo nhận thông báo lỗi và gọi `super(message)`.

**Yêu cầu 2 — Class `KiemTraMatKhau.java`:**

- Viết phương thức `public static void kiemTra(String pw) throws InvalidPasswordException` kiểm tra mật khẩu theo **thứ tự** các quy tắc dưới đây; gặp quy tắc đầu tiên bị vi phạm thì ném `InvalidPasswordException` với thông báo tương ứng.
- Dùng **một** vòng lặp duyệt từng ký tự của mật khẩu (`charAt(i)`) để đếm số chữ hoa, chữ thường, chữ số (dùng `Character.isUpperCase()`, `Character.isLowerCase()`, `Character.isDigit()`) và ký tự đặc biệt.
- Trong `main()`, dùng vòng lặp bắt nhập mật khẩu (dùng `sc.nextLine()`) cho đến khi hợp lệ; gọi `kiemTra()` trong khối `try`, khối `catch (InvalidPasswordException e)` in `Loi: ` kèm `e.getMessage()`. Khi hợp lệ in `Mat khau hop le!`.

| Thứ tự | Quy tắc | Thông báo lỗi |
|---|---|---|
| 1 | Tối thiểu 8 ký tự | `Mat khau phai co it nhat 8 ky tu` |
| 2 | Ít nhất 2 chữ hoa | `Mat khau phai co it nhat 2 chu hoa` |
| 3 | Ít nhất 2 chữ thường | `Mat khau phai co it nhat 2 chu thuong` |
| 4 | Ít nhất 3 chữ số | `Mat khau phai co it nhat 3 chu so` |
| 5 | Ít nhất 1 ký tự đặc biệt thuộc tập `!@#$%^&*` | `Mat khau phai co it nhat 1 ky tu dac biet (!@#$%^&*)` |

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau. Ví dụ lần lượt nhập `abc`, `abcdefgh`, `ABcdefgh`, `ABcd1234`, `ABcd123!`:

```
Nhap mat khau: abc
Loi: Mat khau phai co it nhat 8 ky tu
Nhap mat khau: abcdefgh
Loi: Mat khau phai co it nhat 2 chu hoa
Nhap mat khau: ABcdefgh
Loi: Mat khau phai co it nhat 3 chu so
Nhap mat khau: ABcd1234
Loi: Mat khau phai co it nhat 1 ky tu dac biet (!@#$%^&*)
Nhap mat khau: ABcd123!
Mat khau hop le!
```

**Lưu ý kỹ thuật:** `InvalidPasswordException` kế thừa `Exception` nên là ngoại lệ **Checked**: phương thức ném nó bắt buộc khai báo `throws` trong chữ ký, và nơi gọi bắt buộc phải `try...catch` (hoặc khai báo `throws` tiếp). Sinh viên thử bỏ `throws` hoặc bỏ `try...catch` để quan sát lỗi biên dịch `unreported exception`, rồi so sánh với `IllegalArgumentException` ở Bài 3 (Unchecked, kế thừa `RuntimeException`) vốn không bắt buộc khai báo. Phân biệt `throw` (câu lệnh ném một đối tượng ngoại lệ, đặt trong thân phương thức) và `throws` (khai báo trong chữ ký phương thức). Kiểm tra ký tự đặc biệt bằng `"!@#$%^&*".indexOf(c) >= 0`. Phương thức `kiemTra()` khai báo `static` để gọi được trực tiếp từ `main()` (cũng là `static`) mà không cần tạo đối tượng.

**Tổng điểm Lab 8 = 8.0 điểm (4 bài) + điểm cộng: tối đa 2.0 điểm = 10.0 điểm.**
