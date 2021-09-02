package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.DanhSachThamNienDTO;

public interface IDanhSachThamNienService {
	public ArrayList<DanhSachThamNienDTO> getAll();

	void delete(Integer id);

	public DanhSachThamNienDTO getById(Integer id);

	DanhSachThamNienDTO update(DanhSachThamNienDTO t);

	DanhSachThamNienDTO insert(DanhSachThamNienDTO t);
}
