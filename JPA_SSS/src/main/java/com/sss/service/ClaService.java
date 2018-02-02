package com.sss.service;

import java.util.List;

import com.sss.pojo.Cla;

public interface ClaService {

	List<Cla> getClaList();

	Cla getCla(Integer id);

	List<Cla> getClaByName(String name) throws Exception;

	void add(Cla cla);

	List<Cla> getClaById(Integer id);
}
