package cn.kiana.unic.service.impl;

import cn.kiana.unic.mapper.GoodsMapper;
import cn.kiana.unic.mapper.OrderMapper;
import cn.kiana.unic.mapper.UserMapper;
import cn.kiana.unic.pojo.GoodsOrder;
import cn.kiana.unic.pojo.Result;
import cn.kiana.unic.pojo.User;
import cn.kiana.unic.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result addOrder(GoodsOrder goodsOrder) {
        User user = userMapper.getUserByPhone(goodsOrder.getPhone());
        goodsOrder.setUserId(user.getId());
        orderMapper.insertOrder(goodsOrder);
        for(Integer i :goodsOrder.getTagId())
        {
            orderMapper.insertOrderTag(goodsOrder.getId(),i);
        }
        return Result.success();
    }

    @Override
    public Result updateOrder(Integer id,String opt) {
        /*0：加入购物车
        1：下单，未付款
        2：已付款，未发货
        3：已发货
        4：已签收*/
        if(opt.equals("deliver")){
            orderMapper.updateOrderStatus(id,3);
        }else if(opt.equals("payOff")){
            orderMapper.updateOrderStatus(id,2);
        } else if (opt.equals("signIn")) {
            orderMapper.updateOrderStatus(id,4);
        }
        return Result.success();
    }

    @Override
    public Result selOrdersByPhone(String phone) {
        List<GoodsOrder> orders = orderMapper.selectUserOrders(phone);
        for(GoodsOrder order : orders){
            List<String> tags = orderMapper.selectOrderTags(order.getId());
            order.setOrderDate(order.getOrderTime().toLocalDateTime());
            order.setTagName(tags);
        }
        return Result.success(orders);
    }
}
