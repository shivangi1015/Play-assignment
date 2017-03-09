package RouteTest

import org.scalatestplus.play._
import play.api.test.Helpers._
import play.api.test._

/**
  * Created by knoldus on 8/3/17.
  */
class signupControllerSpec extends PlaySpec with OneAppPerTest {

  "Sign Up" should {

    "render the SignUp page" in {
      val signup = route(app, FakeRequest(GET, "/sign")).get


      status(signup) mustBe (OK)
      contentType(signup) mustBe Some("text/html")
      contentAsString(signup) must include("Sign up!!!")
    }
  }

  "addperson route in signup" should{

      "render the add person page" in {
        val signup = route(app, FakeRequest(POST, "/addPerson")).get
        status(signup) equals (303)

      }

    }

  }

