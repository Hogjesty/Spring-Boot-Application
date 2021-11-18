package ua.example.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.example.dtos.UserDTO;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> getAll();

    Page<UserDTO> getPageBySearch(String search, Pageable pageable);

    Page<UserDTO> getPage(Pageable pageable);

    UserDTO getById(Integer id);

    void update(UserDTO userDTO);

    void addUser(UserDTO userDTO);

    void deleteUser(Integer userId);

//    Page<UserDTO> getUsers(Pageable pageable);
}
