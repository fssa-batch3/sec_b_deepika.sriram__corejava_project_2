package in.fssa.carecentral.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Set;

import in.fssa.carecentral.dto.AppointmentDTO;
import in.fssa.carecentral.exception.ValidationException;
import in.fssa.carecentral.interfaces.AppointmentInterface;
import in.fssa.carecentral.model.Appointment;
import in.fssa.carecentral.service.AppointmentService;
import in.fssa.carecentral.service.DoctorService;
import in.fssa.carecentral.service.UserService;
import in.fssa.carecentral.util.ConnectionUtil;

public class AppointmentDAO implements AppointmentInterface{

	@Override
	public void createAppointment(Appointment app , int doctorId , int userId) throws ValidationException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String query = "INSERT INTO appointments (doctor_id , patient_id , reason_for_consultation , method_of_consultation , date_of_consultation , start_time , end_time) VALUES(?,?,?,?,?,?,?)";
			ps = con.prepareStatement(query);
			DoctorService ds = new DoctorService();
			int doctorid = ds.getDoctorById(doctorId).getId();
			int userid = UserService.getUserById(userId).getId();
			java.util.Date date = AppointmentService.convertStringToDate(app.getDateOfConsultation());
			java.sql.Date date2 = new java.sql.Date(date.getTime());
			Timestamp startTime = Timestamp.valueOf((app.getStartTime()));
			Timestamp endTime = Timestamp.valueOf(app.getEndTime());
			
			ps.setInt(1, doctorid);
			ps.setInt(2, userid);
			ps.setString(3, app.getReasonForConsultation());
			ps.setString(4, app.getMethodOfConsultation().name());
			ps.setDate(5, date2);
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
		}
		
	}

	@Override
	public Set<AppointmentDTO> findAllAppointmentByDoctorId(int doctorId) {
		return null;
	}

	@Override
	public Set<AppointmentDTO> findAllAppointmentByUserId(int userId) {
		return null;
	}

	@Override
	public void updateAppointmentStatus(int appId, AppointmentDTO appointment) {
		
	}

	@Override
	public AppointmentDTO findAppointmentByAppointmentId(int appId) {
		return null;
	}

	@Override
	public Set<AppointmentDTO> findAllAppointmentByStatus(String status) {
		return null;
	}

}
