package com.tianyuHouWebMall.service;

import java.sql.SQLException;

import com.tianyuHouWebMall.dao.UserDao;
import com.tianyuHouWebMall.domain.User;

public class UserService {
	
	public boolean regist(User user) {
		
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row>0? true:false;
	}

	public void active(String activecode) throws SQLException {
		UserDao userdao = new UserDao();
		userdao.active(activecode);
	}

	public boolean checkUserName(String username){
		UserDao userdao = new UserDao();
		Long isExist = 0L;
		try {
			isExist = userdao.checkUserName(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isExist>0?true:false;
	}

	public User login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		return dao.login(username,password);
	}

}
