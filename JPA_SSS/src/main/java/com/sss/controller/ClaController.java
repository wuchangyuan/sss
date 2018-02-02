package com.sss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sss.pojo.Cla;
import com.sss.service.ClaService;

@Controller
@RequestMapping("/cla")
public class ClaController {

	@Autowired
	private ClaService service;

	@RequestMapping("list.html")
	public ModelAndView list() {
		List<Cla> clas = service.getClaList();
		return new ModelAndView("edit", "clas", clas);
	}

	@RequestMapping("check.html")
	@ResponseBody
	public Map<String, Object> check(String name) {
		Map<String, Object> map = new HashMap<>();
		try {
			service.getClaByName(name);
			map.put("status", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			map.put("status", false);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping("add.html")
	public ModelAndView add(Cla cla) {
		service.add(cla);
		return new ModelAndView("success", "addClaMsg", "添加班级成功");
	}

	@RequestMapping("addJsp.html")
	public String addJsp() {
		return "add";
	}
}
