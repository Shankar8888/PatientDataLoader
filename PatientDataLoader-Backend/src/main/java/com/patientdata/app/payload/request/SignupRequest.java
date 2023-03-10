package com.patientdata.app.payload.request;

import java.util.Set;

import javax.validation.constraints.*;
 
public class SignupRequest {
	
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    private Set<String> roles;
    private String role;
    
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    
	private boolean isActive;
	
//	@NotBlank
//	@Size(max = 10, message = "mobileNo accepts upto 10 digits")
//	@Pattern(regexp="(^$|[0-9]{10})",message = "mobileNo accepts numbers only")
//	private String mobileNo;

  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
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
    
//    public Set<String> getRole() {
//      return this.role;
//    }
//    
//    public void setRole(Set<String> role) {
//      this.role = role;
//    }

	public boolean isActive() {
		return isActive;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
//
//	public String getMobileNo() {
//		return mobileNo;
//	}
//
//	public void setMobileNo(String mobileNo) {
//		this.mobileNo = mobileNo;
//	}
}
