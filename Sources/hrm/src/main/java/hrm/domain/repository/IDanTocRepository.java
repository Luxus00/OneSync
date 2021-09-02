package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Dantoc;

@Repository
public interface IDanTocRepository extends JpaRepository<Dantoc, Integer> {
	@Query("SELECT dt from Dantoc dt where dt.pk=:pk and dt.version=:version")
	Dantoc getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);

}
