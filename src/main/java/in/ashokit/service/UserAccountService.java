package in.ashokit.service;

import java.util.List;

import in.ashokit.entities.UserAccount;

public interface UserAccountService {
	
	public String saveOrUpdateUserAcc(UserAccount userAcc);
	public List<UserAccount> getAllUserAcc();
	public UserAccount getUserAcc(Integer userId);
	public boolean deleteUserAcc(Integer userId);
	public boolean updateUserAccStatus(Integer userId,String status); 
	
	

}
