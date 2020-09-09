package com.shoppingcard.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class Category {
	
	// required field
	private String title;

	// optional fields
    private Set<Category> childCategoryList = new HashSet<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Category> getChildCategoryList() {
		return childCategoryList;
	}

	public void setChildCategoryList(Set<Category> childCategoryList) {
		this.childCategoryList = childCategoryList;
	}
	
	public Category(String title) {
	       Objects.requireNonNull(title);
	       this.title = title;
	       // other fields assignment...
	}
	
	public void add(Category Category) {
	
		if (Objects.nonNull(Category) && !Objects.equals(this, Category)) {
		    childCategoryList.add(Category);
		}
	}
	public Set<Category> getCategories() {
	
		Set<Category> allCategories = new HashSet<>();
		
		allCategories.add(this);
		allCategories.addAll(getChildCategories());
		
		return allCategories;
	}

	
    private Set<Category> getChildCategories() {
        Set<Category> allCategories = new HashSet<>();
        for (Category category : childCategoryList) {
            allCategories.addAll(category.getCategories());
        }

        return allCategories;
    }

}
