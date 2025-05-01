package cn.kiana.unic.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String password;
    private String name;
    private String phone;
    private char gender;
    private String img;
    private Date birthday;
    private Date register;
    private String address;

    private String token;
    private MultipartFile file;

    public Boolean checkPhone() {
        int length = phone.length();
        if (length != 11) {return false;}
        for(int i = 0; i < length; i++) {
            if (!Character.isDigit(phone.charAt(i))) {return false;}
        }
        return true;
    }
    public Boolean checkPassword() {
        int length = password.length();
        if (length > 40) {return false;}
        for(int i = 0; i < length; i++) {
            if(password.charAt(i) == ' ') {return false;}
        }
        return true;
    }
}
/*CREATE TABLE user(
    id int UNIQUE NOT NULL AUTO_INCREMENT,
    u_password VARCHAR(50),
    u_name VARCHAR(50),
    u_phone VARCHAR(11) UNIQUE NOT NULL,
    gender CHAR(1) DEFAULT 'M',
    u_img VARCHAR(50),
    u_birthday DATE DEFAULT '1990-01-01',
    u_register DATE DEFAULT (CURRENT_DATE),
    PRIMARY KEY (id)
);*/