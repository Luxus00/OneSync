package hrm.domain.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hrm.domain.model.entity.Danhsachkhoangon;

@Repository
public interface IDanhSachKhoanGonRepository extends JpaRepository<Danhsachkhoangon, Integer> {
	@Query("SELECT dskg from Danhsachkhoangon dskg where dskg.pk=:pk and dskg.version=:version")
	Danhsachkhoangon getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
