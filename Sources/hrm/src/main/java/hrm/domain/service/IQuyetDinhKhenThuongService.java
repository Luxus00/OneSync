package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.QuyetDinhKhenThuongDTO;

public interface IQuyetDinhKhenThuongService {
	public ArrayList<QuyetDinhKhenThuongDTO> getAll();

	void delete(Integer id, Integer version);

	public QuyetDinhKhenThuongDTO getById(Integer id);

	QuyetDinhKhenThuongDTO update(QuyetDinhKhenThuongDTO t);

	QuyetDinhKhenThuongDTO insert(QuyetDinhKhenThuongDTO t);
}
