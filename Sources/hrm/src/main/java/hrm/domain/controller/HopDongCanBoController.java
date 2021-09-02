package hrm.domain.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.HopDongCanBoDTO;
import hrm.domain.model.dto.LoaiHopDongDTO;
import hrm.domain.service.IDonViChucNangService;
import hrm.domain.service.IHopDongCanBoService;
import hrm.domain.service.ILoaiHopDongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@ApiCommonResponse
@RequestMapping("/hopDongCanBoController")
public class HopDongCanBoController {
	@Autowired
	private IHopDongCanBoService hopDongCanBoService;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	@Autowired
	private ILoaiHopDongService loaiHopDongService;

	@GetMapping("/show")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> donViChucNangLists = donViChucNangService.getAll();
		ArrayList<LoaiHopDongDTO> loaiHopDongLists = loaiHopDongService.getAll();
		ModelAndView model = new ModelAndView("hopdongcanbo");
		model.addObject("donViChucNangLists", donViChucNangLists);
		model.addObject("loaiHopDongLists", loaiHopDongLists);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		hopDongCanBoService.delete(pK, version);
		return "hopdongcanbo";
	}

	@PostMapping("/update")
	public String update(@RequestBody HopDongCanBoDTO hd, Model model) throws SQLException {
		hopDongCanBoService.update(hd);
		return "hopdongcanbo";
	}

	@PostMapping("/add")
	public String insert(@RequestBody HopDongCanBoDTO hd, Model model) throws SQLException {
		hopDongCanBoService.insert(hd);
		return "hopdongcanbo";
	}
}