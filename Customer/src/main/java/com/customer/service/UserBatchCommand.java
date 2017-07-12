package com.customer.service;

import static com.netflix.hystrix.HystrixCommandGroupKey.Factory.*;

import java.util.List;

import com.customer.model.User;
import com.netflix.hystrix.HystrixCommand;

/**
 * Created by NCP-605 on 2017/7/10.
 */
public class UserBatchCommand extends HystrixCommand<List<Object>> {
	private ConsumeService consumeService;
	private List<String> ids;

	public UserBatchCommand(List<String> ids, ConsumeService consumeService) {
		super(Setter.withGroupKey(asKey("userServiceCommand")));
		this.ids = ids;
		this.consumeService = consumeService;
	}

	@Override
	protected List<Object> run() throws Exception {
		return consumeService.getUserInfoByIds(ids);
	}
}
