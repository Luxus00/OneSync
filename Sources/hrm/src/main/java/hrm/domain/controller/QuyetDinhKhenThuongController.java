package hrm.domain.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.QuyetDinhKhenThuongDTO;
import hrm.domain.service.IDonViChucNangService;
import hrm.domain.service.IQuyetDinhKhenThuongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ApiCommonResponse
@RequestMapping("/quyetDinhKhenThuongController")
public class QuyetDinhKhenThuongController {
	@Autowired
	private IQuyetDinhKhenThuongService quyetDinhKhenThuongService;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	

	@GetMapping("/show")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> donViChucNanglists = donViChucNangService.getAll();
		ModelAndView model = new ModelAndView("quyetdinhkhenthuong");
		model.addObject("donViChucNanglists", donViChucNanglists);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		quyetDinhKhenThuongService.delete(pK, version);
		return "quyetdinhkhenthuong";
	}

	@PostMapping("/update")
	public String update(@RequestBody QuyetDinhKhenThuongDTO qdkt, Model model) throws SQLException {
		quyetDinhKhenThuongService.update(qdkt);
		return "quyetdinhkhenthuong";
	}

	@PostMapping("/add")
	public String insert(@RequestBody QuyetDinhKhenThuongDTO qdkt, Model model) throws SQLException {
		quyetDinhKhenThuongService.insert(qdkt);
		return "quyetdinhkhenthuong";
	}
}