package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.DanhSachKiemNhiemDTO;

public interface IDanhSachKiemNhiemService {
	public ArrayList<DanhSachKiemNhiemDTO> getAll();

	void delete(Integer id, Integer version);

	public DanhSachKiemNhiemDTO getById(Integer id);

	DanhSachKiemNhiemDTO update(DanhSachKiemNhiemDTO t);

	DanhSachKiemNhiemDTO insert(DanhSachKiemNhiemDTO t);
}
