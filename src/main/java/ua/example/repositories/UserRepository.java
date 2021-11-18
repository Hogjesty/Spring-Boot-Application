package ua.example.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.example.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findAll(Pageable pageable);


    Page<User> findByNameLikeOrSurnameLike(String name, String surname, Pageable pageable);

}
