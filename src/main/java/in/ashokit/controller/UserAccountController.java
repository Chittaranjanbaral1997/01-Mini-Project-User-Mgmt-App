package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entities.UserAccount;
import in.ashokit.service.UserAccountService;

@Controller
public class UserAccountController {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user",new UserAccount());
		return"index";
	}
	
	@PostMapping("/save-user")
	public String handleSubmitBtn(@ModelAttribute("user") UserAccount user,Model model) {
		System.out.println(user);
		// TODO : save form data in database
		String msg = userAccountService.saveOrUpdateUserAcc(user);
		model.addAttribute("msg",msg);
		return "index";
	}
	
	@GetMapping("/users")
	public String getUsers(Model model) {
		List<UserAccount> userList = userAccountService.getAllUserAcc();
		model.addAttribute("users",userList);
		return"view-users";
	}
	 
	@GetMapping("/edit")
	public String editUser(@RequestParam("id") Integer id,Model model) {
		UserAccount userAcc = userAccountService.getUserAcc(id);
		model.addAttribute("user",userAcc);
		return "index";
	}
	
	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") Integer id) {
	    userAccountService.deleteUserAcc(id);
		return "redirect:/users";
	}
	
	@GetMapping("/update")
	public String statusUpdate(@RequestParam("id") Integer id,
			                   @RequestParam("status") String status) {
		userAccountService.updateUserAccStatus(id, status);
		
		return"redirect:/users";
	}
	
	
	

}
