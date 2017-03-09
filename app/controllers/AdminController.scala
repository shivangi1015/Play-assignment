package controllers

import javax.inject.Inject

import play.api.cache
import play.api.cache.CacheApi
import play.api.mvc.{Action, Controller}
import services.{CacheTrait, UserOperation}

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 8/3/17.
  */
class AdminController @Inject() (cache: CacheApi,cacheService: CacheTrait) extends Controller {

  def manageUsers = Action {
    implicit request =>
      val list: ListBuffer[String] = UserOperation.listOfUsersNames
      println("Inside manage users")
      Ok(views.html.AdminPrevileges(list.toList)).flashing("msg" -> "msg")

  }

  def suspend(user: String) = Action {
    implicit request =>
      val suspendedUser = cache.get[Models.UserInfo](user)
//      val userStatus = suspendedUser.get.copy(status = false)
//      cacheService.setcache(user,userStatus)
      suspendedUser match{
        case Some(x) => cacheService.setcache(user,x.copy(status = false))
        case _ => throw new Exception("not register yet go to home page")

      }
      Redirect(routes.AdminController.manageUsers()).flashing("status" -> "Suspended")



  }

  def resume(user: String) = Action {
    implicit request =>
      val resumedUser = cache.get[Models.UserInfo](user)
//      val userStatus = resumedUser.get.copy(status = true)
//      cacheService.setcache(user,userStatus)
      resumedUser match{
        case Some(x) => cacheService.setcache(user,x.copy(status = true))
//          Ok(views.html.index()).flashing("Status"->"User Resumed")
        case _ => Ok(views.html.index())
      }
      Redirect(routes.AdminController.manageUsers()).flashing("status" -> "Resumed")




  }


}


