package cn.kiana.unic.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Vector;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private int id;
    private int shopID;

    private String phone;

    private String name;
    private float price;
    private int number;
    private int sellNumber;

    private String class1;
    private String class2;
    private String class3;

    private Vector<String> type;
    private int imgNumber;
    private String goodsImg1;
    private String goodsImg2;
    private String goodsImg3;
    private String goodsImg4;
    private String goodsImg5;
    private Vector<MultipartFile> files;

    private List<List<TypeTag>> tags;
}
/*CREATE TABLE goods(
    g_id  int UNIQUE NOT NULL AUTO_INCREMENT,
    g_name VARCHAR(50),
    g_price Float NOT NULL,
    g_number int DEFAULT 0,
    s_id int NOT NULL,
    PRIMARY KEY (g_id),
    FOREIGN KEY (s_id) REFERENCES shop(s_id)   ON UPDATE CASCADE ON DELETE CASCADE
);*/