# BÀI THỰC HÀNH 03

Các quy định về cấu trúc Repository, quy trình Git, video nộp bài, cách tính điểm và vấn đáp, cùng các chế tài áp dụng chung, được trình bày tại văn bản [Quy định chung toàn khóa](Quy_Dinh_Chung_Toan_Khoa.md). Văn bản này chỉ trình bày Yêu cầu bài tập riêng của Lab 3.

## MỤC TIÊU

Sau bài thực hành này, sinh viên có khả năng:

- Sử dụng thành thạo ba cấu trúc lặp `for`, `while`, `do...while` và lựa chọn đúng cấu trúc cho từng bài toán.
- Sử dụng `break` và `continue` để điều khiển luồng lặp.
- Khai báo, khởi tạo, nhập/xuất mảng một chiều; duyệt mảng bằng `for` theo chỉ số và `for-each`.
- Thực hiện các thao tác cơ bản trên mảng: lọc, tính tổng, tìm giá trị lớn nhất, tìm kiếm tuyến tính, sắp xếp (tự viết Bubble Sort và dùng thư viện `java.util.Arrays`).
- Quay video thao tác gõ code trực tiếp và diễn giải bản chất kỹ thuật của từng vòng lặp.

---

## YÊU CẦU

**Vị trí lưu trữ chung:** Toàn bộ 4 bài của Lab 3 là 4 class nằm trong cùng một Project `Lab3`, package `com.fpoly.lab3` (xem [Quy định chung](Quy_Dinh_Chung_Toan_Khoa.md), Mục 1). Project được tạo bằng **File → New Project → Java with Maven → Java Application**, Project Name `Lab3`, Project Location là thư mục gốc `STT_MSSV_Ten`, Group Id `com.fpoly`. Class chính `Lab3.java` do NetBeans tự sinh có thể giữ nguyên hoặc xóa.

### Bài 1 (2đ): Trung bình cộng các số chia hết cho 3

Sinh viên tạo class `TrungBinhChia3.java` trong package `com.fpoly.lab3`.

**Yêu cầu:** Nhập vào số nguyên dương `n` (dùng `sc.nextInt()`). Dùng vòng lặp `for` duyệt các số từ 1 đến `n`, tính tổng và đếm số lượng các số chia hết cho 3, sau đó tính trung bình cộng. Nếu `n <= 0` thì in ra `n phai la so nguyen duong` và kết thúc chương trình.

**Công thức tính toán:** `TrungBinh = Tong / Dem`, trong đó `Tong` là tổng và `Dem` là số lượng các số `i` thỏa `i % 3 == 0` với `1 <= i <= n`.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Cac so chia het cho 3: [danh sach cac so, cach nhau boi dau cach]
Tong: [tong]
Trung binh cong: [gia tri, lam tron 2 chu so thap phan]
```

Ví dụ nhập `10`:

```
Cac so chia het cho 3: 3 6 9
Tong: 18
Trung binh cong: 6.00
```

Trường hợp `n = 1` hoặc `n = 2` (không có số nào chia hết cho 3), in ra `Khong co so nao chia het cho 3`.

**Lưu ý kỹ thuật:** `Tong` và `Dem` đều là kiểu `int`, nên phép `Tong / Dem` là **chia nguyên** và làm mất phần thập phân (ví dụ `n = 7`: `9 / 2` cho `4` thay vì `4.50`). Cần ép kiểu `(double) Tong / Dem` trước khi chia. Phải kiểm tra `Dem == 0` trước khi chia: với kiểu `int`, chia cho 0 sẽ ném lỗi `ArithmeticException` và dừng chương trình (khác với kiểu `double` ở Lab 2 trả về `Infinity`).

### Bài 2 (2đ): Kiểm tra nhập liệu với do...while

Sinh viên tạo class `NhapSoHopLe.java` trong package `com.fpoly.lab3`.

**Yêu cầu:** Dùng vòng lặp `do...while` yêu cầu người dùng nhập một số nguyên. Số hợp lệ phải thỏa **đồng thời** ba điều kiện: là số dương, chia hết cho 3 và chia hết cho 5. Nếu không hợp lệ, in thông báo và yêu cầu nhập lại cho đến khi hợp lệ. Đếm số lần đã nhập.

**Điều kiện hợp lệ:** `so > 0 && so % 3 == 0 && so % 5 == 0`

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Nhap so: [so da nhap]
So khong hop le, moi nhap lai!
Nhap so: [so da nhap]
So hop le: [so] (sau [so lan] lan nhap)
```

Ví dụ lần lượt nhập `9`, `-15`, `30`:

```
Nhap so: 9
So khong hop le, moi nhap lai!
Nhap so: -15
So khong hop le, moi nhap lai!
Nhap so: 30
So hop le: 30 (sau 3 lan nhap)
```

**Lưu ý kỹ thuật:** Dùng `do...while` vì thân vòng lặp (lệnh nhập) luôn phải chạy **ít nhất một lần** trước khi có dữ liệu để kiểm tra; nếu dùng `while` thì phải gán trước cho biến một giá trị "giả" để vòng lặp chạy lần đầu. Điều kiện lặp là **phủ định** của điều kiện hợp lệ: `!(so > 0 && so % 3 == 0 && so % 5 == 0)`, tương đương `so <= 0 || so % 3 != 0 || so % 5 != 0` (định luật De Morgan). Sinh viên giải thích trong video vì sao số `0` và `-15` chia hết cho cả 3 và 5 nhưng vẫn bị từ chối. Chú ý dấu `;` bắt buộc sau `while (...)` của `do...while`.

### Bài 3 (2đ): Nhập xuất và xử lý mảng

Sinh viên tạo class `XuLyMang.java` trong package `com.fpoly.lab3`.

**Yêu cầu:** Nhập số phần tử `n` (dùng `do...while` bắt nhập lại nếu `n <= 0`), khai báo mảng `int[] a = new int[n];` rồi dùng vòng lặp `for` nhập giá trị cho từng phần tử. Sau đó thực hiện lần lượt:

1. Xuất toàn bộ mảng bằng vòng lặp `for-each`.
2. Xuất các phần tử có giá trị chẵn (dùng `continue` để bỏ qua phần tử lẻ).
3. Tính tổng các phần tử chia hết cho 4.
4. Tìm giá trị lớn nhất trong mảng.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Mang vua nhap: [cac phan tu, cach nhau boi dau cach]
Cac phan tu chan: [cac phan tu chan] | Khong co phan tu chan
Tong cac so chia het cho 4: [tong]
Gia tri lon nhat: [max]
```

Ví dụ nhập `n = 5`, mảng `-3 8 -12 5 7`:

```
Mang vua nhap: -3 8 -12 5 7
Cac phan tu chan: 8 -12
Tong cac so chia het cho 4: -4
Gia tri lon nhat: 8
```

**Lưu ý kỹ thuật:** Chỉ số mảng bắt đầu từ `0` và kết thúc ở `a.length - 1`; điều kiện lặp viết `i < a.length`, nếu viết `i <= a.length` sẽ gây lỗi `ArrayIndexOutOfBoundsException`. Biến `max` phải khởi tạo bằng `a[0]`, **không** khởi tạo bằng `0`: nếu mảng toàn số âm (ví dụ `-5 -2 -9`), `max = 0` sẽ cho kết quả sai là `0`. Vòng `for-each` chỉ đọc được giá trị, không biết chỉ số và không dùng để gán lại phần tử, vì vậy phần nhập mảng phải dùng `for` theo chỉ số.

### Bài 4 (2đ): Tìm kiếm và sắp xếp mảng

Sinh viên tạo class `TimKiemSapXep.java` trong package `com.fpoly.lab3`.

**Yêu cầu:** Nhập mảng số nguyên `n` phần tử (như Bài 3), sau đó:

1. Nhập giá trị `x`. Dùng tìm kiếm tuyến tính in ra **tất cả** các vị trí (chỉ số) mà `x` xuất hiện trong mảng. Nếu không có, in `Khong tim thay`.
2. Tự viết thuật toán **Bubble Sort** để sắp xếp mảng **giảm dần**, xuất kết quả bằng `Arrays.toString()`.
3. Dùng `Arrays.sort()` trên một **bản sao** của mảng ban đầu (tạo bằng `Arrays.copyOf(a, a.length)`), xuất kết quả để đối chiếu.

**Dữ liệu xuất ra màn hình**, theo đúng định dạng mẫu sau:

```
Vi tri cua [x] trong mang: [cac chi so, cach nhau boi dau cach] | Khong tim thay
Mang giam dan (Bubble Sort): [ket qua Arrays.toString]
Mang tang dan (Arrays.sort): [ket qua Arrays.toString]
```

Ví dụ mảng `4 7 2 7 1`, `x = 7`:

```
Vi tri cua 7 trong mang: 1 3
Mang giam dan (Bubble Sort): [7, 7, 4, 2, 1]
Mang tang dan (Arrays.sort): [1, 2, 4, 7, 7]
```

**Lưu ý kỹ thuật:** Thực hiện tìm kiếm **trước** khi sắp xếp, vì sắp xếp làm thay đổi vị trí phần tử. Khi tìm kiếm, không dùng `break` sau lần tìm thấy đầu tiên (đề yêu cầu in tất cả vị trí); dùng một biến `boolean timThay = false` làm cờ để quyết định có in `Khong tim thay` hay không. Bubble Sort giảm dần đổi chỗ hai phần tử kề nhau khi `a[j] < a[j + 1]`, cần biến tạm để hoán đổi. `Arrays.sort()` chỉ sắp xếp tăng dần với mảng kiểu nguyên thủy `int[]`. Phải sắp xếp trên bản sao vì mảng là **kiểu tham chiếu**: câu lệnh `int[] b = a;` không tạo mảng mới mà chỉ cho `b` trỏ cùng vùng nhớ với `a`, sinh viên chứng minh điều này trong video.

**Tổng điểm Lab 3 = 8.0 điểm (4 bài) + điểm cộng: tối đa 2.0 điểm = 10.0 điểm.**
