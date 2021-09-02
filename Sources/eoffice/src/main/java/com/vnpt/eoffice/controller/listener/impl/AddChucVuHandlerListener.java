package com.vnpt.eoffice.controller.listener.impl;

import com.vnpt.eoffice.controller.listener.HandlerListener;
import com.vnpt.eoffice.dto.ChucVuDTO;
import com.vnpt.eoffice.service.IChucVuService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AddChucVuHandlerListener extends HandlerListener<ChucVuDTO> {
    @Autowired
    IChucVuService iChucVuService;
    @Override
    public void execute(ChucVuDTO chucVuDTO) {
        iChucVuService.insert(chucVuDTO);
    }
}
