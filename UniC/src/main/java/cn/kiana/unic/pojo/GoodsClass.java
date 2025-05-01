package cn.kiana.unic.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Vector;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsClass {
    private String className;
    private int classLevel;

    private String classNewName;

    private String classNameLast;
}
