package models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
	private int id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	/**
	 * True means the user is a manager
	 */
	@Column
	private boolean manager;
	
	

	public User(int id, String email, String password, String name, boolean manager) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.manager = manager;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

}
