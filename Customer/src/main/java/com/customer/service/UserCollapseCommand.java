package com.customer.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.customer.model.User;
import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;

/**
 * Created by NCP-605 on 2017/7/10.
 */
public class UserCollapseCommand extends HystrixCollapser<List<User>, User, String> {
	private String id;
	private ConsumeService consumeService;

	public UserCollapseCommand(String id, ConsumeService consumeService) {
		this.id = id;
		this.consumeService=consumeService;
	}

	@Override
	public String getRequestArgument() {
		return id;
	}

	@Override
	protected void mapResponseToRequests(List<User> users,
		Collection<CollapsedRequest<User, String>> collection) {
		int count = 0;
		for (CollapsedRequest<User, String> collapsedRequest : collection) {
			collapsedRequest.setResponse(users.get(count++));
		}
	}

	@Override
	protected HystrixCommand createCommand(Collection<CollapsedRequest<User, String>> collection) {
		List<String> userIds = new ArrayList<>(collection.size());
		userIds.addAll(collection.stream().map(CollapsedRequest::getArgument).collect(Collectors.toList()));
		return new UserBatchCommand(userIds, consumeService);
	}
}
