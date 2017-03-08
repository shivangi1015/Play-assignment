package services

import javax.inject.Inject

import Models.UserInfo
import play.api.cache.CacheApi

/**
  * Created by knoldus on 8/3/17.
  */
class ServiceCache @Inject() (cache: CacheApi) extends CacheTrait {



    def setcache(value:String,newObject:UserInfo)={

      cache.set(value, newObject)
    }
    def getcache(value:String)={

      cache.get[UserInfo](value)
    }
  }


