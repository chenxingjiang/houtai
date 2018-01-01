package com.shsxt.crm.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shsxt.crm.dao.ModuleDao;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.dto.ModuleDto;

@Service
public class ModuleService {
	@Resource
	private ModuleDao moduleDao;
	@Resource
	private PermissionDao permissionDao;
	
	public List<ModuleDto> queryAllsModuleDtos(){
		List<ModuleDto> moduleDtos=moduleDao.queryAllsModuleDtos();
		return moduleDtos;
	}
	
	
	public List<ModuleDto> queryAllsModuleDtos(Integer rid){
		List<ModuleDto> moduleDtos=moduleDao.queryAllsModuleDtos();
		List<Integer> moduleIds= permissionDao.queryPermissionModuleIdsByRid(rid);
		if(null!=moduleDtos&&moduleDtos.size()>0){
			for(ModuleDto moduleDto:moduleDtos){
				if(moduleIds.contains(moduleDto.getId())){
					moduleDto.setChecked(true);// 节点选中！！！
				}
			}
		}
		return moduleDtos;
	} 
}
