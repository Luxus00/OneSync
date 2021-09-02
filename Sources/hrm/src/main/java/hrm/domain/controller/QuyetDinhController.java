package hrm.domain.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.LoaiQuyetDinhDTO;
import hrm.domain.model.dto.QuyetDinhDTO;
import hrm.domain.service.IDonViChucNangService;
import hrm.domain.service.ILoaiQuyetDinhService;
import hrm.domain.service.IQuyetDinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ApiCommonResponse
@RequestMapping("/quyetDinhController")
public class QuyetDinhController {
	@Autowired
	private IQuyetDinhService quyetDinhService;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	@Autowired
	private ILoaiQuyetDinhService loaiQuyetDinhService;

	@GetMapping("/show")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> donViChucNanglists = donViChucNangService.getAll();
		ArrayList<LoaiQuyetDinhDTO> loaiQuyetDinhlists = loaiQuyetDinhService.getAll();
		ModelAndView model = new ModelAndView("quyetdinh");
		model.addObject("donViChucNanglists", donViChucNanglists);
		model.addObject("loaiQuyetDinhlists", loaiQuyetDinhlists);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		quyetDinhService.delete(pK, version);
		return "quyetdinh";
	}

	@PostMapping("/update")
	public String update(@RequestBody QuyetDinhDTO qd, Model model) throws SQLException {
		quyetDinhService.update(qd);
		return "quyetdinh";
	}

	@PostMapping("/add")
	public String insert(@RequestBody QuyetDinhDTO qd, Model model) throws SQLException {
		quyetDinhService.insert(qd);
		return "quyetdinh";
	}
}