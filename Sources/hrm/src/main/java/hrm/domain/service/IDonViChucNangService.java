package hrm.domain.service;

import java.util.ArrayList;
import java.util.Set;

import hrm.domain.model.dto.BoMonDTO;
import hrm.domain.model.dto.CanBoDTO;
import hrm.domain.model.dto.DonViChucNangDTO;

public interface IDonViChucNangService {
	public ArrayList<DonViChucNangDTO> getAll();

	void delete(Integer id, Integer version);

	public DonViChucNangDTO getById(Integer id);

	DonViChucNangDTO update(DonViChucNangDTO t);

	DonViChucNangDTO insert(DonViChucNangDTO t);

	Set<BoMonDTO> getBoMonByDonViChucNang(Integer donViChucNangPk);

	Set<CanBoDTO> getCanBoByDonViChucNang(Integer donViChucNangPk);
}
