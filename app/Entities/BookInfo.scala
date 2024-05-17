package Entities

sealed abstract class BookInfo(val label: String)
object BookInfo {
  final case object READY extends BookInfo(label = "Ready")
  final case object BORROWING extends BookInfo(label = "Borrowing")
  final case object OVERDUE extends BookInfo(label = "Overdue")
}
