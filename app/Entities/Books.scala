package Entities
import java.util.Date
/*{
    "success": true,
    "result": [
        {
            "id": 1,
            "status": "Ready",
            "name": "book1",
            "author": "author1"
            "description": "description 1",
            "purchasedDate": ""2015-10-1 3:00 PM",
            "category": {
                    "category_id": 1,
                    "name": "Japanese"
            }
            "createdAt": "2018-10-1 3:00 PM",
            "updatedAt": "2018-10-1 3:00 PM",
        }
    ]
}
Error:

{
  "success": false,
  "error": {
    "code": 500,
    "message": "An error occurred! Can not get book list!"
  }
}
Create new book
Route	/api/books
Method	POST
Parameters
name: String (*)
author: String (*)
purchasedDate: Datetime (*)
description: String
categoryId: Long (*)
Sample Payload


{
    "name": "Book1",
    "author": "Monkey D Luffy",
    "purchasedDate": "2015-10-1 3:00 PM",
    "description": "This is description",

    "categoryId": 1
}

 */

case class category(categoryId: Int, categoryName: String )

case class Book(
 name: String,
 author: String,
 description: String,
 purchaseDate: Date,
){

}

/*"id": 1,
"status": "Ready",
"name": "book1",
"author": "author1"
"description": "description 1",
"purchasedDate": ""2015-10-1 3:00 PM",
"category": {
  "category_id": 1,
  "name": "Japanese"
}
"createdAt": "2018-10-1 3:00 PM",
"updatedAt": "2018-10-1 3:00 PM",

 */
