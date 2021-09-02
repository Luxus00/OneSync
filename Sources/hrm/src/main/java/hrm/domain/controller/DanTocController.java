package hrm.domain.controller;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.DanTocDTO;
import hrm.domain.service.IDanTocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiCommonResponse
@RequestMapping("/danTocController")
public class DanTocController {
	@Autowired
	private IDanTocService dantoc;

	@GetMapping("/show")
	public String show() {
		return "dantoc";
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		dantoc.delete(pK, version);
		return "dantoc";
	}

	@PostMapping("/update")
	public String update(@RequestBody DanTocDTO dt, Model model) {
		dantoc.update(dt);
		return "dantoc";
	}

	@PostMapping("/add")
	public String insert(@RequestBody DanTocDTO dt, Model model) {
		dantoc.insert(dt);
		return "dantoc";
	}
}
