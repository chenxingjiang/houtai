package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.vo.Module;

public interface ModuleDao {
	
	public List<ModuleDto> queryAllsModuleDtos();
	
	@Select("select id,module_name as moduleName,opt_value as optValue"
			+ " from t_module where id=#{mid} and is_valid=1")
	public Module  queryModuleById(Integer mid);

}
