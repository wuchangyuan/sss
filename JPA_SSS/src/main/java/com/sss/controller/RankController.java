package com.sss.controller;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sss.job.RankJob;
import com.sss.pojo.Rank;
import com.sss.service.RankService;
import com.sss.service.RedisService;
import com.sss.utils.JsonResult;

@Controller
@RequestMapping("/rank")
public class RankController {
	@Autowired
	private RankService service;
	@Autowired
	private RedisService redisService;

	@RequestMapping("begin.html")
	public String begin() throws SchedulerException {
		System.out.println("定时器开启");
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cla", "groupCla")
				.withSchedule(CronScheduleBuilder.cronSchedule("30 * * * * ?")).build();
		JobDetail jobDetail = JobBuilder.newJob(RankJob.class).build();
		jobDetail.getJobDataMap().put("redisService", redisService);
		scheduler.scheduleJob(jobDetail, cronTrigger);
		scheduler.start();
		return "rank";
	}

	@RequestMapping("add.html")
	public String add(Rank rank, Model model) {
		try {
			service.saveRank(rank);
			model.addAttribute("addRankMsg", "添加排名成功");
			model.addAttribute("rank", null);
			return "rank";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			model.addAttribute("addRankMsg", "项目名已存在");
			model.addAttribute("rank", rank);
			return "rank";
		}
	}

	@RequestMapping("list.html")
	@ResponseBody
	public JsonResult rankList(@RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "10") int pageSize) {
		Page<Rank> page = service.getRankPageList(pageNum, pageSize);
		return JsonResult.setOK(page, "查询成功");
	}

	@RequestMapping("check.html")
	@ResponseBody
	public JsonResult checkGroupNo(Integer groupNo, Integer id) {
		if (id == null) {
			try {
				service.findByGroupNo(groupNo);
				return JsonResult.setOK(null, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return JsonResult.setERROR(null, e.getMessage());
			}
		} else {
			Rank rank = service.getRankById(id);
			if (!rank.getGroupNo().equals(groupNo)) {
				try {
					service.findByGroupNo(groupNo);
					return JsonResult.setOK(null, "");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return JsonResult.setERROR(null, e.getMessage());
				}
			} else {
				return JsonResult.setOK(null, "");
			}
		}

	}

	@RequestMapping("delete.html")
	public ModelAndView deleteRank(Integer id) {
		service.deleteRank(id);
		return new ModelAndView("success", "delRankMsg", "删除排名成功");
	}

	@RequestMapping("get.html")
	@ResponseBody
	public JsonResult getRank(Integer id) {
		Rank rank = service.getRankById(id);
		return JsonResult.setOK(rank, "获取排名成功");
	}

	@RequestMapping("rankJsp.html")
	public String rankJsp() {
		return "rank";
	}
}
