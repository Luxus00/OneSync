package hrm.domain.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import hrm.base.common.annotations.api.ApiCommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import hrm.domain.model.dto.CanBoDTO;
import hrm.domain.model.dto.ChucVuDTO;
import hrm.domain.model.dto.DanTocDTO;
import hrm.domain.model.dto.DonViChucNangDTO;
import hrm.domain.model.dto.NgachCongChucDTO;
import hrm.domain.model.dto.QueQuanDTO;
import hrm.domain.model.dto.TonGiaoDTO;
import hrm.domain.service.ICanBoService;
import hrm.domain.service.IChucVuService;
import hrm.domain.service.IDanTocService;
import hrm.domain.service.IDonViChucNangService;
import hrm.domain.service.INgachCongChucService;
import hrm.domain.service.IQueQuanService;
import hrm.domain.service.ITonGiaoService;

@RestController
@ApiCommonResponse
@RequestMapping("/canBoController")
public class CanBoController {
	@Autowired
	private ICanBoService canBoService;
	@Autowired
	private IDonViChucNangService donViChucNangService;
	@Autowired
	private ITonGiaoService tonGiaoService;
	@Autowired
	private IDanTocService danTocService;
	@Autowired
	private IChucVuService chucVuService;
	@Autowired
	private IQueQuanService queQuanService;
	@Autowired
	private INgachCongChucService ngachCongChucService;

	@GetMapping("/show")
//	@Operation(summary = "Get all", description = "role = ADMIN, USer", security = @SecurityRequirement(name = "bearer-jwt"))
//	@PreAuthorize("@EndPointAuthorizer.authorizer({'ADMIN', 'USER'})")
	public ModelAndView getdata() {
		ArrayList<DonViChucNangDTO> list = donViChucNangService.getAll();
		ArrayList<TonGiaoDTO> tonGiaoLists = tonGiaoService.getAll();
		ArrayList<DanTocDTO> danTocLists = danTocService.getAll();
		ArrayList<ChucVuDTO> chucVuLists = chucVuService.getAll();
		ArrayList<NgachCongChucDTO> ngachCongChucLists = ngachCongChucService
				.getAll();
		ArrayList<QueQuanDTO> queQuanLists = queQuanService.getAll();
		ModelAndView model = new ModelAndView("canbo");
		model.addObject("donViChucNangLists", list);
		model.addObject("tonGiaoLists", tonGiaoLists);
		model.addObject("danTocLists", danTocLists);
		model.addObject("chucVuLists", chucVuLists);
		model.addObject("ngachCongChucLists", ngachCongChucLists);
		model.addObject("queQuanLists", queQuanLists);
		return model;
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "pK") Integer pK, @RequestParam(value = "version") Integer version) {
		canBoService.delete(pK, version);
		return "canbo";
	}

	@PostMapping("/update")
	public String update(@RequestBody CanBoDTO dscb, Model model) throws SQLException {
		canBoService.update(dscb);
		return "canbo";
	}

	@PostMapping("/add")
	public String insert(@RequestBody CanBoDTO dscb, Model model) throws SQLException {
		canBoService.insert(dscb);
		return "canbo";
	}
}