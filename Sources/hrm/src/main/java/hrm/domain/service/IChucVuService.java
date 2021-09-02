package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.ChucVuDTO;

public interface IChucVuService {
	public ArrayList<ChucVuDTO> getAll();

	void delete(Integer id, Integer version);

	public ChucVuDTO getById(Integer id);

	ChucVuDTO update(ChucVuDTO t);

	ChucVuDTO insert(ChucVuDTO t);
}
