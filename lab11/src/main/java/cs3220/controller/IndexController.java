package cs3220.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cs3220.model.GuestBookEntity;
import cs3220.model.UserEntity;
import cs3220.repository.GuestBookEntityRepository;
import cs3220.repository.UserEntityRepository;
import jakarta.validation.Valid;

@Controller
public class IndexController {
	private final GuestBookEntityRepository messageRepository;
	private final UserEntityRepository userRepository;
	private Integer userId;
	private String errorMessage = null;
	
	public IndexController(UserEntityRepository userRepository, GuestBookEntityRepository messageRepository) {
		this.userRepository = userRepository;
		this.messageRepository = messageRepository;
	}
	
	@RequestMapping("/")
	public String index(@ModelAttribute("users") UserEntity newEntry, BindingResult result) {
		this.userId = 0;
		return "index";
	}
	
	@PostMapping("/Login")
	public String postLogin(@Valid @ModelAttribute("users") UserEntity newEntry, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "index";
		}
		List<UserEntity> users = userRepository.findByEmail(newEntry.getEmail());
		
		if (!users.isEmpty()) {
			UserEntity user = users.get(0);
			if (user.getPassword().equals(newEntry.getPassword())) {
				this.userId = user.getId();
				return "redirect:/messages";
			}
		}
		model.addAttribute("errorMessage", "Email or Password not found");
		return "index";
	}
	
	
	@GetMapping("/register")
	public String getRegister(@ModelAttribute("users") UserEntity newEntry, BindingResult result, Model model) {
		
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@Valid @ModelAttribute("users") UserEntity newEntry, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "register";
		}
		List<UserEntity> users = userRepository.findByEmail(newEntry.getEmail());
		if (!users.isEmpty()) {
			model.addAttribute("errorMessage", "You already Register");
			return "register";
		}
		userRepository.save(newEntry);
		return "redirect:/";
	}
	
	@GetMapping("/messages")
	public String getMessages(Model model) {
		if(this.userId == 0) {
			return "redirect:/";
		}
		if(errorMessage != null) {
			model.addAttribute("errorMessage", errorMessage);
			Optional<UserEntity> users = userRepository.findById(this.userId);
			UserEntity user = users.orElse(null);
			model.addAttribute("user", user);
			model.addAttribute("messages", messageRepository.findAll());
			errorMessage = null;
			return "success";
		}
		Optional<UserEntity> users = userRepository.findById(this.userId);
		UserEntity user = users.orElse(null);
		model.addAttribute("user", user);
		model.addAttribute("messages", messageRepository.findAll());
		return "success";
	}
	
	@GetMapping("/addMessage")
	public String getAddMessage(@ModelAttribute("messages") GuestBookEntity newMessageEntry, BindingResult result, Model model) {
		if(this.userId == 0) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "message";
		}
		Optional<UserEntity> users = userRepository.findById(this.userId);
		UserEntity user = users.orElse(null);
		if(user != null) {
			model.addAttribute("user", user);
			return "message";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/addMessage")
	public String postAddMessage(@Valid @ModelAttribute("messages") GuestBookEntity newMessageEntry, BindingResult result, Model model) {
		if(this.userId == 0) {
			return "redirect:/";
		}
		Optional<UserEntity> users = userRepository.findById(this.userId);
		UserEntity user = users.orElse(null);
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "message";
		}
		newMessageEntry.setUser(user);
		user.getMessages().add(newMessageEntry);
		userRepository.save(user);
		return "redirect:/messages";
	}
	
	@GetMapping("/editMessage/{id}")
	public String getEditMessage(@PathVariable int id, @ModelAttribute("messages") GuestBookEntity newMessageEntry, BindingResult result, Model model) {
		Optional<GuestBookEntity> messagesList =  messageRepository.findById(id);
		GuestBookEntity message = messagesList.orElse(null);
		UserEntity user = message.getUser();
		newMessageEntry = message;
		if(this.userId.equals(user.getId())) {
			model.addAttribute("messages", newMessageEntry);
			model.addAttribute("user", user);
			return "editMessage";
		}else {
			errorMessage = "This is not your message!";
			return "redirect:/messages";
		}
	}
	
	@PostMapping("/editMessage/{id}")
	public String postEditMessage(@PathVariable int id, @Valid @ModelAttribute("messages") GuestBookEntity newMessageEntry, BindingResult result, Model model) {
		if(this.userId == 0) {
			return "redirect:/";
		}
		Optional<UserEntity> users = userRepository.findById(this.userId);
		UserEntity user = users.orElse(null);
		Optional<GuestBookEntity> messages =  messageRepository.findById(id);
		GuestBookEntity message = messages.orElse(null);
		if(result.hasErrors()) {
			model.addAttribute("user", user);
			return "editMessage";
		}
		model.addAttribute("user", user);
		message.setMessage(newMessageEntry.getMessage());
		message.setDate(LocalDate.now());
		messageRepository.save(message);
		return "redirect:/messages";
	}
	
	@GetMapping("/deleteMessage/{id}")
	public String getDeleteMessage(@PathVariable int id, Model model) {
		Optional<GuestBookEntity> messages =  messageRepository.findById(id);
		GuestBookEntity message = messages.orElse(null);
		if(message.getUser().getId().equals(this.userId)) {
			if(message != null) {
				messageRepository.delete(message);
			}
		}
		else {
			errorMessage = "This is not your message!";
		}
		return "redirect:/messages";
	}
	
	@GetMapping("/logout")
	public String getLogout() {
		this.userId = 0;
		return "redirect:/";
	}
	
}