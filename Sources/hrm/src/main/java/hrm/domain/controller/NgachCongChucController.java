package hrm.domain.controller;

import java.sql.SQLException;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.NgachCongChucDTO;
import hrm.domain.service.INgachCongChucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiCommonResponse
@RequestMapping("/ngachCongChucController")
public class NgachCongChucController {
	@Autowired
	private INgachCongChucService ngachcongchuc;

	@GetMapping("/show")
	public String show() {
		return "ngachcongchuc";
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		ngachcongchuc.delete(pK, version);
		return "ngachcongchuc";
	}

	@PostMapping("/update")
	public String update(@RequestBody NgachCongChucDTO ncc, Model model)
			throws SQLException {
		ngachcongchuc.update(ncc);
		return "ngachcongchuc";
	}

	@PostMapping("/add")
	public String insert(@RequestBody NgachCongChucDTO ncc, Model model)
			throws SQLException {
		ngachcongchuc.insert(ncc);
		return "ngachcongchuc";
	}
}
