package com.tianyuHouWebMall.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tianyuHouWebMall.domain.Category;
import com.tianyuHouWebMall.domain.Order;
import com.tianyuHouWebMall.domain.Product;


public interface AdminService {

	public List<Category> findAllCategory();

	public void saveProduct(Product product) throws SQLException;

	public List<Order> findAllOrders();

	public List<Map<String, Object>> findOrderInfoByOid(String oid);

}
