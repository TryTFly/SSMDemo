package com.seckill.web;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seckill.entity.Page;
import com.seckill.entity.Seckill;
import com.seckill.service.ManageSeckillService;

@Controller
@RequestMapping("/manage")
public class ManageSeckillController {

	@Autowired
	private ManageSeckillService msService;

	@RequestMapping(value="/{seckillId}/{startTime}/{endTime}/updateTime",produces = {
	"application/json;charset=utf-8" })
	@ResponseBody
	public int updateTimeById(@PathVariable("seckillId") String seckillId,
			@PathVariable("startTime") String startTime,
			@PathVariable("endTime") String endTime) {

		return msService.updateTime(seckillId, startTime, endTime);
	}

	@RequestMapping("/mlist")
	public String manageList(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "currentPage", required = false) String currentPage, Model value) {
		// 获取列表页
		// 创建分页对象
		Page page = new Page();
		Pattern pattern = Pattern.compile("[0-9]{1,9}");
		if (currentPage == null || !pattern.matcher(currentPage).matches()) {
			page.setCurrentPage(1);
		} else {
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		// page传入来获取分页信息
		List<Seckill> mlist = msService.getSeckillListByPage(name, page);
		// 设置modal数据,可以传给页面
		value.addAttribute("mlist", mlist);
		value.addAttribute("name", name);
		value.addAttribute("page", page);
		return "back/manage_list";

	}

	@RequestMapping("/insertJsp")
	public String insertJsp() {
		return "back/insertBatch";
	}

	@RequestMapping("/insertBatch")
	public String doInsert(@RequestParam(value = "name[]", required = false) String[] name,
			@RequestParam(value = "number[]", required = false) String[] number,
			@RequestParam(value = "startTime[]", required = false) String[] startTime,
			@RequestParam(value = "endTime[]", required = false) String[] endTime) {

		msService.insertBatch(name, number, startTime, endTime);

		return "forward:/manage/mlist";
	}

	@RequestMapping("/deleteBatch")
	public String deleteBatch(@RequestParam(value = "currentPage", required = false) String currentPage, Model model,
			@RequestParam(value = "id[]", required = false) String[] ids) {
		model.addAttribute("currentPage", currentPage);
		msService.deleteBatch(ids);
		return "forward:/manage/mlist";

	}

	@RequestMapping("/deleteOne")
	public String deleteOne(@RequestParam(value = "currentPage", required = false) String currentPage, Model model,
			@RequestParam(value = "id", required = false) String seckillId) {
		msService.deleteOne(seckillId);
		return "redirect:/manage/mlist";

	}

}
