package engw.haiku.service

trait HaikuService {

  def valid(verse: String): Boolean = {
    verse.length == 5 + 7 + 5
  }

}

object HaikuServiceImpl extends HaikuService

trait UsesHaikuService {
  val haikuService: HaikuService
}

trait MixInHaikuService {
  val haikuService: HaikuService = HaikuServiceImpl
}
