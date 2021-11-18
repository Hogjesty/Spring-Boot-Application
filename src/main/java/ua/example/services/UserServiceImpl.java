package ua.example.services;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ua.example.dtos.UserDTO;
import ua.example.domain.entity.User;
import ua.example.repositories.UserRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Override
    public List<UserDTO> getAll() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(User::getDTO)
                .collect(Collectors.toList());
    }


    @Override
    public Page<UserDTO> getPageBySearch(String search, Pageable pageable) {

        Page<User> page = userRepository
                .findByNameLikeOrSurnameLike(
                        "%" + search + "%",
                        "%" + search + "%",
                        pageable);

        return page.map(User::getDTO);
    }


    @Override
    public Page<UserDTO> getPage(Pageable pageable) {

        Page<User> page = userRepository.findAll(pageable);

        return page.map(User::getDTO);
    }

    @Override
    public void update(UserDTO userDTO) {

        User user = userRepository.getById(userDTO.getId());

        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setAge(userDTO.getAge());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        userRepository.save(user);
    }

    @Override
    public UserDTO getById(Integer id) {
        User user = userRepository.getById(id);

        return user.getDTO();
    }

    @Override
    public void addUser(UserDTO userDTO) {

        User user = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .age(userDTO.getAge())
                .phoneNumber(userDTO.getPhoneNumber())
                .build();

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

//    @Override
//    public Page<UserDTO> getUsers(Pageable pageable) {
//        int size = pageable.getPageSize();
//        int pageNumber = pageable.getPageNumber();
//        int startPage = size*pageNumber;
//        Page<UserDTO> userDTOPage = new P
//        return null;
//    }
}
