package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Kenyon
 * @title: MyMapper
 * @projectName bank
 * @description: TODO
 * @date 9/6/20197:07 AM
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
