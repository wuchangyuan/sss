package com.sss.test;

import java.util.Calendar;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sss.job.TestJob;
import com.sss.service.ClaService;
import com.sss.service.StudentService;

public class QuartzTest01 {

	public static void main(String[] args) {
		try {
			test03();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void test01() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		Trigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity("a", "g")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever()).build();

		JobDetail jobDetail = JobBuilder.newJob(TestJob.class).build();

		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");

		ClaService claService = context.getBean(ClaService.class);

		jobDetail.getJobDataMap().put("claService", claService);

		scheduler.scheduleJob(jobDetail, trigger);

		scheduler.start();

	}

	public static void test02() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		SimpleTriggerImpl trigger = new SimpleTriggerImpl();
		trigger.setName("b");
		trigger.setRepeatCount(3);
		trigger.setStartTime(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.SECOND, 5);
		trigger.setEndTime(calendar.getTime());
		trigger.setRepeatInterval(20);
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");

		ClaService claService = context.getBean(ClaService.class);

		JobDetail jobDetail = JobBuilder.newJob(TestJob.class).build();

		jobDetail.getJobDataMap().put("claService", claService);
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}

	public static void test03() throws SchedulerException {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-*.xml");

		StudentService claService = context.getBean(StudentService.class);

		JobDetail jobDetail = JobBuilder.newJob(TestJob.class).build();

		jobDetail.getJobDataMap().put("claService", claService);

		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?")).build();

		scheduler.scheduleJob(jobDetail, trigger);

		scheduler.start();
	}
}
