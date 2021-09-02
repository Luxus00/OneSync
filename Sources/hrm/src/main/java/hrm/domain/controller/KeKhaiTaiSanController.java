package hrm.domain.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.CanBoDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.KeKhaiTaiSanDTO;
import hrm.domain.service.ICanBoService;
import hrm.domain.service.IDonViChucNangService;
import hrm.domain.service.IKeKhaiTaiSanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ApiCommonResponse
@RequestMapping("/keKhaiTaiSanController")
public class KeKhaiTaiSanController {
	@Autowired
	private IKeKhaiTaiSanService keKhaiTaiSanService;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	@Autowired
	private ICanBoService canBoService;

	@GetMapping("/show")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> donViChucNangLists = donViChucNangService.getAll();
		ArrayList<CanBoDTO> canBoLists = canBoService.getAll();
		ModelAndView model = new ModelAndView("kekhaitaisan");
		model.addObject("donViChucNangLists", donViChucNangLists);
		model.addObject("canBoLists", canBoLists);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, Model model)
			throws SQLException {
		keKhaiTaiSanService.delete(pK);
		return "kekhaitaisan";
	}

	@PostMapping("/update")
	public String update(@RequestBody KeKhaiTaiSanDTO qdkl, Model model)
			throws SQLException {
		keKhaiTaiSanService.update(qdkl);
		return "kekhaitaisan";
	}

	@PostMapping("/add")
	public String insert(@RequestBody KeKhaiTaiSanDTO qdkl, Model model)
			throws SQLException {
		keKhaiTaiSanService.insert(qdkl);
		return "kekhaitaisan";
	}
}
