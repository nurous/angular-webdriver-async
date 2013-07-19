Basic project to show how to get Selenium WebDriver to wait for Ajax calls to finish before making any assertions

The file 'ajaxFinished.js' adds an interceptor to the end of the ajax call.
The interceptor adds to a list of calls which webdriver can then interrogate and wait to contain the appropriate succesful GET requests (or unsuccessful PUT/POST requests)
