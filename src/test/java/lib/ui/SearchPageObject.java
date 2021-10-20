package lib.ui;

import io.appium.java_client.AppiumDriver;


abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            SEARCH_INPUT_TEXT,
            SEARCH_RESULT_BY_TITLE_DESCRIPTION_TPL;

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchByTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_DESCRIPTION_TPL
                .replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /* TEMPLATES METHODS */

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getResultSearchByTitleAndDescription(title, description);

        this.waitForElementPresent(search_result_xpath,
                "Cannot find article with title "
                        + title +
                        " and description " + description, 5);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,
                "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,
                "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,
                "Can not find and click cancel search button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    }

    public void clearSearchInput()
    {
        this.waitForElementAndClear(
                SEARCH_INPUT_TEXT,
                "Cannot find search input for text clear.", 5
        );
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line,
                "Cannot find and type into search input",15);
    }

    public void waitForSearchResult(String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.waitForElementPresent(search_result_xpath,
                "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {

        String search_result_xpath = getResultSearchElement(substring);

        this.waitForElementAndClick(search_result_xpath,
                "Cannot find and click search result with substring " + substring, 10);
    }
    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Can not find anything by the request ",
                20
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Can not find result element.",
                15
        );
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed to find not any elements.");
    }
}
