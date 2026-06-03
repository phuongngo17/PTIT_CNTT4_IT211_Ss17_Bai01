# Phần 1 - Phân tích logic
Thông báo lỗi Invalid JWT xuất hiện vì trong đoạn mã bạn đã tạo hai Secret Key khác nhau:
key dùng để ký JWT.
differentKey lại được dùng để xác minh JWT.
JWT hoạt động dựa trên nguyên tắc: token chỉ hợp lệ nếu được xác minh bằng đúng Secret Key đã dùng để ký.
Khi bạn tạo một key mới để xác minh, chữ ký không khớp, dẫn đến lỗi xác thực.
Đây là vấn đề logic trong quy trình tạo và xác minh JWT: Secret Key phải nhất quán giữa bước ký và bước xác minh.