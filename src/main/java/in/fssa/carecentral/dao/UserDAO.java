package in.fssa.carecentral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import in.fssa.carecentral.model.User;
import in.fssa.carecentral.enumfiles.*;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.interfaces.UserInterface;
import in.fssa.carecentral.util.ConnectionUtil;
import in.fssa.carecentral.util.PasswordEncryptor;

public class UserDAO implements UserInterface {
	
	/**
	 
	 * @return userList
	 */
	public Set<User> findAll() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<User> userList = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT user_id,first_name,last_name,age,gender,mobile_number,email_id,is_active FROM users WHERE is_active = 1";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			userList = new HashSet<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("user_id"));
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
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return userList;
	}

	
	
	/**
	 * 
	 * @param newUser
	 * @return user_id
	 * @throws ValidationException
	 */
	@Override
	public int create(User newUser) throws RuntimeException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int generatedId = 0;
		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO users (first_name , last_name , age , gender , mobile_number , email_id , password) "
					+ "VALUES(? , ? , ? , ? , ? , ? , ?)";
			ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 
			ps.setString(1, newUser.getFirstName());
			ps.setString(2, newUser.getLastName());
			ps.setInt(3, newUser.getAge());
			ps.setString(4, newUser.getGender().name());
			ps.setLong(5, newUser.getMobileNumber());
			ps.setString(6, newUser.getEmailId());
			ps.setString(7, PasswordEncryptor.encrypt(newUser.getPassword(), System.getenv("SECRET_KEY")));

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				rs = ps.getGeneratedKeys();
				if(rs.next()) {
					 generatedId = rs.getInt(1);
				}
				System.out.println("User had been created successfully");
			} else {
				throw new RuntimeException("User had not been created successfully");
			}

			
			newUser.setId(generatedId);
 
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(con, ps);
		}
		return newUser.getId();
	}

	/**
	 * 
	 * @param newUser
	 * @param id
	 */
	@Override
	public void update(int id, User newUser) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int userId=0;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE users SET first_name = ? , last_name = ? , age = ? , gender = ? , mobile_number = ?  WHERE is_active = 1 AND user_id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, newUser.getFirstName());
			ps.setString(2, newUser.getLastName());
			ps.setInt(3, newUser.getAge());
			ps.setString(4, newUser.getGender().name());
			ps.setLong(5, newUser.getMobileNumber());
			ps.setInt(6, id);
			int rowsaffected = ps.executeUpdate();
			if (rowsaffected>0) {
				
				System.out.println("User had been updated successfully");
			} else {
				throw new RuntimeException("User had not been updated successfully");
			}
			
			
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	
	/**
	 * 
	 * @param id
	 */
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
				throw new RuntimeException("User does not exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}
	
	/**
	 * 
	 * @param id
	 */
	
	public void reactivate(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE users SET is_active = 1 WHERE user_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected<=0) {
				throw new RuntimeException("User does not exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @param id
	 * @return User
	 */
	@Override
	public User findById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT user_id,first_name,last_name,age,gender,mobile_number,email_id,is_active FROM users WHERE is_active = 1 AND user_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				user.setGender(Gender.valueOf(rs.getString("gender")));
				user.setMobileNumber(rs.getLong("mobile_number"));
				user.setEmailId(rs.getString("email_id"));
				user.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	/**
	 * 
	 * @param email
	 * @return User
	 */
	@Override
	public User findByEmail(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT user_id,first_name,last_name,age,gender,mobile_number,email_id,password,is_active FROM users WHERE is_active = 1 AND email_id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setAge(rs.getInt("age"));
				user.setGender(Gender.valueOf(rs.getString("gender")));
				user.setMobileNumber(rs.getLong("mobile_number"));
				user.setEmailId(rs.getString("email_id"));
				user.setPassword(PasswordEncryptor.decrypt(rs.getString("password"), System.getenv("SECRET_KEY")));
				user.setActive(rs.getBoolean("is_active"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			if (e.getMessage().contains("Duplicate entry '"+user.getEmailId()+"' for key 'users.email_id'")) {
				throw new RuntimeException("User already exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}

		return user;
	}

	/**
	 * 
	 * @return count of users
	 */
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
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return count;
	}

}
