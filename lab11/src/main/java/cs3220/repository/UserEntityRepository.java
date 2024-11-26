package cs3220.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import cs3220.model.UserEntity;

public interface UserEntityRepository extends CrudRepository<UserEntity, Integer>{
	List<UserEntity> findByEmail(String email);
	Optional<UserEntity> findById(Integer id);
	

}
