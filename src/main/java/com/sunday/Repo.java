package com.sunday;

import org.springframework.data.repository.CrudRepository;

public interface Repo extends CrudRepository<UserData, Integer> {
}
