package com.imooc.service;

import com.imooc.utils.PagedGridResult;
import com.sun.java.swing.plaf.windows.WindowsTextAreaUI;

public interface MyOrdersService {
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);
}
