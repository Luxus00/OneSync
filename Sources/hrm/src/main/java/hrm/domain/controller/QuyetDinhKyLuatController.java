package hrm.domain.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.QuyetDinhKyLuatDTO;
import hrm.domain.service.IDonViChucNangService;
import hrm.domain.service.IQuyetDinhKyLuatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ApiCommonResponse
@RequestMapping("/quyetDinhKyLuatController")
public class QuyetDinhKyLuatController {
	@Autowired
	private IQuyetDinhKyLuatService quyetDinhKyLuatService;
	@Autowired
	private IDonViChucNangService donViChucNangService;

	@GetMapping("/show")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> donViChucNanglists = donViChucNangService.getAll();
		ModelAndView model = new ModelAndView("quyetdinhkyluat");
		model.addObject("donViChucNanglists", donViChucNanglists);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		quyetDinhKyLuatService.delete(pK, version);
		return "quyetdinhkyluat";
	}

	@PostMapping("/update")
	public String update(@RequestBody QuyetDinhKyLuatDTO qdkl, Model model) throws SQLException {
		quyetDinhKyLuatService.update(qdkl);
		return "quyetdinhkyluat";
	}

	@PostMapping("/add")
	public String insert(@RequestBody QuyetDinhKyLuatDTO qdkl, Model model) throws SQLException {
		quyetDinhKyLuatService.insert(qdkl);
		return "quyetdinhkyluat";
	}
}