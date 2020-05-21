package com.fo0.vaadin.scrumtool.data.table;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = { "id" })
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Likes {

	/**
	 * ID = OwnerId
	 */
	@Id
	private String id;

	@Builder.Default
	private int likeValue = 1;

}
