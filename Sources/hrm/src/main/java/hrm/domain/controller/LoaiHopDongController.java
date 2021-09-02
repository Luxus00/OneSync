package hrm.domain.controller;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.LoaiHopDongDTO;
import hrm.domain.service.ILoaiHopDongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiCommonResponse
@RequestMapping("/loaiHopDongController")
public class LoaiHopDongController {
	@Autowired
	private ILoaiHopDongService loaihopdong;

	@GetMapping("/show")
	public String show() {
		return "loaihopdong";
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		loaihopdong.delete(pK, version);
		return "loaihopdong";
	}

	@PostMapping("/update")
	public String update(@RequestBody LoaiHopDongDTO lhd, Model model) {
		loaihopdong.update(lhd);
		return "loaihopdong";
	}

	@PostMapping("/add")
	public String insert(@RequestBody LoaiHopDongDTO lhd, Model model) {
		loaihopdong.insert(lhd);
		return "loaihopdong";
	}
}