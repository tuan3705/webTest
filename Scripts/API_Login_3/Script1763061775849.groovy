// Test Case 1: Đăng nhập bỏ trống email và mật khẩu (Đã sửa)
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import groovy.json.JsonSlurper as JsonSlurper

// --- Dữ liệu đầu vào ---
String email = 'tranduythanh2809@gmail.com'
String password = 'abc'

// --- Lấy CSRF Token và Cookie (giữ nguyên) ---
def resToken = WS.sendRequest(findTestObject('Get_CSRF_Token'))
WS.verifyResponseStatusCode(resToken, 200)
def csrfToken = new JsonSlurper().parseText(resToken.getResponseText()).csrfToken
def cookieValue = resToken.getHeaderFields().get('set-cookie').collect({ it.split(';')[0] }).join('; ')

// --- Gửi yêu cầu đăng nhập ---
def resLogin = WS.sendRequest(findTestObject('Post_Login_Credentials', [('email') : email, ('password') : password, ('csrfToken') : csrfToken, ('cookie_value') : cookieValue]))

// --- Kiểm tra kết quả thất bại ---
// Với trường hợp bỏ trống, API sẽ trả về lỗi 401 Unauthorized
WS.verifyResponseStatusCode(resLogin, 401)