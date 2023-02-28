package com.donato.challenge.criteria;

public class SearchCriteria {

    private String attr;
    private String value;
    private Long number;
    private SearchOperation searchOperation;

    public SearchCriteria() {}

    public SearchCriteria(String attr, String value, SearchOperation searchOperation) {
        this.attr = attr;
        this.value = value;
        this.searchOperation = searchOperation;
    }
    
    public SearchCriteria(String attr, Long number, SearchOperation searchOperation) {
        this.attr = attr;
        this.value = number.toString().trim();
        this.searchOperation = searchOperation;
    }
    public SearchCriteria(String attr, Integer number, SearchOperation searchOperation) {
        this.attr = attr;
        this.value = number.toString().trim();
        this.searchOperation = searchOperation;
    }
    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
  
	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public SearchOperation getSearchOperation() {
        return searchOperation;
    }

    public void setSearchOperation(SearchOperation searchOperation) {
        this.searchOperation = searchOperation;
    }
}
