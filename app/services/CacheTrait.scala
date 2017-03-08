package services

import Models.UserInfo

/**
  * Created by knoldus on 8/3/17.
  */
trait CacheTrait {

    def setcache(value:String,userObject:UserInfo)
    def getcache(value:String):Option[UserInfo]


}
