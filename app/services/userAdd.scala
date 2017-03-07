package services

import services.UserInfo

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 6/3/17.
  */
object UserOperation {

  val listOfUsers=new ListBuffer[UserInfo]()
  val user1=UserInfo("Shivangi1015","shivangi","gupta","shivangi1015@gmail.com","123","female","18-11-92")

  listOfUsers.append(user1)

  def addUser(user:UserInfo) ={ listOfUsers.append(user) }



  def getUsers:ListBuffer[UserInfo]= listOfUsers



}
