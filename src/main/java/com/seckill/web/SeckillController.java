package com.seckill.web;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seckill.dtoentity.Exposer;
import com.seckill.dtoentity.SeckillExecution;
import com.seckill.dtoentity.SeckillResult;
import com.seckill.entity.Page;
import com.seckill.entity.Seckill;
import com.seckill.enums.SeckillStateEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.service.ManageSeckillService;
import com.seckill.service.SeckillService;

/**
 * springController
 * 
 * @author LOForever
 *
 */
@Controller // @Service @Component ����spring������
@RequestMapping("/seckill") // url·��ӳ��:/ģ��/��Դ/{id}/ϸ�� ����/seckill/list
public class SeckillController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		// ��ȡ�б�ҳ
		List<Seckill> list = seckillService.getSeckillList();
		// list.jsp+model=ModelAndView
		model.addAttribute("list", list);
		return "front/list";// WEB-INF/jsps/list.jsp
	}

	@RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET) // restful
																				// url
	public String detail(@PathVariable("seckillId") long seckillId, Model model) {
		// @PathVariable�󶨲���
		if (seckillId == 0) {
			return "redirect:/seckill/list";
		}
		Seckill seckill = seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill", seckill);
		return "front/detail";
	}

	// ajax json
	@RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") long seckillId) {
		SeckillResult<Exposer> result;

		try {
			Exposer exposer = seckillService.exportSeckillUrl(seckillId);

			result = new SeckillResult<Exposer>(true, exposer);

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			result = new SeckillResult<Exposer>(false, e.getMessage());
		}

		return result;
	}

	@RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST, produces = {
			"application/json;charset=utf-8" })
	@ResponseBody // ��������ע��
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") long seckillId,
			@CookieValue(value = "killPhone", required = false) long userPhone, @PathVariable("md5") String md5) {
		if (userPhone == 0) {
			return new SeckillResult<SeckillExecution>(false, "δע��");
		}

		try {
			// ���ô洢����
			SeckillExecution execution = seckillService.executeSeckillByPRC(seckillId, userPhone, md5);
			return new SeckillResult<SeckillExecution>(true, execution);
		} catch (RepeatKillException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
			return new SeckillResult<SeckillExecution>(true, execution);
			// TODO: handle exception
		} catch (SeckillCloseException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.END);
			return new SeckillResult<SeckillExecution>(true, execution);
			// TODO: handle exception
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			SeckillExecution execution = new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
			return new SeckillResult<SeckillExecution>(true, execution);
		}

	}

	@RequestMapping(value = "/time/now", method = RequestMethod.GET)
	@ResponseBody // json��
	public SeckillResult<Long> time() {
		Date now = new Date();
		return new SeckillResult<Long>(true, now.getTime());
	}

}
