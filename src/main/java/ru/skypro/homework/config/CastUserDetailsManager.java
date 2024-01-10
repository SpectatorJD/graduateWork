package ru.skypro.homework.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
@Service
public class CastUserDetailsManager implements UserDetailsManager {

    private final UserRepository userRepository;

    public CastUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(UserDetails user) {

    }
    public void createUser(Register register) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(register.getUsername());
        userEntity.setPassword(register.getPassword());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setRole(register.getRole());
        userEntity.setPhone(register.getPhone());
        userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return (userRepository.findByEmail(username).isPresent()) ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(CastUserDetails::new).orElseThrow();
    }
}
