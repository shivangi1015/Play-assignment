package RouteTest

import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._


class LoginControllerSpec extends PlaySpec with OneAppPerTest {

  "Login" should {

    "render the login page" in {
      val home = route(app, FakeRequest(GET, "/login")).get
      status(home) mustBe (OK)
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Sign In to view your profile!!!")
    }

  }

  "showProfile route" should{

    "hit the Profile page" in{
      val login = route(app, FakeRequest(POST, "/showProfile")).get
      status(login) equals (303)
    }
  }

  "profile route" should{

    "show the Profile page" in{
      val login = route(app, FakeRequest(GET, "/profile")).get
      status(login) mustBe 303
    }
  }
}