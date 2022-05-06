package kz.narxoz.springproject.repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository <User, Long> {

    List<User> findByEmailContaining(String email);

    User findByUsername(String username);

    List<User> findTop2ByNameStartsWith(String name);

    List<User> findByEmailEndsWith(String email);

    List<User> findBySurnameContaining(String surname);

    List<User> orderByIdAsc();

    List<User> showlastUsers();

    List<User> sortByName();

    List<User> findByEmailNotContaining(String email);

    List<User> findByNameEqualsSurname();

    List<User> emailLike();

    List<User> findDistinctByName();
}
