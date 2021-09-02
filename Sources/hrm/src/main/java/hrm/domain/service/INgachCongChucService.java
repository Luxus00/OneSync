package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.NgachCongChucDTO;

public interface INgachCongChucService {
	public ArrayList<NgachCongChucDTO> getAll();

	void delete(Integer id, Integer version);

	public NgachCongChucDTO getById(Integer id);

	NgachCongChucDTO update(NgachCongChucDTO t);

	NgachCongChucDTO insert(NgachCongChucDTO t);

	NgachCongChucDTO getNgachCongChucById(Integer ngachCongChucPk);
}
