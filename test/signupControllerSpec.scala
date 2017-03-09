import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

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


    //  "render the Profile page with session" in {
    //    val signup = route(app,FakeRequest(GET, "/showProfile").withSession("username" -> "Shivangi1015","fname" -> "Shivangi","mname" -> "xyz","lname" -> "Gupta","email" -> "s1@gmail.com","password" -> "123", "repassword" -> "123", "gender" ->"female", "dob" -> "18-11-92", "isadmin" -> "true")).get
    //    contentType(signup) mustBe Some("text/html")
    //  }
  }

