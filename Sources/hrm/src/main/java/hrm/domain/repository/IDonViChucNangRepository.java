package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Donvichucnang;

@Repository
public interface IDonViChucNangRepository extends JpaRepository<Donvichucnang, Integer> {
	@Query("SELECT dvcn from Donvichucnang dvcn where dvcn.pk=:pk and dvcn.version=:version")
	Donvichucnang getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
