package com.jigexio.store.service.imp;

import com.jigexio.store.dao.GoodsDao;
import com.jigexio.store.dao.OrderDao;
import com.jigexio.store.dao.OrderLineItemDao;
import com.jigexio.store.dao.imp.GoodsDaoImpJdbc;
import com.jigexio.store.dao.imp.OrderDaoImpJdbc;
import com.jigexio.store.dao.imp.OrderLineItemDaoImpJdbc;
import com.jigexio.store.domain.Goods;
import com.jigexio.store.domain.OrderLineItem;
import com.jigexio.store.domain.Orders;
import com.jigexio.store.service.OrdersService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrdersServiceImp implements OrdersService {

    GoodsDao goodsDao = new GoodsDaoImpJdbc();
    OrderDao orderDao = new OrderDaoImpJdbc();
    OrderLineItemDao orderLineItemDao = new OrderLineItemDaoImpJdbc();

    @Override
    public String submitOrders(List<Map<String, Object>> cart) {

        Orders orders = new Orders();
        Date date = new Date();
        // 订单Id=当前时间错+0~100的随机整数
        String ordersid = String.valueOf(date.getTime())
                + String.valueOf((int) (Math.random() * 100));
        orders.setId(ordersid);
        orders.setOrderDate(date);
        orders.setStatus(1);
        orders.setTotal(0.0f);
        // 将订单信息插入到数据库中
        orderDao.create(orders);
        // 商品总金额
        double total = 0.0;

        for (Map item : cart) {
            // item结构 [商品id， 数量]
            Long goodsid = (Long) item.get("goodsid");
            Integer quantity = (Integer) item.get("quantity");
            Goods goods = goodsDao.findByPk(goodsid);
            // 小计金额
            double subtotal = quantity * goods.getPrice();
            total += subtotal;

            OrderLineItem lineItem = new OrderLineItem();
            lineItem.setQuantity(quantity);
            lineItem.setGoods(goods);
            lineItem.setOrders(orders);
            lineItem.setSubTotal(subtotal);

            // 将订单详请插入到数据库
            orderLineItemDao.create(lineItem);
        }

        orders.setTotal(total);
        // 更新订单数据库
        orderDao.modify(orders);

        return ordersid;
    }
}
