package hrm.domain.service;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import hrm.domain.model.dto.BoMonDTO;
import hrm.domain.model.dto.CanBoDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import hrm.domain.model.entity.Donvichucnang;
import hrm.domain.repository.IDonViChucNangRepository;

@Component
public class DonViChucNangServiceImpl implements IDonViChucNangService {
	@Autowired
	private IDonViChucNangRepository repo;
	@Autowired
	protected DozerBeanMapper mapper;

	@Override
	public ArrayList<DonViChucNangDTO> getAll() {
		ArrayList<DonViChucNangDTO> ketqua = new ArrayList<>();

		Iterable<Donvichucnang> listFromDb = repo.findAll();

		for (Donvichucnang d : listFromDb) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d,
					DonViChucNangDTO.class);
			donViChucNangDto.setBoMon(d.getBoMons().stream()
					.map(entity -> mapper.map(entity, BoMonDTO.class))
					.collect(Collectors.toSet()));
			ketqua.add(donViChucNangDto);
		}
		return ketqua;
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
		DonViChucNangDTO donViChucNangDto = mapper.map(entity,
				DonViChucNangDTO.class);
		// lay donvichucnang voi id, va set BoMon voi id do thanh BoMonDTo de su
		// dung
		donViChucNangDto.setBoMon(entity.getBoMons().stream()
				.map(entity1 -> mapper.map(entity1, BoMonDTO.class))
				.collect(Collectors.toSet()));
		return donViChucNangDto;
	}

	@Override
	public Set<BoMonDTO> getBoMonByDonViChucNang(Integer donViChucNangPk) {
		return repo.getOne(donViChucNangPk).getBoMons().stream()
				.map(entity -> mapper.map(entity, BoMonDTO.class))
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<CanBoDTO> getCanBoByDonViChucNang(Integer donViChucNangPk) {
		return repo.getOne(donViChucNangPk).getCanBos().stream()
				.map(entity -> mapper.map(entity, CanBoDTO.class))
				.collect(Collectors.toSet());
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
		mapper.map(dto, entity);
		return repo.save(entity);
	}
}
