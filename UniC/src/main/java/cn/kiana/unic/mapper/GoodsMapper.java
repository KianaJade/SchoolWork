package cn.kiana.unic.mapper;

import cn.kiana.unic.pojo.Goods;
import cn.kiana.unic.pojo.GoodsClass;
import cn.kiana.unic.pojo.GoodsType;
import cn.kiana.unic.pojo.TypeTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Vector;

@Mapper
public interface GoodsMapper {
    List<Goods> getGoodsByPhone(@Param("phone") String phone);

    Goods getGoodsById(int goodsId);
    List<Goods> getGoodsByStart(@Param("start") int start, @Param("limit") int limit);
    Vector<GoodsType> getGoodsTypeByID(int goodsId);
    List<TypeTag> getTypeTagsList(int typeID);

    void addGoods(Goods goods);
    void addGoodsType(GoodsType goodsType);
    void addTypeTag(TypeTag typeTag);

    Vector<GoodsClass> getGoodsClass1();
    Vector<GoodsClass> getGoodsClass2(String className);
    Vector<GoodsClass> getGoodsClass3(String className);
    void addGoodsClass1(GoodsClass goodsClass);
    void addGoodsClass2(GoodsClass goodsClass);
    void addGoodsClass3(GoodsClass goodsClass);

    void deleteGoodsClass1(GoodsClass goodsClass);
    void deleteGoodsClass2(GoodsClass goodsClass);
    void deleteGoodsClass3(GoodsClass goodsClass);

    void updateGoodsClass1(GoodsClass goodsClass);
    void updateGoodsClass2(GoodsClass goodsClass);
    void updateGoodsClass3(GoodsClass goodsClass);

    int getClass1Num();
    int getClass2Num();
    int getClass3Num();
}
