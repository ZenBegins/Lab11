package cs3220.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cs3220.model.GuestBookEntity;

public interface GuestBookEntityRepository extends CrudRepository<GuestBookEntity, Integer>{
	Optional<GuestBookEntity> findById(Integer id);

}
