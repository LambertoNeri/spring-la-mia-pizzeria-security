package com.experis.course.springlamiapizzeriacrud.security;

import com.experis.course.springlamiapizzeriacrud.model.User;
import com.experis.course.springlamiapizzeriacrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // prendo lo username che viene dalla login e cerco sul db uno user con quella email
    Optional<User> loggedUser = userRepository.findByEmail(username);
    if (loggedUser.isPresent()) {
      // c'è un cliente con quella email
      // restituisco un DatabaseUserDetails con i dati dello User
      return new DatabaseUserDetails(loggedUser.get());
    } else {
      // non c'è un utente con quell'email
      throw new UsernameNotFoundException(username);
    }
  }
}
