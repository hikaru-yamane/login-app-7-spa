package com.example.demo.service.p01_login;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.p01_login.User;

public class UserDetailsImpl implements UserDetails {
	
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public Integer getId() {
		return this.user.getId();
	}
	
	@Override
	public String getUsername() {
		return this.user.getName();
	}
	
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_" + this.user.getRoleName());
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}