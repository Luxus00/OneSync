package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.LoaiQuyetDinhDTO;

public interface ILoaiQuyetDinhService {
	public ArrayList<LoaiQuyetDinhDTO> getAll();

	void delete(Integer id, Integer version);

	public LoaiQuyetDinhDTO getById(Integer id);

	LoaiQuyetDinhDTO update(LoaiQuyetDinhDTO t);

	LoaiQuyetDinhDTO insert(LoaiQuyetDinhDTO t);
}
