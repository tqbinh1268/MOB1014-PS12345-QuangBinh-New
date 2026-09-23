# BÀI THỰC HÀNH 02

Các quy định về cấu trúc Repository, quy trình Git, video nộp bài, cách tính điểm và vấn đáp, cùng các chế tài áp dụng chung, được trình bày tại văn bản [Quy định chung toàn khóa](Quy_Dinh_Chung_Toan_Khoa.md). Văn bản này chỉ trình bày Yêu cầu bài tập riêng của Lab 2.

## MỤC TIÊU

Sau bài thực hành này, sinh viên có khả năng:

- Sử dụng thành thạo ba dạng cấu trúc rẽ nhánh `if`: `if` đơn, `if...else` và `if...else if...else`.
- Sử dụng cấu trúc `switch...case` với `break`, `default` và kỹ thuật gộp case cho các trường hợp có cùng kết quả.
- Lựa chọn đúng giữa `if` và `switch` tùy theo bài toán (so sánh theo khoảng giá trị hay so sánh bằng).
- Kiểm tra dữ liệu đầu vào không hợp lệ và xử lý các trường hợp biên (số 0, số âm, chia cho 0, giá trị ngoài miền).
- Quay video thao tác gõ code trực tiếp và diễn giải bản chất kỹ thuật của từng nhánh xử lý.

---

## YÊU CẦU

**Vị trí lưu trữ chung:** Toàn bộ 4 bài của Lab 2 là 4 class nằm trong cùng một Project `Lab2`, package `com.fpoly.lab2` (xem [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 1). Project được tạo bằng **File → New Project → Java with Maven → Java Application**, Project Name `Lab2`, Project Location là thư mục gốc `STT_MSSV_Ten`, Group Id `com.fpoly`. Class chính `Lab2.java` do NetBeans tự sinh có thể giữ nguyên hoặc xóa.

### Bài 1 (2đ): Kiểm tra số chẵn/lẻ, âm/dương

Sinh viên tạo class `KiemTraSo.java` trong package `com.fpoly.lab2`.

**Yêu cầu:** Nhập vào một số nguyên `n` (dùng `sc.nextInt()`). Cho biết:

1. `n` là số chẵn hay số lẻ (dùng cấu trúc `if...else`).
2. `n` là số dương, số âm hay bằng 0 (dùng cấu trúc `if...else if...else`).

**Điều kiện xét:** `n` chẵn khi `n % 2 == 0`, ngược lại là số lẻ. `n` dương khi `n > 0`, âm khi `n < 0`, còn lại là bằng 0.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
[n] la so [chan/le]
[n] la so [duong/am] | [n] bang 0
```

Ví dụ nhập `-7`:

```
-7 la so le
-7 la so am
```

**Lưu ý kỹ thuật:** Không dùng điều kiện `n % 2 == 1` để xác định số lẻ. Trong Java, phép chia lấy dư giữ dấu của số bị chia, nên `-7 % 2` cho kết quả `-1` chứ không phải `1`; điều kiện `n % 2 == 1` sẽ xếp sai mọi số lẻ âm vào nhánh "chẵn". Sinh viên chạy thử với các giá trị `0`, `8`, `-7`, `-8` để kiểm chứng. Lưu ý số `0` là số chẵn, nhưng không phải số dương cũng không phải số âm.

### Bài 2 (2đ): Tính điểm trung bình và xếp loại học lực

Sinh viên tạo class `XepLoaiHocLuc.java` trong package `com.fpoly.lab2`.

**Yêu cầu:** Nhập lần lượt điểm 3 môn Toán, Lý, Hóa (số thực, dùng `sc.nextDouble()`). Kiểm tra dữ liệu hợp lệ trước khi tính: nếu có bất kỳ điểm nào nhỏ hơn 0 hoặc lớn hơn 10 thì in ra `Diem khong hop le` và kết thúc chương trình (dùng lệnh `return;`). Nếu hợp lệ, tính điểm trung bình (môn Toán nhân hệ số 2) và xếp loại theo bảng:

| Điều kiện | Xếp loại |
|---|---|
| `dtb >= 8.0` | Gioi |
| `dtb >= 6.5` | Kha |
| `dtb >= 5.0` | Trung binh |
| Còn lại | Yeu |

**Công thức tính toán:** `DiemTB = (Toan × 2 + Ly + Hoa) / 4`

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Diem trung binh: [gia tri, lam tron 2 chu so thap phan]
Xep loai: [Gioi/Kha/Trung binh/Yeu]
```

**Lưu ý kỹ thuật:** Dùng cấu trúc `if...else if...else` và sắp xếp điều kiện **từ cao xuống thấp**. Các nhánh được xét tuần tự, gặp nhánh đúng đầu tiên thì bỏ qua các nhánh còn lại; nếu đảo thứ tự (xét `dtb >= 5.0` trước) thì học sinh 9 điểm cũng bị xếp "Trung binh". Mẫu số là `4` (không phải `3`) vì Toán được tính 2 lần. Điều kiện kiểm tra dữ liệu dùng toán tử logic `||` để gộp 6 trường hợp sai trong một câu lệnh `if`.

### Bài 3 (2đ): Xác định mùa theo tháng

Sinh viên tạo class `MuaTrongNam.java` trong package `com.fpoly.lab2`.

**Yêu cầu:** Nhập vào tháng `thang` (số nguyên, dùng `sc.nextInt()`). Dùng cấu trúc `switch...case` với kỹ thuật **gộp case** để in ra mùa tương ứng:

| Tháng | Mùa |
|---|---|
| 1, 2, 3 | Mua xuan |
| 4, 5, 6 | Mua ha |
| 7, 8, 9 | Mua thu |
| 10, 11, 12 | Mua dong |
| Giá trị khác | Thang khong hop le |

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Thang [thang]: [Mua xuan/Mua ha/Mua thu/Mua dong]
```

Trường hợp nhập ngoài khoảng 1–12 (ví dụ `0`, `13`, `-5`), in ra `Thang khong hop le`.

**Lưu ý kỹ thuật:** Gộp case bằng cách viết các `case` liên tiếp không có `break` ở giữa (ví dụ `case 1: case 2: case 3:`), hoặc viết gọn `case 1, 2, 3:`. Mỗi nhóm phải kết thúc bằng `break;`. Sinh viên thử xóa `break;` ở nhóm Mùa xuân rồi nhập tháng `2` để quan sát hiện tượng **rơi case (fall-through)**: chương trình chạy tiếp xuống in cả "Mua ha", sau đó giải thích nguyên nhân trong video. Khối `default` xử lý mọi giá trị không khớp case nào.

### Bài 4 (2đ): Máy tính 4 phép toán

Sinh viên tạo class `MayTinh.java` trong package `com.fpoly.lab2`.

**Yêu cầu:** Nhập lần lượt số thực `a`, số thực `b` (dùng `sc.nextDouble()`) và một ký tự phép toán `op` (dùng `sc.next().charAt(0)`). Dùng cấu trúc `switch` theo `op` để thực hiện phép toán tương ứng:

| `op` | Phép toán |
|---|---|
| `'+'` | `a + b` |
| `'-'` | `a - b` |
| `'*'` | `a * b` |
| `'/'` | `a / b` |
| Ký tự khác | Phep toan khong hop le |

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
[a] [op] [b] = [ket qua, lam tron 2 chu so thap phan]
```

Ví dụ nhập `7`, `2`, `/`:

```
7.00 / 2.00 = 3.50
```

Trường hợp `op` là phép chia và `b == 0`, in ra `Khong the chia cho 0`. Trường hợp `op` không thuộc 4 ký tự trên, in ra `Phep toan khong hop le`.

**Lưu ý kỹ thuật:** `op` có kiểu `char`, vì vậy các nhãn case viết trong dấu nháy đơn (`case '+':`), không phải nháy kép (`"+"` là kiểu `String`). Lớp `Scanner` không có phương thức `nextChar()`; `sc.next()` đọc một chuỗi (token), sau đó `.charAt(0)` lấy ký tự đầu tiên. Trong `case '/'` cần lồng một câu lệnh `if` kiểm tra `b == 0` trước khi chia: với kiểu `double`, Java không báo lỗi khi chia cho 0 mà trả về `Infinity` hoặc `NaN`, nên chương trình phải tự chặn trường hợp này.

**Tổng điểm Lab 2 = 8.0 điểm (4 bài) + điểm cộng: tối đa 2.0 điểm = 10.0 điểm.**
