//package HouseWith.hwf.web.Login.Security.User;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@RequiredArgsConstructor
//@Getter
//public class CustomUserDetails implements UserDetails {
//    private final Long id;
//    private final String email;
//    private final String nickname;
//
//    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return null; }
//    @Override public String getPassword() { return null; }
//    @Override public String getUsername() { return null; }
//    @Override public boolean isAccountNonExpired() { return true; }
//    @Override public boolean isAccountNonLocked() { return true; }
//    @Override public boolean isCredentialsNonExpired() { return true; }
//    @Override public boolean isEnabled() { return true; }
//}
