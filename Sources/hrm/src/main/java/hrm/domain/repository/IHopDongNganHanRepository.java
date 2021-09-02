package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Hopdongnganhan;

@Repository
public interface IHopDongNganHanRepository extends JpaRepository<Hopdongnganhan, Integer> {
	@Query("SELECT hdnh from Hopdongnganhan hdnh where hdnh.pk=:pk and hdnh.version=:version")
	Hopdongnganhan getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);

}
