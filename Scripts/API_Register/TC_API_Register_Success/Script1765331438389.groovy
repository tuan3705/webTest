import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper

// cstf token
def csrfResponse = WS.sendRequest(findTestObject('Object Repository/API_GetCsrfToken'))
WS.verifyResponseStatusCode(csrfResponse, 200)

def csrfJson = new JsonSlurper().parseText(csrfResponse.getResponseBodyContent())
def csrfToken = csrfJson.csrfToken

// register
def timestamp = System.currentTimeMillis()
def testName = "Dương Anh Tuấn"
def testEmail = "test${timestamp}@gmail.com"
def testPassword = "Test@123456"

def registerResponse = WS.sendRequest(findTestObject('Object Repository/API_Register', [
    ('csrf_token'): csrfToken,
    ('name'): testName,
    ('email'): testEmail,
    ('password'): testPassword
]))

// Vrf status rsp
WS.verifyResponseStatusCode(registerResponse, 201)

// parse, vld rsp
def registerJson = new JsonSlurper().parseText(registerResponse.getResponseBodyContent())

assert registerJson.id != null
assert registerJson.email == testEmail
assert registerJson.name == testName
assert registerJson.role == "VOLUNTEER"
assert registerJson.passwordHash == null
assert registerJson.password == null