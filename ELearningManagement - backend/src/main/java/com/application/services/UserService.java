package com.application.services;

import java.util.List;

import com.application.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.model.User;
import com.application.repository.UserRepository;


@Service
public class UserService
{
	private  UserRepository userRepo;

  public UserService(UserRepository userRepo){
    this.userRepo=userRepo;
  }

	public User saveUser(User user)
	{
		return userRepo.save(user);
	}



	public User updateUserProfile(User user)
	{
		return userRepo.save(user);
	}

	public List<User> getAllUsers()
	{
    return (List<User>)userRepo.findAll();
	}

	public User fetchUserByEmail(String email)
	{
		return userRepo.findByEmail(email);
	}

	public User fetchUserByUsername(String username)
	{
		return userRepo.findByUsername(username);
	}

	public User fetchUserByEmailAndPassword(String email, String password)
	{
		return userRepo.findByEmailAndPassword(email, password);
	}

	public List<User> fetchProfileByEmail(String email)
	{
		return (List<User>)userRepo.findProfileByEmail(email);
	}


  public void deleteUser(String useremail) {
    User user = userRepo.findByEmail(useremail);

    if (user != null) {
      userRepo.deleteByEmail(useremail);
    } else {
      throw new UserNotFoundException("User with email " + useremail + " does not exist");
    }
  }

}
