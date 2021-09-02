package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Ngachcongchuc;

@Repository
public interface INgachCongChucRepository extends JpaRepository<Ngachcongchuc, Integer> {
	@Query("SELECT ncc from Ngachcongchuc ncc where ncc.pk=:pk and ncc.version=:version")
	Ngachcongchuc getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
