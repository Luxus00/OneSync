package hrm.domain.controller;

import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.CanBoDTO;
import hrm.domain.model.dto.DanhSachThamNienDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.service.ICanBoService;
import hrm.domain.service.IDanhSachThamNienService;
import hrm.domain.service.IDonViChucNangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ApiCommonResponse
@RequestMapping("/danhSachThamNienController")
public class DanhSachThamNienController {
	@Autowired
	private IDanhSachThamNienService danhSachThamNienService;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	@Autowired
	private ICanBoService canBoService;

	@GetMapping("/show")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> donViChucNangLists = donViChucNangService
				.getAll();
		ArrayList<CanBoDTO> canBoLists = canBoService.getAll();
		ModelAndView model = new ModelAndView("danhsachthamnien");
		model.addObject("donViChucNangLists", donViChucNangLists);
		model.addObject("canBoLists", canBoLists);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, Model model) {
		danhSachThamNienService.delete(pK);
		return "danhsachthamnien";
	}

	@PostMapping("/update")
	public String update(@RequestBody DanhSachThamNienDTO dstn, Model model) {
		danhSachThamNienService.update(dstn);
		return "danhsachthamnien";
	}

	@PostMapping("/add")
	public String insert(@RequestBody DanhSachThamNienDTO dstn, Model model) {
		danhSachThamNienService.insert(dstn);
		return "danhsachthamnien";
	}
}
