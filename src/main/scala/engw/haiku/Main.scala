package engw.haiku

import com.twitter.app.Flag
import com.twitter.finagle.Http
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import engw.haiku.router.MixInHaikuRouter
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._

/**
  * POST /v1/haiku
  * body
  * {
  *   "verse": "秋深き隣は何をする人ぞ"
  * }
  *
  * - That's 575.
  * 200 OK
  * {
  *   "haiku": true
  * }
  *
  * - That's not 575.
  * 200 OK
  * {
  *   "haiku": false
  * }
  */

object Main extends TwitterServer with MixInHaikuRouter {

  val port: Flag[Int] = flag("port", 8080, "TCP port for HTTP server")

  val service = haikuRouter.allRoute.toService

  def main(): Unit = {
    log.info("Serving the Todo application")

    val server = Http.server
      .withStatsReceiver(statsReceiver)
      .serve(s":${port()}", service)

    onExit { server.close() }

    Await.ready(adminHttpServer)
  }

}
