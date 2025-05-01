package cn.kiana.unic.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsType {
    private int goodsTypeId;
    private String goodsTypeName;
    private int goodsId;
}
