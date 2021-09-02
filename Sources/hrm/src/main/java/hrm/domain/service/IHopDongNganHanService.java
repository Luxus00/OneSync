package hrm.domain.service;

import java.util.ArrayList;

import hrm.domain.model.dto.HopDongNganHanDTO;

public interface IHopDongNganHanService {

    ArrayList<HopDongNganHanDTO> getAll() ;

    void delete(Integer id, Integer version) ;

    HopDongNganHanDTO getById(Integer id) ;

    HopDongNganHanDTO update(HopDongNganHanDTO t) ;

    HopDongNganHanDTO insert(HopDongNganHanDTO t) ;
}
