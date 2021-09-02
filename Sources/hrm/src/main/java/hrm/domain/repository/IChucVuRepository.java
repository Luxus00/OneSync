package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import hrm.domain.model.entity.Chucvu;

public interface IChucVuRepository extends JpaRepository<Chucvu, Integer> {
	@Query("SELECT cv from Chucvu cv where cv.pk=:pk and cv.version=:version")
	Chucvu getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
