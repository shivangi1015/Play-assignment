import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

/**
  * Created by knoldus on 8/3/17.
  */
class LoginControllerSpec extends PlaySpec with OneAppPerTest {

  "Login" should {

    "render the login page" in {
      val home = route(app, FakeRequest(GET, "/login")).get
      status(home) mustBe (OK)
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Sign In to view your profile!!!")
    }

  }
}