package in.fssa.carecentral.interface_files;

import in.fssa.carecentral.model.User;
import in.fssa.carecentral.model.UserEntity;

public interface UserInterface extends Base<User> {
	public abstract UserEntity findByEmail(String  email);
	public abstract int count();
}
