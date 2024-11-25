package vn.iotstart.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Khai báo rằng đây là một thực thể liên kết với bảng trong database
@Data // Tự động tạo getter, setter, toString, equals, hashCode
@AllArgsConstructor // Tạo constructor với tất cả các thuộc tính
@NoArgsConstructor // Tạo constructor rỗng không tham số
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Khóa chính tự động tăng
    private int id;

    private String name;

    private String email;

    private String password;

    private String roles; // Lưu vai trò (ví dụ: ROLE_USER, ROLE_ADMIN)
}
