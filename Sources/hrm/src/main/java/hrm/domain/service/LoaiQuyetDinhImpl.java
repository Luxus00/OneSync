package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.LoaiQuyetDinhDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import hrm.domain.model.entity.Loaiquyetdinh;
import hrm.domain.repository.ILoaiQuyetDinhRepository;

@Component
public class LoaiQuyetDinhImpl implements ILoaiQuyetDinhService {
	@Autowired
	private ILoaiQuyetDinhRepository repo;

	@Autowired
	protected DozerBeanMapper mapper;

	@Override
	public ArrayList<LoaiQuyetDinhDTO> getAll() {
		ArrayList<LoaiQuyetDinhDTO> ketqua = new ArrayList<>();

		Iterable<Loaiquyetdinh> listFromDb = repo.findAll();

		for (Loaiquyetdinh d : listFromDb) {
			ketqua.add(mapper.map(d, LoaiQuyetDinhDTO.class));
		}
		return ketqua;
	}

	@Override
	public void delete(Integer id, Integer version) {
		Loaiquyetdinh entity = new Loaiquyetdinh();
		entity = repo.getOneByPkAndVersion(id, version);
		if (entity == null) {
			throw new OptimisticLockingFailureException(
					"Concurrent update error");
		}
		repo.delete(entity);
	}

	@Override
	public LoaiQuyetDinhDTO getById(Integer id) {
		Loaiquyetdinh entity = repo.getOne(id);
		return mapper.map(entity, LoaiQuyetDinhDTO.class);
	}

	@Override
	public LoaiQuyetDinhDTO update(LoaiQuyetDinhDTO t) {
		addOrUpdate(t);
		return t;
	}

	@Override
	public LoaiQuyetDinhDTO insert(LoaiQuyetDinhDTO t) {
		addOrUpdate(t);
		return t;
	}

	private Loaiquyetdinh addOrUpdate(LoaiQuyetDinhDTO dto) {
		Loaiquyetdinh entity = new Loaiquyetdinh();

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
