package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.DanTocDTO;

public interface IDanTocService {
	public ArrayList<DanTocDTO> getAll();

	void delete(Integer id, Integer version);

	public DanTocDTO getById(Integer id);

	DanTocDTO update(DanTocDTO t);

	DanTocDTO insert(DanTocDTO t);
}
