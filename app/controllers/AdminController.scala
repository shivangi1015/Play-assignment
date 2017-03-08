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
      Ok(views.html.AdminPrevileges(list.toList))

  }

  def suspend(user: String) = Action {
    implicit request =>
      val suspendedUser = cache.get[Models.UserInfo](user)
      val userStatus = suspendedUser.get.copy(status = false)
      cacheService.setcache(user,userStatus)

      Ok(views.html.index())



  }

  def resume(user: String) = Action {
    implicit request =>
      val resumedUser = cache.get[Models.UserInfo](user)
      val userStatus = resumedUser.get.copy(status = true)
      cacheService.setcache(user,userStatus)
      println("user resumed")
      Ok(views.html.index())


  }


}
