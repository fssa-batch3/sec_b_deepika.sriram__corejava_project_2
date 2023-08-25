package in.fssa.carecentral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.HashSet;

import in.fssa.carecentral.model.*;
import in.fssa.carecentral.dto.DoctorDTO;
import in.fssa.carecentral.enumfiles.Gender;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.service.DoctorService;
import in.fssa.carecentral.service.UserService;
import in.fssa.carecentral.util.ConnectionUtil;

public class DoctorDAO {

	/**
	 * 
	 * @param newDoctor
	 * @param id
	 */
	public void create(DoctorDTO newDoctor , int id) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO doctors(user_id , qualifications , experience , department) VALUES (? , ? , ? , ?)";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, newDoctor.getQualifications());
			ps.setDouble(3, DoctorService.convertYearToMonth(newDoctor.getExperience()));
			ps.setString(4, newDoctor.getDepartment());
			
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Doctor had been created successfully");
			} else {
				throw new RuntimeException("Doctor had not been created successfully");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}finally {
			ConnectionUtil.close(con, ps);
		}
	}

	/**
	 * 
	 * @return list of doctors
	 * @throws ValidationException
	 */
	public Set<DoctorDTO> findAll() throws ValidationException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<DoctorDTO> doctorList =null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT doctor_id,d.user_id,qualifications,experience,department,first_name,last_name,age,gender,mobile_number,email_id FROM users as u INNER JOIN doctors AS d ON u.user_id = d.user_id WHERE d.is_active = 1";
			ps = con.prepareStatement(query);
			doctorList  = new HashSet<DoctorDTO>();
			rs = ps.executeQuery();
			while(rs.next()) {
				DoctorDTO dto = new DoctorDTO();
				dto.setId(rs.getInt("doctor_id"));
				dto.setUserId(rs.getInt("d.user_id"));
				dto.setQualifications(rs.getString("qualifications"));
				dto.setExperience(DoctorService.convertMonthToYear(rs.getInt("experience")));
				dto.setDepartment(rs.getString("department"));
				dto.setFirstName(rs.getString("first_name"));
				dto.setLastName(rs.getString("last_name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(Gender.valueOf(rs.getString("gender")));
				dto.setMobileNumber(rs.getLong("mobile_number"));
				dto.setEmailId(rs.getString("email_id"));
				doctorList.add(dto);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return doctorList;
	}

	/**
	 * 
	 * @param id
	 * @param newDoctor
	 * @throws ValidationException
	 */
	public void update(int id, Doctor  newDoctor) throws ValidationException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE doctors SET qualifications = ? , experience = ? , department = ? WHERE doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, newDoctor.getQualifications());
			ps.setDouble(2, DoctorService.convertYearToMonth(newDoctor.getExperience()));
			ps.setString(3, newDoctor.getDepartment());
			ps.setInt(4, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Doctor had been updated successfully");
			} else {
				throw new RuntimeException("Doctor had not been updated successfully");
			}
		}catch (SQLException e) {
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
	public void delete(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE doctors SET is_active = 0 WHERE doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowsAffected  = ps.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Doctor had been deleted successfully");
			}else {
				throw new RuntimeException("Doctor had not been deleted successfully");
			}
		}catch (SQLException e) {
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
	 * @return doctor
	 */
	public Doctor findById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Doctor d = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT doctor_id,qualifications,experience,department,is_active,user_id FROM doctors WHERE is_active = 1 AND doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				d = new Doctor();
				d.setId(rs.getInt("doctor_id"));
				d.setQualifications(rs.getString("qualifications"));
				d.setExperience(DoctorService.convertMonthToYear(rs.getInt("experience")));
				d.setDepartment(rs.getString("department"));
				d.setDocActive(rs.getBoolean("is_active"));
				d.setUserId(rs.getInt("user_id"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return d;
	}
	
	/**
	 * 
	 * @param id
	 * @return doctor
	 */
	public DoctorDTO findDoctorById(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DoctorDTO dd = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT doctor_id,d.user_id,qualifications,experience,department,first_name,last_name,age,gender,mobile_number,email_id,d.is_active FROM users AS u INNER JOIN doctors AS d on u.user_id = d.user_id WHERE d.is_active = 1 AND doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				dd = new DoctorDTO();
				dd.setId(rs.getInt("doctor_id"));
				dd.setUserId(rs.getInt("d.user_id"));
				dd.setFirstName(rs.getString("first_name"));
				dd.setLastName(rs.getString("last_name"));
				dd.setAge(rs.getInt("age"));
				dd.setGender(Gender.valueOf(rs.getString("gender")));
				dd.setMobileNumber(rs.getLong("mobile_number"));
				dd.setEmailId(rs.getString("email_id"));
				dd.setQualifications(rs.getString("qualifications"));
				dd.setExperience(DoctorService.convertMonthToYear(rs.getInt("experience")));
				dd.setDepartment(rs.getString("department"));
				dd.setDocActive(rs.getBoolean("d.is_active"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dd;
	}
	
	
	/**
	 * 
	 * @param email
	 * @return doctor
	 */
	public DoctorDTO findDoctorByEmail(String email) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		DoctorDTO dd = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT doctor_id,d.user_id,qualifications,experience,department,first_name,last_name,age,gender,mobile_number,email_id FROM users AS u INNER JOIN doctors AS d on u.user_id = d.user_id WHERE d.is_active = 1 AND u.email_id = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if(rs.next()) {
				dd = new DoctorDTO();
				dd.setId(rs.getInt("doctor_id"));
				dd.setUserId(rs.getInt("d.user_id"));
				dd.setFirstName(rs.getString("first_name"));
				dd.setLastName(rs.getString("last_name"));
				dd.setAge(rs.getInt("age"));
				dd.setGender(Gender.valueOf(rs.getString("gender")));
				dd.setMobileNumber(rs.getLong("mobile_number"));
				dd.setEmailId(rs.getString("email_id"));
				dd.setPassword(rs.getString("password"));
				dd.setQualifications(rs.getString("qualifications"));
				dd.setExperience(DoctorService.convertMonthToYear(rs.getInt("experience")));
				dd.setDepartment(rs.getString("department"));
				dd.setDocActive(rs.getBoolean("d.is_active"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return dd;
		
	}

	public void reactivate(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE doctors SET is_active = 1 WHERE doctor_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected<=0) {
				throw new RuntimeException("Doctor does not exist");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(con, ps);
		}
	}

	

}
