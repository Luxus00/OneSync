package hrm.domain.controller;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.service.ITonGiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import hrm.domain.model.dto.TonGiaoDTO;

@RestController
@ApiCommonResponse
@RequestMapping("/tonGiaoController")
public class TonGiaoController {
	@Autowired
	private ITonGiaoService tongiao;

	@GetMapping("/show")
	public String show() {
		return "tongiao";
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		tongiao.delete(pK, version);
		return "tongiao";
	}

	@PostMapping("/update")
	public String update(@RequestBody TonGiaoDTO tg, Model model) {
		tongiao.update(tg);
		return "tongiao";
	}

	@PostMapping("/add")
	public String insert(@RequestBody TonGiaoDTO tg, Model model) {
		tongiao.insert(tg);
		return "tongiao";
	}
}
