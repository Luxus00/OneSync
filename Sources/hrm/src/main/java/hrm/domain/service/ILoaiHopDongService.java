package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.LoaiHopDongDTO;

public interface ILoaiHopDongService {
	public ArrayList<LoaiHopDongDTO> getAll();

	void delete(Integer id, Integer version);

	public LoaiHopDongDTO getById(Integer id);

	LoaiHopDongDTO update(LoaiHopDongDTO t);

	LoaiHopDongDTO insert(LoaiHopDongDTO t);
}
