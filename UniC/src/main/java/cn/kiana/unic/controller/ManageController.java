package cn.kiana.unic.controller;

import cn.kiana.unic.jwt.PassToken;
import cn.kiana.unic.pojo.Goods;
import cn.kiana.unic.pojo.GoodsClass;
import cn.kiana.unic.pojo.Result;
import cn.kiana.unic.service.impl.GoodsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j  //日志
@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/manage")
public class ManageController {
    @Resource
    private GoodsServiceImpl goodsServiceImpl;

    @PostMapping("/addClass")
    @PassToken
    public Result addClass(@RequestBody GoodsClass goodsClass) {
        return goodsServiceImpl.manageGoodsClass("add",goodsClass);
    }

    @PostMapping("/deleteClass")
    @PassToken
    public Result deleteClass(@RequestBody GoodsClass goodsClass) {
        return goodsServiceImpl.manageGoodsClass("delete",goodsClass);
    }

    @PostMapping("/updateClass")
    @PassToken
    public Result updateClass(@RequestBody GoodsClass goodsClass) {
        return goodsServiceImpl.manageGoodsClass("update",goodsClass);
    }

    @GetMapping("/getClassNum")
    @PassToken
    public Result getClassNum() {
        return goodsServiceImpl.manageGoodsClass("num",new GoodsClass());
    }
}
