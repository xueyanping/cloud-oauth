package com.cloud.oauth.framework.base.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cloud.oauth.framework.base.pagination.PageableCriteria;
import com.cloud.oauth.framework.criteria.Criteria;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 基础服务类
 *
 * @param <T>
 * @author zhangzhn
 */
public interface BaseService<T> extends IService<T> {
    /**
     * 插入某行数据
     * @param entity
     * @return
     */
    boolean insert(T entity);

    /**
     *批量插入数据
     * @param entityList
     * @return
     */
    boolean insertBatch(List<T> entityList);

    /**
     *批量指定数据条目数据
     * @param entityList
     * @param batchSize
     * @return
     */
    boolean insertBatch(List<T> entityList, int batchSize);

    /**
     *插入或者更新某记录，当主键为空时，则创建否者更新数据
     * @param entity
     * @return
     */
    boolean insertOrUpdate(T entity);

    /**
     *批量插入或者更新记录，当主键为空时，则创建否者更新数据
     * @param entityList
     * @return
     */
    boolean insertOrUpdateBatch(List<T> entityList);

    /**
     *批量插入或者更新指定条目记录，当主键为空时，则创建否者更新数据
     * @param entityList
     * @param batchSize
     * @return
     */
    boolean insertOrUpdateBatch(List<T> entityList, int batchSize);

    /**
     *根据主键删除某条记录
     * @param id
     * @return
     */
    boolean deleteById(Serializable id);

    /**
     *根据Map组合条件删除某记录
     * @param columnMap
     * @return
     */
    boolean deleteByMap(Map<String, Object> columnMap);

    /**
     *根据wrapper条件删除牟际禄
     * @param wrapper
     * @return
     */
    boolean delete(Wrapper<T> wrapper);

    /**
     *根据ID批量删除记录
     * @param idList
     * @return
     */
    boolean deleteBatchIds(Collection<? extends Serializable> idList);

    /**
     *依据ID批量更新数据
     * @param entityList
     * @return
     */
    boolean updateBatchById(List<T> entityList);

    /**
     *依据ID批量更新指定大小的数据
     * @param entityList
     * @param batchSize
     * @return
     */
    boolean updateBatchById(List<T> entityList, int batchSize);

    /**
     * 根据列获取唯一对象
     *
     * @param column
     * @param value
     * @return
     */
    T findOneByColumn(String column, String value);

    /**
     * 根据列获取列表
     *
     * @param column
     * @param value
     * @return
     */
    List<T> selectList(String column, Object value);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<T> selectList();

    /**
     *
     * @param wrapper
     * @param criteria
     * @param group
     * @return
     */
    T selectOne(QueryWrapper<T> wrapper, Criteria<T> criteria, String group);

    /**
     *
     * @param wrapper
     * @param criteria
     * @return
     */
    T selectOne(QueryWrapper<T> wrapper, Criteria<T> criteria);

    /**
     *
     * @param criteria
     * @return
     */
    T selectOne(Criteria<T> criteria);

    /**
     *
     * @param id
     * @return
     */
    T selectById(Serializable id);

    /**
     *
     * @param wrapper
     * @return
     */
    T selectOne(QueryWrapper<T> wrapper);

    /**
     *
     * @param wrapper
     * @param criteria
     * @return
     */
    Map<String, Object> selectMap(QueryWrapper<T> wrapper, Criteria<T> criteria);

    /**
     *
     * @param criteria
     * @return
     */
    Map<String, Object> selectMap(Criteria<T> criteria);

    /**
     *
     * @param idList
     * @return
     */
    List<T> selectBatchIds(Collection<? extends Serializable> idList);

    /**
     *
     * @param columnMap
     * @return
     */
    List<T> selectByMap(Map<String, Object> columnMap);

    /**
     *
     * @param wrapper
     * @return
     */
    Map<String, Object> selectMap(QueryWrapper<T> wrapper);

    /**
     *
     * @param wrapper
     * @return
     */
    List<Map<String, Object>> selectMaps(QueryWrapper<T> wrapper);

    /**
     *
     * @param wrapper
     * @param criteria
     * @param group
     * @return
     */
    Object selectObj(QueryWrapper<T> wrapper, Criteria<T> criteria, String group);

    /**
     *
     * @param wrapper
     * @param criteria
     * @return
     */
    Object selectObj(QueryWrapper<T> wrapper, Criteria<T> criteria);

    /**
     *
     * @param criteria
     * @return
     */
    Object selectObj(Criteria<T> criteria);

    /**
     *
     * @param wrapper
     * @return
     */
    Object selectObj(QueryWrapper<T> wrapper);

    /**
     *
     * @param wrapper
     * @param criteria
     * @param group
     * @return
     */
    int selectCount(QueryWrapper<T> wrapper, Criteria<T> criteria, String group);

    /**
     *
     * @param wrapper
     * @param criteria
     * @return
     */
    int selectCount(QueryWrapper<T> wrapper, Criteria<T> criteria);

    /**
     *
     * @param criteria
     * @return
     */
    int selectCount(Criteria<T> criteria);

    /**
     *
     * @param wrapper
     * @return
     */
    int selectCount(QueryWrapper<T> wrapper);

    /**
     *
     * @param wrapper
     * @return
     */
    List<Object> selectObjs(QueryWrapper<T> wrapper);

    /**
     *
     * @param wrapper
     * @param criteria
     * @param group
     * @return
     */
    List<Object> selectObjs(QueryWrapper<T> wrapper, Criteria<T> criteria, String group);

    /**
     *
     * @param wrapper
     * @param criteria
     * @return
     */
    List<Object> selectObjs(QueryWrapper<T> wrapper, Criteria<T> criteria);

    /**
     *
     * @param criteria
     * @return
     */
    List<Object> selectObjs(Criteria<T> criteria);

    /**
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Map<String, Object>> selectMapsPage(IPage page, QueryWrapper<T> wrapper);

    /**
     *
     * @param page
     * @param wrapper
     * @param criteria
     * @param group
     * @return
     */
    IPage<Map<String, Object>> selectMapsPage(IPage page, QueryWrapper<T> wrapper, Criteria<T> criteria, String group);

    /**
     *
     * @param page
     * @param wrapper
     * @param criteria
     * @return
     */
    IPage<Map<String, Object>> selectMapsPage(IPage page, QueryWrapper<T> wrapper, Criteria<T> criteria);

    /**
     *
     * @param page
     * @param criteria
     * @return
     */
    IPage<Map<String, Object>> selectMapsPage(IPage page, Criteria<T> criteria);

    /**
     *
     * @param wrapper
     * @return
     */
    List<T> selectList(QueryWrapper<T> wrapper);

    /**
     *
     * @param wrapper
     * @param criteria
     * @param group
     * @return
     */
    List<T> selectList(QueryWrapper<T> wrapper, Criteria<T> criteria, String group);

    /**
     *
     * @param wrapper
     * @param criteria
     * @return
     */
    List<T> selectList(QueryWrapper<T> wrapper, Criteria<T> criteria);

    /**
     *
     * @param criteria
     * @return
     */
    List<T> selectList(Criteria<T> criteria);

    /**
     *
     * @param page
     * @return
     */
    IPage<T> selectPage(IPage<T> page);

    /**
     *
     * @param page
     * @param wrapper
     * @return
     */
    IPage<T> selectPage(IPage<T> page, QueryWrapper<T> wrapper);

    /**
     *
     * @param page
     * @param wrapper
     * @param criteria
     * @param group
     * @return
     */
    IPage<T> selectPage(IPage<T> page, QueryWrapper<T> wrapper, Criteria<T> criteria, String group);

    /**
     *
     * @param page
     * @param wrapper
     * @param criteria
     * @return
     */
    IPage<T> selectPage(IPage<T> page, QueryWrapper<T> wrapper, Criteria<T> criteria);

    /**
     *根据分页以及查询条件查询分页数据
     * @param page
     * @param criteria
     * @return
     */
    IPage<T> selectPage(IPage<T> page, Criteria<T> criteria);

    /**
     *根据分页条件查询分页数据
     * @param pageableCriteria
     * @return
     */
    IPage<T> selectPage(PageableCriteria pageableCriteria);
}
