package com.sss.controller;

import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.sss.job.ClaJob;
import com.sss.pojo.Cla;
import com.sss.pojo.Student;
import com.sss.service.ClaService;
import com.sss.service.RedisService;
import com.sss.service.StudentService;

@Controller
@RequestMapping("/stu")
public class StudentController {

	@Autowired
	private StudentService service;
	@Autowired
	private ClaService service2;
	@Autowired
	private RedisService redisService;

	@RequestMapping("begin.html")
	public String begin() throws SchedulerException {
		System.out.println("定时器开启");
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("cla", "groupCla")
				.withSchedule(CronScheduleBuilder.cronSchedule("* 0/10 * * * ?")).build();
		JobDetail jobDetail = JobBuilder.newJob(ClaJob.class).build();
		jobDetail.getJobDataMap().put("redisService", redisService);
		scheduler.scheduleJob(jobDetail, cronTrigger);
		scheduler.start();
		return "redirect:/stu/list.html";
	}

	@RequestMapping("edit.html")
	public ModelAndView edit(Student student) {
		if (student == null) {
			return null;
		}
		Cla cla = service2.getCla(student.getCla().getId());
		student.setCla(cla);
		service.edit(student);
		return new ModelAndView("success", "editMsg", "编辑成功");
	}

	@RequestMapping("list.html")
	public String list(Model model, @RequestParam(defaultValue = "0") int pageNum, Student student) {
		Page<Student> list = service.list(pageNum, 2, student);
		List<Cla> claList = service2.getClaList();
		model.addAttribute("claList", claList);
		model.addAttribute("stuPage", list);
		return "index";
	}

	@RequestMapping("get.html")
	public String get(Integer id, Model model) {
		List<Cla> claList = service2.getClaList();
		if (id != null) {
			Student student = service.get(id);
			model.addAttribute("student", student);
		}
		model.addAttribute("clas", claList);
		return "edit";
	}

	@RequestMapping("delete.html")
	public ModelAndView delete(Integer id) {
		service.delete(id);
		return new ModelAndView("success", "deleteMsg", "删除成功");
	}

	@RequestMapping("index.html")
	public String index() {
		return "index";
	}

	@RequestMapping("editJsp.html")
	public String editJsp() {
		return "edit";
	}
}
