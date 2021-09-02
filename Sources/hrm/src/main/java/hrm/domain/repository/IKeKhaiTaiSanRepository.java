package hrm.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Kekhaitaisan;

@Repository
public interface IKeKhaiTaiSanRepository extends
		JpaRepository<Kekhaitaisan, Integer> {
}
