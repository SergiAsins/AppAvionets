package AppAvionets.java.AppAvionets.security;

import java.util.ArrayList;
import java.util.Collection;

import AppAvionets.java.AppAvionets.roles.Role;
import AppAvionets.java.AppAvionets.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {

    private User user;

    public SecurityUser(User user){
        this.user = user;
    }

    @Override
    public String getUsername(){
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(Role role : user.getRoles()){
            System.out.println("User role : " + role.getName());
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }
}
