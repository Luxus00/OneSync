package com.vnpt.eoffice.repository;

import com.vnpt.eoffice.domain.Chucvu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IChucVuRepository extends JpaRepository<Chucvu, Integer> {
	@Query("SELECT cv from Chucvu cv where cv.pk=:pk and cv.version=:version")
	Chucvu getOneByPkAndVersion(@Param("pk") Integer pk, @Param("version") Integer version);
}
