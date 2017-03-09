package controllers

import javax.inject._
import Models.UserInfo
import play.api.Logger
import play.api.cache.CacheApi
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import services.{CacheTrait, UserOperation}

import scala.collection.mutable.ListBuffer


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class signupController @Inject() (cache: CacheApi,cacheService: CacheTrait) extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def signup = Action { implicit request =>
    Ok(views.html.signup()).flashing("a" -> "a")
  }


  val userForm: Form[UserInfo] = Form(
    mapping(
      "username" -> nonEmptyText,
      "fname" -> nonEmptyText,
      "mname" -> nonEmptyText,
      "lname" -> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "repassword" -> nonEmptyText,
      "gender" -> nonEmptyText,
      "dob" -> nonEmptyText,
      "type" -> boolean,
      "status" -> boolean
    )(UserInfo.apply)(UserInfo.unapply)
  )

//  def addPerson= Action { implicit request =>
//
//      val user:UserInfo = userForm.bindFromRequest.get
//      UserOperation.addUser(user)
//      Ok(views.html.Profile(user))
//    }

 // val users = UserOperation.getUsers
 // Logger.info(users.toString)


  def addPerson= Action{ implicit request =>
    userForm.bindFromRequest.fold (
      formWithErrors => {
        Logger.info("error occurred")

        Redirect(routes.HomeController.index()).flashing("BadRequest"->"SOMETHING WENT WRONG! Please Try Again")
      },
      userData => {
        Logger.info(userData.toString)
        val flag=cacheService.getcache(userData.username)


       // val flag: List[Boolean] = users.map(x=> if(x.username == userData.username) true else false).toList
      //  println(flag)

        if(flag == None)
        {
          println("Unique username")

          if(userData.password != userData.repassword) {
            Redirect(routes.signupController.signup()).flashing("PasswordMismatch" -> "Pasword does nt match!! Fill details again!")
          }
          else {
           // UserOperation.addUser(userData)
           val encrypt = UserOperation.hash(userData.password)
            println(encrypt)
            val encryptedUserdata = userData.copy(password = encrypt)
            cacheService.setcache(userData.username,encryptedUserdata)
            println(cache.get(userData.username))
            UserOperation.listOfUsersNames += userData.username
            println(UserOperation.listOfUsersNames)
            //Redirect(routes.HomeController.index())
            Redirect(routes.LoginController.showProfile(userData.username))
          }
        }
        else {
          Logger.info("username already taken")
          Redirect(routes.signupController.signup()).flashing("Exist" -> "Username already exists!!")
        }


      }
    )
  }


}
