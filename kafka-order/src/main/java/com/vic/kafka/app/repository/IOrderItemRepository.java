package com.vic.kafka.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vic.kafka.app.entity.OrderItem;

@Repository
public interface IOrderItemRepository extends CrudRepository<OrderItem, Integer> {

}
