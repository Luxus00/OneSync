package hrm.domain.controller;

import java.sql.SQLException;

import hrm.base.common.annotations.api.ApiCommonResponse;
import hrm.domain.model.dto.LoaiQuyetDinhDTO;
import hrm.domain.service.ILoaiQuyetDinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@ApiCommonResponse
@RequestMapping("/loaiQuyetDinhController")
public class LoaiQuyetDinhController {
	@Autowired
	private ILoaiQuyetDinhService loaiquyetdinh;

	@GetMapping("/show")
	public String show() {
		return "loaiquyetdinh";
	}

	@RequestMapping(value = "/delete/{pK}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Integer pK, @RequestParam(value = "version") Integer version) {
		loaiquyetdinh.delete(pK, version);
		return "loaiquyetdinh";
	}

	@PostMapping("/update")
	public String update(@RequestBody LoaiQuyetDinhDTO lqd, Model model) throws SQLException {
		loaiquyetdinh.update(lqd);
		return "loaiquyetdinh";
	}

	@PostMapping("/add")
	public String insert(@RequestBody LoaiQuyetDinhDTO lqd, Model model) throws SQLException {
		loaiquyetdinh.insert(lqd);
		return "loaiquyetdinh";
	}
}