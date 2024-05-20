package Utils

import java.util.Date

case class Varchar(length: Byte, content: String) {
  require(content.length <= length)
}

object Varchar {
  def apply(length: Byte, content: String = ""): String = {
    content.take(length)
  }
}


object Test {
  def main(args: Array[String]): Unit = {
    val varChar = Varchar(5, "aaaaaassss")
    println(varChar)
  }
}
