package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.CanBoDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.HopDongCanBoDTO;
import hrm.domain.model.dto.LoaiHopDongDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hrm.domain.model.entity.Canbo;
import hrm.domain.model.entity.Donvichucnang;
import hrm.domain.model.entity.Hopdongcanbo;
import hrm.domain.model.entity.Loaihopdong;
import hrm.domain.repository.ICanBoRepository;
import hrm.domain.repository.IDonViChucNangRepository;
import hrm.domain.repository.IHopDongCanBoRepository;
import hrm.domain.repository.ILoaiHopDongRepository;

@Service
@Transactional(rollbackFor = Throwable.class)
public class HopDongCanBoServiceImpl implements IHopDongCanBoService {
	@Autowired
	private IHopDongCanBoRepository repo;
	@Autowired
	protected DozerBeanMapper mapper;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	@Autowired
	private IDonViChucNangRepository donViChucNangRepo;
	@Autowired
	private ICanBoRepository canBoRepo;
	@Autowired
	private ILoaiHopDongRepository loaiHopDongRepo;

	@Override
	public ArrayList<HopDongCanBoDTO> getAll() {
		ArrayList<HopDongCanBoDTO> ketqua = new ArrayList<>();
		Iterable<Hopdongcanbo> listFromDb = repo.findAll();

		for (Hopdongcanbo d : listFromDb) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			CanBoDTO canBoDto = mapper.map(d.getCanbo(), CanBoDTO.class);
			LoaiHopDongDTO loaiHopDongDto = mapper.map(d.getLoaihopdong(), LoaiHopDongDTO.class);
			HopDongCanBoDTO HopDongCanBoDTO = mapper.map(d, HopDongCanBoDTO.class);
			HopDongCanBoDTO.setDonViChucNang(donViChucNangDto);
			HopDongCanBoDTO.setCanBo(canBoDto);
			HopDongCanBoDTO.setLoaiHopDong(loaiHopDongDto);
			HopDongCanBoDTO.setNgayKy(d.getNgayKy());
			HopDongCanBoDTO.setTuNgay(d.getTuNgay());
			HopDongCanBoDTO.setDenNgay(d.getDenNgay());
			ketqua.add(HopDongCanBoDTO);
		}
		return ketqua;
	}

	@Override
	public void delete(Integer id, Integer version) {
		Hopdongcanbo entity = new Hopdongcanbo();
		entity = repo.getOneByPkAndVersion(id, version);
		if(entity == null){
			throw new OptimisticLockingFailureException("Concurrent update error");
		}
		repo.delete(entity);
	}

	@Override
	public HopDongCanBoDTO getById(Integer id) {
		Hopdongcanbo entity = repo.getOne(id);
		DonViChucNangDTO donViChucNangDto = mapper.map(entity.getDonvichucnang(), DonViChucNangDTO.class);
		donViChucNangDto.setCanBo(donViChucNangService.getCanBoByDonViChucNang(donViChucNangDto.getPk()));
		LoaiHopDongDTO loaiHopDongDto = mapper.map(entity.getLoaihopdong(), LoaiHopDongDTO.class);
		
		HopDongCanBoDTO hopDongCanBoDto = mapper.map(entity, HopDongCanBoDTO.class);
		hopDongCanBoDto.setDonViChucNang(donViChucNangDto);
		hopDongCanBoDto.setLoaiHopDong(loaiHopDongDto);
		hopDongCanBoDto.setNgayKy(entity.getNgayKy());
		hopDongCanBoDto.setTuNgay(entity.getTuNgay());
		hopDongCanBoDto.setDenNgay(entity.getDenNgay());
		if (entity.getCanbo() != null) {
			CanBoDTO canDto = mapper.map(entity.getCanbo(), CanBoDTO.class);
			hopDongCanBoDto.setCanBo(canDto);
		}
		return hopDongCanBoDto;
	}

	@Override
	public HopDongCanBoDTO update(HopDongCanBoDTO t) {
		addOrUpdate(t);
		return t;
	}

	@Override
	public HopDongCanBoDTO insert(HopDongCanBoDTO t) {
		addOrUpdate(t);
		return t;
	}

	private Hopdongcanbo addOrUpdate(HopDongCanBoDTO dto) {
		Hopdongcanbo entity = new Hopdongcanbo();
		
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
		if(dto.getLoaiHopDong() != null){
			Loaihopdong loaiHopDongEntity = loaiHopDongRepo.getOne(dto.getLoaiHopDong().getPk());
			entity.setLoaihopdong(loaiHopDongEntity);
		}
		return repo.save(entity);
	}
}
