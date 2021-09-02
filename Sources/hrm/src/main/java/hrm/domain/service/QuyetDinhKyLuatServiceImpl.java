package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.CanBoDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.QuyetDinhKyLuatDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hrm.domain.model.entity.Canbo;
import hrm.domain.model.entity.Donvichucnang;
import hrm.domain.model.entity.Quyetdinhkyluat;
import hrm.domain.repository.ICanBoRepository;
import hrm.domain.repository.IDonViChucNangRepository;
import hrm.domain.repository.IQuyetDinhKyLuatRepository;

@Service
@Transactional(rollbackFor = Throwable.class)
public class QuyetDinhKyLuatServiceImpl implements IQuyetDinhKyLuatService {
	@Autowired
	private IQuyetDinhKyLuatRepository repo;
	@Autowired
	protected DozerBeanMapper mapper;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	@Autowired
	private IDonViChucNangRepository donViChucNangRepo;
	@Autowired
	private ICanBoRepository canBoRepo;

	@Override
	public ArrayList<QuyetDinhKyLuatDTO> getAll() {
		ArrayList<QuyetDinhKyLuatDTO> ketqua = new ArrayList<>();

		Iterable<Quyetdinhkyluat> listFromDb = repo.findAll();

		for (Quyetdinhkyluat d : listFromDb) {
			DonViChucNangDTO donViChucNangDto = mapper.map(
					d.getDonvichucnang(), DonViChucNangDTO.class);
			CanBoDTO canBoDto = mapper.map(d.getCanbo(), CanBoDTO.class);

			QuyetDinhKyLuatDTO QuyetDinhKyLuatDTO = mapper.map(d,
					QuyetDinhKyLuatDTO.class);

			QuyetDinhKyLuatDTO.setDonViChucNang(donViChucNangDto);
			QuyetDinhKyLuatDTO.setCanBo(canBoDto);
			QuyetDinhKyLuatDTO.setNgayKy(d.getNgayKy());
			QuyetDinhKyLuatDTO.setTuNgay(d.getTuNgay());
			QuyetDinhKyLuatDTO.setDenNgay(d.getDenNgay());
			ketqua.add(QuyetDinhKyLuatDTO);
		}
		return ketqua;
	}

	@Override
	public void delete(Integer id, Integer version) {
		Quyetdinhkyluat entity = new Quyetdinhkyluat();
		entity = repo.getOneByPkAndVersion(id, version);
		if (entity == null) {
			throw new OptimisticLockingFailureException(
					"Concurrent update error");
		}
		repo.delete(entity);
	}

	@Override
	public QuyetDinhKyLuatDTO getById(Integer id) {
		Quyetdinhkyluat entity = repo.getOne(id);
		DonViChucNangDTO donViChucNangDto = mapper.map(
				entity.getDonvichucnang(), DonViChucNangDTO.class);
		donViChucNangDto.setCanBo(donViChucNangService
				.getCanBoByDonViChucNang(donViChucNangDto.getPk()));
		QuyetDinhKyLuatDTO dto = mapper.map(entity, QuyetDinhKyLuatDTO.class);
		dto.setDonViChucNang(donViChucNangDto);
		dto.setNgayKy(entity.getNgayKy());
		dto.setTuNgay(entity.getTuNgay());
		dto.setDenNgay(entity.getDenNgay());
		if (entity.getCanbo() != null) {
			CanBoDTO canDto = mapper.map(entity.getCanbo(), CanBoDTO.class);
			dto.setCanBo(canDto);
		}
		return dto;
	}

	@Override
	public QuyetDinhKyLuatDTO update(QuyetDinhKyLuatDTO t) {
		addOrUpdate(t);
		return t;
	}

	@Override
	public QuyetDinhKyLuatDTO insert(QuyetDinhKyLuatDTO t) {
		addOrUpdate(t);
		return t;
	}

	private Quyetdinhkyluat addOrUpdate(QuyetDinhKyLuatDTO dto) {
		Quyetdinhkyluat entity = new Quyetdinhkyluat();

		if (dto.getPk() != null && dto.getPk().intValue() != -1) {
			entity = repo.getOneByPkAndVersion(dto.getPk(), dto.getVersion());
			if (entity == null) {
				throw new OptimisticLockingFailureException("Concurrent update error");
			}
		}
		mapper.map(dto, entity);
		if (dto.getDonViChucNang() != null) {
			Donvichucnang donViChucNangEntity = donViChucNangRepo.getOne(dto.getDonViChucNang().getPk());
			entity.setDonvichucnang(donViChucNangEntity);
		}
		if (dto.getCanBo() != null) {
			Canbo canBoEntity = canBoRepo.getOne(dto.getCanBo().getPk());
			entity.setCanbo(canBoEntity);
		}
		return repo.save(entity);
	}
}
