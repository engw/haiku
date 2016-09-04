package engw.haiku.valid

import engw.haiku.service.{MixInHaikuService, UsesHaikuService}
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._

trait HaikuRouter extends UsesHaikuService {

  import HaikuRouter._

  val haikuRoute = post(`haiku` :: HaikuParam.endpoint) { (param: HaikuParam) =>
    val result = haikuService.valid(param.verse)
    Ok(ResponseBody(result))
  }

  val allRoute = haikuRoute

}

object HaikuRouter {
  val `haiku`: Endpoint0 = "v1" / "haiku"

  case class HaikuParam(verse: String)
  object HaikuParam {
    val endpoint: Endpoint[HaikuParam] = body.as[HaikuParam]
  }

  case class ResponseBody(haiku: Boolean)
}

object HaikuRouterImpl extends HaikuRouter with MixInHaikuService

trait UsesHaikuRouter {
  val haikuRouter: HaikuRouter
}

trait MixInHaikuRouter {
  val haikuRouter: HaikuRouter = HaikuRouterImpl
}
