import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

//  CSRF Token
def csrfResponse = WS.sendRequest(findTestObject('Object Repository/API_GetCsrfToken'))
WS.verifyResponseStatusCode(csrfResponse, 200)

def csrfJson = new JsonSlurper().parseText(csrfResponse.getResponseBodyContent())
def csrfToken = csrfJson.csrfToken

// rgt user
def timestamp = System.currentTimeMillis()
def testName = "Dương Anh Tuấn"
def testEmail = "duplicate${timestamp}@gmail.com"
def testPassword = "Test@123456"

def firstResponse = WS.sendRequest(findTestObject('Object Repository/API_Register', [
    ('csrf_token'): csrfToken,
    ('name'): testName,
    ('email'): testEmail,
    ('password'): testPassword
]))

WS.verifyResponseStatusCode(firstResponse, 201)

// rergt user
def secondResponse = WS.sendRequest(findTestObject('Object Repository/API_Register_Duplicate', [
    ('csrf_token'): csrfToken,
    ('name'): "Dương Anh Tuấn",
    ('email'): testEmail,  // SAME EMAIL
    ('password'): "Test@654321"
]))

// verify rsp conflict (409)
WS.verifyResponseStatusCode(secondResponse, 409)

// verify error message
def errorMessage = secondResponse.getResponseBodyContent()
assert errorMessage.contains("Email đã được sử dụng"), "Wrong error message"
