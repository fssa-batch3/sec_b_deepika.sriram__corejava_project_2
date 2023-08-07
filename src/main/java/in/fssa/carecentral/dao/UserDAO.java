package in.fssa.carecentral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import in.fssa.carecentral.interface_files.UserInterface;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.enumFiles.*;
import in.fssa.carecentral.util.ConnectionUtil;

public class UserDAO implements UserInterface {


	public Set<User> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList =null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT * FROM users WHERE is_active = 1";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			userList = new HashSet<User>();
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
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con, ps);
		}
		
		return userList;
	}

	@Override
	public void create(User newUser) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO users (first_name , last_name , age , gender , mobile_number , email_id , password) VALUES(? , ? , ? , ? , ? , ? , ?)";
			ps = con.prepareStatement(query);
			ps.setString(1, newUser.getFirstName());
			ps.setString(2, newUser.getLastName());
			ps.setInt(3, newUser.getAge());
			ps.setString(4, newUser.getGender().name());
			ps.setLong(5, newUser.getMobileNumber());
			ps.setString(6, newUser.getEmailId());
			ps.setString(7, newUser.getPassword());

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User had been created successfully");
			} else {
				throw new RuntimeException("User had not been created successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	@Override
	public void update(int id, User newT) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE users SET first_name = ? WHERE is_active = 1 AND user_id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, "Vaishnavi");
			ps.setInt(2, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User had been updated successfully");
			} else {
				throw new RuntimeException("User had not been updated successfully");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	@Override
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE users SET is_active = 0 WHERE user_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("User had been deleted successfully");
			} else {
				throw new RuntimeException("User had not been deleted successfully");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	@Override
	public User findById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT * FROM users WHERE is_active = 1 AND user_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				user.setGender(Gender.valueOf(rs.getString("gender")));
				user.setMobileNumber(rs.getLong("mobile_number"));
				user.setEmailId(rs.getString("email_id"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

		return user;
	}

	@Override
	public User findByEmail(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT * FROM users WHERE is_active = 1 AND email_id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				user.setGender(Gender.valueOf(rs.getString("gender")));
				user.setMobileNumber(rs.getLong("mobile_number"));
				user.setEmailId(rs.getString("email_id"));
				user.setPassword(rs.getString("password"));
				user.setActive(rs.getBoolean("is_active"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}

		return user;
	}

	@Override
	public int count() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT COUNT(*) FROM users WHERE is_active = 1";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con, ps);
		}
		return count;
	}

}
