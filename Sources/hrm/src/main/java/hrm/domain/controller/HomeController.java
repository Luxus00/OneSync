package hrm.domain.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import hrm.base.common.annotations.api.ApiCommonResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiCommonResponse
@RequestMapping("/")
public class HomeController {
	@RequestMapping(method = GET)
	public String home() {
		return "home";
	}
}
