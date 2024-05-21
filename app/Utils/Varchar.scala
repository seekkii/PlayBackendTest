package Utils

abstract class Varchar(maxLength: Int) {
  def value: String
  require(value.length <= maxLength, s"Value exceeds maximum length of $maxLength")

}

case class Title(value: String) extends Varchar(15)

