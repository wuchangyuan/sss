package com.sss.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.sss.service.RedisService;

public class ClaJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap jobDataMap = arg0.getJobDetail().getJobDataMap();
		RedisService redisService = (RedisService) jobDataMap.get("redisService");
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		System.out.println("开始定时备份:" + format.format(new Date()));
		redisService.barkCla();
		System.out.println("定时备份结束:" + format.format(new Date()));
	}

}
