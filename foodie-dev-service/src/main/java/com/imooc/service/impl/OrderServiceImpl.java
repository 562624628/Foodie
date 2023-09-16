package com.imooc.service.impl;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.YesNoEnum;
import com.imooc.mapper.OrderItemsMapper;
import com.imooc.mapper.OrderStatusMapper;
import com.imooc.mapper.OrdersMapper;
import com.imooc.pojo.*;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.pojo.bo.UserAddressBO;
import com.imooc.service.ItemService;
import com.imooc.service.OrderService;
import com.imooc.service.UserAddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Sid sid;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderStatusMapper orderStatusMapper;
    @Override
    @Transactional
    public String createOrder(SubmitOrderBO submitOrderBO) {
        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();
        //包邮费用设置为0
        Integer postAmount = 0;
        //新订单数据保存
        Orders newOrder = new Orders();
        String id = sid.nextShort();
        newOrder.setId(id);
        newOrder.setUserId(userId);
        UserAddressBO userAddressBO = userAddressService.selectByUserIdAndAddressId(userId, addressId);
        newOrder.setReceiverName(userAddressBO.getReceiver());
        newOrder.setReceiverMobile(userAddressBO.getMobile());
        newOrder.setReceiverAddress(userAddressBO.getCity()+userAddressBO.getDistrict()+userAddressBO.getDetail());

        newOrder.setTotalAmount(0);
        newOrder.setRealPayAmount(0);
        newOrder.setIsComment(YesNoEnum.NO.getCode());
        newOrder.setIsDelete(YesNoEnum.NO.getCode());

        newOrder.setLeftMsg(leftMsg);
        newOrder.setPayMethod(payMethod);
        newOrder.setPostAmount(postAmount);
        newOrder.setCreatedTime(new Date());
        newOrder.setUpdatedTime(new Date());

        //循环根据itemSpecIds保存订单商品信息白噢
        List<String> itemSpecs = Arrays.asList(itemSpecIds.split(","));
        Integer totalAmount = 0;
        Integer realPayAmount = 0;

        for (String itemSpecId : itemSpecs) {
            //查询规格具体信息 获取对应的价格
            ItemsSpec itemsSpec = itemService.queryitemSpecById(itemSpecId);
            //todo 缓存redis缓存
            totalAmount = totalAmount + itemsSpec.getPriceNormal() * 1;
            realPayAmount = realPayAmount + itemsSpec.getPriceDiscount();
            //根据规格id 获取商品信息以及商品的图片
            String itemId = itemsSpec.getItemId();
            Items items = itemService.queryItemById(itemId);
            String imgUrl = itemService.queryItemMainImgById(itemId);
            //循环保存子订单
            String subOrderId = sid.nextShort();
            OrderItems subOrder = new OrderItems();
            subOrder.setId(subOrderId);
            subOrder.setOrderId(id);
            subOrder.setItemId(itemId);
            subOrder.setItemName(items.getItemName());
            subOrder.setItemImg(imgUrl);
            subOrder.setBuyCounts(1);
            subOrder.setItemSpecId(itemSpecId);
            subOrder.setItemSpecName(itemsSpec.getName());
            subOrder.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(subOrder);
            //扣除库存
            itemService.decreaseItemSpecStock(itemSpecId,1);

        }
        newOrder.setTotalAmount(totalAmount);
        newOrder.setRealPayAmount(realPayAmount);
        ordersMapper.insert(newOrder);
        //保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(id);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.getCode());
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);
        return id;
    }
}
