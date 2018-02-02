package com.sss.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.domain.Page;

import com.sss.pojo.Student;
import com.sss.service.StudentService;

public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap jobDataMap = arg0.getJobDetail().getJobDataMap();
		StudentService service = (StudentService) jobDataMap.get("claService");
		Page<Student> page = service.list(0, 4, null);
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String string = format.format(new Date());
		System.out.println(string);
		System.out.println(page.getContent());
	}

}
