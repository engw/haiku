package engw.haiku.service

import org.scalatest.FunSuite

class HaikuServiceTest extends FunSuite {

  test("isHaiku returns true for 松尾芭蕉's famous haiku") {
    val service = HaikuServiceImpl
    val verse = "古池や蛙飛び込む水の音"

    val isHaiku = service.isHaiku(verse)
    assert(isHaiku)
  }

}
