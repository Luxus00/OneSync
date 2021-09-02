package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.DanhSachKhoanGonDTO;

public interface IDanhSachKhoanGonService {
	public ArrayList<DanhSachKhoanGonDTO> getAll();

	void delete(Integer id, Integer version);

	public DanhSachKhoanGonDTO getById(Integer id);

	DanhSachKhoanGonDTO update(DanhSachKhoanGonDTO t);

	DanhSachKhoanGonDTO insert(DanhSachKhoanGonDTO t);

}
