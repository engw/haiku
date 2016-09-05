package engw.haiku.service

import com.atilika.kuromoji.ipadic.Tokenizer

import scala.annotation.tailrec
import scala.collection.JavaConverters._

trait HaikuService {

  val tokenizer = new Tokenizer()

  def isHaiku(verse: String): Boolean = {
    val tokens = tokenizer.tokenize(verse).asScala.toList
    val readings = tokens.map(_.getReading)

    isHaikuLength(readings.foldLeft("")(_ + _)) && isHaikuRhythm(readings)
  }

  private def isHaikuLength(verse: String): Boolean = 17 <= verse.length && verse.length <= 20

  private def isHaikuRhythm(readings: List[String]): Boolean = {

    /**
      * 読み仮名リストを先頭から順番に足していった時に長さが c になるか判定し、
      * 判定結果と残りの読み仮名を返す関数。
      *
      * @param rs 読み仮名リスト
      * @param c 文字の長さ
      * @return 判定結果と残りの読み仮名
      */
    @tailrec
    def fallInto(c: Int, rs: List[String]): (Boolean, List[String]) = {
      rs match {
        case Nil => (c == 0, rs)
        case x :: xs if x.length == c => (true, xs)
        case x :: xs if x.length > c  => (false, xs)
        case x :: xs                  => fallInto(c - x.length, xs)
      }
    }

    @tailrec
    def _isHaikuRhythm(counts: List[Int], rs: List[String]): Boolean = {
      counts match {
        case Nil => true
        case c :: cs =>
          val (gotRhythm, leftOvers) = fallInto(c, rs)
          if (gotRhythm) _isHaikuRhythm(cs, leftOvers) else false
      }
    }

    _isHaikuRhythm(List(5, 7, 5), readings)
  }

}

object HaikuServiceImpl extends HaikuService

trait UsesHaikuService {
  val haikuService: HaikuService
}

trait MixInHaikuService {
  val haikuService: HaikuService = HaikuServiceImpl
}
