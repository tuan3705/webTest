import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

//  CSRF Token
def csrfResponse = WS.sendRequest(findTestObject('Object Repository/API_GetCsrfToken'))
WS.verifyResponseStatusCode(csrfResponse, 200)

def csrfJson = new JsonSlurper().parseText(csrfResponse.getResponseBodyContent())
def csrfToken = csrfJson.csrfToken

// resgiter w weakpw
def timestamp = System.currentTimeMillis()
def weakPassword = "123456"  
def testEmail = "test${timestamp}@gmail.com"

def registerResponse = WS.sendRequest(findTestObject('Object Repository/API_Register', [
    ('csrf_token'): csrfToken,
    ('name'): "Dương Anh Tuấn",
    ('email'): testEmail,
    ('password'): weakPassword
]))

// verify rsp   400 = bad req
WS.verifyResponseStatusCode(registerResponse, 400)

// Verify error message
def errorMessage = registerResponse.getResponseBodyContent()
assert errorMessage.contains("Mật khẩu") || errorMessage.contains("ít nhất"), " Wrong error message"
