package hrm.domain.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.DanhSachKhoanGonDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.service.IDanhSachKhoanGonService;
import hrm.domain.service.IDonViChucNangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ApiCommonResponse
@RequestMapping("/danhSachKhoanGonController")
public class DanhSachKhoanGonController {
	@Autowired
	private IDanhSachKhoanGonService danhSachKhoanGonService;
	@Autowired
	private IDonViChucNangService donViChucNangService;

	@GetMapping("/show")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> list = donViChucNangService.getAll();
		ModelAndView model = new ModelAndView("danhsachkhoangon");
		model.addObject("donViChucNangLists", list);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "pK") Integer pK,
			@RequestParam(value = "version") Integer version) {
		danhSachKhoanGonService.delete(pK, version);
		return "danhsachkhoangon";
	}

	@PostMapping("/update")
	public String update(@RequestBody DanhSachKhoanGonDTO dskg, Model model)
			throws SQLException {
		danhSachKhoanGonService.update(dskg);
		return "danhsachkhoangon";
	}

	@PostMapping("/add")
	public String insert(@RequestBody DanhSachKhoanGonDTO dskg, Model model)
			throws SQLException {
		danhSachKhoanGonService.insert(dskg);
		return "danhsachkhoangon";
	}
}