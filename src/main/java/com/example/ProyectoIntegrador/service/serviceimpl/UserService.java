package com.example.ProyectoIntegrador.service.serviceimpl;

import com.example.ProyectoIntegrador.persistance.entities.Rol;
import com.example.ProyectoIntegrador.persistance.entities.User;
import com.example.ProyectoIntegrador.persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

        Optional<User> user = userRepository.getUserByName(nombre);
        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        for (Rol rol: user.get().getRoles()) {
            GrantedAuthority autorizacion = new SimpleGrantedAuthority(rol.getName());
            autorizaciones.add(autorizacion);
        }
        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.get().getName(),"{noop}"+user.get().getPassword(),true,true,true,true,autorizaciones);
        return userDetail;
    }
}