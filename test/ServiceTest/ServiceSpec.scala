package ServiceTest

import Models.UserInfo
import org.mockito.Mockito.when
import org.scalatest.mock.MockitoSugar
import org.scalatestplus.play.{OneAppPerTest, PlaySpec}
import play.api.cache.CacheApi
import services.{CacheTrait, ServiceCache}


class ServiceSpec extends PlaySpec with MockitoSugar with OneAppPerTest  {


  "SignupController" should {


    "get correct value from cache" should {
      "get the person with the specified email Id" in {
        val cache = mock[CacheApi]
        val mockObj = new ServiceCache(cache)
        when(mockObj.getcache(" ")) thenReturn Option(UserInfo.apply("","","","","","","","","",true,true))

      }
    }


}

  "Tesing set method of cache trait" should {
    "adding to cache" in {
      val cache = mock[CacheApi]
      val customCache = mock[CacheTrait]
      val user = UserInfo("shivangi","","","","","","","","",false,false)
      val service = new ServiceCache(cache)
      customCache.setcache("aa", user)
      when(cache.get[UserInfo]("aa")) thenReturn Some(user)

    }


  }
}




