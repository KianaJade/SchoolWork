package cn.kiana.unic.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    private int id;
    private String name;
    private String img;
    private String address;
    private String userID;
    private String des;
    private Timestamp createTime;

    private String phone;
    private MultipartFile imgFile;
}
/*CREATE TABLE shop(
    s_id int UNIQUE NOT NULL AUTO_INCREMENT,
    id int NOT NULL,
    s_img VARCHAR(50),
    s_name VARCHAR(50),
    s_des VARCHAR(50),
    s_address VARCHAR(50),
    PRIMARY KEY (s_id),
    FOREIGN KEY (id) REFERENCES user(id)   ON UPDATE CASCADE ON DELETE CASCADE
);*/