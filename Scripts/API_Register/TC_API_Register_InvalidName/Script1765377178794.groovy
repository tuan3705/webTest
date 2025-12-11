import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

//  CSRF Token
def csrfResponse = WS.sendRequest(findTestObject('Object Repository/API_GetCsrfToken'))
WS.verifyResponseStatusCode(csrfResponse, 200)

def csrfJson = new JsonSlurper().parseText(csrfResponse.getResponseBodyContent())
def csrfToken = csrfJson.csrfToken

// register w invlid name
def timestamp = System.currentTimeMillis()
def invalidName = "Dương Anh Tuấn 123"  
def testEmail = "test${timestamp}@gmail.com"
def testPassword = "Test@123456"

def registerResponse = WS.sendRequest(findTestObject('Object Repository/API_Register', [
    ('csrf_token'): csrfToken,
    ('name'): invalidName,
    ('email'): testEmail,
    ('password'): testPassword
]))

// verify rsp 400 ( bad req)
WS.verifyResponseStatusCode(registerResponse, 400)

// verify error message
def errorMessage = registerResponse.getResponseBodyContent()
assert errorMessage.contains("Tên chỉ được chứa chữ cái và khoảng trắng"), " Wrong error message"