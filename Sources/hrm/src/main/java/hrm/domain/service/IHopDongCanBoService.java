package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.HopDongCanBoDTO;

public interface IHopDongCanBoService {
	ArrayList<HopDongCanBoDTO> getAll();

	void delete(Integer id, Integer version);

	HopDongCanBoDTO getById(Integer id);

	HopDongCanBoDTO update(HopDongCanBoDTO t);

	HopDongCanBoDTO insert(HopDongCanBoDTO t);
}
