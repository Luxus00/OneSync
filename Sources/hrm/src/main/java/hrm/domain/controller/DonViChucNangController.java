package hrm.domain.controller;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.service.IDonViChucNangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiCommonResponse
@RequestMapping("/donViChucNangController")
public class DonViChucNangController {
	@Autowired
	private IDonViChucNangService donvichucnang;

	@GetMapping("/show")
	public String show() {
		return "donvichucnang";
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		donvichucnang.delete(pK, version);
		return "donvichucnang";
	}

	@PostMapping("/update")
	public String update(@RequestBody DonViChucNangDTO dv, Model model) {
		donvichucnang.update(dv);
		return "donvichucnang";
	}

	@PostMapping("/add")
	public String insert(@RequestBody DonViChucNangDTO dvcn, Model model) {
		donvichucnang.insert(dvcn);
		return "donvichucnang";
	}
}