package com.BBC.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.BBC.dbutil.DBUtil;
import com.BBC.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	Connection connection;
	int flag=0;
	
	public UserServiceImpl() throws SQLException {
		connection = DBUtil.getConnection();
	}

	@Override
	public int loginValidation(String EmployeeId, String OTP) {
		
		try {
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee_info WHERE EmployeeId = '"+EmployeeId+"'");
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(3).equals(EmployeeId) && rs.getString(4).equals(OTP)) {
					flag =1;
				}else {
					flag =0;
					System.out.println("Invalid EmployeeId & OTP");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	
}
