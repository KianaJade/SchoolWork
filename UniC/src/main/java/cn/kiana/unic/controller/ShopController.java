package cn.kiana.unic.controller;

import cn.hutool.core.io.FileUtil;
import cn.kiana.unic.jwt.PassToken;
import cn.kiana.unic.pojo.*;
import cn.kiana.unic.service.ShopService;
import cn.kiana.unic.service.impl.GoodsServiceImpl;
import cn.kiana.unic.service.impl.OrderServiceImpl;
import cn.kiana.unic.service.impl.ShopServiceImpl;
import cn.kiana.unic.utils.SaveImgUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import static cn.kiana.unic.utils.UUIDUtils.getUUID;

@Slf4j  //日志
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/shop")
public class ShopController {
    @Resource
    private ShopServiceImpl shopServiceImpl;

    @Resource
    private GoodsServiceImpl goodsServiceImpl;

    @Resource
    private OrderServiceImpl orderServiceImpl;

    @PostMapping("/updateShopInfo/avatar")
    public Result updateAvatar(Shop shop) throws IOException {
        SaveImgUtil saveImgUtil = new SaveImgUtil();
        saveImgUtil.saveImg(shop.getImgFile());
        shop = shopServiceImpl.getShopByShopID(shop.getId());
        saveImgUtil.deleteImg(shop.getImg());
        shop.setImg(saveImgUtil.fileName);
        shopServiceImpl.updateShop(shop);
        return Result.success(shop);
    }

    @PostMapping("/updateShopInfo/baseInfo")
    public Result updateBaseInfo(@RequestBody Shop shop) {
        shop.setImg(shopServiceImpl.getShopByShopID(shop.getId()).getImg());
        shopServiceImpl.updateShop(shop);
        return Result.success();
    }

    @GetMapping("/getShopInfo")
    public Result getShop(@RequestParam String phone) {
        User user = new User();
        user.setPhone(phone);
        return shopServiceImpl.getShopByUserID(user);
    }

    @PostMapping("/createShop")
    public Result createShop(@RequestBody User user) {
        return shopServiceImpl.createShop(user);
    }

    @PostMapping("/createGoods")
    public Result createGoods(Goods goods) throws IOException {
        Vector<MultipartFile> files = goods.getFiles();
        if (files == null) {return Result.error("文件列表为空");}

        SaveImgUtil saveImgUtil = new SaveImgUtil();
        for (int i=0;i<files.size();i++) {
            MultipartFile file = files.get(i);
            if(!saveImgUtil.saveImg(file)){
                return Result.error("文件为空");
            }
            if(i == 0){
                goods.setGoodsImg1(saveImgUtil.fileName);
            } else if (i == 1) {
                goods.setGoodsImg2(saveImgUtil.fileName);
            }else if (i == 2) {
                goods.setGoodsImg3(saveImgUtil.fileName);
            }else if (i == 3) {
                goods.setGoodsImg4(saveImgUtil.fileName);
            }else if (i == 4) {
                goods.setGoodsImg5(saveImgUtil.fileName);
            }
        }

        return goodsServiceImpl.addGoods(goods);
    }

    @GetMapping("/getShopOrders")
    public Result getShopOrders(@RequestParam String phone)
    {
        return shopServiceImpl.getShopOrders(phone);
    }

    @PostMapping("/deliverGoods")
    public Result deliverGoods(@RequestBody GoodsOrder goodsOrder)
    {
        return orderServiceImpl.updateOrder(goodsOrder.getId(),"deliver");
    }

    @GetMapping("/getShopGoods")
    public Result getShopGoods(@RequestParam String phone)
    {
        return shopServiceImpl.getShopGoods(phone);
    }
}
