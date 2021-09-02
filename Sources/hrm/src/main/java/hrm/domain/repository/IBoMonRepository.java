package hrm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Bomon;

@Repository
public interface IBoMonRepository extends JpaRepository<Bomon, Integer> {

}
