package hrm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Quatrinhluong;

@Repository
public interface IQuaTrinhLuongRepository extends
		JpaRepository<Quatrinhluong, Integer> {
}
