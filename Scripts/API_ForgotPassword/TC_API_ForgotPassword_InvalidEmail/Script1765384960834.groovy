import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

// CSRF Token
def csrfResponse = WS.sendRequest(findTestObject('Object Repository/API_GetCsrfToken'))
def csrfJson = new JsonSlurper().parseText(csrfResponse.getResponseBodyContent())
def csrfToken = csrfJson.csrfToken

// Request w ivl email
def forgotResponse = WS.sendRequest(findTestObject('Object Repository/API_ForgotPassword', [
    ('csrf_token'): csrfToken,
    ('email'): "invalidemail"  
]))

// Verify rsp 400 = bad req
WS.verifyResponseStatusCode(forgotResponse, 400)

def errorMessage = forgotResponse.getResponseBodyContent()
assert errorMessage.contains("Email không hợp lệ")
