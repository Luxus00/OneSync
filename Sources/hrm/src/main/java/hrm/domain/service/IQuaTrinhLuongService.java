package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.QuaTrinhLuongDTO;

public interface IQuaTrinhLuongService {
	public ArrayList<QuaTrinhLuongDTO> getAll();

	void delete(Integer id);

	public QuaTrinhLuongDTO getById(Integer id);

	QuaTrinhLuongDTO update(QuaTrinhLuongDTO t);

	QuaTrinhLuongDTO insert(QuaTrinhLuongDTO t);
}
