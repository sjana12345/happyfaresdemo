package com.happyfaresdemo.entity;

public class Testentity {

	public String caseId;
	public String scenario;
	public String description;
	public String keyword;
	public String locator;
	public String additionallocator;
	public String testdata;
	public String platform;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public String getAdditionallocator() {
		return additionallocator;
	}

	public void setAdditionallocator(String additionallocator) {
		this.additionallocator = additionallocator;
	}

	public String getTestdata() {
		return testdata;
	}

	public void setTestdata(String testdata) {
		this.testdata = testdata;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Testentity(String caseId, String scenario, String description, String keyword, String locator,
			String additionallocator, String testdata, String platform) {
		super();
		this.caseId = caseId;
		this.scenario = scenario;
		this.description = description;
		this.keyword = keyword;
		this.locator = locator;
		this.additionallocator = additionallocator;
		this.testdata = testdata;
		this.platform = platform;
	}

	public Testentity() {
		super();
	}
}
