package hrm.domain.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.DanhSachKiemNhiemDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.service.IDanhSachKiemNhiemService;
import hrm.domain.service.IDonViChucNangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ApiCommonResponse
@RequestMapping("/danhSachKiemNhiemController")
public class DanhSachKiemNhiemController {
	@Autowired
	private IDanhSachKiemNhiemService danhSachKiemNhiemService;
	@Autowired
	private IDonViChucNangService donViChucNangService;

	@GetMapping("/show")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> list = donViChucNangService.getAll();
		ModelAndView model = new ModelAndView("danhsachkiemnhiem");
		model.addObject("donViChucNangLists", list);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "pK") Integer pK,
			@RequestParam(value = "version") Integer version) {
		danhSachKiemNhiemService.delete(pK, version);
		return "danhsachkiemnhiem";
	}

	@PostMapping("/update")
	public String update(@RequestBody DanhSachKiemNhiemDTO dskm, Model model)
			throws SQLException {
		danhSachKiemNhiemService.update(dskm);
		return "danhsachkiemnhiem";
	}

	@PostMapping("/add")
	public String insert(@RequestBody DanhSachKiemNhiemDTO dskm, Model model)
			throws SQLException {
		danhSachKiemNhiemService.insert(dskm);
		return "danhsachkiemnhiem";
	}
}
