package hrm.domain.service;

import hrm.domain.model.dto.BacLuongDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hrm.domain.model.entity.Bacluong;
import hrm.domain.repository.IBacLuongRepository;

@Component
public class BacLuongServiceImpl implements IBacLuongService{
	@Autowired
	private IBacLuongRepository repo;
	@Autowired
	protected DozerBeanMapper mapper;
	@Override
	public BacLuongDTO getHeSoLuongById(Integer bacLuongPk) {
		Bacluong bacLuongEntity = repo.getOne(bacLuongPk);
		BacLuongDTO bacLuongDto = mapper.map(bacLuongEntity, BacLuongDTO.class);
		return bacLuongDto;
	}
}
