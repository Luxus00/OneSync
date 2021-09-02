package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Hopdongcanbo;

@Repository
public interface IHopDongCanBoRepository extends JpaRepository<Hopdongcanbo, Integer> {
	@Query("SELECT hdcb from Hopdongcanbo hdcb where hdcb.pk=:pk and hdcb.version=:version")
	Hopdongcanbo getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
