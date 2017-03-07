package controllers

import javax.inject._

import Models.LoginData
import play.api.Logger
import play.api.cache.CacheApi
import play.api.data.Forms._
import play.api.data._

import play.api.mvc._
import services.UserOperation


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class LoginController @Inject() (cache: CacheApi) extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def login = Action {
    Ok(views.html.welcome())
  }

  val userForm: Form[LoginData] = Form {
    mapping(
      "username" -> nonEmptyText,
      "password" -> nonEmptyText


    )(LoginData.apply)(LoginData.unapply)
  }

//  val users = UserOperation.getUsers


  def showProfile(name:String) = Action {
    implicit request =>
    //val result = users.flatMap(user=>if(user.username == name) Some(user) else None)
      val data=cache.get[Models.UserInfo](name)
      if(data.get.isadmin == false)
      Ok(views.html.Profile(data.toList.head))
      else
        Ok(views.html.Admin(data.toList.head))


  }


  def processForm= Action{ implicit request =>
    userForm.bindFromRequest.fold (
      formWithErrors => {
        Logger.info("error")
        Redirect(routes.HomeController.index()).flashing("Error Message"->"Incorrect username or password")
      },
      userData => {

        // val flag = users.map(x => if(x.username == userData.username && x.password == userData.password) true else false).toList
        val data=cache.get[Models.UserInfo](userData.username)
        val encrypt = UserOperation.hash(userData.password)
      println(encrypt)
        val flag=data.map(x => if(x.username == userData.username && x.password == encrypt) true else false)
        println(flag)

        if(flag.contains(true)){
          println("inside iff")
         Redirect(routes.LoginController.showProfile(userData.username))

        }
        else {
          println("inside else")
          Redirect(routes.HomeController.index()).flashing("msg" -> "Incorrect username or password")
        }
      }
    )
  }








}
