package cs3220.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class UserEntity {
	@Id
	@GeneratedValue
	private Integer userId;
	@NotBlank(message = "Email is required")
	private String email;
	private String userName;
	@NotBlank(message = "Password is required")
	private String password;
	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<GuestBookEntity> messages;

	public UserEntity() {

	}

	public UserEntity(String email, String userName, String password) {
		this.email = email;
		this.userName = userName;
		this.password = password;
	}

	public String getName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return userId;
	}

	public void setId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<GuestBookEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<GuestBookEntity> messages) {
		this.messages = messages;
	}

}
