package com.example.advertiser.services;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.advertiser.modal.Advertiser;

@Mapper
public interface AdvertiseMapper {

	@Select("select * from advertiser")
	List<Advertiser> findAll();
	
	@Select("select * from advertiser where id=#{id}")
	Advertiser findById(int id);
	
	@Insert("insert into advertiser(name, contactName, creditLimit) values (#{name}, #{contactName}, #{creditLimit})")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int insertAdvertiser(Advertiser adv);
	
	@Update("update advertiser set name=#{name}, contactName=#{contactName}, creditLimit=#{creditLimit} where id=#{id}")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int updateAdvertiser(Advertiser adv);
	
	@Delete("delete from advertiser where id=#{id}")
	@Options(useGeneratedKeys=true, keyProperty="id")
	int deleteAdvertiser(int id);

}
