package com.assignment.ecommerce.eventstore.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.assignment.ecommerce.eventstore.entity.UserActivity;

public interface UserActivityRepository extends ElasticsearchRepository<UserActivity, String> {

}
