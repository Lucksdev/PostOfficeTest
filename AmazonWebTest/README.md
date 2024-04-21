UI Automation Test is implemented using Cypress Framework with JavaScript

**Setup Instructios**

1. Open AmazonWebTest folder in VisualStudiosCode(VSC)
2. Open New Terminal
3. Execute the commands in the below order
>npm init

>npm install cypress

>npx cypress open

4. Click on E2E Testing(Configured)
5. Start E2E Testing in Chrome(assuming Chrome is the default browser)
6. Spec window will be opened
7. Click on amazonsearchtest.cy.js(spec file) to start running the tests
8. Tests will start running on your browser
   (For reference, video recording of the execution of tests is in the root folder - "VideoRecording-UIAutomationTest.mov")
9. There is no command in cypress to close the browser, but to   launch the browser and execute the tests and then close the browser, the below command can be used from the terminal

>npx cypress run --headed
