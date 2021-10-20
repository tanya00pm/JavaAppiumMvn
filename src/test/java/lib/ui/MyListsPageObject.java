package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL;

    private static String getFolderXPathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXPathByTitle(String article_title)
    {System.out.println("method getSavedArticleXPathByTitle is started");
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Can not find folder by name " +  name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath;
        System.out.println("waitForArticleToAppearByTitle started");
        if(Platform.getInstance().isAndroid()){
       article_xpath = getFolderXPathByName(article_title);
        } else {
         article_xpath = getSavedArticleXPathByTitle(article_title);
        }
        System.out.println("article_xpath " + article_xpath);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                10
        );
        System.out.println("waitForArticleToAppearByTitle ended");
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXPathByName(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXPathByTitle(article_title);
        //  System.out.println("article_xpath: "+article_xpath);

        // this.swipeElementToLeft(article_xpath, "Can not find saved article.");

        // if(Platform.getInstance().isIOS()) {
            //     this.clickElementToTheRightUpperCorner(article_xpath, "Can not find saved article");
            // }

       // this.waitForArticleToDisappearByTitle(article_title);
    }

    public void clickArticleByTitle(String article_title)
    {
        String article_xpath = getFolderXPathByName(article_title);

        this.waitForElementAndClick(
                article_xpath,
                "Cannot find and click saved article by title " + article_title,
                15
        );
    }
}
