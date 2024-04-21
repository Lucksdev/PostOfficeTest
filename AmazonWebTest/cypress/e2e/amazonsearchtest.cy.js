import TestHomePage from './pageobjects/testhomepage';

const testHomePage = new TestHomePage();

describe("ECommerce Search Test",()=>{
    it("Search Test and Confirm Value is there in the displayed search list",()=>{
        //Navigate to the URL: "https://www.amazon.com/".
        cy.visit(testHomePage.url)
        //Enter the product name "iphone" in the search bar.
        cy.get(testHomePage.searchTextField).type(testHomePage.searchText)
        //Click on the search button.
        cy.get(testHomePage.searchButton).click()
        //Verify that the first result contains the text "Apple iPhone".
        cy.get(testHomePage.searchResult).first().within(() => {
            // Verify if the text "Apple iPhone" is present
            cy.contains(testHomePage.resultSearchText).should('exist').then(($text) => {
            });
        });
        //Close the browser.
        /*no explicit command to close the browser in cypress but this can be achieved
        by the command 'cypress run --headed' which will launch the tests and close the 
        browser, but this is still not a direct command
         */
    })
})