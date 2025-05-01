package cn.kiana.unic.service;

import cn.kiana.unic.pojo.Result;
import cn.kiana.unic.pojo.Shop;
import cn.kiana.unic.pojo.User;

public interface ShopService {
    Result getShopByUserID(User user);
    Shop getShopByShopID(int shopID);
    Result createShop(User user);
    Result getShopOrders(String phone);
    Result getShopGoods(String phone);
    void updateShop(Shop shop);
}
