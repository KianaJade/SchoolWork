package cn.kiana.unic.mapper;

import cn.kiana.unic.pojo.GoodsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    void insertOrder(GoodsOrder order);
    void insertOrderTag(@Param("orderId") int orderId, @Param("tagId") int tagId);
    List<GoodsOrder> selectShopOrders(String phone);
    List<GoodsOrder> selectUserOrders(String phone);
    List<String> selectOrderTags(int orderId);
    void updateOrderStatus(@Param("orderId") int orderId, @Param("status") int status);
}
