package ViewTest

import Models.UserInfo
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.mvc.Flash
import play.api.test.FakeRequest
import play.api.test.Helpers._


/**
  * Created by knoldus on 10/3/17.
  */
class ViewSpec extends PlaySpec with OneAppPerTest with MockitoSugar {

  "Rending index page" in new App {
    val a = mock[Flash]
    val html = views.html.index()(a)
    contentAsString(html) must include("Username already exists!!")
  }

  "Rending profile page" in new App {
    val fakeFlash = mock[Flash]
    val html = views.html.Profile(

      (UserInfo("Shivangi1015", "shivangi", "gupta", "abc", "s1@gmail.com", "12345", "12345", "female","18-11-92", true, true)))
    contentAsString(html) must include("Shivangi1015")


  }

  "Rending admin profile" in new App {
    val fakeFlash = mock[Flash]
    val html = views.html.AdminPrevileges(List("Shivangi1015"))
    contentAsString(html) must include("Shivangi")
  }

  "Rending signup page" in new App {
    val a = mock[Flash]
    val html = views.html.signup()(a)
    contentAsString(html) must include("Username already exists!!")
  }

}


