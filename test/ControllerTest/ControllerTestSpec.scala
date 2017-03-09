package ControllerTest

import Models.UserInfo
import controllers.{AdminController, LoginController, HomeController}
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.{PlaySpec, OneAppPerTest}
import play.api.test.FakeRequest
import play.api.test.Helpers._
import services.ServiceCache
import views.html.AdminPrevileges_Scope0.AdminPrevileges
import org.mockito.Mockito.when



/**
  * Created by knoldus on 10/3/17.
  */
class ControllerTestSpec extends PlaySpec with OneAppPerTest with MockitoSugar {

  "HomeController" should {

    "render the index page" in {

      val homeObj = new HomeController
      val home = homeObj.index().apply(FakeRequest())
      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Login Application")
    }

  }

    "LoginController" should {

      "showProfile" in {
        val customCache = mock[ServiceCache]
        val showObj = new LoginController(customCache)
        val user: UserInfo = UserInfo("shivangi","","","","","","","","",false,false)
        customCache.setcache("aa", user)
        val userDetails: Option[UserInfo] = customCache.getcache("aa")
        when(userDetails) thenReturn Some(user)
        val home = showObj.showProfile("aa").apply(FakeRequest())
        status(home) mustBe OK
        contentType(home) mustBe Some("text/html")
        contentAsString(home) must include("Profile")
      }

    }


  "AdminPanelController.scala" should {

    "showMaintanancePanel" in {
      val customCache = mock[ServiceCache]
      val showObj = new AdminController(customCache)
      val home = showObj.manageUsers().apply(FakeRequest())
      status(home) equals 200
    }

    "suspend" in {
      val customCache = mock[ServiceCache]
      val showObj = new AdminController(customCache)
      val user: UserInfo = UserInfo("shivangi","","","","","","","","",false,false)
      customCache.setcache("xyz", user)
      val userDetails: Option[UserInfo] = customCache.getcache("xyz")
      when(userDetails) thenReturn Some(user)
      val home = showObj.suspend("xyz").apply(FakeRequest())
      status(home) equals 303
    }

    "resume" in {
      val customCache = mock[ServiceCache]
      val showObj = new AdminController(customCache)
      val user: UserInfo = UserInfo("shivangi","","","","","","","","",false,false)
      customCache.setcache("xyz", user)
      val userDetails: Option[UserInfo] = customCache.getcache("xyz")
      when(userDetails) thenReturn Some(user)
      val home = showObj.resume("xyz").apply(FakeRequest())
      status(home) equals 303
    }


  }
}
