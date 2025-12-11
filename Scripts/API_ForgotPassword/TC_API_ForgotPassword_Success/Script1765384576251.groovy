import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

// CSRF Token
def csrfResponse = WS.sendRequest(findTestObject('Object Repository/API_GetCsrfToken'))
WS.verifyResponseStatusCode(csrfResponse, 200)

def csrfJson = new JsonSlurper().parseText(csrfResponse.getResponseBodyContent())
def csrfToken = csrfJson.csrfToken

// req resset mail
def forgotResponse = WS.sendRequest(findTestObject('Object Repository/API_ForgotPassword', [
('csrf_token'): csrfToken,
('email'): "abcde@gmail.com"
]))

// verrify
WS.verifyResponseStatusCode(forgotResponse, 200)

def responseJson = new JsonSlurper().parseText(forgotResponse.getResponseBodyContent())

assert responseJson.message != null
assert responseJson.message.contains("email tồn tại")