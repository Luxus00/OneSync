package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Quyetdinhkhenthuong;

@Repository
public interface IQuyetDinhKhenThuongRepository extends JpaRepository<Quyetdinhkhenthuong, Integer> {
	@Query("SELECT qdkt from Quyetdinhkhenthuong qdkt where qdkt.pk=:pk and qdkt.version=:version")
	Quyetdinhkhenthuong getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
