package services

import Models.UserInfo

import scala.collection.mutable.ListBuffer


// import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 6/3/17.
  */
object UserOperation {
//
//  val listOfUsers=new ListBuffer[UserInfo]()
//  val user1=UserInfo("Shivangi1015","shivangi","aaa","gupta","shivangi1015@gmail.com","123","123","female","18-11-92",true)
//
//  listOfUsers.append(user1)
//
//  def addUser(user:UserInfo) ={ listOfUsers.append(user) }
//
//
//
//  def getUsers:ListBuffer[UserInfo]= listOfUsers
val listOfUsersNames = new ListBuffer[String]()


  def hash(s: String) = {
    val m = java.security.MessageDigest.getInstance("MD5")
    val b = s.getBytes("UTF-8")
    m.update(b, 0, b.length)
    new java.math.BigInteger(1, m.digest()).toString(16)
  }

}
