package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.DanTocDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import hrm.domain.model.entity.Dantoc;
import hrm.domain.repository.IDanTocRepository;

@Component
public class DanTocImpl implements IDanTocService {
	@Autowired
	private IDanTocRepository repo;

	@Autowired
	protected DozerBeanMapper mapper;

	@Override
	public ArrayList<DanTocDTO> getAll() {
		ArrayList<DanTocDTO> ketqua = new ArrayList<>();

		Iterable<Dantoc> listFromDb = repo.findAll();

		for (Dantoc d : listFromDb) {
			ketqua.add(mapper.map(d, DanTocDTO.class));
		}
		return ketqua;
	}

	@Override
	public void delete(Integer id, Integer version) {
		Dantoc entity = new Dantoc();
		entity = repo.getOneByPkAndVersion(id, version);
		if (entity == null) {
			throw new OptimisticLockingFailureException(
					"Concurrent update error");
		}
		repo.delete(entity);
	}

	@Override
	public DanTocDTO getById(Integer id) {
		Dantoc entity = repo.getOne(id);
		return mapper.map(entity, DanTocDTO.class);
	}

	@Override
	public DanTocDTO update(DanTocDTO t) {
		addOrUpdate(t);
		return t;
	}

	@Override
	public DanTocDTO insert(DanTocDTO t) {
		addOrUpdate(t);
		return t;
	}

	private Dantoc addOrUpdate(DanTocDTO dto) {
		Dantoc entity = new Dantoc();

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
