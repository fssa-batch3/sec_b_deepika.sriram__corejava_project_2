package in.fssa.carecentral.interface_files;

import in.fssa.carecentral.model.Doctor;

public interface DoctorInterface extends Base<Doctor>{
	public abstract Doctor findByEmail(String email);
}
