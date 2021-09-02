package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.QuyetDinhKyLuatDTO;

public interface IQuyetDinhKyLuatService {
	public ArrayList<QuyetDinhKyLuatDTO> getAll();

	void delete(Integer id, Integer version);

	public QuyetDinhKyLuatDTO getById(Integer id);

	QuyetDinhKyLuatDTO update(QuyetDinhKyLuatDTO t);

	QuyetDinhKyLuatDTO insert(QuyetDinhKyLuatDTO t);
}
