package cn.kiana.unic.service;

import cn.kiana.unic.pojo.Goods;
import cn.kiana.unic.pojo.GoodsClass;
import cn.kiana.unic.pojo.Result;

public interface GoodsService {
    Result getGoodsClass();
    Result manageGoodsClass(String opt, GoodsClass gc);
    Result addGoods(Goods goods);
    Result getGoodsByStart(int start, int limit);
    Result getGoodsByID(int id);
}
