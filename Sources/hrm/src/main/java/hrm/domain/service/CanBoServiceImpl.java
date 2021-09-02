package hrm.domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import hrm.domain.model.dto.BacLuongDTO;
import hrm.domain.model.dto.BoMonDTO;
import hrm.domain.model.dto.CanBoDTO;
import hrm.domain.model.dto.ChucVuDTO;
import hrm.domain.model.dto.DanTocDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.NgachCongChucDTO;
import hrm.domain.model.dto.QueQuanDTO;
import hrm.domain.model.dto.TonGiaoDTO;
import hrm.domain.model.entity.Bacluong;
import hrm.domain.model.entity.Bomon;
import hrm.domain.model.entity.Canbo;
import hrm.domain.model.entity.Chucvu;
import hrm.domain.model.entity.Dantoc;
import hrm.domain.model.entity.Donvichucnang;
import hrm.domain.model.entity.Ngachcongchuc;
import hrm.domain.model.entity.Quequan;
import hrm.domain.model.entity.Tongiao;
import hrm.domain.repository.IBacLuongRepository;
import hrm.domain.repository.IBoMonRepository;
import hrm.domain.repository.ICanBoRepository;
import hrm.domain.repository.IChucVuRepository;
import hrm.domain.repository.IDanTocRepository;
import hrm.domain.repository.IDonViChucNangRepository;
import hrm.domain.repository.INgachCongChucRepository;
import hrm.domain.repository.IQueQuanRepository;
import hrm.domain.repository.ITonGiaoRepository;

@Component
public class CanBoServiceImpl implements ICanBoService {
	@Autowired
	private ICanBoRepository repo;
	@Autowired
	private IDonViChucNangRepository donViChucNangRepo;
	@Autowired
	private IBoMonRepository boMonRepository;
	@Autowired
	private IBacLuongRepository bacLuongRepository;
	@Autowired
	private IChucVuRepository chucVuRepository;
	@Autowired
	private IDanTocRepository danTocRepository;
	@Autowired
	private ITonGiaoRepository tonGiaoRepository;
	@Autowired
	private INgachCongChucRepository ngachCongChucRepository;
	@Autowired
	private IQueQuanRepository queQuanRepository;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	@Autowired
	private INgachCongChucService ngachCongChucService;
	@Autowired
	protected DozerBeanMapper mapper;

	@Override
	public ArrayList<CanBoDTO> getAll() {
		ArrayList<CanBoDTO> ketqua = new ArrayList<>();
		Iterable<Canbo> listFromDb = repo.findAll();
		for (Canbo d : listFromDb) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}

	@Override
	public void delete(Integer id, Integer version) {
		Canbo entity = new Canbo();
		entity = repo.getOneByPkAndVersion(id, version);
		if (entity == null) {
				throw new OptimisticLockingFailureException("Concurrent update error");
		}
		repo.delete(entity);
	}

	@Override
	public CanBoDTO getById(Integer id) {
		Canbo entity = repo.getOne(Integer.valueOf(id));
		DonViChucNangDTO donViChucNangDto = mapper.map(entity.getDonvichucnang(), DonViChucNangDTO.class);
		donViChucNangDto.setBoMon(donViChucNangService.getBoMonByDonViChucNang(donViChucNangDto.getPk()));
		DanTocDTO danTocDto = mapper.map(entity.getDantoc(), DanTocDTO.class);
		TonGiaoDTO tonGiaoDto = mapper.map(entity.getTongiao(),TonGiaoDTO.class);
		ChucVuDTO chucVuDto = mapper.map(entity.getChucvu(), ChucVuDTO.class);
		NgachCongChucDTO ngachCongChucDto = mapper.map(entity.getNgachcongchuc(), NgachCongChucDTO.class);
		ngachCongChucDto.setBacLuong(ngachCongChucService.getNgachCongChucById(ngachCongChucDto.getPk()).getBacLuong());
		QueQuanDTO queQuanDto = mapper.map(entity.getQuequan(),QueQuanDTO.class);

		CanBoDTO canBoDto = mapper.map(entity, CanBoDTO.class);
		canBoDto.setDonViChucNang(donViChucNangDto);
		canBoDto.setDanToc(danTocDto);
		canBoDto.setTonGiao(tonGiaoDto);
		canBoDto.setChucVu(chucVuDto);
		canBoDto.setNgachCongChuc(ngachCongChucDto);
		canBoDto.setQueQuan(queQuanDto);
		canBoDto.setNgaySinh(entity.getNgaySinh());
		canBoDto.setNgayCapCmnd(entity.getNgayCapCmnd());
		canBoDto.setNgayNhanHocVi(entity.getNgayNhanHocVi());
		canBoDto.setNgayNhanHocHam(entity.getNgayNhanHocHam());
		canBoDto.setNgayVaoDcsvnDuBi(entity.getNgayVaoDcsvnDuBi());
		canBoDto.setNgayVaoDcsvnChinhThuc(entity.getNgayVaoDcsvnChinhThuc());
		canBoDto.setNgayNhapNgu(entity.getNgayNhapNgu());
		canBoDto.setNgayXuatNgu(entity.getNgayXuatNgu());
		canBoDto.setNgayThoiViec(entity.getNgayThoiViec());
		canBoDto.setNgayVeHuu(entity.getNgayVeHuu());
		canBoDto.setNgayTuyenDung(entity.getNgayTuyenDung());
		canBoDto.setNgayHuong(entity.getNgayHuong());
		if (entity.getBomon() != null) {
			BoMonDTO boMonDto = mapper.map(entity.getBomon(), BoMonDTO.class);
			canBoDto.setBoMon(boMonDto);
		}
		if (entity.getBacluong() != null) {
			BacLuongDTO bacLuongDto = mapper.map(entity.getBacluong(), BacLuongDTO.class);
			canBoDto.setBacLuong(bacLuongDto);
		}
		return canBoDto;
	}

	@Override
	public CanBoDTO update(CanBoDTO t) {
		addOrUpdate(t);
		return t;
	}

	@Override
	public CanBoDTO insert(CanBoDTO t) {
		addOrUpdate(t);
		return t;
	}

	private Canbo addOrUpdate(CanBoDTO dto) {
		Canbo entity = new Canbo();
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
		if (dto.getBoMon() != null) {
			Bomon bomonEntity = boMonRepository.getOne(dto.getBoMon().getPk());
			entity.setBomon(bomonEntity);
		}
		if (dto.getChucVu() != null) {
			Chucvu chucvuEntity = chucVuRepository.getOne(dto.getChucVu().getPk());
			entity.setChucvu(chucvuEntity);
		}
		if (dto.getDanToc() != null) {
			Dantoc dantocEntity = danTocRepository.getOne(dto.getDanToc().getPk());
			entity.setDantoc(dantocEntity);
		}
		if (dto.getTonGiao() != null) {
			Tongiao tongiaoEntity = tonGiaoRepository.getOne(dto.getTonGiao().getPk());
			entity.setTongiao(tongiaoEntity);
		}
		if (dto.getNgachCongChuc() != null) {
			Ngachcongchuc ngachcongchucEntity = ngachCongChucRepository.getOne(dto.getNgachCongChuc().getPk());
			entity.setNgachcongchuc(ngachcongchucEntity);
		}
		if (dto.getQueQuan() != null) {
			Quequan quequanEntity = queQuanRepository.getOne(dto.getQueQuan().getPk());
			entity.setQuequan(quequanEntity);
		}
		if (dto.getBacLuong() != null) {
			Bacluong bacluongEntity = bacLuongRepository.getOne(dto.getBacLuong().getPk());
			entity.setBacluong(bacluongEntity);
		}
		return repo.save(entity);
	}
	
	@Override
	public Set<CanBoDTO> getByGioiTinh(String cbGioiTinhs){
		Set<CanBoDTO> ketqua = new HashSet<>();
		Set<Canbo> entities  = new HashSet<>();
		entities = repo.findByGioiTinh(cbGioiTinhs);
		for (Canbo d : entities) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}
	
	@Override
	public Set<CanBoDTO> getByDanToc(Integer cbDanTocs){
		Set<CanBoDTO> ketqua = new HashSet<>();
		Set<Canbo> entities  = new HashSet<>();
		entities = repo.findByDanToc(cbDanTocs);
		for (Canbo d : entities) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}
	
	@Override
	public Set<CanBoDTO> getByTonGiao(Integer cbTonGiaos){
		Set<CanBoDTO> ketqua = new HashSet<>();
		Set<Canbo> entities  = new HashSet<>();
		entities = repo.findByTonGiao(cbTonGiaos);
		for (Canbo d : entities) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}
	
	@Override
	public Set<CanBoDTO> getByChucVu(Integer cbChucVus){
		Set<CanBoDTO> ketqua = new HashSet<>();
		Set<Canbo> entities  = new HashSet<>();
		entities = repo.findByChucVu(cbChucVus);
		for (Canbo d : entities) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}
	
	@Override
	public Set<CanBoDTO> getByChucDanh(String chucDanh){
		Set<CanBoDTO> ketqua = new HashSet<>();
		Set<Canbo> entities  = new HashSet<>();
		entities = repo.findByChucDanh(chucDanh);
		for (Canbo d : entities) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}
	
	@Override
	public Set<CanBoDTO> getByDonViBoMon(Integer cbDonViChucNangs, Integer cbBoMons){
		if (cbBoMons == null) {
			return null;
		}else{
			Set<CanBoDTO> ketqua = new HashSet<>();
			Set<Canbo> entities  = new HashSet<>();
			entities = repo.findByDonViBoMon(cbDonViChucNangs, cbBoMons);
			for (Canbo d : entities) {
				DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
				ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
				CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
				canBoDto.setDonViChucNang(donViChucNangDto);
				canBoDto.setChucVu(chucVuDto);
				ketqua.add(canBoDto);
			}
			return ketqua;
		}
	}
	
	@Override
	public Set<CanBoDTO> getByQueQuan(Integer cbQueQuans){
		Set<CanBoDTO> ketqua = new HashSet<>();
		Set<Canbo> entities  = new HashSet<>();
		entities = repo.findByQueQuan(cbQueQuans);
		for (Canbo d : entities) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}
	
	@Override
	public Set<CanBoDTO> getByNgachCongChuc(Integer cbNgachCongChucs){
		Set<CanBoDTO> ketqua = new HashSet<>();
		Set<Canbo> entities  = new HashSet<>();
		entities = repo.findByNgachCongChuc(cbNgachCongChucs);
		for (Canbo d : entities) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}
	
	@Override
	public Set<CanBoDTO> getByDonViChucNang(Integer txtDonViChucNangs){
		Set<CanBoDTO> ketqua = new HashSet<>();
		Set<Canbo> entities  = new HashSet<>();
		entities = repo.findByDonViChucNang(txtDonViChucNangs);
		for (Canbo d : entities) {
			DonViChucNangDTO donViChucNangDto = mapper.map(d.getDonvichucnang(), DonViChucNangDTO.class);
			ChucVuDTO chucVuDto = mapper.map(d.getChucvu(), ChucVuDTO.class);
			CanBoDTO canBoDto = mapper.map(d, CanBoDTO.class);
			canBoDto.setDonViChucNang(donViChucNangDto);
			canBoDto.setChucVu(chucVuDto);
			ketqua.add(canBoDto);
		}
		return ketqua;
	}

}
