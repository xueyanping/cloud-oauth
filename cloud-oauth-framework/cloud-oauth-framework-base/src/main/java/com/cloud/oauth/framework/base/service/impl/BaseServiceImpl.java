package com.cloud.oauth.framework.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;

import com.cloud.oauth.framework.base.mybatis.SqlMethod;
import com.cloud.oauth.framework.base.pagination.PageableCriteria;
import com.cloud.oauth.framework.base.service.BaseService;
import com.cloud.oauth.framework.criteria.Criteria;
import com.cloud.oauth.framework.criteria.parser.CriteriaParser;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 基础服务实现类
 *
 * @param <T>
 * @author zhangzhn
 */
@SuppressWarnings("rawtypes")
public class BaseServiceImpl<T> extends ServiceImpl<BaseMapper<T>,T> implements BaseService<T> {

    public static final Log logger = LogFactory.getLog(ServiceImpl.class);

    @Autowired
    protected BaseMapper<T> mapper;

    protected CriteriaParser criteriaParser = new CriteriaParser();

    public QueryWrapper<T> newEntityWrapper() {
        return new QueryWrapper<>();
    }

    /**
     * 根据Mapper类型获取Mapper
     *
     * @param m
     * @return
     */
    @SuppressWarnings("unchecked")
    public <M extends BaseMapper> M getMapper(Class<M> m) {
        return (M) mapper;
    }

    public <M extends BaseMapper> M getMapper() {
        return (M) mapper;
    }


    @Override
    public T findOneByColumn(String column, String value) {
        T t = this.selectOne(new QueryWrapper<T>().eq(column, value));
        return t;
    }

    @Override
    public List<T> selectList() {
        return this.selectList(new QueryWrapper<>());
    }


    /**
     * 获取SqlStatement
     *
     * @param sqlMethod
     * @return
     */

    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    /**
     * 插入实体
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(T entity) {
        return retBool(mapper.insert(entity));
    }

    /**
     * 批量查询
     * @param entityList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertBatch(List<T> entityList) {
        return insertBatch(entityList, 30);
    }

    /**
     * 批量插入
     *
     * @param entityList
     * @param batchSize
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertBatch(List<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = entityList.size();
            String sqlStatement = sqlStatement(com.baomidou.mybatisplus.core.enums.SqlMethod.INSERT_ONE);
            for (int i = 0; i < size; i++) {
                batchSqlSession.insert(sqlStatement, entityList.get(i));
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw new MybatisPlusException("Error: Cannot execute insertBatch Method. Cause", e);
        }
        return true;
    }

    /**
     * <p>
     * TableId 注解存在更新记录，否插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertOrUpdate(T entity) {
        if (null != entity) {
            Class<?> cls = entity.getClass();
            TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
            if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idVal)) {
                    return insert(entity);
                } else {
                    /*
                     * 更新成功直接返回，失败执行插入逻辑
                     */
                    return updateById(entity) || insert(entity);
                }
            } else {
                throw new MybatisPlusException("Error:  Can not execute. Could not find @TableId.");
            }
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertOrUpdateBatch(List<T> entityList) {
        return insertOrUpdateBatch(entityList, 30);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertOrUpdateBatch(List<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = entityList.size();
            for (int i = 0; i < size; i++) {
                    insertOrUpdate(entityList.get(i));
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw new MybatisPlusException("Error: Cannot execute insertOrUpdateBatch Method. Cause", e);
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Serializable id) {
        return SqlHelper.retBool(mapper.deleteById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteByMap(Map<String, Object> columnMap) {
        if (MapUtils.isEmpty(columnMap)) {
            throw new MybatisPlusException("deleteByMap columnMap is empty.");
        }
        return SqlHelper.retBool(mapper.deleteByMap(columnMap));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Wrapper<T> wrapper) {
        return SqlHelper.retBool(mapper.delete(wrapper));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteBatchIds(Collection<? extends Serializable> idList) {
        return SqlHelper.retBool(mapper.deleteBatchIds(idList));
    }

    @Override
    public boolean save(T entity) {
        return false;
    }

    @Override
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean removeById(Serializable id) {
        return false;
    }

    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return false;
    }

    @Override
    public boolean remove(Wrapper<T> queryWrapper) {
        return false;
    }

    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(T entity) {
        return retBool(mapper.updateById(entity));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(T entity, Wrapper<T> wrapper) {
        return retBool(mapper.update(entity, wrapper));
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBatchById(List<T> entityList) {
        return updateBatchById(entityList, 30);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBatchById(List<T> entityList, int batchSize) {
        return updateBatchById(entityList, batchSize, true);
    }

    /**
     * 根据主键ID进行批量修改
     *
     * @param entityList 实体对象列表
     * @param batchSize  批量刷新个数
     * @param selective  是否滤掉空字段
     * @return boolean
     */
    private boolean updateBatchById(List<T> entityList, int batchSize, boolean selective) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        updateBatchByIdImpl(entityList, batchSize, selective);
        return true;
    }

    private void updateBatchByIdImpl(List<T> entityList, int batchSize, boolean selective) {
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = entityList.size();
            SqlMethod sqlMethod = selective ? SqlMethod.UPDATE_BY_ID : SqlMethod.UPDATE_ALL_COLUMN_BY_ID;
            String sqlStatement = sqlStatement(sqlMethod);
            for (int i = 0; i < size; i++) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put("et", entityList.get(i));
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw new MybatisPlusException("Error: Cannot execute updateBatchById Method. Cause", e);
        }
    }

    @Override
    public T selectById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return mapper.selectBatchIds(idList);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        return mapper.selectByMap(columnMap);
    }

    @Override
    public T selectOne(QueryWrapper<T> wrapper) {
        return SqlHelper.getObject(logger, mapper.selectList(wrapper));
    }

    @Override
    public T selectOne(QueryWrapper<T> wrapper, Criteria<T> criteria, String group) {
        return (T) this.selectOne(this.criteriaParser.parse(criteria, wrapper, group));
    }

    @Override
    public T selectOne(QueryWrapper<T> wrapper, Criteria<T> criteria) {
        return this.selectOne(wrapper, criteria, null);
    }

    @Override
    public T selectOne(Criteria<T> criteria) {
        return this.selectOne(new QueryWrapper<>(), criteria, null);
    }

    @Override
    public Map<String, Object> selectMap(QueryWrapper<T> wrapper) {
        return SqlHelper.getObject(logger, mapper.selectMaps(wrapper));
    }


    public Map<String, Object> selectMap(QueryWrapper<T> wrapper, Criteria<T> criteria, String group) {
        return this.selectMap(criteriaParser.parse(criteria, wrapper, group));
    }

    @Override
    public Map<String, Object> selectMap(QueryWrapper<T> wrapper, Criteria<T> criteria) {
        return this.selectMap(wrapper, criteria, null);
    }

    @Override
    public Map<String, Object> selectMap(Criteria<T> criteria) {
        return this.selectMap(new QueryWrapper<>(), criteria, null);
    }

    @Override
    public Object selectObj(QueryWrapper<T> wrapper) {
        return SqlHelper.getObject(logger, mapper.selectObjs(wrapper));
    }

    @Override
    public Object selectObj(QueryWrapper<T> wrapper, Criteria<T> criteria, String group) {
        return this.selectObj(criteriaParser.parse(criteria, wrapper, group));
    }

    @Override
    public Object selectObj(QueryWrapper<T> wrapper, Criteria<T> criteria) {
        return this.selectObj(wrapper, criteria, null);
    }

    @Override
    public Object selectObj(Criteria<T> criteria) {
        return this.selectObj(new QueryWrapper<>(), criteria, null);
    }

    @Override
    public int selectCount(QueryWrapper<T> wrapper) {
        return SqlHelper.retCount(mapper.selectCount(wrapper));
    }

    @Override
    public int selectCount(QueryWrapper<T> wrapper, Criteria<T> criteria, String group) {
        return this.selectCount(criteriaParser.parse(criteria, wrapper, group));
    }

    @Override
    public int selectCount(QueryWrapper<T> wrapper, Criteria<T> criteria) {
        return this.selectCount(wrapper, criteria, null);
    }

    @Override
    public int selectCount(Criteria<T> criteria) {
        return this.selectCount(new QueryWrapper<>(), criteria, null);
    }

    @Override
    public List<Map<String, Object>> selectMaps(QueryWrapper<T> wrapper) {
        return mapper.selectMaps(wrapper);
    }

    @Override
    public List<Object> selectObjs(QueryWrapper<T> wrapper) {
        return mapper.selectObjs(wrapper);
    }

    @Override
    public List<Object> selectObjs(QueryWrapper<T> wrapper, Criteria<T> criteria, String group) {
        return this.selectObjs(criteriaParser.parse(criteria, wrapper, group));
    }

    @Override
    public List<Object> selectObjs(QueryWrapper<T> wrapper, Criteria<T> criteria) {
        return this.selectObjs(wrapper, criteria, null);
    }

    @Override
    public List<Object> selectObjs(Criteria<T> criteria) {
        return this.selectObjs(new QueryWrapper<>(), criteria, null);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage page, QueryWrapper<T> wrapper) {
        return mapper.selectMapsPage(page, wrapper);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage page, QueryWrapper<T> wrapper, Criteria<T> criteria, String group) {
        return this.selectMapsPage(page, criteriaParser.parse(criteria, wrapper, group));
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage page, QueryWrapper<T> wrapper, Criteria<T> criteria) {
        return this.selectMapsPage(page, wrapper, criteria, null);
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage page, Criteria<T> criteria) {
        return this.selectMapsPage(page, new QueryWrapper<T>(), criteria, null);
    }


    @Override
    public List<T> selectList(QueryWrapper<T> wrapper) {
        return mapper.selectList(wrapper);
    }

    @Override
    public List<T> selectList(String column, Object value) {
        return this.selectList(new QueryWrapper<T>().eq(column, value));
    }

    @Override
    public List<T> selectList(QueryWrapper<T> wrapper, Criteria<T> criteria, String group) {
        return this.selectList(criteriaParser.parse(criteria, wrapper, group));
    }

    @Override
    public List<T> selectList(QueryWrapper<T> wrapper, Criteria<T> criteria) {
        return this.selectList(wrapper, criteria, null);
    }

    @Override
    public List<T> selectList(Criteria<T> criteria) {
        return this.selectList(new QueryWrapper<>(), criteria, null);
    }

    @Override
    public IPage<T> selectPage(IPage<T> page) {
        return this.selectPage(page, new QueryWrapper<>());
    }

    @Override
    public IPage<T> selectPage(IPage<T> page, QueryWrapper<T> wrapper) {
        return mapper.selectPage(page, wrapper);
    }
    @Override
    public IPage<T> selectPage(IPage<T> page, QueryWrapper<T> wrapper, Criteria<T> criteria, String group) {
        return this.selectPage(page, criteriaParser.parse(criteria, wrapper, group));
    }

    @Override
    public IPage<T> selectPage(IPage<T> page, QueryWrapper<T> wrapper, Criteria<T> criteria) {
        return this.selectPage(page, wrapper, criteria, null);
    }

    @Override
    public IPage<T> selectPage(IPage<T> page, Criteria<T> criteria) {
        return this.selectPage(page, new QueryWrapper<>(), criteria, null);
    }

    @Override
    public IPage<T> selectPage(PageableCriteria pageableCriteria) {
        return selectPage(pageableCriteria.getPage(),pageableCriteria);
    }


    @Override
    public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        if (CollectionUtils.isEmpty(entityList)) {
            throw new IllegalArgumentException("Error: entityList must not be empty");
        }
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            int size = entityList.size();
            com.baomidou.mybatisplus.core.enums.SqlMethod sqlMethod = com.baomidou.mybatisplus.core.enums.SqlMethod.UPDATE_BY_ID;
            String sqlStatement = sqlStatement(sqlMethod);
            int i = 0;
            for (T entity : entityList) {
                MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                param.put("et", entity);
                batchSqlSession.update(sqlStatement, param);
                if (i >= 1 && i % batchSize == 0) {
                    batchSqlSession.flushStatements();
                }
            }
            batchSqlSession.flushStatements();
        } catch (Throwable e) {
            throw new MybatisPlusException("Error: Cannot execute updateBatchById Method. Cause", e);
        }
        return true;
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        return this.insertOrUpdate(entity);
    }

    @Override
    public T getById(Serializable id) {
        return mapper.selectById(id);
    }

    @Override
    public List<T> listByIds(Collection<? extends Serializable> idList) {
        return mapper.selectBatchIds(idList);
    }

    @Override
    public List<T> listByMap(Map<String, Object> columnMap) {
        return mapper.selectByMap(columnMap);
    }

    @Override
    public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {

        try {
            return SqlHelper.getObject(logger, mapper.selectList(queryWrapper));
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getCause());
            if (throwEx) {
                throw e;
            } else {
                return null;
            }
        }
    }

    @Override
    public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return SqlHelper.getObject(logger, mapper.selectMaps(queryWrapper));
    }

    @Override
    public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> func) {
        return SqlHelper.getObject(logger, mapper.selectObjs(queryWrapper).stream().map(func).collect(Collectors.toList()));
    }

    @Override
    public int count(Wrapper<T> queryWrapper) {
        return mapper.selectCount(queryWrapper);
    }

    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return this.selectList((QueryWrapper<T>) queryWrapper);
    }

//    @Override
//    public IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper) {
//        return mapper.selectPage(page, queryWrapper);
//    }

    @Override
    public List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper) {
        return this.selectMaps((QueryWrapper<T>) queryWrapper);
    }

    @Override
    public <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return this.selectObjs((QueryWrapper<T>) queryWrapper).stream().map(mapper).collect(Collectors.toList());
    }

//    @Override
//    public IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper) {
//        return this.selectMapsPage(page, (QueryWrapper<T>) queryWrapper);
//    }

    @Override
    public BaseMapper<T> getBaseMapper() {
        return mapper;
    }
}
