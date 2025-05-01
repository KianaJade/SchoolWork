package cn.kiana.unic.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOrder {
    private int id;
    private Timestamp orderTime;
    private LocalDateTime orderDate;
    private int number;
    private float totalPrice;
    private float singlePrice;
    private int status;
    private int userId;
    private int goodsId;
    private String goodsName;
    private List<Integer> tagId;
    private List<String> tagName;

    private String phone;

    private String img1;
}
/*CREATE TABLE goodsOrder(
    o_id int UNIQUE NOT NULL AUTO_INCREMENT,
    o_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    o_number int DEFAULT 1,
    o_totalPrice Float DEFAULT 0,
    o_singlePrice Float DEFAULT 0,
    o_status int DEFAULT 0,
    g_id int NOT NULL,
    id int NOT NULL,
    PRIMARY KEY (o_id),
    FOREIGN KEY (g_id) REFERENCES goods(g_id)   ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id) REFERENCES user(id)   ON UPDATE CASCADE ON DELETE CASCADE
);*/
