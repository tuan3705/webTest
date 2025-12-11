import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('http://localhost:3000/register')

WebUI.maximizeWindow()

WebUI.verifyElementPresent(findTestObject('Page_Register/logo'), 5)

WebUI.verifyElementText(findTestObject('Page_Register/title'), 'Tạo tài khoản mới')

WebUI.verifyElementPresent(findTestObject('Page_Register/input_name'), 5)

WebUI.verifyElementPresent(findTestObject('Page_Register/input_email'), 5)

WebUI.verifyElementPresent(findTestObject('Page_Register/input_password'), 5)

WebUI.verifyElementPresent(findTestObject('Page_Register/checkbox_terms'), 5)

WebUI.verifyElementPresent(findTestObject('Page_Register/btn_submit'), 5)

WebUI.verifyElementPresent(findTestObject('Page_Register/link_login'), 5)

WebUI.verifyElementPresent(findTestObject('Page_Register/link_dkhoan'), 0)

WebUI.verifyElementPresent(findTestObject('Page_Register/link_csach'), 0)

WebUI.closeBrowser()

