package hrm.domain.controller;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.HopDongNganHanDTO;
import hrm.domain.service.IHopDongNganHanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiCommonResponse
@RequestMapping("/hopDongNganHanController")
public class HopDongNganHanController {
	@Autowired
	private IHopDongNganHanService hopDongNganHan;

	@GetMapping("/show")
	public String show() {
		return "hopdongnganhan";
	}

	@RequestMapping(value = "/delete/{pk}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		hopDongNganHan.delete(pK, version);
		return "hopdongnganhan";
	}

	@PostMapping("/update")
	public String update(@RequestBody HopDongNganHanDTO hd, Model model) {
		hopDongNganHan.update(hd);
		return "hopdongnganhan";
	}
	
	@PostMapping("/add")
	public String insert(@RequestBody HopDongNganHanDTO hd, Model model) {
		hopDongNganHan.insert(hd);
		return "hopdongnganhan";
	}

}
