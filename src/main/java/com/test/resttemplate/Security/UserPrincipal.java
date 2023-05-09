package com.test.resttemplate.Security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.test.resttemplate.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPrincipal implements UserDetails{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String mobileNumber;
	private String password;
	private Collection<? extends GrantedAuthority> authorities; 
	
	public UserPrincipal(Long id, String mobileNumber, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserPrincipal build(User user) {
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName().name());
		return new UserPrincipal(user.getId(), user.getMobileNumber(), user.getPassword(), List.of(authority));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return mobileNumber;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
