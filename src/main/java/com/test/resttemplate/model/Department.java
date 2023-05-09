package com.test.resttemplate.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department {
	private Long id;
	private String name;
	private List<Position> posititons;
}
