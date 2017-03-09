import Models.UserInfo
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.cache.CacheApi
import play.api.mvc.Result
import play.api.test.FakeRequest
import services.{ServiceCache, CacheTrait}
import play.api.test.Helpers._
import org.mockito.Mockito.when





import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 9/3/17.
  */
class ServiceSpec extends PlaySpec with MockitoSugar with OneAppPerTest  {

  //  "CacheListService should" should {
  //
  //    "check existence of  user in cache ListBuffer(else part) " in {
  //
  //      val cache = mock[CacheApi]
  //      val serviceObject = new ServiceCache(cache)
  //      when(cache.get[ListBuffer[UserInfo]]("Key")).thenReturn(Some(ListBuffer[UserInfo]()))
  //      serviceObject.setcache("abc",) mustBe true
  //    }
  //
  //
  //  }

  "SignupController" should {

//    "get the correct value from cache " in {
//      val mockDataService = mock[CacheApi]
//      mockDataService.set("value",UserInfo)
//      val obj= new CacheTrait(mockDataService)
//      when(obj.getcache("abc")) thenReturn(Some(UserInfo))
//      val home = route(app, FakeRequest(POST, "/sign")).get
//      status(home) mustBe OK
//      contentType(home) mustBe Some("text/html")
//      contentAsString(home) must include("Sign up!!!")
//    }

//    "get the correct value from cache " in {
//    val mockDataService = mock[CacheTrait]
//    when(mockDataService.getcache("")) thenReturn None
//    //when(mockDataService.removeCache(UserDetails("","","","","","",0,"",0,"",false,false))) thenReturn true
////  val home = route(app, FakeRequest(POST, "/signin")).get
////   status(home) equals 303
//  }

//    "set the correct value in cache" in{
//      val mockDataService = mock[CacheApi]
//      val mockObj = new ServiceCache(mockDataService)
//      when(mockObj.setcache("",UserInfo("shivangi","","","","","","","","",false,false))) thenReturn Option("")
//
//    }


    "get correct value from cache" should {
      "get the person with the specified email Id" in {
        val cache = mock[CacheApi]
        val mockObj = new ServiceCache(cache)
        when(mockObj.getcache(" ")) thenReturn Option(UserInfo.apply("","","","","","","","","",true,true))

      }
    }


}
}


//
//"get the correct value from cache " in {
//  val mockDataService = mock[CacheApi]
//  val serviceObject = new LoginController(mockDataService)
//  when(serviceObject.getcache("")) thenReturn(Some(UserInfo))
//  val home = route(app, FakeRequest(POST, "/sign")).get
//  status(home) mustBe OK
//  contentType(home) mustBe Some("text/html")
//  contentAsString(home) must include("Sign up!!!")
//}