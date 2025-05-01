package cn.kiana.unic.service;

import cn.kiana.unic.pojo.Goods;
import cn.kiana.unic.pojo.GoodsOrder;
import cn.kiana.unic.pojo.Result;

public interface OrderService {
    Result addOrder(GoodsOrder goodsOrder);
    Result updateOrder(Integer id,String opt);
    Result selOrdersByPhone(String phone);
}
