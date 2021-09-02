package hrm.domain.controller;

import java.sql.SQLException;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.ChucVuDTO;
import hrm.domain.service.IChucVuService;
import hrm.listener.ApiSynchronized;
import hrm.listener.Const;
import hrm.listener.Message;
import hrm.listener.PublicData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiCommonResponse
@RequestMapping("/chucVuController")
public class ChucVuController {
	@Autowired
	private IChucVuService chucvu;

	@GetMapping("/show")
	public String show() {
		return "chucvu";
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		chucvu.delete(pK, version);
		return "chucvu";
	}

	@PostMapping("/update")
	public String update(@RequestBody ChucVuDTO lhd, Model model)
			throws SQLException {
		chucvu.update(lhd);
		return "chucvu";
	}
	@Autowired
	PublicData publicData;
	@PostMapping("/add")
	public String insert(@RequestBody ChucVuDTO cv)
			throws SQLException {
		chucvu.insert(cv);
		publicData.postForObject(Message.builder()
				.data(cv)
				.apiType(Const.CURRENT_SERVICE_NAME)
				.apiType(ApiSynchronized.CHUC_VU_CREATE.name())
				.build());
		return "chucvu";
	}
}
