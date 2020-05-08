#Book Journal

####Overview

Book Journal is an application which let's you keep track of the books you've read, 
the time you spent on each and of the current status of the reading (so you maybe don't forget that pdf book that you've started). I created it because I think that it can make you aware of your progress in some
 areas and of the evolution of your interests, as well as of the subjects you did not approach so far. 
 
###Development
#####Spring Boot RESTFul Api
This is an application made in Intellij Idea using Kotlin and the Spring Boot Framework.
It works as a server that can receive requests according to the following protocol:
A book is an object containing an automatically generated id, a title, an author, a status and the date times of the beginning and finishing of a reading.
Example:<br>
{
<br>"id":"3",
<br>"author":"Karl Marks & Friedrich Engels",
<br>"title":"The Communist Manifesto",
<br>"status":"STARTED",
<br>"startDate":"21.4.2020",
<br>"finishDate":"not set"
<br>
}
* **POST** http://\<host\>:\<port\>/books 
>\+ body (title and author are required) in json format. Here is an example:
>
>{<br>
 "author": "David Hume",<br>
 "title": "Essential Philosophical Works"<br>
 } 
><br>
>The rest of the fields are automatically generated: the status will be set as STARTED and the
> start date is set according to the current date (using LocalDateTime). The finish date is
emphasized as not yet set. The id will be the index in the database.

* **PUT** http://\<host\>:\<port\>/books/\<id\>
>\+ body (status or/and review) in json format. Here is an example:
>
>{<br>
 "status": "ABANDONED",<br>
>"review": awesome"
 } 
><br>
>The options for the status are STARTED, IN_PROGRESS, ABANDONED, FINISHED. This request
> does only change the status of the book correspondent to the provided id (which is a path variable).
>However, it has a different behaviour when FINISHED is given. The finish date is changed according 
>to the current date. This is the only way this field can actually change in the application.
>
* **GET** http://\<host\>:\<port\>/books
>This request returns all the entries in the database.

* **GET** http://\<host\>:\<port\>/books\<id\>
>With the id provided as a path variable you can also make a filtering of the results.
>
>* **DEL** http://\<host\>:\<port\>/books
> Deletes all the entries found in the database.
* **DEL** http://\<host\>:\<port\>/books\<id\>
 >It deletes an entry according to the id given.
>
>The entries are saved locally and available in different sessions. This app uses MongoDB as a 
>database functionality provider.

 