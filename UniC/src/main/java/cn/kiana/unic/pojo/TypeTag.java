package cn.kiana.unic.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeTag {
    private int tagID;
    private String tagName;
    private int typeID;
}

/*
CREATE TABLE tag(
    tag_id int UNIQUE NOT NULL AUTO_INCREMENT,
    tag_name VARCHAR(50),
    t_id int NOT NULL,
    PRIMARY KEY (tag_id),
    FOREIGN KEY (t_id) REFERENCES goodsType(t_id)   ON UPDATE CASCADE ON DELETE CASCADE
);*/
