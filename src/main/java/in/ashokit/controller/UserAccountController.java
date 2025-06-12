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
		// TODO : save form data in database
		String msg = userAccountService.saveOrUpdateUserAcc(user);
		model.addAttribute("msg",msg);
		model.addAttribute("user",new UserAccount());
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
	public String deleteUser(@RequestParam("id") Integer id ,Model model) {
	    userAccountService.deleteUserAcc(id);
	    model.addAttribute("msg","User Record is Deleted!!!");
		return "forward:/users";
	}
	
	@GetMapping("/update")
	public String statusUpdate(@RequestParam("id") Integer id,
			                   @RequestParam("status") String status,Model model) {
		userAccountService.updateUserAccStatus(id, status);
		if ("Y".equals(status)) {
			model.addAttribute("msg","User Account Activated");
		}else {
			model.addAttribute("msg","User Account De-Activated"); 
		}
		return"forward:/users";
	}
	
	
	

}
