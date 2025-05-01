package cn.kiana.unic.mapper;

import cn.kiana.unic.pojo.Shop;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShopMapper {
    Shop selectByUserID(Integer ID);
    void insertShop(Integer userID);
    Shop selectShopByID(Integer ID);
    void updateShop(Shop shop);
}
