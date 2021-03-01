package com.cloud.oauth.framework.base.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共Mapper，可执行任何SQL语句
 *
 * @author zhangzhn
 * @since 2019-06-10
 **/

public class CommonMapper {

    private static final String SQL = "sql";

    public boolean insert(String sql, Object... args) {
        return SqlRunner.DEFAULT.insert(sql, args);
    }
    public boolean delete(String sql, Object... args) {
        return SqlRunner.DEFAULT.delete(sql, args);
    }

    /**
     * 获取sqlMap参数
     *
     * @param sql
     * @param args
     * @return
     */
    private Map<String, String> sqlMap(String sql, Object... args) {
        Map<String, String> sqlMap = new HashMap<>();
        sqlMap.put(SQL, StringUtils.sqlArgsFill(sql, args));
        return sqlMap;
    }

    public boolean update(String sql, Object... args) {
        return SqlRunner.DEFAULT.update(sql, args);
    }

    public List<Map<String, Object>> selectList(String sql, Object... args) {
        return SqlRunner.DEFAULT.selectList(sql, args);
    }

    public List<Object> selectObjs(String sql, Object... args) {
        return SqlRunner.DEFAULT.selectObjs(sql, args);
    }

    public Object selectObj(String sql, Object... args) {
        return SqlRunner.DEFAULT.selectObj(sql, args);
    }

    public int selectCount(String sql, Object... args) {
        return SqlRunner.DEFAULT.selectCount(sql, args);
    }

    public Map<String, Object> selectOne(String sql, Object... args) {
        return SqlRunner.DEFAULT.selectOne(sql, args);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public IPage<Map<String, Object>> selectPage(IPage page, String sql, Object... args) {
        return SqlRunner.DEFAULT.selectPage(page, sql, args);
    }
}
