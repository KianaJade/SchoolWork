package cn.kiana.unic.controller;

import cn.hutool.core.io.FileUtil;
import cn.kiana.unic.jwt.PassToken;
import cn.kiana.unic.pojo.GoodsOrder;
import cn.kiana.unic.pojo.Result;
import cn.kiana.unic.service.GoodsService;
import cn.kiana.unic.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j  //日志
@RestController
@CrossOrigin(maxAge = 3600)
public class HomeController {
    @Resource
    private GoodsService goodsService;
    private static final String ROOT_PATH =  System.getProperty("user.dir")+ File.separator +"images";

    @Resource
    private OrderService orderService;

    @GetMapping("/getGoodsClass")
    @PassToken
    private Result getGoodsClass() {
        return goodsService.getGoodsClass();
    }

    @GetMapping("/getGoods")
    @PassToken
    public Result getGoods(@RequestParam int start,@RequestParam int limit) {
        return goodsService.getGoodsByStart(start, limit);
    }

    @GetMapping("/download/{imageName}")
    @PassToken
    public void downloadFile(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        String filePath = ROOT_PATH + File.separator + imageName;
        if(!FileUtil.exist(filePath)){
            return ;
        }
        byte[] data = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
    }

    @GetMapping("/shopping")
    public Result getGoods(@RequestParam int goodsId) {
        return goodsService.getGoodsByID(goodsId);
    }

    @PostMapping("/shopping/addOrder")
    public Result deleteClass(@RequestBody GoodsOrder goodsOrder) {
        return orderService.addOrder(goodsOrder);
    }

    @PostMapping("/updateOrder/payOff")
    public Result updateOrder1(@RequestBody List<Integer> orderId) {
        for (Integer integer : orderId) {
            orderService.updateOrder(integer, "payOff");
        }
        return Result.success();
    }

    @PostMapping("/updateOrder/signIn")
    public Result updateOrder2(@RequestBody Integer orderId) {
        orderService.updateOrder(orderId, "signIn");
        return Result.success();
    }

    @GetMapping("/getUserOrders")
    public Result getUserOrders(@RequestParam String phone) {
        return orderService.selOrdersByPhone(phone);
    }
}
