package com.vnpt.eoffice.service.impl;


import com.vnpt.eoffice.config.GenericMapper;
import com.vnpt.eoffice.domain.Donvichucnang;
import com.vnpt.eoffice.dto.DonViChucNangDTO;
import com.vnpt.eoffice.repository.IDonViChucNangRepository;
import com.vnpt.eoffice.service.IDonViChucNangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DonViChucNangServiceImpl implements IDonViChucNangService {
	@Autowired
	private IDonViChucNangRepository repo;
	@Autowired
	GenericMapper mapper;

	@Override
	public List<DonViChucNangDTO> getAll() {
		ArrayList<DonViChucNangDTO> ketqua = new ArrayList<>();
		var listFromDb = repo.findAll();
		return mapper.mapToListOfType(listFromDb,DonViChucNangDTO.class);
	}

	@Override
	public void delete(Integer id, Integer version) {
		Donvichucnang entity = new Donvichucnang();
		entity = repo.getOneByPkAndVersion(id, version);
		if (entity == null) {
			throw new OptimisticLockingFailureException(
					"Concurrent update error");
		}
		repo.delete(entity);
	}

	@Override
	public DonViChucNangDTO getById(Integer id) {
		Donvichucnang entity = repo.getOne(id);
		DonViChucNangDTO donViChucNangDto = mapper.mapToType(entity,
				DonViChucNangDTO.class);
		return donViChucNangDto;
	}

	@Override
	public DonViChucNangDTO update(DonViChucNangDTO t) {
		addOrUpdate(t);
		return t;
	}

	@Override
	public DonViChucNangDTO insert(DonViChucNangDTO t) {
		addOrUpdate(t);
		return t;
	}

	private Donvichucnang addOrUpdate(DonViChucNangDTO dto) {
		Donvichucnang entity = new Donvichucnang();

		if (dto.getPk() != null && dto.getPk() != -1) {
			entity = repo.getOneByPkAndVersion(dto.getPk(), dto.getVersion());
			if (entity == null) {
				throw new OptimisticLockingFailureException("Concurrent update error");
			}
		}
		mapper.mapSrcToDestNotNullProperty(dto, entity);
		return repo.save(entity);
	}
}
