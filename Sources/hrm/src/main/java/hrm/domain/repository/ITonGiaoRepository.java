package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Tongiao;

@Repository
public interface ITonGiaoRepository extends JpaRepository<Tongiao, Integer> {
	@Query("SELECT tg from Tongiao tg where tg.pk=:pk and tg.version=:version")
	Tongiao getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);

}
