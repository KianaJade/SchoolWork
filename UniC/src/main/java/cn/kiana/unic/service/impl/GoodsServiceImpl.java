package cn.kiana.unic.service.impl;

import cn.kiana.unic.mapper.GoodsMapper;
import cn.kiana.unic.mapper.ShopMapper;
import cn.kiana.unic.mapper.UserMapper;
import cn.kiana.unic.pojo.*;
import cn.kiana.unic.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static cn.kiana.unic.utils.GetStringYH.getRightStr;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public Result getGoodsClass(){
        Vector<String> goodsClass = new Vector<>();
        Vector<GoodsClass> goodsClass1 = goodsMapper.getGoodsClass1();
        for(GoodsClass gc1 : goodsClass1){
            goodsClass.add("1");
            goodsClass.add(gc1.getClassName());
            Vector<GoodsClass> goodsClass2 = goodsMapper.getGoodsClass2(gc1.getClassName());
            for(GoodsClass gc2 : goodsClass2){
                goodsClass.add("2");
                goodsClass.add(gc2.getClassName());
                Vector<GoodsClass> goodsClass3 = goodsMapper.getGoodsClass3(gc2.getClassName());
                for(GoodsClass gc3 : goodsClass3){
                    goodsClass.add("3");
                    goodsClass.add(gc3.getClassName());
                }
            }
        }
        return Result.success(goodsClass);
    }

    @Override
    public Result manageGoodsClass(String opt, GoodsClass gc) {
        int l = gc.getClassLevel();
        int num = 0 ;
        if(opt.equals("add")){
            if(l == 1){goodsMapper.addGoodsClass1(gc);}
            else if(l == 2){goodsMapper.addGoodsClass2(gc);}
            else if(l == 3){goodsMapper.addGoodsClass3(gc);}
        }else if(opt.equals("delete")){
            if(l == 1){goodsMapper.deleteGoodsClass1(gc);}
            else if(l == 2){goodsMapper.deleteGoodsClass2(gc);}
            else if(l == 3){goodsMapper.deleteGoodsClass3(gc);}
        }else if(opt.equals("update")){
            if(l == 1){goodsMapper.updateGoodsClass1(gc);}
            else if(l == 2){goodsMapper.updateGoodsClass2(gc);}
            else if(l == 3){goodsMapper.updateGoodsClass3(gc);}
        } else if (opt.equals("num")) {
            num += goodsMapper.getClass1Num();
            num += goodsMapper.getClass2Num();
            num += goodsMapper.getClass3Num();
            return Result.success(num);
        }
        return Result.success();
    }

    @Override
    public Result addGoods(Goods goods) {
        User user = userMapper.getUserByPhone(goods.getPhone());
        Shop shop = shopMapper.selectByUserID(user.getId());
        goods.setShopID(shop.getId());

        goodsMapper.addGoods(goods);

        Vector<String> type = goods.getType();
        GoodsType goodsType = new GoodsType();
        goodsType.setGoodsId(goods.getId());
        for(int i=0;i<type.size();i++){
            String type1 = type.get(i);
            type1=getRightStr(type1);
            if(!type1.isEmpty()) goodsType.setGoodsTypeName(type1);
            goodsMapper.addGoodsType(goodsType);

            i++;
            for(;i<type.size();i++){
                TypeTag typeTag = new TypeTag();
                typeTag.setTagName(getRightStr(type.get(i)));
                typeTag.setTypeID(goodsType.getGoodsTypeId());
                goodsMapper.addTypeTag(typeTag);

                if(type.get(i).charAt(type.get(i).length() - 1) == ']')break;
            }
        }

        return Result.success();
    }

    @Override
    public Result getGoodsByStart(int start, int limit) {
        List<Goods> goodsList = goodsMapper.getGoodsByStart(start, limit);
        return Result.success(goodsList);
    }

    @Override
    public Result getGoodsByID(int id) {
        Goods goods = goodsMapper.getGoodsById(id);

        Vector<GoodsType> goodsTypes = goodsMapper.getGoodsTypeByID(goods.getId());
        Vector<String> types = new Vector<>();

        List<List<TypeTag>> tags = new ArrayList<>();
        for(GoodsType goodsType : goodsTypes){
            types.add(getRightStr(goodsType.getGoodsTypeName()));
            List<TypeTag> tags1 = goodsMapper.getTypeTagsList(goodsType.getGoodsTypeId());
            tags.add(tags1);
        }

        goods.setType(types);
        goods.setTags(tags);

        return Result.success(goods);
    }
}
