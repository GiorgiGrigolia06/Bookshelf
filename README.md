# Bookshelf
Bookshelf is an app that lets you explore and discover books using the Google Books API service. With its user-friendly interface, you can quickly search for books by title, author, or keywords and get well-organized results. 

During development, one of the main challenges was effectively fetching data from the Google Books API service. It turned out to be more complex than I anticipated, but I managed to conquer it by implementing Retrofit for network communication and using the ViewModel architecture. 

To ensure the app's reliability, I conducted thorough testing on the ViewModel and NetworkRepository. During the testing phase, I utilized dependency injection to provide the necessary dependencies to the ViewModel and NetworkRepository. This approach allowed me to achieve loose coupling between different components and facilitated easier testing and maintenance of the app.
