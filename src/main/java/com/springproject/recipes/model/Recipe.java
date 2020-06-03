package com.springproject.recipes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "A recipe title is required.")
	private String title;

	@NotBlank(message = "A description is required.")
	private String description;

	@ManyToOne
	private User author;

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	private List<Ingredient> ingredients;

	@OneToMany(cascade = CascadeType.ALL)
	@Valid
	private List<Instruction> instructions;

}
