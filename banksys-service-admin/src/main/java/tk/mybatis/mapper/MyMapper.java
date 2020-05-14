package tk.mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author Kenyon
 * @title: MyMapper
 * @projectName bank
 * @description: TODO
 * @date 9/8/20197:46 PM
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
