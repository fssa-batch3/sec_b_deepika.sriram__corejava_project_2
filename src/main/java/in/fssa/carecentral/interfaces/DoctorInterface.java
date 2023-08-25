package in.fssa.carecentral.interfaces;

import in.fssa.carecentral.model.Doctor;

public interface DoctorInterface extends Base<Doctor>{
	public abstract Doctor findByEmail(String email);
}
