package com.sss.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sss.pojo.Cla;
import com.sss.pojo.Rank;
import com.sss.service.ClaService;
import com.sss.service.RankService;
import com.sss.service.RedisService;
import com.sss.utils.RedisUtil;

@Service
@Transactional
public class RedisServiceImpl implements RedisService {
	@Autowired
	private ClaService service;
	@Autowired
	private RankService RankService;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public void barkCla() {
		// TODO Auto-generated method stub
		List<Cla> list;
		Integer id = 0;
		if (redisUtil.exists("cla")) {
			String last = redisUtil.getLast("cla");
			if (last.length() > 0) {
				Cla cla = JSON.parseObject(last, Cla.class);
				id = cla.getId();
			}
		}

		list = service.getClaById(id);
		if (list != null && list.size() > 0) {
			for (Cla cla : list) {
				redisUtil.ladd("cla", JSON.toJSONString(cla));
			}
		}

	}

	@Override
	public void barkRank() {
		// TODO Auto-generated method stub
		List<Rank> list;
		Integer id = 0;
		if (redisUtil.exists("rank")) {
			String last = redisUtil.getLast("rank");
			if (last.length() > 0) {
				Rank rank = JSON.parseObject(last, Rank.class);
				id = rank.getId();
			}
		}
		list = RankService.findAllById(id);
		if (list != null && list.size() > 0) {
			for (Rank rank : list) {
				redisUtil.ladd("rank", JSON.toJSONString(rank));
			}
		}
	}

}
