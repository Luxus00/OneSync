package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Danhsachkiemnhiem;

@Repository
public interface IDanhSachKiemNhiemRepository extends JpaRepository<Danhsachkiemnhiem, Integer> {
	@Query("SELECT dskn from Danhsachkiemnhiem dskn where dskn.pk=:pk and dskn.version=:version")
	Danhsachkiemnhiem getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
