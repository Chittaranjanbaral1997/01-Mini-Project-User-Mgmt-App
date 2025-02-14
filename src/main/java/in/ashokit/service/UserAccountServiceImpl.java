package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entities.UserAccount;
import in.ashokit.repository.UserAccountRepo;
import in.ashokit.service.UserAccountService;
@Service 
public class UserAccountServiceImpl implements UserAccountService {
    
	@Autowired
	private UserAccountRepo userAccRepo;
	
	@Override
	public String saveOrUpdateUserAcc(UserAccount userAcc) {
		
		Integer userid=userAcc.getUserid();
		
		//Upsert (Insert or Update)
		userAccRepo.save(userAcc);
		
		if (userid==null) {
			return "UserRecord saved!!!";	
		}else {
			return "User Record updated!!!";
		}
	}

	@Override
	public List<UserAccount> getAllUserAcc() {
		
		//List<UserAccount> accounts=userAccRepo.findAll();
		//return accounts;
		return userAccRepo.findAll();
	}

	@Override
	public UserAccount getUserAcc(Integer userId) {
		
		Optional<UserAccount> findById = userAccRepo.findById(userId);
		if (findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean deleteUserAcc(Integer userId) {
		boolean existsById = userAccRepo.existsById(userId);
		if (existsById) {
			userAccRepo.deleteById(userId);
			return true;  
		}
		return false;
	}

	@Override
	public boolean updateUserAccStatus(Integer userId, String status) {
		try {
			userAccRepo.updateUserAccStatus(userId, status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
