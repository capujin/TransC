package com.mvc.test.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvc.test.entity.User.User;
import com.mvc.test.mapper.UMapper;
import com.mvc.test.service.Uservice;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UServiceImpl extends ServiceImpl<UMapper,User> implements Uservice {

}
