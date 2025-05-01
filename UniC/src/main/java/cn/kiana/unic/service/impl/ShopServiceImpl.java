package cn.kiana.unic.service.impl;

import cn.kiana.unic.mapper.GoodsMapper;
import cn.kiana.unic.mapper.OrderMapper;
import cn.kiana.unic.mapper.ShopMapper;
import cn.kiana.unic.mapper.UserMapper;
import cn.kiana.unic.pojo.*;
import cn.kiana.unic.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public Result getShopByUserID(User user) {
        user = userMapper.getUserByPhone(user.getPhone());
        Shop shop = shopMapper.selectByUserID(user.getId());
        return Result.success(shop);
    }

    @Override
    public Shop getShopByShopID(int shopID) {
        return shopMapper.selectShopByID(shopID);
    }

    @Override
    public Result createShop(User user) {
        user = userMapper.getUserByPhone(user.getPhone());
        Shop shop = shopMapper.selectByUserID(user.getId());
        if(shop != null){
            return Result.error("用户已存在商铺");
        }

        shopMapper.insertShop(user.getId());
        return getShopByUserID(user);
    }

    @Override
    public Result getShopOrders(String phone) {
        List<GoodsOrder> orders = orderMapper.selectShopOrders(phone);
        for(GoodsOrder order : orders){
            List<String> tags = orderMapper.selectOrderTags(order.getId());
            order.setOrderDate(order.getOrderTime().toLocalDateTime());
            order.setTagName(tags);
        }
        return Result.success(orders);
    }

    @Override
    public Result getShopGoods(String phone) {
        List<Goods> goods = goodsMapper.getGoodsByPhone(phone);
        return Result.success(goods);
    }

    @Override
    public void updateShop(Shop shop) {
        shopMapper.updateShop(shop);
    }
}
