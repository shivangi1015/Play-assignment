import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.test.FakeRequest
import play.api.test.Helpers._


class AdminControllerSpec extends PlaySpec with OneAppPerTest  {

  "Admin controller" should {

    "render the admin privileges page" in {
      val admin = route(app, FakeRequest(GET, "/manage")).get
      status(admin) mustBe (OK)
      contentType(admin) mustBe Some("text/html")
      contentAsString(admin) must include("Admin Previleges")
    }

  }

//  "usersuspended in Admin controller" should {
//
//    "be able to suspend the user" in {
//      val admin = route(app, FakeRequest(GET, "/usersuspended")).get
//      status(admin) mustBe (303)
//    }
//
//  }

}
