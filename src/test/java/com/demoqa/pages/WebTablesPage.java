package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WebTablesPage {
    private final WebDriver driver;

    @FindBy(id = "searchBox") private WebElement searchBox;
    @FindBy(id = "addNewRecordButton") private WebElement addButton;
    @FindBy(id = "firstName") private WebElement firstNameInput;
    @FindBy(id = "lastName") private WebElement lastNameInput;
    @FindBy(id = "userEmail") private WebElement emailInput;
    @FindBy(id = "age") private WebElement ageInput;
    @FindBy(id = "salary") private WebElement salaryInput;
    @FindBy(id = "department") private WebElement departmentInput;
    @FindBy(id = "submit") private WebElement submitButton;
    @FindBy(css = ".rt-tbody .rt-tr-group") private List<WebElement> tableRows;
    @FindBy(css = ".rt-tbody .rt-tr-group:first-child .rt-td:first-child") private WebElement firstRowFirstName;

    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchForUser(String text) {
        searchBox.clear();
        searchBox.sendKeys(text);
    }

    public void clickAddButton() {
        addButton.click();
    }

    public void fillUserForm(String firstName, String lastName, String email, String age, String salary, String department) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        emailInput.sendKeys(email);
        ageInput.sendKeys(age);
        salaryInput.sendKeys(salary);
        departmentInput.sendKeys(department);
        submitButton.click();
    }

    public int getTableRowCount() {
        return (int) tableRows.stream().filter(row -> !row.getText().trim().isEmpty()).count();
    }

    public String getFirstRowFirstName() {
        return firstRowFirstName.getText();
    }
}
