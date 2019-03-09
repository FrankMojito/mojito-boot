package com.mojito.mojitoboot.common.mapper;

import com.mojito.mojitoboot.common.daomodel.GoodsDO;

import java.util.List;

public interface GoodsDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Fri Jan 25 00:11:56 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Fri Jan 25 00:11:56 CST 2019
     */
    int insert(GoodsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Fri Jan 25 00:11:56 CST 2019
     */
    int insertSelective(GoodsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Fri Jan 25 00:11:56 CST 2019
     */
    GoodsDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Fri Jan 25 00:11:56 CST 2019
     */
    int updateByPrimaryKeySelective(GoodsDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods
     *
     * @mbg.generated Fri Jan 25 00:11:56 CST 2019
     */
    int updateByPrimaryKey(GoodsDO record);

    List<GoodsDO> selectGoodsList();
}