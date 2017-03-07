package controllers

import javax.inject._
import play.api.Logger
import play.api.data._
import play.api.data.Forms._
import play.api.mvc._
import services.{UserInfo, UserOperation}


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class signupController @Inject() extends Controller {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def signup = Action {
    Ok(views.html.signup())
  }


  val userForm: Form[UserInfo] = Form(
    mapping(
      "username" -> nonEmptyText,
      "fname" -> nonEmptyText,
      "lname" -> nonEmptyText,
      "email" -> nonEmptyText,
      "password" -> nonEmptyText,
      "gender" -> nonEmptyText,
      "dob" -> nonEmptyText
    )(UserInfo.apply)(UserInfo.unapply)
  )

  def addPerson= Action { implicit request =>

      val user:UserInfo = userForm.bindFromRequest.get
      UserOperation.addUser(user)
      Ok(views.html.Profile(user))
    }

}
