package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Quyetdinhkyluat;

@Repository
public interface IQuyetDinhKyLuatRepository extends JpaRepository<Quyetdinhkyluat, Integer> {
	@Query("SELECT qdkl from Quyetdinhkyluat qdkl where qdkl.pk=:pk and qdkl.version=:version")
	Quyetdinhkyluat getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}