package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Loaihopdong;

@Repository
public interface ILoaiHopDongRepository extends JpaRepository<Loaihopdong, Integer> {
	@Query("SELECT lhd from Loaihopdong lhd where lhd.pk=:pk and lhd.version=:version")
	Loaihopdong getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
