package com.crazyloong.cat.rsNew.mybatis.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.crazyloong.cat.rsNew.mybatis.entity.RsnOrderPlaced;

/**
 * 中免日上以下单记录(RsnOrderPlaced)表数据库访问层
 *
 * @author makejava
 * @since 2022-06-29 18:34:08
 */
@Mapper
public interface RsnOrderPlacedDao extends BaseMapper<RsnOrderPlaced> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<RsnOrderPlaced> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<RsnOrderPlaced> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<RsnOrderPlaced> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<RsnOrderPlaced> entities);

}

