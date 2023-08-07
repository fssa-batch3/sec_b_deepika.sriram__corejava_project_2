package in.fssa.carecentral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.carecentral.interface_files.UserInterface;
import in.fssa.carecentral.model.User;

import in.fssa.carecentral.util.ConnectionUtil;

public class UserDAO implements UserInterface{

	@Override
	public Set<User> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = new HashSet<User>();
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT * FROM users WHERE is_active = 1";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				user.setGender(Gender.valueOf(rs.getString("gender")));
				user.setMobileNumber(rs.getLong("mobile_number"));
				user.setEmailId(rs.getString("email_id"));
				user.setActive(rs.getBoolean("is_active"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}
		return null;
	}
	
	@Override
	public void create(User newUser) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO users (first_name , last_name , age , gender , mobile_number , email_id , password) VALUES(? , ? , ? , ? , ? , ? , ?)";
			ps = con.prepareStatement(query);
			ps.setString(1 , newUser.getFirstName());
			ps.setString(2, newUser.getLastName());
			ps.setInt(3, newUser.getAge());
			ps.setString(4, newUser.getGender().name());
			ps.setLong(5, newUser.getMobileNumber());
			ps.setString(6, newUser.getEmailId());
			ps.setString(7, newUser.getPassword());
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected >0) {
				System.out.println("User had been created successfully");
			}
			else {
				throw new RuntimeException("User had not been created successfully");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con, ps);
		}
	}

	@Override
	public void update(int id, User newT) {
		
	}

	@Override
	public void delete(int id) {
		
	}

	@Override
	public User findById(int id) {
		return null;
	}

	@Override
	public User findByEmail(String email) {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}
	
}
