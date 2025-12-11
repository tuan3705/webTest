import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

//  CSRF Token
def csrfResponse = WS.sendRequest(findTestObject('Object Repository/API_GetCsrfToken'))
WS.verifyResponseStatusCode(csrfResponse, 200)

def csrfJson = new JsonSlurper().parseText(csrfResponse.getResponseBodyContent())
def csrfToken = csrfJson.csrfToken

// register w invalid mail
def invalidEmail = "duonganhtuan@invalid" 
def testPassword = "Test@123456"

def registerResponse = WS.sendRequest(findTestObject('Object Repository/API_Register', [
    ('csrf_token'): csrfToken,
    ('name'): "Dương Anh Tuấn",
    ('email'): invalidEmail,
    ('password'): testPassword
]))

// verify rsp 400 ( bad req )
WS.verifyResponseStatusCode(registerResponse, 400)

// verify error message
def errorMessage = registerResponse.getResponseBodyContent()
assert errorMessage.contains("Email không hợp lệ"), "Wrong error message"