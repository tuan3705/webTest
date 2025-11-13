import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

String email = 'tranduythanh2809@gmail.com'

String password = '28092005'

def resToken = WS.sendRequest(findTestObject('Get_CSRF_Token'))

WS.verifyResponseStatusCode(resToken, 200)

def csrfToken = new JsonSlurper().parseText(resToken.getResponseText()).csrfToken

def cookieValue = resToken.getHeaderFields().get('set-cookie').collect({ 
        it.split(';')[0]
    }).join('; ')

def resLogin = WS.sendRequest(findTestObject('Post_Login_Credentials', [('email') : email, ('password') : password, ('csrfToken') : csrfToken
            , ('cookie_value') : cookieValue]))
WS.verifyResponseStatusCode(resLogin, 200)


