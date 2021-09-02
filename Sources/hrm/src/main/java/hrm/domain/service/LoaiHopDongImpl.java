package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.LoaiHopDongDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import hrm.domain.model.entity.Loaihopdong;
import hrm.domain.repository.ILoaiHopDongRepository;

@Component
public class LoaiHopDongImpl implements ILoaiHopDongService {
	@Autowired
	private ILoaiHopDongRepository repo;

	@Autowired
	protected DozerBeanMapper mapper;

	@Override
	public ArrayList<LoaiHopDongDTO> getAll() {
		ArrayList<LoaiHopDongDTO> ketqua = new ArrayList<>();

		Iterable<Loaihopdong> listFromDb = repo.findAll();

		for (Loaihopdong d : listFromDb) {
			ketqua.add(mapper.map(d, LoaiHopDongDTO.class));
		}
		return ketqua;
	}

	@Override
	public void delete(Integer id, Integer version) {
		Loaihopdong entity = new Loaihopdong();
		entity = repo.getOneByPkAndVersion(id, version);
		if (entity == null) {
			throw new OptimisticLockingFailureException(
					"Concurrent update error");
		}
		repo.delete(entity);
	}

	@Override
	public LoaiHopDongDTO getById(Integer id) {
		Loaihopdong entity = repo.getOne(id);
		return mapper.map(entity, LoaiHopDongDTO.class);
	}

	@Override
	public LoaiHopDongDTO update(LoaiHopDongDTO t) {
		addOrUpdate(t);
		return t;
	}

	@Override
	public LoaiHopDongDTO insert(LoaiHopDongDTO t) {
		addOrUpdate(t);
		return t;
	}

	private Loaihopdong addOrUpdate(LoaiHopDongDTO dto) {
		Loaihopdong entity = new Loaihopdong();

		if (dto.getPk() != null && dto.getPk().intValue() != -1) {
			entity = repo.getOneByPkAndVersion(dto.getPk(), dto.getVersion());
			if (entity == null) {
				throw new OptimisticLockingFailureException("Concurrent update error");
			}
		}
		mapper.map(dto, entity);
		return repo.save(entity);
	}
}
