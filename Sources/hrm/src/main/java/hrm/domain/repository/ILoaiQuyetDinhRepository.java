package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Loaiquyetdinh;

@Repository
public interface ILoaiQuyetDinhRepository extends
		JpaRepository<Loaiquyetdinh, Integer> {
	@Query("SELECT lqd from Loaiquyetdinh lqd where lqd.pk=:pk and lqd.version=:version")
	Loaiquyetdinh getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);

}
