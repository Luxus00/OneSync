package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.QueQuanDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hrm.domain.model.entity.Quequan;
import hrm.domain.repository.IQueQuanRepository;
@Component
public class QueQuanServiceImpl implements IQueQuanService {
	@Autowired
	private IQueQuanRepository repo;

	@Autowired
	protected DozerBeanMapper mapper;

	@Override
	public ArrayList<QueQuanDTO> getAll() {
		ArrayList<QueQuanDTO> ketqua = new ArrayList<>();

		Iterable<Quequan> listFromDb = repo.findAll();

		for (Quequan d : listFromDb) {
			ketqua.add(mapper.map(d, QueQuanDTO.class));
		}
		return ketqua;
	}
}
