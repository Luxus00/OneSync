package com.vnpt.eoffice.service;

import com.vnpt.eoffice.dto.DonViChucNangDTO;

import java.util.ArrayList;
import java.util.List;

public interface IDonViChucNangService {
	List<DonViChucNangDTO> getAll();

	void delete(Integer id, Integer version);

	DonViChucNangDTO getById(Integer id);

	DonViChucNangDTO update(DonViChucNangDTO t);

	DonViChucNangDTO insert(DonViChucNangDTO t);
}
