package in.fssa.carecentral.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import in.fssa.carecentral.dto.AppointmentDTO;
import in.fssa.carecentral.enumfiles.Gender;
import in.fssa.carecentral.enumfiles.MethodOfConsultation;
import in.fssa.carecentral.enumfiles.Status;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.interfaces.AppointmentInterface;
import in.fssa.carecentral.model.Appointment;
import in.fssa.carecentral.model.User;
import in.fssa.carecentral.service.AppointmentService;
import in.fssa.carecentral.service.DoctorService;
import in.fssa.carecentral.service.UserService;
import in.fssa.carecentral.util.ConnectionUtil;

public class AppointmentDAO implements AppointmentInterface{
	
	@Override
	public List<Map<String,LocalTime>> findAllBookedTimingsByDate(String date) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String,LocalTime> timing = null;
		List<Map<String,LocalTime>> listOfTimings = new ArrayList<Map<String,LocalTime>>();
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT start_time , end_time FROM appointments WHERE date_of_consultation = ? AND (status = ? OR status = ?)";
			ps = con.prepareStatement(query);
			LocalDate consultationdate = AppointmentService.convertStringToDate(date);
			java.sql.Date sqlDate = Date.valueOf(consultationdate);
			ps.setDate(1, sqlDate);
			ps.setString(2, "On_process");
			ps.setString(3, "Approved");
			
			rs = ps.executeQuery();
			while(rs.next()) {
				timing = new HashMap<String , LocalTime>();
				LocalTime startTime = rs.getTimestamp("start_time").toLocalDateTime().toLocalTime();
				LocalTime endTime = rs.getTimestamp("end_time").toLocalDateTime().toLocalTime();
				timing.put("start time" , startTime);
				timing.put("end time", endTime);
				
				listOfTimings.add(timing);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.print(e);
			throw new RuntimeException(e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
		
		return listOfTimings;
	}

	@Override
	public void createAppointment(Appointment app) throws ValidationException{
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO appointments (doctor_id , patient_id , reason_for_consultation , method_of_consultation , date_of_consultation , start_time , end_time) VALUES(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			
			
			LocalDate localDate = AppointmentService.convertStringToDate(app.getDateOfConsultation());
			
			java.sql.Date date = Date.valueOf(localDate);
			
			Timestamp startTime = Timestamp.valueOf((localDate+" "+app.getStartTime()));
			
			Timestamp endTime = Timestamp.valueOf(localDate+" "+app.getEndTime());
			
			
			ps.setInt(1, app.getDoctorId());
			ps.setInt(2, app.getPatientId());
			ps.setString(3, app.getReasonForConsultation());
			ps.setString(4, app.getMethodOfConsultation().name());
			ps.setDate(5, date);
			ps.setTimestamp(6, startTime);
			ps.setTimestamp(7, endTime);
			
			int rowsAffected =  ps.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Appointment has been booked successfully");
			}else {
				throw new RuntimeException("Appointment has not been booked successfully");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new RuntimeException(e);
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			ConnectionUtil.close(con, ps);
		}
		
	}

	@Override
	public Set<AppointmentDTO> findAllAppointmentByDoctorId(int doctorId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Set<AppointmentDTO> appointmentList = null;
		AppointmentDTO appointment = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT user_id , first_name , last_name , age , gender , mobile_number , a.* FROM appointments AS a INNER JOIN users AS u ON u.user_id = a.patient_id WHERE doctor_id = ?";
			ps = con.prepareStatement(query); 
			String doctorName = DoctorService.getDoctorById(doctorId).fullName(); 
			ps.setInt(1, doctorId);
			
			appointmentList = new HashSet<AppointmentDTO>();
			rs = ps.executeQuery();
			while(rs.next()) {
				appointment  = new AppointmentDTO();
				appointment.setId(rs.getInt("id"));
				appointment.setUserId(rs.getInt("patient_id"));
				appointment.setPatientName(rs.getString("first_name")+" "+rs.getString("last_name"));
				appointment.setAge(rs.getInt("age"));
				appointment.setGender(Gender.valueOf(rs.getString("gender")));
				appointment.setMobileNumber(rs.getLong("mobile_number"));
				appointment.setReasonForConsultation(rs.getString("reason_for_consultation"));
				appointment.setMethodOfConsultation(MethodOfConsultation.valueOf(rs.getString("method_of_consultation")));
				appointment.setDateOfConsultation(rs.getString("date_of_consultation"));
				appointment.setStartTime(rs.getString("start_time"));
				appointment.setEndTime(rs.getString("end_time"));
				appointment.setDoctorId(doctorId);
				appointment.setDoctorName(doctorName);
				appointment.setStatus(Status.valueOf(rs.getString("status")));
				
				appointmentList.add(appointment);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new RuntimeException(e);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return appointmentList;
	}

	@Override
	public Set<AppointmentDTO> findAllAppointmentByUserId(int userId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<AppointmentDTO> listOfAppointments = new HashSet<AppointmentDTO>();
		AppointmentDTO appointment = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT user_id , first_name , last_name , age , gender , mobile_number , a.* FROM appointments AS a INNER JOIN users AS u ON u.user_id = a.patient_id WHERE patient_id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			while(rs.next()) {
				appointment = new AppointmentDTO();
				appointment.setDoctorId(rs.getInt("doctor_id"));
				String doctorName = DoctorService.getDoctorById(appointment.getDoctorId()).fullName();
				appointment.setDoctorName(doctorName);
				appointment.setId(rs.getInt("id"));
				appointment.setUserId(rs.getInt("patient_id"));
				appointment.setPatientName(rs.getString("first_name")+" "+rs.getString("last_name"));
				appointment.setAge(rs.getInt("age"));
				appointment.setGender(Gender.valueOf(rs.getString("gender")));
				appointment.setMobileNumber(rs.getLong("mobile_number"));
				appointment.setReasonForConsultation(rs.getString("reason_for_consultation"));
				appointment.setMethodOfConsultation(MethodOfConsultation.valueOf(rs.getString("method_of_consultation")));
				appointment.setDateOfConsultation(rs.getString("date_of_consultation"));
				appointment.setStartTime(rs.getString("start_time"));
				appointment.setEndTime(rs.getString("end_time"));
				appointment.setStatus(Status.valueOf(rs.getString("status")));
				appointment.setReasonForRejection(rs.getString("reason_for_rejection_of_appointment"));
				
				listOfAppointments.add(appointment);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ValidationException e) {
			e.printStackTrace();
		}finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return listOfAppointments;
	}

	@Override
	public void updateAppointmentStatus(int appId, Appointment appointment) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "UPDATE appointments SET status = ? , reason_for_rejection_of_appointment = ? WHERE id = ? AND status = 'On_process'";
			ps = con.prepareStatement(query);
			ps.setString(1, appointment.getStatus().name());
			ps.setString(2, appointment.getReasonForConsultation());
			ps.setInt(3, appId);
			
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected>0) {
				System.out.println("Appointment Status updated successfully");
			}else {
				throw new RuntimeException("Can't update the appointment status");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new RuntimeException(e);
			
		}finally {
			ConnectionUtil.close(con, ps);
		}
		
	}

	@Override
	public AppointmentDTO findAppointmentByAppointmentId(int appId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		AppointmentDTO appointment = null;
		User user = null;
		try {
			con = ConnectionUtil.getConnection();
			String query = "SELECT user_id , first_name , last_name , age , gender , mobile_number , a.* FROM appointments AS a INNER JOIN users AS u ON u.user_id = a.patient_id WHERE id = ?";
			ps = con.prepareStatement(query);
			ps.setInt(1, appId);
			rs = ps.executeQuery();
			if(rs.next()) {
				appointment = new AppointmentDTO();
				user = UserService.getUserById(rs.getInt("patient_id"));
				appointment.setId(appId);
				appointment.setUserId(user.getId());
				appointment.setDoctorId(rs.getInt("doctor_id"));
				String doctorName = DoctorService.getDoctorById(appointment.getDoctorId()).fullName();
				appointment.setPatientName(user.fullName());
				appointment.setAge(user.getAge());
				appointment.setGender(user.getGender());
				appointment.setMobileNumber(user.getMobileNumber());
				appointment.setDoctorName(doctorName);
				appointment.setReasonForConsultation(rs.getString("reason_for_consultation"));
				appointment.setMethodOfConsultation(MethodOfConsultation.valueOf(rs.getString("method_of_consultation")));
				appointment.setDateOfConsultation(rs.getString("date_of_consultation"));
				appointment.setStartTime(rs.getString("start_time"));
				appointment.setEndTime(rs.getString("end_time"));
				appointment.setStatus(Status.valueOf(rs.getString("status")));
				appointment.setReasonForRejection(rs.getString("reason_for_rejection_of_appointment"));
			}else {
				throw new RuntimeException("Appointment doesn't exists");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e);
			throw new RuntimeException(e);
		} catch (ValidationException e) {
			e.printStackTrace();
		}finally {
			ConnectionUtil.close(con, ps, rs);
		}
		return appointment;
	}


	

}
