package com.app.appthitracnghiem_api.security;

import com.app.appthitracnghiem_api.entity.Accounts;
import com.app.appthitracnghiem_api.entity.Role_Account;
import com.app.appthitracnghiem_api.service.AccountServiceImp;
import com.app.appthitracnghiem_api.service.RoleAccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	AccountServiceImp accountServiceImp;
	
	@Autowired
	RoleAccountServiceImp roleAccountServiceImp;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		
		
		Accounts accounts = accountServiceImp.getAccountByUsername(username);
		
		
		//cacsh 1
		List<Map<String, ?>> listRole = roleAccountServiceImp.getAllRolesByUsername(username);

		for (Map<String, ?> map : listRole) {
			System.out.println("Kiem tra role " + map.get("role_name"));
			SimpleGrantedAuthority role = new SimpleGrantedAuthority(map.get("role_name").toString());
			roles.add(role);
		}
		
		
		//cachs 2
//		List<Role_User> listRole = roleUserServiceImp.getRoleByUserId(users.getId());
//		
//		for (Role_User role_User : listRole) {
//			System.out.println("Kiem tra role " + role_User.getRoles().getRoleName());
//			SimpleGrantedAuthority role = new SimpleGrantedAuthority( role_User.getRoles().getRoleName());
//			roles.add(role);
//		}
		
		//cách 3 :
//		for (Role_Account roleAccount : accounts.getListRoleAccount()) {
//			System.out.println("Kiem tra role " + roleAccount.getRole().getRoleName());
//			SimpleGrantedAuthority role = new SimpleGrantedAuthority( roleAccount.getRole().getRoleName());
//			roles.add(role);
//		}

//		SimpleGrantedAuthority role = new SimpleGrantedAuthority( "ROLE_ADMIN");
//		roles.add(role);
		User user = new User(accounts.getUsername(), accounts.getPassword(), roles);
		return user;
	}

}
