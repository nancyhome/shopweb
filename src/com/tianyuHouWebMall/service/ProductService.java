package com.tianyuHouWebMall.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.tianyuHouWebMall.dao.ProductDao;
import com.tianyuHouWebMall.domain.Category;
import com.tianyuHouWebMall.domain.Order;
import com.tianyuHouWebMall.domain.PageBean;
import com.tianyuHouWebMall.domain.Product;
import com.tianyuHouWebMall.utils.DataSourceUtils;

public class ProductService {

	public List<Product> findHotProduct() {
		ProductDao dao = new ProductDao();
		List<Product> hotProduct = null;
		try {
			hotProduct = dao.findHotProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProduct;
	}

	public List<Product> findNewProduct() {
		ProductDao dao = new ProductDao();
		List<Product> newProduct = null;
		try {
			newProduct = dao.findNewProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newProduct;
	}

	public List<Category> findAllCategory() {
		ProductDao dao = new ProductDao();
		List<Category> list = null;
		try {
			list = dao.finAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public PageBean<Product> findProductListByCid(String cid, int currentPage, int currentCount) {
		ProductDao dao = new ProductDao();
		
		PageBean<Product> pageBean = new PageBean<>();
		
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		
		int totalCount = 0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		
		int totalPage = (int)Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		
		int index = (currentPage-1)*currentCount;
		List<Product> list = null;
		try {
			list = dao.findProductByPage(cid,index,currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		pageBean.setList(list);
		
		return pageBean;
	}

	public Product findProductByPid(String pid) {
		ProductDao dao = new ProductDao();
		Product pro = null;
		try {
			pro = dao.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pro;
	}
	
	//提交订单 将订单的数据和订单项的数据存储到数据库中
		public void submitOrder(Order order) {
			
			ProductDao dao = new ProductDao();
			
			try {
				//1、开启事务
				DataSourceUtils.startTransaction();
				//2、调用dao存储order表数据的方法
				dao.addOrders(order);
				//3、调用dao存储orderitem表数据的方法
				dao.addOrderItem(order);
				
			} catch (SQLException e) {
				try {
					DataSourceUtils.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			} finally {
				try {
					DataSourceUtils.commitAndRelease();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}

		public void updateOrderAdrr(Order order) {
			ProductDao dao = new ProductDao();
			try {
				dao.updateOrderAdrr(order);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		public void updateOrderState(String r6_Order) {
			ProductDao dao = new ProductDao();
			try {
				dao.updateOrderState(r6_Order);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		//获得指定用户的订单集合
		public List<Order> findAllOrders(String uid) {
			ProductDao dao = new ProductDao();
			List<Order> orderList = null;
			try {
				orderList = dao.findAllOrders(uid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return orderList;
		}

		public List<Map<String, Object>> findAllOrderItemByOid(String oid) {
			ProductDao dao = new ProductDao();
			List<Map<String, Object>> mapList = null;
			try {
				mapList = dao.findAllOrderItemByOid(oid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return mapList;
		}

}
