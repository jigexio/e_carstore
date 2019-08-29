package com.jigexio.store.service.imp;

import com.jigexio.store.dao.GoodsDao;
import com.jigexio.store.dao.imp.GoodsDaoImpJdbc;
import com.jigexio.store.domain.Goods;
import com.jigexio.store.service.GoodsService;

import java.util.List;

public class GoodsServiceImp implements GoodsService {

    GoodsDao goodsDao = new GoodsDaoImpJdbc();

    @Override
    public List<Goods> queryAll() {
        return goodsDao.findAll();
    }

    @Override
    public List<Goods> queryByStartEnd(int start, int end) {
        return goodsDao.findStartEnd(start, end);
    }

    @Override
    public Goods querDetail(long goodsid) {
        return goodsDao.findByPk(goodsid);
    }
}
