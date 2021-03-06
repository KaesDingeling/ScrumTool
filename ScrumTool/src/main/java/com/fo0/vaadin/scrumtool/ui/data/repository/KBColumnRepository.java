package com.fo0.vaadin.scrumtool.ui.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fo0.vaadin.scrumtool.ui.data.table.TKBColumn;

@Repository
public interface KBColumnRepository extends CrudRepository<TKBColumn, String> {

}
