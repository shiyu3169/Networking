# Webcrawler
### Team Members: Zhongxi Wang, Shiyu Wang

This project is intended to implement a web crawler that uses HTTP protocol to 5 secret flag data
hidden in a fake social networking website.

## Approach
Our approach was to firstly come out the structure of the project and then build from bottom to top.
Firstly we build a pair of message handler, "ClientMessage.py" and "ServerMessage.py"
to handle the messages sending from client to server and the one responded from server.
After that, a CookieJar was built to handle the cookie attributes sending and received from server.
In order to combine them, we made "MyCurl.py" to combine and communicate them to work via a socket in next level.
After getting data from server by HTTP protocol, the top level was to
find the links and flags in the responded message

## Challenge
The first challenge for us was to make a blueprint of this project,
so we spent much time to understand and think about
how many files, class and functions we need to make the project running.
After starting implementation, we faced the key challenge of this project: how to handle messages of HTTP protocol.
It took us so much time to make request function and the function to handle the special chunked body from server.
The final challenge was to handle HTTP status codes. We spent some time to understand and handle 302.
Test Since we built our project from bottom to top, we tested the HTTP protocol working firstly.
Then starting to handle parsing the response from server.

## Installation
* [Python 3]

[Python 3]: <https://www.python.org/download/releases/3.0/>

## Run
Execute Makefile
