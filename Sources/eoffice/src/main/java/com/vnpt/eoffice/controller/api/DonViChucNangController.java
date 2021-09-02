package com.vnpt.eoffice.controller.api;


import com.vnpt.eoffice.controller.listener.ApiSynchronized;
import com.vnpt.eoffice.controller.listener.Message;
import com.vnpt.eoffice.controller.listener.PublicData;
import com.vnpt.eoffice.controller.response.ResponseBody;
import com.vnpt.eoffice.dto.DonViChucNangDTO;
import com.vnpt.eoffice.service.IDonViChucNangService;
import com.vnpt.eoffice.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donViChucNangController")
public class DonViChucNangController {
	@Autowired
	private IDonViChucNangService donvichucnang;

	@Autowired
	PublicData publicData;

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseBody> delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		donvichucnang.delete(pK, version);
		publicData.postForObject(new Message(
				ApiSynchronized.CHUC_VU_DELETE.name(),
				Const.CURRENT_SERVICE_NAME,
				pK
		));
		return  ResponseEntity.ok(ResponseBody.ofSuccess());
	}

	@PostMapping("/update")
	public ResponseEntity<ResponseBody> update(@RequestBody DonViChucNangDTO dv, Model model) {
		donvichucnang.update(dv);
		publicData.postForObject(new Message(
				ApiSynchronized.CHUC_VU_UPDATE.name(),
				Const.CURRENT_SERVICE_NAME,
				dv
		));
		return  ResponseEntity.ok(ResponseBody.ofSuccess());
	}

	@PostMapping("/add")
	public ResponseEntity<ResponseBody> insert(@RequestBody DonViChucNangDTO dvcn, Model model) {
		donvichucnang.insert(dvcn);
		publicData.postForObject(new Message(
				ApiSynchronized.CHUC_VU_CREATE.name(),
				Const.CURRENT_SERVICE_NAME,
				dvcn
		));
		return  ResponseEntity.ok(ResponseBody.ofSuccess());
	}
}