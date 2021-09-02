package com.vnpt.eoffice.service.impl;

import com.vnpt.eoffice.config.GenericMapper;
import com.vnpt.eoffice.controller.listener.Message;
import com.vnpt.eoffice.controller.listener.PublicData;
import com.vnpt.eoffice.domain.Chucvu;
import com.vnpt.eoffice.dto.ChucVuDTO;
import com.vnpt.eoffice.repository.IChucVuRepository;
import com.vnpt.eoffice.service.IChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ChucVuImpl implements IChucVuService {
	@Autowired
	private IChucVuRepository repo;
	
	@Autowired
	protected GenericMapper mapper;

	@Override
	public ArrayList<ChucVuDTO> getAll() {
		ArrayList<ChucVuDTO> ketqua = new ArrayList<>();

		Iterable<Chucvu> listFromDb = repo.findAll();

		for (Chucvu d : listFromDb) {
			ketqua.add(mapper.mapToType(d, ChucVuDTO.class));
		}
		return ketqua;
	}

	@Override
	public void delete(Integer id, Integer version) {
		Chucvu entity = new Chucvu();
		entity = repo.getOneByPkAndVersion(id, version);
		if (entity == null) {
			throw new OptimisticLockingFailureException(
					"Concurrent update error");
		}
		repo.delete(entity);
	}

	@Override
	public ChucVuDTO getById(Integer id) {
		Chucvu entity = repo.getOne(id);
		return mapper.mapToType(entity, ChucVuDTO.class);
	}

	@Override
	public ChucVuDTO update(ChucVuDTO t) {
		addOrUpdate(t);
		return t;
	}

	@Override
	public ChucVuDTO insert(ChucVuDTO t) {
		addOrUpdate(t);
		return t;
	}

	private Chucvu addOrUpdate(ChucVuDTO dto) {
		Chucvu entity = new Chucvu();


		mapper.mapSrcToDestNotNullProperty(dto, entity);
		Message message=new Message();
		message.setData(entity);
		message.setApiType("add");
		return repo.save(entity);
	}
	@Autowired
	PublicData publicData;
}
