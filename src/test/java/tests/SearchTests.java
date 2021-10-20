package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("We found too few results!",
                amount_of_search_results > 0);
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "ewewwefergthh";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    /* Ex3 */
    @Test
    public void testSearchResultsCancel()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Kotlin";
        SearchPageObject.typeSearchLine(search_line);
        int search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("No articles for text '"+ search_line +
                        "' have found.",
                search_results > 0);

        SearchPageObject.clearSearchInput();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
    /* Ex3 */

    /* Ex9, Ex12 */
    @Test
    public void testSearchArticlesByTitleAndDescription()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Kotlin";
        SearchPageObject.typeSearchLine(search_line);
        int search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("Search results are less than 3",
                search_results > 2);

        SearchPageObject.waitForElementByTitleAndDescription("Kotlin",
                "Wikimedia disambiguation page");

        SearchPageObject.waitForElementByTitleAndDescription("Kotlin (programming language)",
                "Programming language");

        SearchPageObject.waitForElementByTitleAndDescription("Kotlin-class destroyer",
                "Class of Soviet cold-war destroyers");
    }
    /* Ex9, Ex12 */
}
