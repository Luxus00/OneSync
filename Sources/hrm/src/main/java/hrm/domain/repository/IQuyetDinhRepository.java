package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Quyetdinh;

@Repository
public interface IQuyetDinhRepository extends JpaRepository<Quyetdinh, Integer> {
	@Query("SELECT qd from Quyetdinh qd where qd.pk=:pk and qd.version=:version")
	Quyetdinh getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
